package com.between.prueba.controller


import com.between.prueba.dto.ProductPriceResponse
import com.between.prueba.service.ProductPriceService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import java.time.LocalDateTime

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

    def "Retornar precios a aplicar a un producto de acuerdo a una fecha"() {
        given: "Que se llama al end point /productPrice/{applicationDate}/{productId}/{brandId}"
        def date = LocalDateTime.now()
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
