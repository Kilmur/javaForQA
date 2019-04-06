package jqa.maxim.starikov.mantis.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

  @Id
  private int id;

  private String username;

  private String email;

  @Override
  public String toString() {
    return "UserData{" +
      "id=" + id +
      ", username='" + username + '\'' +
      ", email='" + email + '\'' +
      '}';
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public int getId() {
    return id;
  }

  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public String getEmail() {
    return email;
  }


}
