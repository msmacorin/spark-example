package br.com.macorin.spark.services.crawlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.macorin.spark.domain.models.Crawler;
import br.com.macorin.spark.infra.configs.Environment;
import br.com.macorin.spark.infra.exceptions.OpenUrlException;
import br.com.macorin.spark.services.CrawlerService;

public class Crawling {

    private static final Logger LOGGER = LoggerFactory.getLogger(Crawling.class);

    private static final int MAX_THREADS = 20;

    private final CrawlerService crawlerService = new CrawlerService();

    private static final String ELEMENT_HREF = "a[href]";
    private static final String ABSOLUTE_URL = "abs:href";

    private Set<String> processed = new HashSet<>();
    private Queue<String> toProcess = new LinkedBlockingQueue<>();

    private String baseUrl;
    private String keyword;
    private String crawlerId;

    public void execute(final String crawlerId) {
        this.crawlerId = crawlerId;

        Crawler crawler = crawlerService.find(crawlerId);
        this.keyword = crawler.getKeyword();
        this.baseUrl = crawler.getBaseUrl();

        toProcess.add(this.baseUrl);
        startTasks();
        crawlerService.finishCrawler(crawlerId);
        LOGGER.info("Processamento da palavra {} finalizado. Sites vasculhados: {}", keyword, processed.size());
    }

    private Void processUrl(final String url) {
        try {
            if (!processed.contains(url)) {
                LOGGER.info("Processando url: {}", url);
                processed.add(url);
                Document document = getDocument(url);
                searchKeywordOnBody(url, document.body().text());

                Elements links = document.select(ELEMENT_HREF);
                links.stream().forEach(l -> addToProcess(l.attr(ABSOLUTE_URL)));
                LOGGER.info("Finalizando url: {}", url);
            }
        } catch (OpenUrlException e) {
            LOGGER.error("Erro ao abrir URL {}", url, e);
        }
        return null;
    }

    private void addToProcess(final String url) {
        if (url != null && !toProcess.contains(url) 
            && url.startsWith(this.baseUrl)) {
            toProcess.add(url);
        }
    }

    private void startTasks() {
        try {
            List<Callable<Void>> tasks = new ArrayList<>();
            while (tasks.size() < MAX_THREADS || toProcess.isEmpty()) {
                tasks.add(() -> processUrl(toProcess.remove()));
            }

            ExecutorService executorService = Executors.newWorkStealingPool();
            executorService.invokeAll(tasks);

            if (!toProcess.isEmpty() && !resultLimitExceeded()) {
                startTasks();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error("Erro na execução das tasks.", e);
        }
    }

    private Document getDocument(final String url) {
        try {
            Document document = Jsoup.connect(url).get();
            if (document == null || document.body() == null) {
                throw new OpenUrlException("Erro ao carregar o documento");
            }
            return document;
        } catch (Exception e) {
            throw new OpenUrlException(e);
        }
    }

    private boolean resultLimitExceeded() {
        if (Environment.getInstance().getMaxResults() >= 0) {
            Crawler crawler = crawlerService.find(this.crawlerId);
            return Optional.of(crawler.getUrls()).orElse(new HashSet<>()).size() >= Environment.getInstance()
                    .getMaxResults();
        }
        return false;
    }

    private void searchKeywordOnBody(final String url, final String body) {
        Pattern pattern = Pattern.compile(String.format("(\\b(%s)\\b)", this.keyword), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(body);

        if (matcher.find() && !resultLimitExceeded()) {
            LOGGER.info("Palavra {} encontrada em {}", this.keyword, url);
            crawlerService.addNewUrl(crawlerId, url);
        }
    }
}
