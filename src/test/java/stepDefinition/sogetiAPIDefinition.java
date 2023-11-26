package stepDefinition;

import static io.restassured.RestAssured.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;

public class sogetiAPIDefinition {

    Response response;
    JSONObject jo;

    @Given("I hit the Get API")
    public void i_hit_the_get_api() {
        response = given().
                contentType(ContentType.JSON).
                when().
                get("http://api.zippopotam.us/de/bw/stuttgart");
        //System.out.println("Response is: " + response.prettyPrint());
    }

    @Then("Verify that the response status code is {int} and content type is JSON")
    public void verify_that_the_response_status_code_is_and_content_type_is(int expectedStatusCode) {
        //System.out.println("Expected Status code: "+expectedStatusCode);
        // int actualStatusCode = response.getStatusCode();
        // Assert.assertEquals(expectedStatusCode,actualStatusCode);
        // System.out.println("Actual Status code: "+actualStatusCode);
        response.then().
                assertThat().
                statusCode(expectedStatusCode).
                and().
                assertThat().
                contentType(ContentType.JSON);
    }

    @Then("Verify that the response time is below 1s")
    public void verify_that_the_response_time_is_below_1s() {
        long responseTimeInMS = response.time();
        System.out.println("Response Time in MS : " + responseTimeInMS);
        response.then().assertThat().time(Matchers.lessThan(1000L));
    }

    @Then("Verify in Response - That country is {string} and state is {string}")
    public void verify_in_response_that_country_is_and_state_is(String country, String state) {
        response.then().assertThat().body("country", Matchers.equalTo(country)).and().
                and().assertThat().body("state", Matchers.equalTo(state));
    }

    @Then("Verify in Response For Post Code {string} the place name has {string}")
    public void verify_in_response_for_post_code_the_place_name_has(String postalCode, String placeName) {
        boolean status = false;
        jo = new JSONObject(response.asString());

        for(int i=0; i<jo.getJSONArray("places").length();i++){

            String responsePostalCode = jo.getJSONArray("places").getJSONObject(i).get("post code").toString();

            if(responsePostalCode.equals(postalCode)){

                String responsePlaceName = jo.getJSONArray("places").getJSONObject(i).get("place name").toString();

                if(responsePlaceName.equals(placeName)){

                    System.out.println("For Postal Code: " + postalCode + " Place name is: " + placeName);
                    System.out.println("For Postal Code: " + responsePostalCode + " Place name is: " + responsePlaceName);

                    status = true;
                }
                break;
            }
        }
        Assert.assertEquals(true,status);
    }

    @Given("I hit the Get API with {string} and {string}")
    public void i_hit_the_get_api_with_and(String Country, String Postal_Code) {

        response = given().
                when().
                baseUri("http://api.zippopotam.us/").
                basePath("{country}/{postal-code}").pathParam("country",Country).
                pathParam("postal-code",Postal_Code).
                get();
        //System.out.println("######$$$$$$$$$%:      "+response.prettyPrint());
    }

    @Then("Verify in Response - {string} for each input Country and Postal Code")
    public void verify_in_response_for_each_input_country_and_postal_code(String expectedPlaceName) {

        jo = new JSONObject(response.asString());
        String responsePlaceName = jo.getJSONArray("places").getJSONObject(0).get("place name").toString();

        System.out.println("Expected Place Name:      "+expectedPlaceName);
        System.out.println("Actual Place Name:      "+responsePlaceName);

        Assert.assertEquals(expectedPlaceName,responsePlaceName);
    }
}
