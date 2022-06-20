package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification request;
    public RequestSpecification requestSpecification() throws IOException {
        if(request==null){
        PrintStream logs=new PrintStream(new FileOutputStream("logs.txt"));
         request=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key","qaclick123").
                 addFilter(RequestLoggingFilter.logRequestTo(logs))
                 .addFilter(ResponseLoggingFilter.logResponseTo(logs))
                 .setContentType(ContentType.JSON).build();
        }
         return request;
    }
    public String getGlobalValue(String key) throws IOException {
        FileInputStream fis=new FileInputStream("src\\test\\java\\resources\\GlobalValue.properties");
        Properties prop=new Properties();
        prop.load(fis);
        return prop.getProperty(key);
    }
    public String getJsonPath(Response response, String key){
        String str=response.asString();
        JsonPath js=new JsonPath(str);
        return js.get(key).toString();
    }

}
