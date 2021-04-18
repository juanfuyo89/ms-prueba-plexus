package com.between.prueba

import spock.lang.Specification

class MsPruebaBetweenApplicationSpec extends Specification {

    private MsPruebaBetweenApplication msPruebaBetweenApplication

    def setup() {
        msPruebaBetweenApplication = new MsPruebaBetweenApplication()
    }

    def "Ejecutar main"() {
        given: "Que se llama al metodo main()"
        println 'given'
        when: "ejecuto el metodo main()"
        println 'when'
        then: "resultado esperado"
        println 'then'
    }
}
