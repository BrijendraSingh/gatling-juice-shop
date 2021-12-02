package juiceworkshop;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;


public class JuiceShopLocalHostTest extends Simulation{
    HttpProtocolBuilder httpProtocol = http
        .baseUrl("http://127.0.0.1:3000/")
        .header("Accept", "application/json");
    
    ScenarioBuilder scn = scenario("My first Test")
        
    .exec(http("Get all Security Questions")
        .get("/api/SecurityQuestions/"))  
        .pause(5)

    .exec(http("Fetch All Quantitys")
        .get("api/Quantitys"))
        .pause(5)

    .exec(http("Product Search")
        .get("/rest/products/search"))
        .pause(5);    

        {
            setUp(
                scn.injectOpen(nothingFor(5),
                rampUsers(10).during(10)
                )
            ).protocols(httpProtocol)
            .maxDuration(60);
        }

}
