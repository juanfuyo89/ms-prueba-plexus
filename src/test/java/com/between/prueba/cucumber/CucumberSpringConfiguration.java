package com.between.prueba.cucumber;

import com.between.prueba.MsPruebaBetweenApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = MsPruebaBetweenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = MsPruebaBetweenApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {

}