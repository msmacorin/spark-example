package br.com.macorin.spark.domain.dtos.responses;

import java.io.Serializable;
import java.util.Objects;

public class CrawlerCreatedDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -7343355712807124193L;
    
    private String id;

    public CrawlerCreatedDTO(String id) {
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CrawlerCreatedDTO)) {
            return false;
        }
        CrawlerCreatedDTO crawlerCreateResponseDTO = (CrawlerCreatedDTO) o;
        return Objects.equals(id, crawlerCreateResponseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
