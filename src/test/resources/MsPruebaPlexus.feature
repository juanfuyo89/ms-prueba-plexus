Feature: MsPruebaPlexus

  Scenario Outline: Al momento de consultar el precio a aplicar a un producto
    Given Obtengo un token para consumo del servicio
    When Al querer obtener el precio a aplicar a un producto con id '<productId>' para la marca con id '<brandId>' y en la fecha '<applicationDate>'
    Then Se recupera el precio aplicado '<price>'
    Examples:
      | productId | brandId | applicationDate | price |
      | 35455 | 1 | 2020-06-14T10:00:00 | 35.50 |
      | 35455 | 1 | 2020-06-14T16:00:00 | 25.45 |
      | 35455 | 1 | 2020-06-14T21:00:00 | 35.50 |
      | 35455 | 1 | 2020-06-15T10:00:00 | 30.50 |
      | 35455 | 1 | 2020-06-16T21:00:00 | 38.95 |
