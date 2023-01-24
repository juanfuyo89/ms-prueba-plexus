package com.plexus.prueba.cucumber;

import com.plexus.prueba.MsPruebaPlexusApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = MsPruebaPlexusApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = MsPruebaPlexusApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {

}