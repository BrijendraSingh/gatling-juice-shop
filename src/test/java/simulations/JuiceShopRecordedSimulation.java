package simulations;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class JuiceShopRecordedSimulation extends Simulation {

  {
    HttpProtocolBuilder httpProtocol = http
      .baseUrl("https://juice-shop.herokuapp.com")
      .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*detectportal\\.firefox\\.com.*"))
      .acceptHeader("*/*")
      .acceptEncodingHeader("gzip, deflate")
      .acceptLanguageHeader("en-US,en;q=0.5")
      .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:94.0) Gecko/20100101 Firefox/94.0");
    
    Map<CharSequence, String> headers_0 = new HashMap<>();
    headers_0.put("Sec-Fetch-Dest", "empty");
    headers_0.put("Sec-Fetch-Mode", "cors");
    headers_0.put("Sec-Fetch-Site", "same-origin");
    
    Map<CharSequence, String> headers_1 = new HashMap<>();
    headers_1.put("Content-type", "text/plain;charset=UTF-8");
    headers_1.put("Origin", "https://juice-shop.herokuapp.com");
    headers_1.put("Sec-Fetch-Dest", "empty");
    headers_1.put("Sec-Fetch-Mode", "cors");
    headers_1.put("Sec-Fetch-Site", "same-origin");


    ScenarioBuilder scn = scenario("JuiceShopRecordedSimulation")
      .exec(
        http("request_0")
          .get("/socket.io/?EIO=4&transport=polling&t=Nrw8AIE")
          .headers(headers_0)
          .resources(
            http("request_1")
              .post("/socket.io/?EIO=4&transport=polling&t=Nrw8ASt&sid=XPEqNERLFpqaysnYABAk")
              .headers(headers_1)
              .body(RawFileBody("simulations/juiceshoprecordedsimulation/0001_request.html")),
            http("request_2")
              .get("/socket.io/?EIO=4&transport=polling&t=Nrw8ASu&sid=XPEqNERLFpqaysnYABAk")
              .headers(headers_0)
          )
      );

	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
