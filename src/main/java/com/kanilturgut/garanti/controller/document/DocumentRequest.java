package com.kanilturgut.garanti.controller.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kanilturgut.garanti.model.Document;

public class DocumentRequest {

    @JsonProperty("product")
    private String product;

    @JsonProperty("market")
    private String market;

    @JsonProperty("expiredAt")
    private long expiredAt;

    public Document toDocument() {
        return new Document(this.market, this.product, this.expiredAt);
    }
}
