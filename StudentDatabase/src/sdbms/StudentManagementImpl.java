package sdbms;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import customsorting.*;

import InvalidChoice.InvalidChoiceException;
import customException.StudentNotFoundException;

public class StudentManagementImpl implements StudentManagementSystem{

	Scanner scan=new Scanner(System.in);

	Map<String,Student> db=new LinkedHashMap<String,Student>();


	@Override
	public void addStudent() {

		System.out.println("Enter Age");
		int age=scan.nextInt(); //Accepting Age

		System.out.println("Enter Name");
		String name=scan.next();//Accepting Name

		System.out.println("Enter the marks");
		int marks=scan.nextInt(); //Accepting marks


		Student std=new Student(age, name,marks);
		db.put(std.getId(),std);

		System.out.println("Student Record Inserted Successfully");
		System.out.println("Your Student ID is: "+std.getId());

	}
	@Override
	public void displayStudent() {
		System.out.println("Enter Student Id");
		String id=scan.next(); //String id=scan.next().id.toUpperCase();
		id=id.toUpperCase();
		if(db.containsKey(id)) {//id-->key
			Student std=db.get(id);//getting the Student id
			System.out.println("Student Details: ");
			System.out.println("------------------");
			//System.out.println(db.get(id));

			System.out.println("Id: "+std.getId());
			System.out.println("Age: "+std.getAge());
			System.out.println("Name: "+std.getName());
			System.out.println("Marks: "+std.getmarks());
			//printing reference as toString() is Overridden
			//System.out.println(std);
		}
		else
		{
			try {
				String message="Student with the Id "+id+" is not found";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}
	@Override
	public void displayAllStudents() {

		if(db.size()>0) {//if(!isEmpty())
			System.out.println("Student records are as follows:");
			System.out.println("-------------------------------");
			Set<String>keys=db.keySet();
			for(String key:keys) {
				System.out.println(db.get(key));
			}
		}
		else {
			try {
				String message="No Sufficient Student Records to Display";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void removeStudent() {
		System.out.println("Enter Student Id");
		String id=scan.next().toUpperCase();

		if(db.containsKey(id)) {
			System.out.println("Student record found");
			System.out.println(db.get(id));//printing student object
			db.remove(id);
			System.out.println("Student Record deleted successfully");
		}
		else
		{
			try {
				String message="Student with the id "+id+" is not found";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}
	@Override
	public void removeAllStudents() {
		if(db.size()>0){//if(!isEmpty())
			System.out.println("No of Student Records: "+db.size());
			db.clear();
			System.out.println("All Student Records deleted Successfully");
		}
		else
		{
			try {
				String message="No Student Records to delete!";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}
	@Override
	public void updateStudent() {
		System.out.println("Enter Student Id");
		String id=scan.next().toUpperCase();
		if(db.containsKey(id)) {
			System.out.println("Student record found");
			Student std=db.get(id);

			System.out.println("1:Age 2:Name 3:Marks\n Enter your choice ");
			int choice=scan.nextInt();

			switch(choice) {
			case 1:
				System.out.println("Enter age:");
				int age=scan.nextInt();
				std.setAge(age);
				System.out.println("Age updated Successfully");
				break;

			case 2:
				System.out.println("Enter name:");
				String name=scan.next();
				std.setName(name);
				System.out.println("Name updated Successfully");
				break;

			case 3:

				System.out.println("Enter the marks");
				int marks=scan.nextInt();
				std.setMarks(marks);
				System.out.println("Marks updated Successfully");
				break;
			default:
				System.out.println("Invalid choice");
			}
		}else {
			try {
				String message="Student with the id "+id+" is not found";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void countStudents() {

		System.out.println("Number of Student Records: "+db.size());
	}
	@Override
	public void sortStudents() {
		if(!db.isEmpty()) 
		{
			//converting map into set using Keyset()
			Set<String> keys=db.keySet();
			//converting list &objects of Arraylist storing studentsobjects
			List<Student> list=new ArrayList<Student>();
			//Traverse the keys
			for(String key:keys)
			{
				list.add(db.get(key));
			}
			System.out.println("1.sort student by id\n2.sort student by age\n3:sort student hy name\n 4:sort studnet by marks");
			System.out.println("entre the choice ");
			int choice=scan.nextInt();
			switch(choice)
			{
			case 1:
				Collections.sort(list,new  SortStudentById());
				display(list);
				break;
			case 2:
				Collections.sort(list,new  SortStudentByAge());
				display(list);
				break;
			case 3:
				Collections.sort(list,new SortStudentByName());
				display(list);
				break;
			case 4:
				Collections.sort(list,new SortStudentByMarks());
				display(list);
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
		}
		else 
		{
			try {
				String message="No sufficient student object found";
				throw new  StudentNotFoundException(message);
			}
			catch( StudentNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}

	private static void display(List<Student> list)
	{
		for(Student s:list)
		{
			System.out.println(s);
		}
	}
	@Override
	public void findStudentWithHighestMarks() {
		Set<String> keys=db.keySet();
		List<Student> list=new ArrayList<Student>();
		for(String key:keys)
		{
			list.add(db.get(key));
		}
		Collections.sort(list, new SortStudentByMarks());
		System.out.println(list.get(list.size()-1));

	}
	@Override
	public void findStudentWithLowestMarks() {
		Set<String> keys=db.keySet();
		List<Student> list=new ArrayList<Student>();
		for(String key:keys)
		{
			list.add(db.get(key));
		}
		Collections.sort(list, new SortStudentByMarks());
		System.out.println(list.get(0));

	}
}
