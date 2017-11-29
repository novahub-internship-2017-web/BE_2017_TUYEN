package office;

public class Employees extends Person {
  String department;
  int    numberWorkDay;
  float  coefficientsSalary;
  double allowance;
  String position;

  public Employees() {
    super();
  }

  public Employees(String name, int yearOfBirth, String address) {
    super(name, yearOfBirth, address);
  }

  public Employees(String department, int numberWorkDay, float coefficientsSalary, double allowance, String position) {
    super();
    this.department = department;
    this.numberWorkDay = numberWorkDay;
    this.coefficientsSalary = coefficientsSalary;
    this.allowance = allowance;
    this.position = position;
  }

  public Employees(String name, int yearOfBirth, String address, String department, int numberWorkDay,
      float coefficientsSalary, double allowance, String position) {
    super(name, yearOfBirth, address);
    this.department = department;
    this.numberWorkDay = numberWorkDay;
    this.coefficientsSalary = coefficientsSalary;
    this.allowance = allowance;
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

  public double getAllowance() {
    return allowance;
  }

  public void setAllowance(double allowance) {
    this.allowance = allowance;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

}
