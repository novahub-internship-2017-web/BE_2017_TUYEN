package office;

import java.util.Scanner;

public class StaffManagement {

  private static Scanner input;

  public static void menuMain() {
    System.out.println("1.Add new staff");
    System.out.println("2.Edit staff");
    System.out.println("3.Delete staff");
    System.out.println("4.View list staff");
    System.out.println("5.Exit");
  }

  public static void menu1() {
    System.out.println("1.Add staff to the end of the list");
    System.out.println("2.Add staff to the begin of the list");
    System.out.println("3.Add staff to the list in any position");
    System.out.println("4.Return main menu");
  }

  public static void main(String[] args) {
    boolean check = true, continueAddStaff = false;
    int countine;
    int checkSelectFunction = 0;
    int selectTypeStaff = 0, checkSelectFunctionAddStaff = 0;
    input = new Scanner(System.in);
    do {
      menuMain();
      try {
        System.out.print("Select function: ");
        checkSelectFunction = Integer.parseInt(input.next());

        if ((checkSelectFunction < 1) || (checkSelectFunction > 5)) {
          check = true;
          System.out.println("No function!");
        } else {
          check = false;
        }
      } catch (NumberFormatException e) {
        System.out.println("No function!");
      }
    } while (check);

    switch (checkSelectFunction) {
    case 1:
      // Add new staff
      // selectAddStaff
      check = true;
      do {
        menu1();
        try {
          checkSelectFunctionAddStaff = Integer.parseInt(input.next());

          if ((checkSelectFunctionAddStaff < 1) || (checkSelectFunctionAddStaff > 4)) {
            check = true;
            System.out.println("No functinon!");
          } else {
            check = false;
          }
        } catch (NumberFormatException e) {
          System.out.println("No functinon!");
        }
      } while (check);

      switch (checkSelectFunctionAddStaff) {
      case 1:
        // selectTypeStaff
        do {
          check = true;
          do {
            System.out.println("1. Teacher");
            System.out.println("2. Employees");
            System.out.print("Select type Staff: ");
            try {
              selectTypeStaff = Integer.parseInt(input.next());
              if ((selectTypeStaff < 1) || (selectTypeStaff > 2)) {
                check = true;
                System.out.println("No functinon!");
              } else {
                check = false;
              }
            } catch (NumberFormatException e) {
              System.out.println("No functinon!");
            }
          } while (check);
          if (selectTypeStaff == 1) {
            // add teacher
            
          } else {
            // add employees
          }
          
          System.out.print("Coutinue add Staff? ");
          
          if(continueAddStaff) {
            
          }

        } while (continueAddStaff);

        break;
      case 2:
        break;
      case 3:
        break;

      default:
        break;
      }

      break;
    case 2:
      break;
    case 3:
      break;
    case 4:
      break;
    default:
      break;
    }
    System.out.println();

  }

}
