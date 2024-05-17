package steps;

import Utilities.RestAssuredExtension;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo.Address;
import pojo.Location;
import pojo.Posts;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class GetPostSteps {

    RestAssuredExtension restAssuredExtension = new RestAssuredExtension();

    private static ResponseOptions<Response> response;

    @Given("I perform GET operation")
    public void i_perform_get_operation(){
       // given().contentType(ContentType.JSON);
    }


    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String url) throws Throwable{
       // response = restAssuredExtension.GetOps(url);
    }

//    @And("I perform GET for the post number {string}")
//    public void iPerformGETForThePostNumber(String postNumber) {
////        when().get(String.format("http://localhost:3000/posts/%s",postNumber))
////                .then().body("title", is("a title"));
//
//        BDDStyleMethod.SimpleGETpost(postNumber);
//
//    }

    @Then("I should see the title as {string}")
    public void i_should_see_the_title_as(String titleName) {
        //method 1
        //assertThat(response.getBody().jsonPath().get("title"), hasItem("a title"));

        // Method 2 - Deserialization of JSON Objects to POJO Class
        var posts = response.getBody().as(Posts.class);
        assertThat(posts.getTitle(),equalTo(titleName));
    }

    @Then("I should see the title names")
    public void iShouldSeeTheTitleNames() {
    BDDStyleMethod.performContainsCollections();
    }

    @Then("I should see verify GET parameter")
    public void iShouldSeeVerifyGETParameter() {
        BDDStyleMethod.performPathParameter();
    }

    @Then("I should see verify GET query parameter")
    public void iShouldSeeVerifyGETQueryParameter() {
        BDDStyleMethod.performQueryParameter();
    }


    @Given("I perform POST operation for {string}")
    public void iPerformPOSTOperationFor(String arg0) {
    BDDStyleMethod.performPOSTWithBodyParameter();
    }


    @Given("I perform authorization operation for {string} with body")
    public void iPerformAuthorizationOperationForWithBody(String url, DataTable table) {

        List<List<String>> cells = table.cells();
        Map<String, String> body = new HashMap<>();
        body.put("email", cells.get(1).get(0));
        body.put("password", cells.get(1).get(1));

        response = RestAssuredExtension.PostOpsWithBody(url, body);


    }

    @When("I perform GET operation for with token {string}")
    public void iPerformGETOperationForWithToken(String url) {
        response = RestAssuredExtension.GetOpsWithToken(url, response.getBody().jsonPath().get("access_token"));
    }

    @Then("I should see the author name as {string}")
    public void iShouldSeeTheAuthorNameAs(String title) {
        assertThat(response.getBody().jsonPath().get("title"), hasItem(title));

    }

    @And("I perform GET operation with query parameter for address {string}")
    public void iPerformGETOperationWithQueryParameterForAddress(String url, DataTable table) {
        List<List<String>> cells = table.cells();
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("id", cells.get(1).get(0));

        // response
        response = RestAssuredExtension.GetWithQueryParamWithToken(url, queryParams, response.getBody().jsonPath().get("access_token"));
    }

    @Then("I should see the street name as {string} for the {string} address")
    public void iShouldSeeTheStreetNameAsForTheAddress(String streetName, String addressType) {
        var location = response.getBody().as(Location[].class);

        // filter the address based on the type of addresses
        Address address = location[0].getAddress().stream().filter(x -> x.getType().equalsIgnoreCase(addressType))
                        .findFirst().orElse(null);
        assertThat(address.getStreet(),equalTo(streetName));
    }

    @Then("I should see the title as {string} with json validation")
    public void iShouldSeeTheTitleAsWithJsonValidation(String arg0) {

        // returns body as a String
        var responseBody = response.getBody().asString();

        // Assert
        assertThat(responseBody, matchesJsonSchemaInClasspath("post.json"));
    }
}
