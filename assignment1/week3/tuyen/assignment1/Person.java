package week3.tuyen.assignment1;

import java.util.Scanner;

import week3.tuyen.define.Define;

public class Person {
  String    name;
  int       yearOfBirth;
  String    address;
  String    career;

  public Person() {
    super();
  }

  public Person(String name, int yearOfBirth, String address, String career) {
    super();
    this.name = name;
    this.yearOfBirth = yearOfBirth;
    this.address = address;
    this.career = career;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCareer() {
    return career;
  }

  public void setCareer(String career) {
    this.career = career;
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

  public void show() {
    System.out.printf("%25s\t %8s\t %25s\t %10s\t ", name, yearOfBirth, address, career);
  }

  @SuppressWarnings("resource")
  public Person inputData() {
    boolean check = true;
    Scanner input = new Scanner(System.in);
    System.out.print("Name: ");
    name = input.nextLine();
    do {
      try {
        System.out.print("Year Of Birth: ");
        yearOfBirth = Integer.parseInt(input.nextLine());
        if (yearOfBirth < 1 || yearOfBirth > 9999) {
          throw new IllegalAccessException();
        } else {
          check = false;
        }
      } catch (Exception e) {
        System.out.println("Error input!");
      }
    } while (check);
    System.out.print("Address: ");
    address = input.nextLine();
    return new Person(name, yearOfBirth, address, career);
  }

  public double salaryStaff() {
    return Define.MANAGER;
  }

}
