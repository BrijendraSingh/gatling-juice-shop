package juiceworkshop;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;


public class JuiceShopDeployedTest extends Simulation{
    HttpProtocolBuilder httpProtocol = http
        .baseUrl("https://juice-shop.herokuapp.com/")
        .header("Accept", "application/json");
    
    ScenarioBuilder scn = scenario("My first Test")
        
    .exec(http("Home Page")
        .get("/"))
        .pause(5)

    .exec(http("Quantitys")
        .get("api/Quantitys"))
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
