// Originally copied from Fig. 10.9: PayrollSystemTest.java, with changes to fit the homework in mind
// Employee hierarchy test program.

import java.util.ArrayList;

public class PayrollSystemTest {
   public static void main(String[] args) {

      final int BONUS = 200; //birthday bonus to be added if the birthday month is the same as current month

      // creating array of employees and adding members to it
      ArrayList<Employee> employees = new ArrayList<Employee>();

      // trying and catching exceptions, to show using exceptions.
      try {
         employees.add(new SalariedEmployee("John", "Smith", new DateOfBirth(1911, 1, 11), "111-11-1111", 800.00));
         employees.add(new HourlyEmployee("Karen", "Price", new DateOfBirth(1922, 2, 22), "222-22-2222", 16.75, 40));
         employees.add(new CommissionEmployee("Sue", "Jones", new DateOfBirth(1933, 3, 31), "333-33-3333", 10000, .06));
         employees.add(new BasePlusCommissionEmployee("Bob", "Lewis", new DateOfBirth(1944, 4, 14), "444-44-4444", 5000, .04, 300));
         employees.add(new PieceWorker("Chen", "Ulmer", new DateOfBirth(1988, 8, 18), "555-55-5555", 90, 100));
          }
      catch (IllegalArgumentException e){
         System.out.println(e.getMessage());
          }

      // generically process each element in array employees
      for (Employee currentEmployee : employees) {
         System.out.println(currentEmployee); // invokes toString

         // determine whether element is a BasePlusCommissionEmployee
         if (currentEmployee instanceof BasePlusCommissionEmployee) {
            // downcast Employee reference to
            // BasePlusCommissionEmployee reference
            BasePlusCommissionEmployee employee =
                    (BasePlusCommissionEmployee) currentEmployee;

            employee.setBaseSalary(1.10 * employee.getBaseSalary());

            System.out.printf(
                    "new base salary with 10%% increase is: $%,.2f%n",
                    employee.getBaseSalary());
         }

         int currentMonth = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
         if(currentEmployee.getDob().getMonth()-1 == currentMonth) {
            System.out.printf("earned $%,.2f%n", currentEmployee.earnings());
            System.out.printf("earned a birthday bonus, earned in total $%,.2f%n%n", currentEmployee.earnings()+BONUS);
         }
         else
            System.out.printf("earned $%,.2f%n%n", currentEmployee.earnings());
      }



      // get type name of each object in employees array
      for (int j = 0; j < employees.size(); j++) {
         System.out.printf("Employee[%d] is a %s%n", j,
            employees.get(j).getClass().getName());
      }                                                 
   } 
}