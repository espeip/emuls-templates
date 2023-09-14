package ru.alfabank.api.eco.emuls.supplier;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class HttpHeaderSupplier {

    private HttpHeaders httpHeaders = new HttpHeaders();

    public HttpHeaders getHeader(String type) {
        httpHeaders.set("Content-Type", type);
        return httpHeaders;
    }
}
