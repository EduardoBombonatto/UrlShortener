package com.eduardo.urlshortener.controllers;

import com.eduardo.urlshortener.services.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(value = "/urls")
@RequiredArgsConstructor
public class UrlController {

    private final UrlShortenerService service;

    public record ShortenRequest(String longUrl) {
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> shortenUrl(@RequestBody ShortenRequest request) {
        String shortUrl = service.shortenUrl(request.longUrl());

        String fullShortUrl = "http://localhost:8080/" + shortUrl;
        return ResponseEntity.ok(Map.of("shortUrl", fullShortUrl));
    }

    @GetMapping
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        String originalUrl = service.getOriginalUrl(shortUrl);

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(originalUrl))
                .build();
    }

}
