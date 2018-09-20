import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UrlBuilder {
    private static String baseUrl = "";
    private static String applicationId = "";
    private static String restApiKey = "";
    private static String tableName = "";

    public static String getBasicUrl(){
        return new StringBuilder()
                .append(baseUrl)
                .append("/")
                .append(applicationId)
                .append("/")
                .append(restApiKey)
                .append("/data/")
                .append(tableName).toString();
    }

    public static String getUrlWithId(String id){
        return new StringBuilder()
                .append(baseUrl)
                .append("/")
                .append(applicationId)
                .append("/")
                .append(restApiKey)
                .append("/data/")
                .append(tableName)
                .append("/")
                .append(id).toString();
    }

    public static void readProperties(){
        String resourceName = "config.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
            baseUrl = props.getProperty("baseUrl");
            applicationId = props.getProperty("applicationId");
            restApiKey = props.getProperty("restApiKey");
            tableName = props.getProperty("tableName");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
