package week3.tuyen.assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StaffManagement {

  public static void menuMain() {
    System.out.println("1.Add new staff.");
    System.out.println("2.Edit staff.");
    System.out.println("3.Delete staff.");
    System.out.println("4.View list staff.");
    System.out.println("5.Exit.");
  }

  public static void menu1() {
    System.out.println("1.Add staff to the end of the list.");
    System.out.println("2.Add staff to the begin of the list.");
    System.out.println("3.Add staff to the list in any position.");
    System.out.println("4.Return main menu.");
  }

  public static void menu4() {
    System.out.println("1. Show staff list.");
    System.out.println("2. Show list after ascending order according to salary.");
    System.out.println("3. Show list after sorted in alphabetical order according to the name.");
    System.out.println("4. Search staff by name.");
    System.out.println("5. Staff search by year of birth");
    System.out.println("6. Return main menu.");

  }

  public static void headerShow() {
    System.out.printf("%5s\t %25s\t %8s\t %25s\t %10s\t %15s\t %15s\t %10s\t %5s\t %5s\t", "Num", "Name", "Year",
        "Address", "Career", "C1", "C2", "C3", "C4", "C5");
  }

  @SuppressWarnings("resource")
  public static void main(String[] args) {
    Person objPerson = new Person();
    ArrayList<Person> listStaff = new ArrayList<>();
    boolean check = true;
    int continueAddStaff = 0;
    int selectFunctionMain = 0;
    int selectFunctionAdd = 0;
    int selectTypeStaff = 0;
    int continueMain = 1;
    int continueShow = 0;
    int selectFunctionShow = 0;
    String msgFunctionError = "No function!";
    String msgFindError = "Can not find the poisition!";
    String msgSelectFunction = "Select function: ";
    String msgIsEmpty = "No Staff on the list!";
    String showNum = "%5s\t";
    Scanner input;
    input = new Scanner(System.in);
    do {
      do {
        menuMain();
        try {
          System.out.print(msgSelectFunction);
          selectFunctionMain = Integer.parseInt(input.nextLine());

          if ((selectFunctionMain < 1) || (selectFunctionMain > 5)) {
            check = true;
            System.out.println(msgFunctionError);
          } else {
            check = false;
          }
        } catch (NumberFormatException e) {
          System.out.println(msgFunctionError);
        }
      } while (check);

      switch (selectFunctionMain) {
      case 1:
        // Add new staff
        // selectAddStaff
        do {
          check = true;
          do {
            menu1();
            try {
              System.out.print(msgSelectFunction);
              selectFunctionAdd = Integer.parseInt(input.nextLine());
              if ((selectFunctionAdd < 1) || (selectFunctionAdd > 4)) {
                check = true;
                System.out.println(msgFunctionError);
              } else {
                check = false;
              }
            } catch (NumberFormatException e) {
              System.out.println(msgFunctionError);
            }
          } while (check);
          if (selectFunctionAdd != 4) {
            // selectTypeStaff
            check = true;
            do {
              System.out.println("1. Teacher");
              System.out.println("2. Employees");
              System.out.print("Select type Staff: ");
              try {
                selectTypeStaff = Integer.parseInt(input.nextLine());
                if ((selectTypeStaff < 1) || (selectTypeStaff > 2)) {
                  check = true;
                  System.out.println(msgFunctionError);
                } else {
                  check = false;
                }
              } catch (NumberFormatException e) {
                System.out.println(msgFunctionError);
              }
            } while (check);
            if (selectTypeStaff == 1) {
              objPerson = new Teacher();
              objPerson.inputData();
            } else {
              objPerson = new Employees();
              objPerson.inputData();
            }
          }

          switch (selectFunctionAdd) {
          case 1:
            // add Staff to the end of the list
            listStaff.add(objPerson);
            break;
          case 2:
            // add Staff to the begin of the list
            listStaff.add(0, objPerson);
            break;
          case 3:
            check = true;
            int pos = 0;
            do {
              try {
                System.out.print("Enter add position: ");
                pos = Integer.parseInt(input.nextLine());
                if (listStaff.isEmpty()) {
                  if (pos == 1)
                    check = false;
                  else {
                    check = true;
                    System.out.println(msgFindError);
                  }
                } else {
                  if (pos < 1 || pos > listStaff.size()) {
                    check = true;
                    System.out.println(msgFindError);
                  } else {
                    check = false;
                  }
                }
              } catch (Exception e) {
                System.out.println(msgFindError);
              }
            } while (check);

            listStaff.add(pos - 1, objPerson);
            break;

          default:
            // case 4:
            break;
          }
          if (selectFunctionAdd != 4) {
            check = true;
            do {
              try {
                System.out.print("Continue add Staff (Yes = 1, No = 0)? ");
                continueAddStaff = Integer.parseInt(input.nextLine());
                if (continueAddStaff < 0 || continueAddStaff > 1) {
                  check = true;
                  System.out.println(msgFunctionError);
                } else {
                  check = false;
                }

              } catch (Exception e) {
                System.out.println(msgFunctionError);
              }
            } while (check);
          } else {
            continueAddStaff = 0;
          }

        } while (continueAddStaff == 1);
        break;
      case 2:
        // edit Staff
        int posEdit = 0;
        if (listStaff.isEmpty()) {
          System.out.println(msgIsEmpty);
        } else {
          check = true;
          do {
            try {
              System.out.print("Enter edit position:  ");
              posEdit = Integer.parseInt(input.nextLine());
              if ((posEdit < 1) || (posEdit > listStaff.size())) {
                check = true;
                System.out.println(msgFindError);
              } else {
                check = false;
              }

            } catch (Exception e) {
              System.out.println(msgFindError);
            }
          } while (check);
          if (listStaff.get(posEdit - 1).getCareer().equals("Employees")) {
            objPerson = new Employees();
            objPerson.inputData();

          } else {
            if (listStaff.get(posEdit - 1).getCareer().equals("Teacher")) {
              objPerson = new Employees();
              objPerson.inputData();
            }
          }

          listStaff.add(posEdit - 1, objPerson);
          listStaff.remove(posEdit);
        }

        break;
      case 3:
        // delete
        int posDelete = 0;
        if (listStaff.isEmpty()) {
          System.out.println(msgIsEmpty);
        } else {
          check = true;
          do {
            try {
              System.out.print("Enter delete position:  ");
              posDelete = Integer.parseInt(input.nextLine());
              if ((posDelete < 1) || (posDelete > listStaff.size())) {
                check = true;
                System.out.println(msgFindError);
              } else {
                check = false;
              }

            } catch (Exception e) {
              System.out.println(msgFindError);
            }
          } while (check);
          listStaff.remove(posDelete - 1);
        }

        break;
      case 4:
        do {
          check = true;
          do {
            menu4();

            try {
              System.out.print(msgSelectFunction);
              selectFunctionShow = Integer.parseInt(input.nextLine());
              if ((selectFunctionShow < 1) || (selectFunctionShow > 6)) {
                System.out.println(msgFunctionError);
                check = true;
              } else {
                check = false;
              }
            } catch (Exception e) {
              System.out.println(msgFunctionError);
            }
          } while (check);

          switch (selectFunctionShow) {
          case 1:
            // show list
            if (listStaff.isEmpty()) {
              System.out.println(msgIsEmpty);
            } else {
              int i = 0;
              headerShow();
              System.out.println();
              for (Person person : listStaff) {
                System.out.printf(showNum, i++);
                person.show();
                System.out.println();
              }
            }
            break;
          case 2:
            // Show list after ascending order according to salary.
            if (listStaff.isEmpty()) {
              System.out.println(msgIsEmpty);
            } else {
              int i = 0;
              headerShow();
              ArrayList<Person> listSortBySalary = listStaff;
              Collections.sort(listSortBySalary, new SalaryComparator());
              for (Person person : listSortBySalary) {
                System.out.printf(showNum, i++);
                person.show();
                System.out.println();
              }
            }
            break;
          case 3:
            // Show list after sorted in alphabetical order according to the name.
            if (listStaff.isEmpty()) {
              System.out.println(msgIsEmpty);
            } else {
              int i = 0;
              headerShow();
              ArrayList<Person> listSortByName = listStaff;
              Collections.sort(listSortByName, new NameComparator());
              for (Person person : listSortByName) {
                System.out.printf(showNum, i++);
                person.show();
                System.out.println();
              }
            }
            break;
          case 4:
            // Search staff by name.
            ArrayList<Person> listResultSearchByName = new ArrayList<>();
            String nameSearch;
            System.out.print("Enter the name to search: ");
            nameSearch = input.nextLine();
            for (Person person : listStaff) {
              if (person.getName().contains(nameSearch)) {
                listResultSearchByName.add(person);
              }
            }
            if (listResultSearchByName.isEmpty()) {
              System.out.println("Not found!");
            } else {
              int m = 0;
              headerShow();
              System.out.println();
              for (Person person : listResultSearchByName) {
                System.out.printf(showNum, m++);
                person.show();
                System.out.println();
              }
            }
            break;
          case 5:
            // search by year
            ArrayList<Person> listResultSearchByYear = new ArrayList<>();
            int yearSearch = 0;
            check = true;
            do {
              try {
                System.out.print("Enter the year to search: ");
                yearSearch = Integer.parseInt(input.nextLine());
                check = false;
              } catch (Exception e) {
                System.out.println("Input error!");
              }
            } while (check);
            for (Person person : listStaff) {
              if (person.getYearOfBirth() == yearSearch) {
                listResultSearchByYear.add(person);
              }
            }
            if (listResultSearchByYear.isEmpty()) {
              System.out.println("Not found!");
            } else {
              int m = 0;
              headerShow();
              System.out.println();
              for (Person person : listResultSearchByYear) {
                System.out.printf(showNum, m++);
                person.show();
                System.out.println();
              }
            }
            break;
          default:
            break;
          }

          if (selectFunctionShow != 6) {
            check = true;
            do {
              try {
                System.out.print("\nContinue show list? (Yes = 1, No = 0) ");
                continueShow = Integer.parseInt(input.nextLine());
                if (continueShow < 0 || continueShow > 1) {
                  System.out.println(msgFunctionError);
                  check = true;
                } else {
                  check = false;
                }
              } catch (Exception e) {
                System.out.println(msgFunctionError);
              }
            } while (check);

          } else {
            continueShow = 0;
          }
        } while (continueShow == 1);

        break;
      default:
        // case 5: Exit
        System.out.println("Goodbye!!!!");
        System.exit(0);
        break;
      }

    } while (continueMain == 1);
  }

}
