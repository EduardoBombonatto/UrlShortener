package com.eduardo.urlshortener.services;

import com.eduardo.urlshortener.entities.UrlEntity;
import com.eduardo.urlshortener.repositories.UrlRepository;
import com.eduardo.urlshortener.utils.Base62Encoder;
import com.eduardo.urlshortener.utils.SnowflakeIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {

    private final UrlRepository urlRepository;
    private final SnowflakeIdGenerator idGenerator;
    private final Base62Encoder base62Encoder;

    public String shortenUrl(String url) {
        long id =  idGenerator.nextId();

        String shortUrl = base62Encoder.encode(id);
        UrlEntity urlEntity = new UrlEntity(id, shortUrl, url, LocalDateTime.now());
        urlRepository.save(urlEntity);
        return shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        UrlEntity map = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new RuntimeException("Url not found"));
        return map.getLongUrl();
    }
}
