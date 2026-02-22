package com.eduardo.urlshortener.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_urls")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlEntity {

    @Id
    private Long id;

    @Column(name = "short_url", unique = true, nullable = false, length = 7)
    private String shortUrl;

    @Column(name = "long_url", unique = true, nullable = false, length = 2048)
    private String longUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
