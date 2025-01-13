package id.my.hendisantika.vertx_post_service;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.http.HttpClient;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(VertxExtension.class)
public class TestMainVerticle {
  private final static Logger LOGGER = Logger.getLogger(TestMainVerticle.class.getName());
  HttpClient client;


  @BeforeEach
  void setup(Vertx vertx, VertxTestContext testContext) {
    vertx.deployVerticle(new MainVerticle(), testContext.succeeding(id -> testContext.completeNow()));
    var options = new HttpClientOptions()
      .setDefaultPort(8888);
    this.client = vertx.createHttpClient(options);
  }

  // Repeat this test 3 times
  @RepeatedTest(3)
  @Timeout(value = 10, timeUnit = TimeUnit.SECONDS)
  @DisplayName("Check the HTTP response...")
  void testHello(Vertx vertx, VertxTestContext testContext) {
    client.request(HttpMethod.GET, "/hello")
      .compose(req -> req.send().compose(HttpClientResponse::body))
      .onComplete(
        testContext.succeeding(
          buffer -> testContext.verify(
            () -> {
              assertThat(buffer.toString()).isEqualTo("Hello from my route");
              testContext.completeNow();
            }
          )
        )
      );
  }

  @AfterEach
  @DisplayName("Check that the verticle is still there")
  void lastChecks(Vertx vertx) {
    assertThat(vertx.deploymentIDs())
      .isNotEmpty()
      .hasSize(1);
  }

  @Test
  void verticle_deployed(Vertx vertx, VertxTestContext testContext) throws Throwable {
    testContext.completeNow();
  }
}
