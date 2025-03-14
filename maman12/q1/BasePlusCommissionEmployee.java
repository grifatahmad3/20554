// Fig. 10.8: BasePlusCommissionEmployee.java
// BasePlusCommissionEmployee class extends CommissionEmployee.

public class BasePlusCommissionEmployee extends CommissionEmployee {
   private double baseSalary; // base salary per week

   // constructor
   public BasePlusCommissionEmployee(String firstName, String lastName, DateOfBirth dob,
      String socialSecurityNumber, double grossSales, double commissionRate, double baseSalary) throws IllegalArgumentException {
      super(firstName, lastName, dob, socialSecurityNumber,
         grossSales, commissionRate);

      if (baseSalary < 0.0) { // validate baseSalary                  
         throw new IllegalArgumentException("Base salary must be >= 0.0");
      }      
             
      this.baseSalary = baseSalary;                
   }

   // set base salary
   public void setBaseSalary(double baseSalary) {
      if (baseSalary < 0.0) { // validate baseSalary                  
         throw new IllegalArgumentException("Base salary must be >= 0.0");
      }
            
      this.baseSalary = baseSalary;                
   } 

   // return base salary
   public double getBaseSalary() {return baseSalary;}

   // calculate earnings; override method earnings in CommissionEmployee
   @Override                                                            
   public double earnings() {return getBaseSalary() + super.earnings();}

   // return String representation of BasePlusCommissionEmployee object
   @Override                                                           
   public String toString() {                                          
      return String.format("%s %s; %s: $%,.2f",                        
         "base-salaried", super.toString(),                            
         "base salary", getBaseSalary());                              
   }                                                                   
} 



/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
