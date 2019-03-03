package jqa.maxim.starikov.addressbook.models;

import java.util.Objects;

public class ContactData {
  private final String name;
  private final String lastname;
  private final String address;
  private final String email;
  private final String phone;

  public ContactData(String name, String lastname, String address, String email, String phone) {
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.phone = phone;
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
      "name='" + name + '\'' +
      ", lastname='" + lastname + '\'' +
      ", address='" + address + '\'' +
      ", email='" + email + '\'' +
      ", phone='" + phone + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(name, that.name) &&
      Objects.equals(lastname, that.lastname) &&
      Objects.equals(address, that.address) &&
      Objects.equals(email, that.email) &&
      Objects.equals(phone, that.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, lastname, address, email, phone);
  }
}
