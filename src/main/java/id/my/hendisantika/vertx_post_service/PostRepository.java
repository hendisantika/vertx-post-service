package id.my.hendisantika.vertx_post_service;

import io.vertx.sqlclient.Row;

import java.util.function.Function;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * Project : vertx-post-service
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/01/25
 * Time: 06.15
 * To change this template use File | Settings | File Templates.
 */
public class PostRepository {
  private static final Logger LOGGER = Logger.getLogger(PostRepository.class.getName());

  private static final Function<Row, Post> MAPPER = (row) ->
    Post.of(
      row.getUUID("id"),
      row.getString("title"),
      row.getString("content"),
      row.getLocalDateTime("created_at")
    );

}
