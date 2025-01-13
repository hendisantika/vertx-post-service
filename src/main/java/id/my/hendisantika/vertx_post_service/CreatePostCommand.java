package id.my.hendisantika.vertx_post_service;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * Project : vertx-post-service
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/01/25
 * Time: 06.12
 * To change this template use File | Settings | File Templates.
 */
public class CreatePostCommand implements Serializable {

  String title;
  String content;

  public static CreatePostCommand of(String title, String content) {
    CreatePostCommand post = new CreatePostCommand();
    post.setTitle(title);
    post.setContent(content);

    return post;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CreatePostCommand postForm = (CreatePostCommand) o;
    return title.equals(postForm.title) &&
      content.equals(postForm.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, content);
  }

  @Override
  public String toString() {
    return "PostForm{" +
      "title='" + title + '\'' +
      ", content='" + content + '\'' +
      '}';
  }
}
