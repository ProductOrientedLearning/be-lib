package pol.ecom.micro.shop.lib.integrate.rest.impl;
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


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pol.ecom.micro.shop.lib.dto.response.ProductDto;
import pol.ecom.micro.shop.lib.integrate.rest.ProductIntegrate;
import pol.ecom.micro.shop.lib.util.MessageUtil;
import pol.ecom.micro.shop.lib.util.RestUtility;

@Component
public class ProductIntegrateImpl implements ProductIntegrate {

    @Value("${url.product-service}")
    private String urlProduct;
    @Value("${header.security.key-token}")
    private String keyToken;
    @Value("${header.security.value-token}")
    private String valueToken;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MessageUtil messageUtil;


    @Override
    public ProductDto getProductById(long id) {
        RestUtility<ProductDto> restUtility = new RestUtility<>();
        restUtility.setRestTemplate(restTemplate);
        restUtility.setUrl(urlProduct + "/internal/product/id/" + id);
        restUtility.setTypeReference(new ParameterizedTypeReference<ProductDto>() {
        });
        restUtility.setHttpMethod(HttpMethod.GET);
        restUtility.setKeyToken(keyToken);
        restUtility.setValueToken(valueToken);
        ResponseEntity<ProductDto> response = restUtility.exchange();
        return response.getBody();
    }
}
