package FreeGeoIP;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class GetInfoByHostname {
    String IP = "goit.com.ua";
    String baseURI = "http://freegeoip.net/";

    @Test
    public void testIpWithJSON() {
        String FORMAT = "json";
        RestAssured.baseURI = baseURI + FORMAT;

        when().
                get("ip/" + IP).
        then().
                body("country_code", equalTo("UA")).
                body("country_name", equalTo("Ukraine"));
    }

    @Test
    public void testIpWithXML() {
        String FORMAT = "xml";
        RestAssured.baseURI = baseURI + FORMAT;

        when().
                get("ip/" + IP).
        then().
                body("Response.CountryCode", equalTo("UA")).
                body("Response.CountryName", equalTo("Ukraine"));
    }

}
