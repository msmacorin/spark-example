package br.com.macorin.spark.domain.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.macorin.spark.domain.models.Crawler;
import br.com.macorin.spark.domain.models.enums.CrawlerStatus;

import org.apache.commons.lang3.RandomStringUtils;

public class CrawlerRepository {
    
    protected Map<String, Crawler> crawlers;
    protected static final int ID_COUNT = 8;
    private static CrawlerRepository instance;

    private CrawlerRepository(){}

    public static CrawlerRepository getInstance() {
        if (instance == null) {
            instance = new CrawlerRepository();
            instance.crawlers = new HashMap<>();
        }
        return instance;
    }

    public Optional<Crawler> find(String id) {
        Crawler crawler = instance.crawlers.get(id);
        return crawler != null ? Optional.of(crawler) : Optional.empty();
    }

    public Crawler create(String baseUrl, String keyword) {
        Crawler crawler = new Crawler();
        crawler.setId(RandomStringUtils.randomAlphanumeric(ID_COUNT));
        crawler.setStatus(CrawlerStatus.ACTIVE);
        crawler.setBaseUrl(baseUrl);
        crawler.setKeyword(keyword);

        instance.crawlers.put(crawler.getId(), crawler);

        return crawler;
    }

    public void update(String id, Crawler crawler) {
        instance.crawlers.computeIfPresent(id, (key, val) -> {
            val.setStatus(crawler.getStatus());
            val.setUrls(crawler.getUrls());
            return val;
        });
    }
}
