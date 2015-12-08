package netgloo.models;

import org.hibernate.Session;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  @NotNull
  @Size(min = 3, max = 80)
  private String email;
  
  @NotNull
  @Size(min = 2, max = 80)
  private String name;

  @NotNull
  @Size(min = 2, max = 80)
  private String password;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Role role;

  public User() { }

  public User(long id) { 
    this.id = id;
  }

  public User(String email, String name, String password, Role role) {
    this.email = email;
    this.name = name;
    this.password = password;
    this.role = role;
  }

  public enum Role {
    GIAO_VIEN("Giáo viên"), HOC_SINH("Học sinh"), ADMIN("Administrator");

    private String role;

    private Role(String s) {
      role = s;
    }

    public String getText() {
      return role;
    }
  }

  public long getId() {
    return id;
  }

  public void setId(long value) {
    this.id = value;
  }

  @org.hibernate.annotations.Index(name = "email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String value) {
    this.email = value;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

} // class User
