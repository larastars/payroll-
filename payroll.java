import java.util.Calendar;
import java.util.Scanner;


public class payroll {
	static double payCheck = 0;
	static double addition = 0;
	
	public static void main (String [] args)
	{
		Scanner keyboard = new Scanner (System.in);
		
		System.out.println("Enter (1) for Hourly:");
		System.out.println("Enter (2) for Salaried:");
		System.out.println("Enter (3) for Salaried plus Commission:");
		int type = keyboard.nextInt();
		
		if (type == 1)
		{
			Employee.load();
			Hourly.load();
			Employee.getBonus();
			Hourly.getEarning();
		}
		else if (type == 2)
		{
			Employee.load();
			Salaried.load();
			Employee.getBonus();
			Salaried.getEarning();
		}
		else if (type == 3)
		{
			Employee.load();
			SalariedPlusCommission.load();
			Employee.getBonus();
			SalariedPlusCommission.getEarning();
		}
		else 
		{
			System.out.println("Invalid Entery");
			return;
		}
		
		Employee.Report();
	}
	static class Employee
	{
		private static String name;
		private static String SocialSecurityNumber;
		private static int BirthdayMonth;
		private static int BirthdayWeek;
		
		public static void load()
		{
			Scanner keyboard = new Scanner (System.in);
			System.out.println("Enter name: ");
			name = keyboard.nextLine();
			System.out.println("Enter 9 digits Social Security Number as in (111-11-1111): ");
			SocialSecurityNumber = keyboard.nextLine();
			//checks if the social is 9 digits (11 + the dashes)
			if (SocialSecurityNumber.length() != 11 )
			{
				System.out.println("Invalid Entry, Enter 9 digits social");
				return;
			}
			System.out.println("Enter Birthday Month (1-12): ");
			BirthdayMonth = keyboard.nextInt();
			//check if BirthdayMonth is between 1 and 12
			if ((BirthdayMonth < 1)||(BirthdayMonth > 12))
			{
				System.out.println("Invalid Entery, Enter a number between (1-12)");
				return;
			}
			System.out.println("Enter Birthday Week (1-4): ");
			BirthdayWeek = keyboard.nextInt();
			//check if BirthdayWeek is between 1 and 4
			if ((BirthdayWeek < 1)||(BirthdayWeek > 4))
			{
				System.out.println("Invalid Entery, Enter a number between (1-4)");
				return;
			}
		}
		public static void Report()
		{
			// report method instead of toString method
			System.out.println("");
			System.out.println("****PAYCHECK REPORT****");
			System.out.println("employee: " + name);
			System.out.println("social security number: " + SocialSecurityNumber);
			System.out.println("paycheck " + "$" + payCheck);
			
		}
		public static double getBonus()
		{
			//compare the birth month and the birth week of the employee
			Calendar rightNow = Calendar.getInstance();
			int currentMonth = rightNow.get(Calendar.MONTH) + 1;
			int currentWeek = rightNow.get(Calendar.WEEK_OF_MONTH);
			System.out.println("Current Month : " + currentMonth);
			System.out.println("Current Week : " + currentWeek);
			
			 if ((BirthdayMonth == currentMonth)&&(BirthdayWeek == currentWeek))
					addition = 100; 
			 
			 return addition;
			 
		}
		
	}
	static class Hourly
	{
		private static double hourlyPay;
		private static double hoursWorkedPastWeek;
		
		public static void load ()
		{
			Scanner keyboard = new Scanner (System.in);
			System.out.println("Enter your hourly pay: ");
			hourlyPay = keyboard.nextDouble();
			System.out.println("Enter your hours worked during the past week: ");
			hoursWorkedPastWeek = keyboard.nextDouble();
			
		}
		public static double getEarning ()
		{
		
			if (hoursWorkedPastWeek < 40)
			{
				payCheck = addition + hourlyPay * hoursWorkedPastWeek;
			}
			else
			{
				payCheck = addition + (hourlyPay + (hourlyPay/2)) * hoursWorkedPastWeek;
			}
				
			return payCheck;
			
		}
		
	}
	static class Salaried
	{
		private static double weeklySalary;
		
		public static void load()
		{
			Scanner keyboard = new Scanner (System.in);
			System.out.println("Enter your weekly salary: ");
			weeklySalary = keyboard.nextDouble();
			
		}
		public static double getEarning()
		{
			payCheck = addition + weeklySalary;
			return payCheck;
		}
	}

	static class SalariedPlusCommission 
	{
		public static double SalesPastWeek;
		public static double CommissionRate;
		public static double BaseSalary;
		
		private static void load()
		{
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Enter the salary: " );
			BaseSalary = keyboard.nextDouble();
			System.out.println("Enter the amount of sales during the past week: ");
			SalesPastWeek = keyboard.nextDouble();
			System.out.println("Enter the commission rate: " );
			CommissionRate = keyboard.nextDouble();
			
		}
		private static double getEarning()

		{
			payCheck = addition + BaseSalary + (SalesPastWeek * CommissionRate);
			return payCheck;
		}
	}
}
