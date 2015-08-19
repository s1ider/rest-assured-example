package FreeGeoIP;

import com.jayway.restassured.RestAssured;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;


public class GetInfoByIP {
    String IP = "173.252.110.27";
    String baseURI = "http://freegeoip.net/";

    @Test
    public void testIpWithJSON() {
        String FORMAT = "json";
        RestAssured.baseURI = baseURI + FORMAT;

        when().
            get("ip/" + IP).
        then().
            body("country_code", equalTo("US")).
            body("country_name", equalTo("United States"));
    }

    @Test
    public void testIpWithXML() {
        String FORMAT = "xml";
        RestAssured.baseURI = baseURI + FORMAT;
        when().
            get("ip/" + IP).
        then().
            body("Response.CountryCode", equalTo("US")).
            body("Response.CountryName", equalTo("United States"));
    }

}
