package tuyen.novahub.controller;

import java.util.Comparator;

import tuyen.novahub.entities.Person;

public class NameComparator implements Comparator<Person> {

  @Override
  public int compare(Person obj1, Person obj2) {
    return obj1.getName().compareTo(obj2.getName());
  }

}
