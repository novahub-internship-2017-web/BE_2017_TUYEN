package week3.tuyen.assignment1;

import java.util.Comparator;

public class NameComparator implements Comparator<Person> {

  @Override
  public int compare(Person obj1, Person obj2) {
    return obj1.getName().compareTo(obj2.getName());
  }

}
