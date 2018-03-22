package week3.tuyen.assignment1;

import java.util.Scanner;

import week3.tuyen.define.Define;

public class Employees extends Person {
  String        department;
  int           numberWorkDay;
  float         coefficientsSalary;
  String        position;
  static String job = "Employees";

  public Employees() {
    super();
    super.setCareer(job);
  }

  public Employees(String department, int numberWorkDay, float coefficientsSalary, String position) {
    super();
    this.department = department;
    this.numberWorkDay = numberWorkDay;
    this.coefficientsSalary = coefficientsSalary;
    this.position = position;
  }

  public Employees(String name, int yearOfBirth, String address, String department, int numberWorkDay,
      float coefficientsSalary, String position) {
    super(name, yearOfBirth, address, job);
    this.department = department;
    this.numberWorkDay = numberWorkDay;
    this.coefficientsSalary = coefficientsSalary;
    this.position = position;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public int getNumberWorkDay() {
    return numberWorkDay;
  }

  public void setNumberWorkDay(int numberWorkDay) {
    this.numberWorkDay = numberWorkDay;
  }

  public float getCoefficientsSalary() {
    return coefficientsSalary;
  }

  public void setCoefficientsSalary(float coefficientsSalary) {
    this.coefficientsSalary = coefficientsSalary;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }
@Override
  public double salaryStaff() {
    return getCoefficientsSalary() * 730 + allowance() + getNumberWorkDay() * 30;
  }

  // @Override
  public double allowance() {
    if (this.position.equals("Manager")) {
      return Define.MANAGER;
    }
    if (this.position.equals("Deputy Department")) {
      return Define.DEPUTYDEPARTMENT;
    } else {
      return Define.EMPLOYEES;
    }

  }

  @Override
  public void show() {
    super.show();
    System.out.printf("%15s\t %15s\t %10s\t %5s\t %5s\t", department, position, allowance(), numberWorkDay,
        coefficientsSalary);
  }

  @Override
  public Employees inputData() {
    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);
    String msgError = "Error input!";
    super.inputData();
    System.out.print("Department: ");
    department = input.nextLine();
    boolean check = true;
    int choose = 0;

    check = true;
    do {
      try {
        System.out.print("Number work day: ");
        numberWorkDay = Integer.parseInt(input.nextLine());
        if (numberWorkDay < 0 || numberWorkDay > 30) {
          System.out.println(msgError);
          check = true;
        } else {
          check = false;
        }
      } catch (Exception e) {
        System.out.println(msgError);
      }

    } while (check);

    check = true;
    do {
      try {
        System.out.print("Coefficients Salary: ");
        coefficientsSalary = Float.parseFloat(input.nextLine());
        if (coefficientsSalary < 0) {
          System.out.println(msgError);
          check = true;
        } else
          check = false;
      } catch (Exception e) {
        System.out.println(msgError);
      }
    } while (check);

    check = true;
    do {
      try {
        System.out.print("1. Manager\t2. Deputy Department\t3. Employees\nPosition: ");
        choose = Integer.parseInt(input.nextLine());
        if (choose < 1 || choose > 3) {
          System.out.println(msgError);
          check = true;
        } else
          check = false;
      } catch (Exception e) {
        System.out.println(msgError);
      }

    } while (check);

    switch (choose) {
    case 1:
      position = "Manager";
      break;
    case 2:
      position = "Deputy Department";
      break;
    default:
      position = "Employees";
      break;
    }
    return new Employees(name, yearOfBirth, address, department, numberWorkDay, coefficientsSalary, position);
  }

}
