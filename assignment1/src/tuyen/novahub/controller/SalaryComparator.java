package tuyen.novahub.controller;

import java.util.Comparator;

import tuyen.novahub.entities.Person;

public class SalaryComparator implements Comparator<Person> {

  @Override
  public int compare(Person obj1, Person obj2) {
    if (obj1.salaryStaff() == obj2.salaryStaff())
      return 0;
    else if (obj1.salaryStaff() > obj2.salaryStaff())
      return 1;
    else
      return -1;
  }
}
