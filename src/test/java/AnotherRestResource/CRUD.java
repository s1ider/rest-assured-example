package AnotherRestResource;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.internal.path.json.JSONAssertion;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class CRUD {

    final String baseURI = "http://jsonplaceholder.typicode.com";

    @Before
    public void setUp() {
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.baseURI = baseURI;
    }


    @Test
    public void testCreateNewPost() throws Exception {
        given().
            formParam("title", "foo").
            formParam("body", "bodybar").
            formParam("userId", "1").
        when().
            post("/posts").
        then().
            statusCode(201).
            body("title", equalTo("foo")).body("id", anything());
    }

    @Test
    public void testUpdateExistedPost() throws Exception {
        Response response =
        given().
            formParam("title", "foo").
        when().
            put("/posts/1");

        System.out.print(response.asString());
        response.then().
                body("title", equalTo("foo")).
                statusCode(200);
    }

    @Test
    public void testDeletePost() throws Exception {
        when().
                delete("/posts/2").
        then().
                statusCode(204);
    }

    @Test
    public void testReadComments() throws Exception {
        given().
                param("postId", 1).
        when().
                get("/comments").
        then().
                body(".", hasSize(5));
    }
}
