package com.plexus.prueba.service;

import com.plexus.prueba.dto.ProductPriceResponse;

import java.time.LocalDateTime;

public interface ProductPriceService {
    ProductPriceResponse getProductPrice(LocalDateTime applicationDate,
                                         Integer productId, Integer brandId) throws Throwable;
}
