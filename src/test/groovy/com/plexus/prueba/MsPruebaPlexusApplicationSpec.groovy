package com.plexus.prueba

import spock.lang.Specification

class MsPruebaPlexusApplicationSpec extends Specification {

    private MsPruebaPlexusApplication msPruebaPlexusApplication

    def setup() {
        msPruebaPlexusApplication = new MsPruebaPlexusApplication()
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
