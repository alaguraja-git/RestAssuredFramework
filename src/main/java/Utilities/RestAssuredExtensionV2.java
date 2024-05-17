package Utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredExtensionV2 {


    private RequestSpecBuilder builder = new RequestSpecBuilder();
    private String method;
    private String url;

    private static RequestSpecification Request;

    /**
     * RestAssuredExtensionV2 constructor method to call the initial settings for the following method
     * @param uri
     * @param method
     * @param token
     */
    public RestAssuredExtensionV2(String uri, String method, String token){

    // formulate the API url
        this.url = "http://localhost:3000" + uri;
        this.method=method;

        if(token!=null){
            builder.addHeader("Authorization", "Bearer " + token);
        }
    }

    /**
     * ExecuteAPI to execute the API for GET/POST/DELETE
     * @return ResponseOptions<Response>
     */

    public ResponseOptions<Response> ExecuteAPI(){
        RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpecification);

        if(this.method.equalsIgnoreCase("POST")){
            return request.post(this.url);
        } else if (this.method.equalsIgnoreCase("DELETE")){
            return request.delete(this.url);
        } else if (this.method.equalsIgnoreCase("GET")){
            return request.get(this.url);
        }
        return null;
    }

    /**
     * Authenticate to get the token variable
     * @param body
     * @return string token
     */
    public String Authenticate(Object body){
        builder.setBody(body);
        return ExecuteAPI().getBody().jsonPath().get("access_token");
    }

    /**
     * Execute API with query params being passed as the input of ot
     * @param queryPath
     * @return Response
     */
    public ResponseOptions<Response> ExecuteWithQueryParams(Map<String, String> queryPath) {
    builder.addQueryParams(queryPath);
    return  ExecuteAPI();
    }


    /**
     * ExecuteWithPathParams with path params being passed as the input of it
     * @param pathparam
     * @return Response
     */
    public ResponseOptions<Response> ExecuteWithPathParams(Map<String, String> pathparam) {
        builder.addPathParams(pathparam);
        return  ExecuteAPI();
    }


    /**
     * ExecuteWithPathParamsAndBody with path params and Body being passed as the input of it
     * @param pathparam
     * @return Response
     */
    public ResponseOptions<Response> ExecuteWithPathParamsAndBody(Map<String, String> pathparam, Map<String, String> body) {
        builder.setBody(body);
        builder.addPathParams(pathparam);
        return  ExecuteAPI();
    }

}
