package testCases;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class MyStepdefs {
    String jsonString;
    @Given("^Send a request to get the group details with (.*) and (.*)$")
    public void sendARequestToGetTheGroupDetails(String name,String amps) {
        JSONObject grp=new JSONObject();
        grp.put("name",name);
        grp.put("amps",amps);
        System.out.print("Print group values"  +grp);


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

         jsonString = res.asString();
        Assert.assertEquals(jsonString.contains("\t\n" +
                "Return the changeGroup created"),false);
        System.out.print("end");
    }

    @Then("^Response should be ok$")
    public void responseShouldBeOk() {
        Assert.assertEquals(jsonString.contains("\t\n" +
                "Return the changeGroup created"),false);
        System.out.print("end");
    }

    @Given("^Send request to create Connector with (.*),(.*),(.*) and (.*)$")
    public void sendRequestToCreateConnectorWithNameAmpsStationNameAndConnectorAnmps(String name,Double amps,String SName,Double CAmps) {
        /*create group*/
        JSONObject grp=new JSONObject();
        grp.put("name",name);
        grp.put("amps",amps);
        Response res_grp =

                given()
                        .contentType("application/json")
                        .body(grp.toString())
                        .when()
                        .post("https://gfx-smartcharging-assignment-api.azurewebsites.net/Groups")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

        jsonString = res_grp.asString();
        Assert.assertEquals(jsonString.contains("\t\n" +
                "Return the changeGroup created"),false);
        JsonPath jsonPathEvaluator = res_grp.jsonPath();
        String grp_id = jsonPathEvaluator.get("id");
        System.out.println("Group id"+grp_id);

        /*Create charge station*/


        JSONObject chrgSt=new JSONObject();
        chrgSt.put("name",SName);
        Response res_chargeStation =

                given()
                        .contentType("application/json")
                        .body(chrgSt.toString())
                        .when()
                        .post("https://gfx-smartcharging-assignment-api.azurewebsites.net/Groups/"+grp_id+"/ChargeStations")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

        jsonString = res_chargeStation.asString();
        System.out.println("ChargeStation response"+res_chargeStation.getStatusLine());
        Assert.assertEquals(jsonString.contains("\t\n" +
                "Return the Charge station created"),false);
         jsonPathEvaluator = res_chargeStation.jsonPath();
        String charge_stn_id = jsonPathEvaluator.get("id");
        System.out.println("Charge Station id"+charge_stn_id);

            JSONObject connector=new JSONObject();
            connector.put("amps",CAmps.toString());
            Response res_connector =

                    given()
                            .contentType("application/json")
                            .body(connector.toString())
                            .when()
                            .post("https://gfx-smartcharging-assignment-api.azurewebsites.net/ChargeStation/"+charge_stn_id+"/Connectors")
                            .then()
                            .statusCode(200)
                            .log().body()
                            .extract().response();

            jsonString = res_connector.asString();
            System.out.println("ChargeStation response"+res_connector.getStatusLine());
            Assert.assertEquals(jsonString.contains("\t\n" +
                    "Return the Connector created"),false);
            jsonPathEvaluator = res_connector.jsonPath();
            int connector_id = jsonPathEvaluator.get("id");
            System.out.println("Connector id"+connector_id);

        /*Delete Group id for cleanup*/

        Response del_grp =

                given()
                        .contentType("application/json")
                        .when()
                        .delete("https://gfx-smartcharging-assignment-api.azurewebsites.net/Groups/"+grp_id)
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

        jsonString = del_grp.asString();
        System.out.println("Grp id deleted"+del_grp.getStatusLine());
        Assert.assertEquals(jsonString.contains("\t\n" +
                "Return the Connector created"),false);




    }

    @Then("Corresponding Connector, charge station and group is created")
    public void correspondingConnectorChargeStationAndGroupIsCreated() {
    }
}
