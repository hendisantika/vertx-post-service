package id.my.hendisantika.vertx_post_service;

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
}
