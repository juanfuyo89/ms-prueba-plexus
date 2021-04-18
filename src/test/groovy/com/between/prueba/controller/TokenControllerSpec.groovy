package com.between.prueba.controller

import com.between.prueba.dto.TokenResponse
import com.between.prueba.service.TokenService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

/**
 *
 *Test TokenController
 *
 *@author: Juan Carlos Fuyo
 */
class TokenControllerSpec extends Specification {

    private TokenController tokenController
    private TokenService tokenService

    def setup() {
        tokenService = Mock(TokenService)
        tokenController = new TokenController(
                tokenService
        )
    }

    def "Retornar token para consumo del ms"() {
        given: "Que se llama al end point /token/{username}/{password}"
        def accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJteW1KV1QiLCJpYX"
        def tokenType = "Bearer"
        def username = "prueba"
        def password = "prueba123+"
        def tokenResponse = TokenResponse.builder()
                .accessToken(accessToken).tokenType(tokenType)
                .expiresIn("432000").build()
        tokenService.validateUser(username,password) >> tokenResponse
        println 'given'
        when: "ejecuto el metodo getToken(username,password)"
        ResponseEntity<TokenResponse> responseEntity =
                tokenController.getToken(username,password)
        println 'when'
        then: "resultado esperado"
        responseEntity.statusCode.is2xxSuccessful()
        responseEntity.body.accessToken == accessToken
        responseEntity.body.tokenType == tokenType
        println 'then'
    }

}
