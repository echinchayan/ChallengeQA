@Test
Feature: LOGIN: Autenticación de usuarios al sistema
  Permite la autenticación de usuarios por correo y contraseña

  Scenario Outline: 01. Autenticación ingresando datos correctos
    Given el usuario tiene acceso al servicio
    When envio POST request a LOGIN_URL
    """
    {
        "email":"<email>",
        "password":"<password>"
    }
    """
    And para casos de prueba "<casoDePrueba>"
    Then el codigo de respuesta es <codeEsperado>
    And el mensaje de respuesta es <mensajeEsperado>


    Examples:
      | casoDePrueba                      | email                | password | codeEsperado | mensajeEsperado |
      | Contraseña de 5 caracteres        | erickc1902@gmail.com | 12345    | 200          | LOGIN VALID     |
      | Contraseña de más de 5 caracteres | erickc1902@gmail.com | 123456   | 200          | LOGIN VALID     |


  Scenario Outline: 02: Validación de ingreso de datos
    Given el usuario tiene acceso al servicio
    When envio POST request a LOGIN_URL
    """
    {
        "email":"<email>",
        "password":"<password>"
    }
    """
    And para casos de prueba "<casoDePrueba>"
    Then el codigo de respuesta es <codeEsperado>
    And el mensaje de respuesta es <mensajeEsperado>


    Examples:
      | casoDePrueba                         | email                | password | codeEsperado | mensajeEsperado |
      | Email sin formato correcto           | erickc1902gmail.com  | 12345    | 422          | INVALID         |
      | Contraseña con menos de 5 caracteres | erickc1902@gmail.com | 1234     | 422          | INVALID         |
      | Validación de campos obligatorios    |                      |          | 422          | REQUIRED        |
      | Validación de campo email vacio      |                      | 12345    | 422          | REQUIRED        |
      | Validación de campo password         | erickc1902@gmail.com |          | 422          | REQUIRED        |


