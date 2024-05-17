package steps;

import Utilities.RestAssuredExtension;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.core.IsNot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.*;

public class POSTProfileSteps {

    RestAssuredExtension restAssuredExtension = new RestAssuredExtension();

    private static ResponseOptions<Response> response;

    @Given("I perform POST operation for {string} with body")
    public void iPerformPOSTOperationForWithBody(String url, DataTable table)  {

        //Set Body
        HashMap<String, String> body = new HashMap<>();
        body.put("name", table.cell(1,0));

        //Set PathParam
        HashMap<String, String> pathParam = new HashMap<>();
        pathParam.put("profileNo", table.cell(1,1));

        //perform post operation
        response = restAssuredExtension.PostOpsWithBodyAndPathParams(url,pathParam, body);

    }

    @Then("I should see the body has title as {string}")
    public void iShouldSeeTheBodyHasNameAs(String name) {
        assertThat(response.getBody().jsonPath().get("name"), hasItem(name));

    }

    // DELETE OPERATION
    @Given("I ensure to perform POST operation for {string} with the body as")
    public void iEnsureToPerformPOSTOperationForWithTheBodyAs(String url, DataTable table) {

        List<List<String>> cells = table.cells();

        Map<String, String> body = new HashMap<>();
        body.put("id", cells.get(1).get(0));
        body.put("title", cells.get(1).get(1));
        body.put("views", cells.get(1).get(2));

        // Perform post operation

        RestAssuredExtension.PostOpsWithBody(url, body);

    }

    @And("I perform DELETE Operation for {string}")
    public void iPerformDELETEOperationFor(String url, DataTable table) {

        List<List<String>> cells = table.cells();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", cells.get(1).get(0));
        RestAssuredExtension.DeleteOpsWithPathParam(url, pathParams);
    }

    @And("I perform GET Operation with path param for {string}")
    public void iPerformGETOperationWithPathParamFor(String url, DataTable table) {

        List<List<String>> cells = table.cells();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", cells.get(1).get(0));

        response = RestAssuredExtension.GetWithPathParam(url, pathParams);

    }

    @Then("I {string} see the body with the title as {string}")
    public void iShouldNotSeeTheBodyWithTheTitleAs(String condition, String title) {
    if(condition.equalsIgnoreCase("should not")){
        assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
    }else {
        assertThat(response.getBody().jsonPath().get("title"), is(title));
    }


    }

    @And("I perform PUT Operation for {string}")
    public void iPerformPUTOperationFor(String url, DataTable table) {
        List<List<String>> cells = table.cells();

        Map<String, String> body = new HashMap<>();
        body.put("id", cells.get(1).get(0));
        body.put("title", cells.get(1).get(1));
        body.put("views", cells.get(1).get(2));

        List<List<String>> cells1 = table.cells();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", cells1.get(1).get(0));

        // Perform PUT operation
        RestAssuredExtension.PUTOpsWithBodyAndPathParams(url, body, pathParams);

    }

//    @Then("I should see the body with the title as {string}")
//    public void iShouldSeeTheBodyWithTheTitleAs(String title) {
//        assertThat(response.getBody().jsonPath().get("title"), is(title));
//    }
}
