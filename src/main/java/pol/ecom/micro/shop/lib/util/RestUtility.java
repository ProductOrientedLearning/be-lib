package pol.ecom.micro.shop.lib.util;
/*
 * This is course Microservice Product Oriented
 * MIT No Attribution

 * Copyright (c) 2023 <Dr.JohnLe & Mr.HaNguyen>

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */


import lombok.Setter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Setter
public class RestUtility<T> {
    private String url;
    private String keyToken;
    private String valueToken;
    private HttpMethod httpMethod;
    private Object body;
    private List<Object> params;
    private HttpHeaders headers = null;
    private Class<T> type;
    private ParameterizedTypeReference<T> typeReference;
    private RestTemplate restTemplate;

    public ResponseEntity<T> exchange() {
        return this.restTemplate.exchange(buildExchangeUrl(), httpMethod, buildRequest(), typeReference);
    }

    private String buildExchangeUrl() {
        return UriComponentsBuilder.fromHttpUrl(url).toUriString();
    }

    private HttpEntity<Object> buildRequest() {
        HttpEntity<Object> request;
        if (headers == null) {
            headers = buildHeader();
        }
        request = new HttpEntity<>(body, headers);
        return request;
    }

    private HttpHeaders buildHeader() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add(keyToken, valueToken);
        return header;
    }
}
