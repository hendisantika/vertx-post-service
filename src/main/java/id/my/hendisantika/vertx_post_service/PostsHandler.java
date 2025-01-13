package id.my.hendisantika.vertx_post_service;

import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * Project : vertx-post-service
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/01/25
 * Time: 06.17
 * To change this template use File | Settings | File Templates.
 */
public class PostsHandler {
  private static final Logger LOGGER = Logger.getLogger(PostsHandler.class.getSimpleName());

  PostRepository posts;

  private PostsHandler(PostRepository _posts) {
    this.posts = _posts;
  }

  //factory method
  public static PostsHandler create(PostRepository posts) {
    return new PostsHandler(posts);
  }

  public void all(RoutingContext rc) {
//        var params = rc.queryParams();
//        var q = params.get("q");
//        var limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("q"));
//        var offset = params.get("offset") == null ? 0 : Integer.parseInt(params.get("offset"));
//        LOGGER.log(Level.INFO, " find by keyword: q={0}, limit={1}, offset={2}", new Object[]{q, limit, offset});
    this.posts.findAll()
      .onSuccess(
        data -> rc.response().end(Json.encode(data))
      );
  }
}