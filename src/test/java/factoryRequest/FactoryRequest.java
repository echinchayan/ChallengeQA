package factoryRequest;

public class FactoryRequest {

    public static IRequest make(String typeRequest){
        IRequest request;

        switch (typeRequest.toLowerCase()){
            case "post":
                request= new RequestPOST();
                break;
            case "put":
                request= new RequestPUT();
                break;
            case "delete":
                request= new RequestDELETE();
                break;
            case "get":
                request= new RequestGET();
                break;
            default:
                request=new RequestGET();
                break;
        }
        return request;
    }




}
