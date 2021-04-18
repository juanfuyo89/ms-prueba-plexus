package com.between.prueba.controller;

import com.between.prueba.dto.ProductPriceResponse;
import com.between.prueba.service.ProductPriceService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 *
 * Clase controlador de precios
 *
 *@author: Juan Carlos Fuyo
 */
@Slf4j
@RestController
@AllArgsConstructor
@NoArgsConstructor
public class ProductPriceController {

    @Autowired
    private ProductPriceService productPriceService;

    @ApiOperation("Servicio que obtiene el precio a aplicar a un producto de acuerdo a una fecha")
    @GetMapping(value = "/productPrice/{applicationDate}/{productId}/{brandId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductPriceResponse> getProductPrice(@PathVariable("applicationDate")
                                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                            LocalDateTime applicationDate,
                                                                @PathVariable("productId") Integer productId,
                                                                @PathVariable("brandId") Integer brandId) throws Throwable {
        return ResponseEntity.ok(productPriceService.getProductPrice(applicationDate,productId,brandId));
    }

}
