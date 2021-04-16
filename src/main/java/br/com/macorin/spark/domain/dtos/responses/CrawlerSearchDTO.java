package br.com.macorin.spark.domain.dtos.responses;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import br.com.macorin.spark.domain.models.Crawler;
import br.com.macorin.spark.domain.models.enums.CrawlerStatus;

public class CrawlerSearchDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5336102355971874530L;
    
    private String id;
    private String baseUrl;
    private String keyword;
    private CrawlerStatus status;
    private Set<String> urls;

    public CrawlerSearchDTO(Crawler crawler) {
        this.id = crawler.getId();
        this.baseUrl = crawler.getBaseUrl();
        this.keyword = crawler.getKeyword();
        this.status = crawler.getStatus();
        this.urls = crawler.getUrls();
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
        if (!(o instanceof CrawlerSearchDTO)) {
            return false;
        }
        CrawlerSearchDTO crawlerSearchDTO = (CrawlerSearchDTO) o;
        return Objects.equals(id, crawlerSearchDTO.id) && Objects.equals(baseUrl, crawlerSearchDTO.baseUrl) && Objects.equals(keyword, crawlerSearchDTO.keyword) && Objects.equals(status, crawlerSearchDTO.status) && Objects.equals(urls, crawlerSearchDTO.urls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, baseUrl, keyword, status, urls);
    }

}
