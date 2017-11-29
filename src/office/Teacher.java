package office;

public class Teacher extends Person {
  String faculty;
  String degree;
  double allowance;
  int    numberLessonsInMoth;
  float  coefficientsSalary;

  public Teacher() {
    super();
  }

  public Teacher(String name, int yearOfBirth, String address) {
    super(name, yearOfBirth, address);
  }

  public Teacher(String name, int yearOfBirth, String address, String faculty, String degree, double allowance,
      int numberLessonsInMoth, float coefficientsSalary) {
    super(name, yearOfBirth, address);
    this.faculty = faculty;
    this.degree = degree;
    this.allowance = allowance;
    this.numberLessonsInMoth = numberLessonsInMoth;
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

  public double getAllowance() {
    return allowance;
  }

  public void setAllowance(double allowance) {
    this.allowance = allowance;
  }

  public int getNumberLessonsInMoth() {
    return numberLessonsInMoth;
  }

  public void setNumberLessonsInMoth(int numberLessonsInMoth) {
    this.numberLessonsInMoth = numberLessonsInMoth;
  }

  public float getCoefficientsSalary() {
    return coefficientsSalary;
  }

  public void setCoefficientsSalary(float coefficientsSalary) {
    this.coefficientsSalary = coefficientsSalary;
  }

//  @Override
//  public String toString() {
//    return "Teacher [faculty=" + faculty + ", degree=" + degree + ", allowance=" + allowance + ", numberLessonsInMoth="
//        + numberLessonsInMoth + ", coefficientsSalary=" + coefficientsSalary + "]";
//  }
  
  
  

}
