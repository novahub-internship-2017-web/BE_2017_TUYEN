package week3.tuyen.assignment1;

import java.util.Scanner;

import week3.tuyen.define.Define;

public class Teacher extends Person {
  String        faculty;
  String        degree;
  int           numberLessonsInMonth;
  float         coefficientsSalary;
  static String job = "Teacher";

  public Teacher() {
    super();
    super.setCareer(job);
  }

  public Teacher(String name, int yearOfBirth, String address, String career) {
    super(name, yearOfBirth, address, career);
  }

  public Teacher(String name, int yearOfBirth, String address, String faculty, String degree, int numberLessonsInMonth,
      float coefficientsSalary) {
    super(name, yearOfBirth, address, job);
    this.faculty = faculty;
    this.degree = degree;
    this.numberLessonsInMonth = numberLessonsInMonth;
    this.coefficientsSalary = coefficientsSalary;
  }

  public String getFaculty() {
    return faculty;
  }

  public void setFaculty(String faculty) {
    this.faculty = faculty;
  }

  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  public int getNumberLessonsInMonth() {
    return numberLessonsInMonth;
  }

  public void setNumberLessonsInMonth(int numberLessonsInMonth) {
    this.numberLessonsInMonth = numberLessonsInMonth;
  }

  public float getCoefficientsSalary() {
    return coefficientsSalary;
  }

  public void setCoefficientsSalary(float coefficientsSalary) {
    this.coefficientsSalary = coefficientsSalary;
  }

  public double salaryTeacher() {
    return getCoefficientsSalary() * 730 + allowance() + getNumberLessonsInMonth() * 45;
  }

  private double allowance() {
    if (this.degree.equals("Bachelor")) {
      return Define.BACHELOR;
    } else {
      if (this.degree.equals("Masters")) {
        return Define.MASTERS;
      } else {
        return Define.DOCTOR;
      }
    }
  }

  @Override
  public void show() {
    super.show();
    System.out.printf("%15s\t %15s\t %10s\t %5s\t %5s\t", faculty, degree, allowance(),
        numberLessonsInMonth, coefficientsSalary);
  }

  @Override
  public Teacher inputData() {
    String msgError = "Error input!";
    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);
    super.inputData();
    System.out.print("Faculty: ");
    faculty = input.nextLine();
    boolean check = true;
    int choose = 0;
    do {
      try {
        System.out.print("1. Bachelor\t2. Masters\t3. Doctor\nDegree: ");
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
      degree = "Bachelor";
      break;
    case 2:
      degree = "Masters";
      break;
    default:
      degree = "Doctor";
      break;
    }

    check = true;
    do {
      try {
        System.out.print("Number lessons in month: ");
        numberLessonsInMonth = Integer.parseInt(input.nextLine());
        if (numberLessonsInMonth < 0) {
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
    return new Teacher(name, yearOfBirth, address, faculty, degree, numberLessonsInMonth, coefficientsSalary);
  }

}
