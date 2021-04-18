package com.between.prueba.controller;

import com.between.prueba.dto.TokenResponse;
import com.between.prueba.service.TokenService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** 
 * 
 * Clase controlador para obtener el token de consumo del ms
 * 
 *@author: Juan Carlos Fuyo
 */
@Slf4j
@RestController
@AllArgsConstructor
@NoArgsConstructor
public class TokenController {

  @Autowired
  private TokenService tokenService;

  @ApiOperation("Servicio que obtiene el token para consumir los demás servicios expuestos por el ms")
  @GetMapping(value = "/token/{username}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TokenResponse> getToken(@PathVariable("username") String username,
                                                @PathVariable("password") String password) throws Throwable {
    return ResponseEntity.ok(tokenService.validateUser(username,password));
  }

}
