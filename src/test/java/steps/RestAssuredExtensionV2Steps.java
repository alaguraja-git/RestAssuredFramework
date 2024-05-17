package steps;

import Utilities.APIConstants;
import Utilities.RestAssuredExtension;
import Utilities.RestAssuredExtensionV2;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import model.Posts;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pojo.Address;
import pojo.Location;
import pojo.LoginBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class RestAssuredExtensionV2Steps {

    private static final Log log = LogFactory.getLog(RestAssuredExtensionV2Steps.class);
    public static ResponseOptions<Response> response;
    public static String token;

    @Given("I perform authorization operation for {string} with body using RestAssuredExtensionV2")
    public void iPerformAuthorizationOperationForWithBodyUsingRestAssuredExtensionV2(String uri, DataTable table) {

        List<List<String>> cells = table.cells();
//        Map<String, String> body = new HashMap<>();
//        body.put("email", cells.get(1).get(0));
//        body.put("password", cells.get(1).get(1));

        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(cells.get(1).get(0));
        loginBody.setPassword(cells.get(1).get(1));

       // response = RestAssuredExtension.PostOpsWithBody(url, body);

        RestAssuredExtensionV2 restAssuredExtensionV2 = new RestAssuredExtensionV2(uri, APIConstants.ApiMethods.POST,null);
        token = restAssuredExtensionV2.Authenticate(loginBody);

    }
    @And("I perform GET operation with query parameter for address {string} using RestAssuredExtensionV2")
    public void iPerformGETOperationWithQueryParameterForAddressUsingRestAssuredExtensionV2(String uri, DataTable table) {
        List<List<String>> cells = table.cells();
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("id", cells.get(1).get(0));

//        // response
//        response = RestAssuredExtension.GetWithQueryParamWithToken(url, queryParams, response.getBody().jsonPath().get("access_token"));

        //  using RestAssuredExtension V2

        RestAssuredExtensionV2 restAssuredExtensionV2 = new RestAssuredExtensionV2(uri, "GET", token );
        response = restAssuredExtensionV2.ExecuteWithQueryParams(queryParams);
    }

    @Then("I should see the street name as {string} for the {string} address using RestAssuredExtensionV2")
    public void iShouldSeeTheStreetNameAsForTheAddressUsingRestAssuredExtensionV2(String streetName, String addressType) {
        var location = response.getBody().as(Location[].class);

        // filter the address based on the type of addresses
        Address address = location[0].getAddress().stream().filter(x -> x.getType().equalsIgnoreCase(addressType))
                .findFirst().orElse(null);
        assertThat(address.getStreet(),equalTo(streetName));
    }

// using LOMBOK
    @Given("I perform authorization operation for {string} with body using Lombok")
    public void iPerformAuthorizationOperationForWithBodyUsingLombok(String uri, DataTable table) {
        List<List<String>> cells = table.cells();
//        Map<String, String> body = new HashMap<>();
//        body.put("email", cells.get(1).get(0));
//        body.put("password", cells.get(1).get(1));

        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(cells.get(1).get(0));
        loginBody.setPassword(cells.get(1).get(1));

        // response = RestAssuredExtension.PostOpsWithBody(url, body);

        RestAssuredExtensionV2 restAssuredExtensionV2 = new RestAssuredExtensionV2(uri, APIConstants.ApiMethods.POST,null);
        token = restAssuredExtensionV2.Authenticate(loginBody);

    }

    @When("I perform GET operation for with token {string} using Lombok")
    public void iPerformGETOperationForWithTokenUsingLombok(String uri) {
//        List<List<String>> cells = table.cells();
//        Map<String, String> queryParams = new HashMap<>();
//        queryParams.put("id", cells.get(1).get(0));

//        // response
//        response = RestAssuredExtension.GetWithQueryParamWithToken(url, queryParams, response.getBody().jsonPath().get("access_token"));

        //  using RestAssuredExtension V2

        RestAssuredExtensionV2 restAssuredExtensionV2 = new RestAssuredExtensionV2(uri, "GET", token );
       // response = restAssuredExtensionV2.ExecuteWithQueryParams(queryParams);



    }

    @Then("I should see the title as {string} using Lombok")
    public void iShouldSeeTheTitleAsUsingLombok(String titleName) {
        //method 1
//        assertThat(response.getBody().jsonPath().get("title"), hasItem("a title"));

//        // Method 2 - Deserialization of JSON Objects to POJO Class
        var posts = response.getBody().as(Posts.class);
        assertThat(posts.getTitle(),equalTo(titleName));

        // Method 3 - using with Lombok
//        Posts posts = Posts.builder().build();
//        Posts post = response.getBody().as(posts.getClass());
//        assertThat(post.getTitle(),equalTo(titleName));

        // Method 3 - using with Lombok again reduce the code
        Posts post = response.getBody().as(Posts.class);
        assertThat(post.getTitle(),equalTo(titleName));





    }
}
