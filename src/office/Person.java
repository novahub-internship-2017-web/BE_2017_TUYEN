package office;

public class Person {
  String name;
  int    yearOfBirth;
  String address;

  public Person() {
    super();
  }

  public Person(String name, int yearOfBirth, String address) {
    super();
    this.name = name;
    this.yearOfBirth = yearOfBirth;
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getYearOfBirth() {
    return yearOfBirth;
  }

  public void setYearOfBirth(int yearOfBirth) {
    this.yearOfBirth = yearOfBirth;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

//  @Override
//  public String toString() {
//    return "Person [name=" + name + ", yearOfBirth=" + yearOfBirth + ", address=" + address + "]";
//  }

}
