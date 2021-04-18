package com.between.prueba.service;

import com.between.prueba.dto.ProductPriceResponse;

import java.time.LocalDateTime;

public interface ProductPriceService {
    ProductPriceResponse getProductPrice(LocalDateTime applicationDate,
                                         Integer productId, Integer brandId) throws Throwable;
}
