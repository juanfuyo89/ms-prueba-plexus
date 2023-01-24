package com.plexus.prueba.cucumber;

import com.plexus.prueba.dto.ProductPriceResponse;
import com.plexus.prueba.util.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

public class MsPruebaPlexusSteps {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private Login login;

    private String token;
    private ResponseEntity<ProductPriceResponse> priceResponseEntity;

    @Given("Obtengo un token para consumo del servicio")
    public void obtengo_un_token_para_consumo_del_servicio() {
        token = login.getToken();
    }

    @When("Al querer obtener el precio a aplicar a un producto con id {string} para la marca con id {string} y en la fecha {string}")
    public void al_querer_obtener_el_precio_a_aplicar_a_un_producto_con_id_para_la_marca_con_id_y_en_la_fecha(String string, String string2, String string3) {
        HttpHeaders headers = login.obtenerHeaders(token);
        HttpEntity<Object> formEntity = new HttpEntity<>(null, headers);
        priceResponseEntity = testRestTemplate.exchange(login.getUriMs() + "/productPrice/" + string3 + "/"
                + string + "/" + string2, HttpMethod.GET, formEntity, ProductPriceResponse.class);
    }

    @Then("Se recupera el precio aplicado {string}")
    public void se_recupera_el_precio_aplicado(String priceStr) {
        ProductPriceResponse response = priceResponseEntity.getBody();
        Double price = Double.parseDouble(priceStr);
        Assert.assertEquals(priceResponseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getPrice(),price);
    }

}
