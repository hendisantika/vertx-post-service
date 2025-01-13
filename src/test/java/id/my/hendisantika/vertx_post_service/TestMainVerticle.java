package id.my.hendisantika.vertx_post_service;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.http.HttpClient;
import java.util.logging.Logger;

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

  @Test
  void verticle_deployed(Vertx vertx, VertxTestContext testContext) throws Throwable {
    testContext.completeNow();
  }
}
