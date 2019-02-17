package jqa.maxim.starikov.addressbook;

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
}
