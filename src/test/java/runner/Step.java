package runner;

import configuration.ConfigAPI;
import configuration.ConfigEnvironment;
import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import helpers.JsonHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;

public class Step {

    Response response;
    RequestInformation requestInformation=new RequestInformation();
    Map<String,String> variables = new HashMap<>();
    Boolean globalAssert=true;
    String parametrosGET = "";
    Boolean esMetodoGET = false;

    String xKey = "";
    @Given("el usuario tiene acceso al servicio")
    public void elUsuarioTieneAccesoAlServicio() throws Exception {
        Map <String,String> headAuth= new HashMap<>();

        Map <String,String> head= new HashMap<>();
        head.put("Content-Type", "application/json");
        requestInformation.setHeaders(head);
    }

    @Given("el usuario no tiene {} acceso al servicio")
    public void elUsuarioNoTieneTokenAccesoAlServicio(String llave) throws Exception {
        Map <String,String> headAuth= new HashMap<>();

        Map <String,String> head= new HashMap<>();
        head.put("Content-Type", "application/json");
        requestInformation.setHeaders(head);
    }

    @When("envio {} request a {}")
    public void envioRequest(String metodo,String url, String parametros) throws Exception {
        if (!metodo.toUpperCase().equals("GET")) {
            requestInformation.setUrl(this.replaceValue(url)).setBody(parametros);
        } else {
            requestInformation.setUrl(this.replaceValue(url)).setBody("").setQueryParams(convertStringMap(parametros));
            parametrosGET = parametros;
            esMetodoGET = true;
        }
        response = FactoryRequest.make(metodo).send(requestInformation);
    }

    /*@When("enviamos GET request a {} usando")
    public void enviamosGetRequestUsando(String url, String parametros) throws Exception {
        requestInformation.setUrl(this.replaceValue(url)).setBody("").setQueryParam(parametros.trim());
        response= FactoryRequest.make("GET").send(requestInformation);
    }*/

    @And("para casos de prueba {}")
    public void casoDePrueba(String casoDePrueba){

        System.out.println("****************************************************************");
        System.out.println("CASO DE PRUEBA: "+ casoDePrueba);
        System.out.println();
        System.out.println("****************************************************************");
        System.out.println("                           Datos Enviados");
        System.out.println("****************************************************************");
        System.out.println("Body: "+ requestInformation.getBody());
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Param: "+ requestInformation.getQueryParams());
        System.out.println();
        System.out.println("****************************************************************");
        System.out.println("                           Datos de Respuesta");
        System.out.println("****************************************************************");
        System.out.println(response.body().asPrettyString());

    }

    @Then("el codigo de respuesta es {int}")
    public void elCodigoDeRespuestaEs(int expectedCode){
        response.then().statusCode(expectedCode);
    }

    @And("el mensaje de respuesta es {}")
    public void elMensajeDeRespuestaEs(String expectedMessage) {
        response.then().contentType(equalTo("application/json; charset=utf-8")).assertThat().body("msg",equalTo(expectedMessage));
    }

    @And("body de respuesta esperado")
    public void bodyDeRespuestaEsperado(String expectedBody) {
        globalAssert = JsonHelper.areEqualJson(this.replaceValue(expectedBody), response.body().asString());
    }

    public String replaceValue(String value){
        for (String key: this.variables.keySet() ) {
            value=value.replace(key,this.variables.get(key));
        }
        value= value.replace("LOGIN_URL", ConfigAPI.LOGIN_URL)
                .replace("REGISTER_URL", ConfigAPI.REGISTER_URL);

        return value;
    }

    public Map<String, String> convertStringMap(String parametros) {
        Map<String, String> param = new HashMap<>();
        if (!parametros.isEmpty()) {
            String[] campos = parametros.split(",");
            for (int i = 0; i < campos.length; i++) {
                campos[i] = campos[i].replaceAll("\\s+", "");
                String[] camposValores = campos[i].split("=");
                if (camposValores.length == 1) {
                    param.put(camposValores[0], "");
                } else {
                    param.put(camposValores[0], camposValores[1]);
                }
            }
        }
        return param;
    }


}
