package id.my.hendisantika.vertx_post_service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : vertx-post-service
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/01/25
 * Time: 06.14
 * To change this template use File | Settings | File Templates.
 */
public class Post {
  UUID id;
  String title;
  String content;
  LocalDateTime createdAt;

  public static Post of(String title, String content) {
    return of(null, title, content, null);
  }

  public static Post of(UUID id, String title, String content, LocalDateTime createdAt) {
    Post data = new Post();
    data.setId(id);
    data.setTitle(title);
    data.setContent(content);
    data.setCreatedAt(createdAt);
    return data;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "Post{" +
      "id='" + id + '\'' +
      ", title='" + title + '\'' +
      ", content='" + content + '\'' +
      ", createdAt=" + createdAt +
      '}';
  }
}
