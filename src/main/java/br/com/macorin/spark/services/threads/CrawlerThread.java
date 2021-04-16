package br.com.macorin.spark.services.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.macorin.spark.services.crawlers.Crawling;

public class CrawlerThread extends Thread {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerThread.class);

    private final String crawlerId;

    public CrawlerThread(String crawlerId) {
        this.crawlerId = crawlerId;
    }

    @Override
    public void run() {
        LOGGER.info("Iniciando crawler {}", crawlerId);
        new Crawling().execute(crawlerId);
    }
}
