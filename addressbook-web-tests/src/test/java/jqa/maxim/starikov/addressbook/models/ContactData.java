package jqa.maxim.starikov.addressbook.models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {

  @Id
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String name = "";

  @Expose
  private String lastname = "";

  @Expose
  @Type(type = "text")
  private String address = "";

  @Expose
  @Type(type = "text")
  private String email = "";
  @Type(type = "text")
  private String email2 = "";
  @Type(type = "text")
  private String email3 = "";
  @Column(name = "home")
  @Type(type = "text")
  private String phoneHome = "";

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String phoneMobile = "";
  @Column(name = "work")
  @Type(type = "text")
  private String phoneWork = "";
  @Transient
  private String allPhones;
  @Transient
  private String allEmails;

  @Type(type = "text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
    joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<>();
  

  public int getId() {
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

  public String getPhoneHome() {
    return phoneHome;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withPhoneHome(String phone) {
    this.phoneHome = phone;
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getPhoneMobile() {
    return phoneMobile;
  }

  public ContactData withPhoneMobile(String phoneMobile) {
    this.phoneMobile = phoneMobile;
    return this;
  }

  public String getPhoneWork() {
    return phoneWork;
  }

  public ContactData withPhoneWork(String phoneWork) {
    this.phoneWork = phoneWork;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public File getPhoto() {
    if (photo != null) {
      return new File(photo);
    }
    return null;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactData withGroups(Set<GroupData> groups) {
    this.groups = groups;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", lastname='" + lastname + '\'' +
      ", address='" + address + '\'' +
      ", email='" + email + '\'' +
      ", email2='" + email2 + '\'' +
      ", email3='" + email3 + '\'' +
      ", phoneHome='" + phoneHome + '\'' +
      ", phoneMobile='" + phoneMobile + '\'' +
      ", phoneWork='" + phoneWork + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
      Objects.equals(name, that.name) &&
      Objects.equals(lastname, that.lastname) &&
      Objects.equals(address, that.address) &&
      Objects.equals(email, that.email) &&
      Objects.equals(email2, that.email2) &&
      Objects.equals(email3, that.email3) &&
      Objects.equals(phoneHome, that.phoneHome) &&
      Objects.equals(phoneMobile, that.phoneMobile) &&
      Objects.equals(phoneWork, that.phoneWork);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname, address, email, email2, email3, phoneHome, phoneMobile, phoneWork);
  }
}
