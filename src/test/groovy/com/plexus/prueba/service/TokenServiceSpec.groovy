package com.plexus.prueba.service

import com.plexus.prueba.repository.UserRepository
import com.plexus.prueba.repository.entity.User
import com.plexus.prueba.security.TokenGenerator
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification

/**
 *
 *Test TokenService
 *
 *@author: Juan Carlos Fuyo
 */
class TokenServiceSpec extends Specification {

    private TokenService tokenService
    private UserRepository userRepository
    private TokenGenerator tokenGenerator

    def setup() {
        userRepository = Mock(UserRepository.class)
        tokenGenerator = Mock(TokenGenerator.class)
        tokenService = new TokenServiceImpl(userRepository, tokenGenerator,"43200")
    }

    def "Retornar token para consumo del ms"() {
        given: "Que se llama al metodo validateUser(username,password)"
        def accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJteW1KV1QiLCJpYX"
        def tokenType = "Bearer "
        def username = "prueba"
        def password = "prueba123+"
        def user = User.builder().id(1).username(username)
                .password(password).build()
        Optional<User> responseOptional = Optional.of(user)
        println 'given'
        when: "ejecuto el metodo validateUser(username,password)"
        userRepository.validateUser(username,password) >> responseOptional
        tokenGenerator.getJWTToken(username) >> accessToken
        def response = tokenService.validateUser(username,password)
        println 'when'
        then: "resultado esperado"
        response.accessToken == accessToken
        response.tokenType == tokenType
        println 'then'
    }

    def "Retornar ResponseStatusException para un usuario no registrado"() {
        given: "Que se llama al metodo validateUser(username,password)"
        def username = "prueba"
        def password = "prueba123+"
        Optional<User> responseOptional = Optional.empty()
        println 'given'
        when: "ejecuto el metodo validateUser(username,password)"
        userRepository.validateUser(username,password) >> responseOptional
        tokenService.validateUser(username,password)
        println 'when'
        then: "resultado esperado"
        thrown ResponseStatusException
        println 'then'
    }

}
