package factoryRequest;
import java.util.Map;

public class RequestInformation {

    private String url;
    private String body;
   // private String queryParam="";
    private Map<String,String> headers;

    private Map<String,String> queryParams;



    public RequestInformation(){}

    public String getUrl() {
        return url;
    }

    public RequestInformation setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getBody() {
        return body;
    }

    public RequestInformation setBody(String body) {
        this.body = body;
        return this;
    }

    public Map<String,String> getQueryParams() {
        return queryParams;
    }

    public RequestInformation setQueryParams(Map<String,String>  queryParams) {
        this.queryParams = queryParams;
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public RequestInformation setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }
}
