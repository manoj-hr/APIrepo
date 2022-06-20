package stepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.EndPoints;
import resources.TestDataPayload;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class StepDefination extends Utils {
    RequestSpecification req;
    ResponseSpecification resp;
    Response response;
    TestDataPayload data=new TestDataPayload();
    static String place_id;
    @Given("add Place payload {string} {string} {string} {string}")
    public void add_place_payload(String name, String phone_number, String address, String language) throws IOException {
        req=given().spec(requestSpecification()).body(data.addPlacePayload(name,phone_number,address,language));
    }
    @When("user calls {string} using {string} http method")
    public void user_calls_using_post_http_method(String resources,String method) {
        EndPoints endpoint=EndPoints.valueOf(resources);
//        System.out.println(endpoint.getEndPoint()+" "+method);
        resp=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if(method.equalsIgnoreCase("POST"))
        {
            response=req.when().post(endpoint.getEndPoint()).then().spec(resp).extract().response();
//            System.out.println("Response is\n "+response.asString());
        }
        else if(method.equalsIgnoreCase("GET"))
        {
            response=req.when().post(endpoint.getEndPoint()).then().spec(resp).extract().response();
        }
        else if(method.equalsIgnoreCase("Delete"))
        {
            response=req.when().delete(endpoint.getEndPoint()).then().spec(resp).extract().response();
//            System.out.println("Response is\n "+response.asString());
        }

    }
    @Then("API call got success with status code {int}")
    public void api_call_got_success_with_status_code(Integer int1) {
        assertEquals(200,response.getStatusCode());
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {

        assertEquals(getJsonPath(response,key),value);
    }
    @Then("verify the place_id created for {string} using {string}")
    public void verify_the_place_id_created_for_using(String expectedName, String resources) throws IOException {
//        System.out.println("response is \n"+response.asPrettyString());
        place_id=getJsonPath(response,"place_id");
        req=given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_using_post_http_method(resources,"get");
        String actualName=getJsonPath(response,"name");
//        System.out.println(actualName);
        assertEquals(actualName,expectedName);

    }
    @Given("deletePLace payload")
    public void delete_p_lace_payload() throws IOException {
        req=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }
}
