package factoryRequest;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RequestGET implements IRequest{
    @Override
    public Response send(RequestInformation requestInformation) {
        Response response= given().relaxedHTTPSValidation()
                .headers(requestInformation.getHeaders())
                .urlEncodingEnabled(true)
                .queryParams(requestInformation.getQueryParams())
                .body(requestInformation.getBody())
                .log().all()
                .when()
                .get(requestInformation.getUrl());
        response.then().log().all();
        return response;
    }

}
