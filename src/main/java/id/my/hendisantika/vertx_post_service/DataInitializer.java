package id.my.hendisantika.vertx_post_service;

import io.vertx.sqlclient.Pool;

import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * Project : vertx-post-service
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/01/25
 * Time: 06.13
 * To change this template use File | Settings | File Templates.
 */
public class DataInitializer {
  private final static Logger LOGGER = Logger.getLogger(DataInitializer.class.getName());

  private final Pool client;

  public DataInitializer(Pool client) {
    this.client = client;
  }

  public static DataInitializer create(Pool client) {
    return new DataInitializer(client);
  }
}
