package br.com.macorin.spark.domain.models;

import java.util.Objects;
import java.util.Set;

import br.com.macorin.spark.domain.models.enums.CrawlerStatus;

public class Crawler {
    private String id;
    private String baseUrl;
    private String keyword;
    private CrawlerStatus status;
    private Set<String> urls;

    public Crawler() {
    }

    public Crawler(String id, String baseUrl, String keyword, CrawlerStatus status, Set<String> urls) {
        this.id = id;
        this.baseUrl = baseUrl;
        this.keyword = keyword;
        this.status = status;
        this.urls = urls;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public CrawlerStatus getStatus() {
        return this.status;
    }

    public void setStatus(CrawlerStatus status) {
        this.status = status;
    }

    public Set<String> getUrls() {
        return this.urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Crawler)) {
            return false;
        }
        Crawler crawler = (Crawler) o;
        return Objects.equals(id, crawler.id) && Objects.equals(baseUrl, crawler.baseUrl) && Objects.equals(keyword, crawler.keyword) && Objects.equals(status, crawler.status) && Objects.equals(urls, crawler.urls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, baseUrl, keyword, status, urls);
    }

}
