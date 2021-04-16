package br.com.macorin.spark.services;

import java.util.HashSet;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.macorin.spark.domain.dtos.requests.CrawlerCreateDTO;
import br.com.macorin.spark.domain.dtos.responses.CrawlerCreatedDTO;
import br.com.macorin.spark.domain.models.Crawler;
import br.com.macorin.spark.domain.models.enums.CrawlerStatus;
import br.com.macorin.spark.domain.repositories.CrawlerRepository;
import br.com.macorin.spark.infra.exceptions.ResourceNotFoundException;
import br.com.macorin.spark.services.threads.CrawlerThread;

public class CrawlerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerService.class);
    protected CrawlerRepository crawlerRepository = CrawlerRepository.getInstance();

    public Crawler find(String id) {
        Optional<Crawler> crawler = crawlerRepository.find(id);
        if (crawler.isPresent()) {
            return crawler.get();
        }
        throw new ResourceNotFoundException(String.format("Crawler %s não existe", id));
    }

    public CrawlerCreatedDTO add(CrawlerCreateDTO crawlerCreateDTO) {
        crawlerCreateDTO.validate();
        String id = createNewCrawler(crawlerCreateDTO);
        return new CrawlerCreatedDTO(id);
    }

    public void addNewUrl(String id, String url) {
        Crawler crawler = find(id);
        if (crawler.getUrls() == null) {
            crawler.setUrls(new HashSet<>());
        }
        crawler.getUrls().add(url);
        crawlerRepository.update(id, crawler);
    }

    public void finishCrawler(String id) {
        LOGGER.info("Finalizando crawler {}", id);
        Crawler crawler = find(id);
        crawler.setStatus(CrawlerStatus.DONE);
        crawlerRepository.update(id, crawler);
    }

    private void startThread(String id, String keyword) {
        LOGGER.info("Criando thread {} || {}", id, keyword);
        Thread thread = new CrawlerThread(id);
        thread.start();
    }

    protected String createNewCrawler(CrawlerCreateDTO crawlerCreateDTO) {
        Crawler crawler = crawlerRepository.create(crawlerCreateDTO.getBaseUrl(), crawlerCreateDTO.getKeyword());
        startThread(crawler.getId(), crawlerCreateDTO.getKeyword());

        LOGGER.info("Crawler {} criado para {}", crawler.getId(), crawlerCreateDTO.getKeyword());

        return crawler.getId();
    }
}
