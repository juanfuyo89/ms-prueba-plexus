package com.plexus.prueba.service

import com.plexus.prueba.dto.ProductPriceResponse
import com.plexus.prueba.repository.PriceRepository
import com.plexus.prueba.repository.entity.Price
import org.dozer.DozerBeanMapper
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification

import java.time.LocalDateTime

/**
 *
 *Test ProductPriceService
 *
 *@author: Juan Carlos Fuyo
 */
class ProductPriceServiceSpec extends Specification {

    private ProductPriceService productPriceService
    private PriceRepository priceRepository
    private DozerBeanMapper mapper

    def setup() {
        priceRepository = Mock(PriceRepository.class)
        mapper = Mock(DozerBeanMapper.class)
        productPriceService = new ProductPriceServiceImpl(mapper, priceRepository)
    }

    def "Retornar precios a aplicar a un producto de acuerdo a una fecha"() {
        given: "Que se llama al metodo getProductPrice(applicationDate,productId,brandId)"
        def date = LocalDateTime.now()
        def priceId = 1
        def brandId = 1
        def productId = 35455
        def curr = "EUR"
        def price1 = Price.builder().priceId(priceId).brandId(brandId).startDate(date)
                .endDate(date).priceList(2).productId(productId).priority(1)
                .price(25.45).curr(curr).build()
        def price2 = Price.builder().priceId(priceId).brandId(brandId).startDate(date)
                .endDate(date).priceList(1).productId(productId).priority(0)
                .price(35.50).curr(curr).build()
        def responseList = Arrays.asList(price1,price2)
        Optional<List<Price>> responseOptional = Optional.of(responseList)

        def productPriceResponse = ProductPriceResponse.builder()
                .productId(productId).brandId(brandId).priceList(2).startDate(date)
                .endDate(date).price(25.45).build()
        println 'given'
        when: "ejecuto el metodo getProductPrice(applicationDate,productId,brandId)"
        priceRepository.findByDateAndProductAndBrand(date,productId,brandId) >> responseOptional
        mapper.map(price1, ProductPriceResponse.class) >> productPriceResponse
        def response = productPriceService.getProductPrice(date,productId,brandId)
        println 'when'
        then: "resultado esperado"
        response.productId == productId
        response.price == 25.45
        println 'then'
    }

    def "Retornar ResponseStatusException al aplicar precio a un producto que no existe"() {
        given: "Que se llama al metodo getProductPrice(applicationDate,productId,brandId)"
        def date = LocalDateTime.now()
        Optional<List<Price>> responseOptional = Optional.of(new ArrayList<>())
        println 'given'
        when: "ejecuto el metodo getProductPrice(applicationDate,productId,brandId)"
        priceRepository.findByDateAndProductAndBrand(date,99999,1) >> responseOptional
        def response = productPriceService.getProductPrice(date,99999,1)
        println 'when'
        then: "resultado esperado"
        thrown ResponseStatusException
        println 'then'
    }

}
