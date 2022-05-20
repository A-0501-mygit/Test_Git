package testCases;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class tcChargingGroups
{
    @Test
    public void test_addNewgroups() {
        JSONObject grp=new JSONObject();
        grp.put("name","Anjana_Group_1");
        grp.put("amps",1.2);
    System.out.print("whatever"  +grp);
        //String current_Body=grp.toString();
        //System.out.print("whatever"  +current_Body);

        Response res =

                given()
                        .contentType("application/json")
                        .body(grp.toString())
                        .when()
                        .post("https://gfx-smartcharging-assignment-api.azurewebsites.net/Groups")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains("\t\n" +
               "Return the changeGroup created"),false);
        System.out.print("end");

    }
    }









//    given()
//                .when()
//                .post("https://gfx-smartcharging-assignment-api.azurewebsites.net/Groups")
//
//                .then()
//                .statusCode(200);

