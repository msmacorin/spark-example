package br.com.macorin.spark.domain.models.enums;

import com.google.gson.annotations.SerializedName;

public enum CrawlerStatus {
    
    @SerializedName("active")
    ACTIVE,
    
    @SerializedName("done")
    DONE;
}
