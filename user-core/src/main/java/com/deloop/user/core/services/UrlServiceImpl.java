package com.deloop.user.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    @Value("${domain.url}")
    private String domain_url;

    @Override
    public String getDomain() {
        return domain_url;
    }
}
