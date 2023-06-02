package sdbms;
import java.util.Scanner;

import InvalidChoice.InvalidChoiceException;
public class Solution {
	public static void main(String[] args) {
		System.out.println("Welcome to Student Database Management System");
		System.out.println("---------------------------------------------");
		Scanner scan=new Scanner(System.in);
		//Upcasting-->to achieve Abstraction
		StudentManagementSystem sms=new  StudentManagementImpl();
		while(true) {
		System.out.println("1:addStudent\n2:displayStudent\n3:displayAllStudents "
		+ "\n4:removeStudent\n5:removeAllStudents\n6:updateStudent\n7:countStudents\n8:sortStudents "
		+ "\n9:findStudentWithHighestMarks\n10:findStudentWithLowestMarks\n11:Exit\nEnter the choice");
		int choice=scan.nextInt();
		switch(choice) {
		case 1:
		sms.addStudent();
		break;
		case 2:
		sms.displayStudent();
		break;
		case 3:
		sms.displayAllStudents();
		break;
		case 4:
		sms.removeStudent();
		break;
		case 5:
		sms.removeAllStudents();
		break;
		case 6:
		sms.updateStudent();
		break;
		case 7:
		sms.countStudents();
		break;
		case 8:
		sms.sortStudents();
		break;
		case 9:
		sms.findStudentWithHighestMarks();
		break;
		case 10:
		sms.findStudentWithLowestMarks();
		break;
		case 11:
		System.out.println("Thank you!");
		System.exit(0);
		break;
		default:
		try {
			String message="Invalid choice,kindly entre valid choice";
		 throw new InvalidChoiceException(message);
		}
		catch(InvalidChoiceException e)
		{
			System.out.println(e.getMessage());
		}
		}
		System.out.println("------------------------------------");
		}
		}


}
