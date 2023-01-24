package com.plexus.prueba.controller


import com.plexus.prueba.dto.ProductPriceResponse
import com.plexus.prueba.service.ProductPriceService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.Month

/**
 *
 *Test ProductPriceController
 *
 *@author: Juan Carlos Fuyo
 */
class ProductPriceControllerSpec extends Specification {

    private ProductPriceController productPriceController
    private ProductPriceService productPriceService

    def setup() {
        productPriceService = Mock(ProductPriceService)
        productPriceController = new ProductPriceController(
                productPriceService
        )
    }

    def "Retornar precios a aplicar test 1"() {
        given: "Que se llama al end point /productPrice/{applicationDate}/{productId}/{brandId}"
        def date = LocalDateTime.of(2020, Month.JUNE,14,10,0,0)
        def productId = 35455
        def brandId = 1
        def productPriceResponse = ProductPriceResponse.builder()
                .productId(productId).brandId(brandId).priceList(4)
                .startDate(date).endDate(date).price(38.95).build()
        productPriceService.getProductPrice(date,productId,brandId) >> productPriceResponse
        println 'given'
        when: "ejecuto el metodo getProductPrice(applicationDate,productId,brandId)"
        ResponseEntity<ProductPriceResponse> responseEntity =
                productPriceController.getProductPrice(date,productId,brandId)
        println 'when'
        then: "resultado esperado"
        responseEntity.statusCode.is2xxSuccessful()
        responseEntity.body.priceList == 4
        responseEntity.body.price == 38.95
        println 'then'
    }

    def "Retornar precios a aplicar test 2"() {
        given: "Que se llama al end point /productPrice/{applicationDate}/{productId}/{brandId}"
        def date = LocalDateTime.of(2020,Month.JUNE,14,16,0,0)
        def productId = 35455
        def brandId = 1
        def productPriceResponse = ProductPriceResponse.builder()
                .productId(productId).brandId(brandId).priceList(4)
                .startDate(date).endDate(date).price(38.95).build()
        productPriceService.getProductPrice(date,productId,brandId) >> productPriceResponse
        println 'given'
        when: "ejecuto el metodo getProductPrice(applicationDate,productId,brandId)"
        ResponseEntity<ProductPriceResponse> responseEntity =
                productPriceController.getProductPrice(date,productId,brandId)
        println 'when'
        then: "resultado esperado"
        responseEntity.statusCode.is2xxSuccessful()
        responseEntity.body.priceList == 4
        responseEntity.body.price == 38.95
        println 'then'
    }

    def "Retornar precios a aplicar test 3"() {
        given: "Que se llama al end point /productPrice/{applicationDate}/{productId}/{brandId}"
        def date = LocalDateTime.of(2020,Month.JUNE,14,16,0,0)
        def productId = 35455
        def brandId = 1
        def productPriceResponse = ProductPriceResponse.builder()
                .productId(productId).brandId(brandId).priceList(4)
                .startDate(date).endDate(date).price(38.95).build()
        productPriceService.getProductPrice(date,productId,brandId) >> productPriceResponse
        println 'given'
        when: "ejecuto el metodo getProductPrice(applicationDate,productId,brandId)"
        ResponseEntity<ProductPriceResponse> responseEntity =
                productPriceController.getProductPrice(date,productId,brandId)
        println 'when'
        then: "resultado esperado"
        responseEntity.statusCode.is2xxSuccessful()
        responseEntity.body.priceList == 4
        responseEntity.body.price == 38.95
        println 'then'
    }

    def "Retornar precios a aplicar test 4"() {
        given: "Que se llama al end point /productPrice/{applicationDate}/{productId}/{brandId}"
        def date = LocalDateTime.of(2020,Month.JUNE,14,16,0,0)
        def productId = 35455
        def brandId = 1
        def productPriceResponse = ProductPriceResponse.builder()
                .productId(productId).brandId(brandId).priceList(4)
                .startDate(date).endDate(date).price(38.95).build()
        productPriceService.getProductPrice(date,productId,brandId) >> productPriceResponse
        println 'given'
        when: "ejecuto el metodo getProductPrice(applicationDate,productId,brandId)"
        ResponseEntity<ProductPriceResponse> responseEntity =
                productPriceController.getProductPrice(date,productId,brandId)
        println 'when'
        then: "resultado esperado"
        responseEntity.statusCode.is2xxSuccessful()
        responseEntity.body.priceList == 4
        responseEntity.body.price == 38.95
        println 'then'
    }

}
