package br.com.macorin.spark.domain.dtos.requests;

import java.io.Serializable;
import java.util.Objects;

import br.com.macorin.spark.infra.exceptions.FieldValidationException;

public class CrawlerCreateDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -766321681071090626L;
    
    private static final int MIN_KEYWORD = 4;
    private static final int MAX_KEYWORD = 32;

    private String baseUrl;
    private String keyword;

    public CrawlerCreateDTO() {
    }

    public CrawlerCreateDTO(String keyword) {
        this.keyword = keyword;
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

    public void validate() {
        StringBuilder errors = new StringBuilder();
        if (baseUrl == null || baseUrl.isEmpty()) {
            errors.append("baseUrl: elemento requerido.");
        }

        if (keyword == null || keyword.isEmpty() || keyword.length() < MIN_KEYWORD || keyword.length() > MAX_KEYWORD) {
            errors.append("keyword: ").append(String.format("A palavra deve ter entre %d e %d", MIN_KEYWORD, MAX_KEYWORD));
        }
        
        if (errors.length() > 0) {
            throw new FieldValidationException(errors.toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CrawlerCreateDTO)) {
            return false;
        }
        CrawlerCreateDTO crawlerCreateDTO = (CrawlerCreateDTO) o;
        return Objects.equals(baseUrl, crawlerCreateDTO.baseUrl) && Objects.equals(keyword, crawlerCreateDTO.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseUrl, keyword);
    }

}
