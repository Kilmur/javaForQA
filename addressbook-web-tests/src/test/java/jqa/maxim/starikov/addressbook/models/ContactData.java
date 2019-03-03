package jqa.maxim.starikov.addressbook.models;

import java.util.Objects;

public class ContactData {
  private final String id;
  private final String name;
  private final String lastname;
  private final String address;
  private final String email;
  private final String phone;

  public ContactData(String name, String lastname, String address, String email, String phone) {
    this.id = null;
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.phone = phone;
  }

  public ContactData(String id, String name, String lastname, String address, String email, String phone) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.phone = phone;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  @Override
  public String toString() {
    return "ContactData{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", lastname='" + lastname + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(id, that.id) &&
      Objects.equals(name, that.name) &&
      Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname);
  }
}
