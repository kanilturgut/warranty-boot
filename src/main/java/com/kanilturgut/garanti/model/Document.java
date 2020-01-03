package com.kanilturgut.garanti.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "garanti_belgeleri")
public class Document extends AuditModel {

    @Id
    @GeneratedValue(generator = "document_generator")
    @SequenceGenerator(
            name = "document_generator",
            sequenceName = "seq_document"
    )
    private Long id;

    @Column(name = "market", nullable = false)
    private String market;

    @Column(name = "product", nullable = false)
    private String product;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expired_at", nullable = false)
    private Date expiredAt;

    public Document() {
    }

    public Document(String market, String product, long expiredAt) {
        this.market = market;
        this.product = product;
        this.expiredAt = new Date(expiredAt);
    }
}
