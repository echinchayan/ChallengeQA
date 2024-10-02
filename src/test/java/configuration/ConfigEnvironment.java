package configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigEnvironment {

    public static String host;
    public static String user;
    public static String rol;
    public static String authKey;

    public  void readValues() throws IOException {
        Properties properties = new Properties();
        String propertyFile="qa.properties";

        InputStream inputStream= getClass().getClassLoader().getResourceAsStream(propertyFile);

        if (inputStream!=null){
            properties.load(inputStream);
        }
        host= properties.getProperty("host");
        user= properties.getProperty("user");
        rol= properties.getProperty("rol");
        authKey= properties.getProperty("auth-key");
        inputStream.close();
    }

}
