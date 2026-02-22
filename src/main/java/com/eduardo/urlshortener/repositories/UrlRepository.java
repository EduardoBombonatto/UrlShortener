package com.eduardo.urlshortener.repositories;

import com.eduardo.urlshortener.entities.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
}
