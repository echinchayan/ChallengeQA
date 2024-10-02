package helpers;
import org.json.JSONObject;
import java.util.Iterator;

public class JsonHelper {

    public static boolean areEqualJson (String expectedResult,String actualResult ){
        boolean areEqual=true;
        JSONObject expectedJSON= new JSONObject(expectedResult);
        JSONObject actualJSON= new JSONObject(actualResult);
        Iterator<?> keyList= expectedJSON.keys();
        while (keyList.hasNext()){
            String key= (String) keyList.next();
            String expectedValue= String.valueOf(expectedJSON.get(key));
            String actualValue= String.valueOf(actualJSON.get(key));
            if (expectedJSON.has(key) && actualJSON.has(key)) {
                if (expectedValue.equals("IGNORE") || actualValue.equals("IGNORE")) {
                    System.out.println("WARN> se ignoro la comparacion del atributo [" + key + "]");
                } else if (!expectedValue.equals(actualValue)) {
                    areEqual = false;
                    System.out.println("ERROR> los valores no son iguales del atributo [" + key + "]" +
                            "expected: " + expectedValue + " VS actual: " + actualValue);
                }
            }else{
                areEqual = false;
                System.out.println("ERROR no existe un atributo: "+key);
            }
        }
        return areEqual;
    }
}
