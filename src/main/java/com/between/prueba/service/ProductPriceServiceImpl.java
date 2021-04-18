package com.between.prueba.service;

import com.between.prueba.dto.ProductPriceResponse;
import com.between.prueba.repository.PriceRepository;
import com.between.prueba.repository.entity.Price;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Comparator;

@Slf4j
@Service
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Autowired
    private PriceRepository priceRepository;

    public ProductPriceResponse getProductPrice(LocalDateTime applicationDate,
                                                Integer productId, Integer brandId) throws Throwable {
        return priceRepository.findByDateAndProductAndBrand(applicationDate,productId,brandId)
                .flatMap(prices -> prices.stream().max(Comparator.comparing(Price::getPriority))
                        .map(price -> dozerBeanMapper.map(price, ProductPriceResponse.class))).orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "No se encontraros precios del producto");
                });
    }

}
