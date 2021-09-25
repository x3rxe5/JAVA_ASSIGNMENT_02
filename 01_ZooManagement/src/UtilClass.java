/*
 * 	AUTHOR : PULKIT SHARMA
 */



import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Scanner;

public class UtilClass {
	
	/*
	 * STATIC VARIABLES INIT. 
	 */
	static Statement stmt;
	static Connection con;
	static Scanner sc = new Scanner(System.in);
	static String sqlStringBefore = "UPDATE zoo_management SET ";
	static String sqlStringAfter = " WHERE id=";
	
	/*
	 *  CONSTRUCTOR
	 */
	
	public UtilClass(Statement st,Connection conn) {
		this.stmt = st;
		this.con = conn;
	}
	
	/*
	  ALL METHODS 
	 */
	
	// menu for showing
	public void menu() {
		try {
			System.out.println("Welcome to the zoo management System");
			System.out.println(" ---------------------------------- ");
			System.out.print("\n");
			System.out.println("[ 1 ] ENTER THE ANIMAL RECORD");
			System.out.println("[ 2 ] DELETE THE RECORD");
			System.out.println("[ 3 ] UPDATE THE ANIMAL RECORD");
			System.out.println("[ 4 ] DISPLAY ALL ANIMAL RECORD");
			System.out.println("[ 5 ] DISPLAY SPECIFIC ANIMAL RECORD");
			System.out.println("[ 6 ] FIND ANIMAL AND COUNT ANIMAL RECORD");
			System.out.println("[ 7 ] DISPLAY SPECIFIC SPECIES RECORD");
			System.out.println("[ 0 ] EXIT THE MENU");
			System.out.print("Enter your Choice =");
			
						
			int choice = sc.nextInt();			
			switch(choice) {
				case 0:
					System.exit(0);
					break;
				case 1:
					insertRecord();
					break;
				case 2:
					deleteRecord();
					break;
				case 3:
					updateRecord();
					break;
				case 4:
					allRecord();
					break;
				case 5:
					specificRecord();
					break;
				case 6:
					displayByAnimalRecord();
					break;
				case 7:
					displayBySpeciesRecord();
					break;
				default:
					System.out.println("You Entered Wrong Input ");
					Thread.sleep(1000);
					System.exit(0);
					break;
			}			
		}catch(Exception e) {
			System.out.print(e);
		}
		
	}

	// Insert Record
	public static void insertRecord() {
		try {
						
			System.out.print("Enter Animal Name/(CAGE_NUMBER) => ");
			String name = sc.next();
			System.out.print("Enter Animal Type => ");
			String animalType = sc.next();
			System.out.print("Enter Species Type => ");
			String speciesType = sc.next();
			System.out.print("Enter Animal age => ");
			int age = sc.nextInt();
			
 
			String sql = "INSERT INTO zoo_management(name,animal_type,species_type,age) VALUES ('" + name + "' , '" + animalType + "' , '" + speciesType + "' , '" + age +"')";
			stmt.executeUpdate(sql);
			System.out.println("[+] RECORD INSERTED SUCCESSFULLY");
		}catch(Exception e) {
			System.out.print(e);
		}
	}
	
	// delete the record
	public static void deleteRecord() {
		try {
			System.out.print("Enter id number to delete => ");
			int idNumber = sc.nextInt();			
			String sql = "DELETE FROM zoo_management WHERE id="+(char)idNumber;
			stmt.executeUpdate(sql);				
			System.out.println("[+] RECORD DELETED SUCCESSFULLY");
		}catch(Exception e) {
			System.out.print(e);
		}
	}

	// Update the record
	public static void updateRecord() {
		try {
			System.out.println("Which Field do you want to update ? ");
			System.out.println("1) Animal Name");
			System.out.println("2) Animal type");
			System.out.println("3) Animal Species");
			System.out.println("4) Animal Age");
			System.out.print("Please Enter a choice => ");
			int choice = sc.nextInt();
			System.out.print("Please Provide the update value to change => ");
			String change = sc.next();
			System.out.print("Please provide the student number => ");
			int idNumber = sc.nextInt();
			String sql = new String();
			
			// CHOICE SELECTION
			if(choice == 1) {
				sql = sqlStringBefore+"name="+change+sqlStringAfter+(char)idNumber;				
			}else if(choice == 2) {
				sql = sqlStringBefore+"animal_type="+change+sqlStringAfter+(char)idNumber;
			}else if(choice == 3) {
				sql = sqlStringBefore+"species_type="+change+sqlStringAfter+(char)idNumber;
			}else if(choice == 4) {
				sql = sqlStringBefore+"age="+change+sqlStringAfter+(char)idNumber;
			}else {
				System.out.print("You have entered wrong Choice ");
				System.exit(0);
			}
			
			// FIRING THE STATEMENT
			stmt.executeUpdate(sql);
			System.out.println("[+] RECORD UPDATED SUCCESSFULLY");
		}catch(Exception e) {
			System.out.print(e);
		}
	}

	// All record for display
	public static void allRecord() {
		try {
			String sql = "SELECT * FROM zoo_management";
			
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3) +" "+rs.getInt(4));
		}catch(Exception e) {
			System.out.print(e);
		}
	}
	
	// Specific Record
	public static void specificRecord() {
		try {
			System.out.print("Enter a id number => ");
			int idNumber = sc.nextInt();
			String sql = "SELECT * FROM zoo_management WHERE id="+idNumber;
			
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3) +" "+rs.getInt(4));
			
		}catch(Exception e) {
			System.out.print(e);
		}
	}
	
	// COUNT ANIMAL
	public static void displayByAnimalRecord() {
		try {
			System.out.println("[ IN ] ENTER THE ANIMAL NAME : ");
			String animalName = sc.next();
			
			String sql = "SELECT * FROM zoo_management WHERE animal_type='"+animalName+"'";
			ResultSet rs;
			rs = stmt.executeQuery(sql);
			
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3) +" "+rs.getString(4)+" "+rs.getInt(5));
			
			String sqlCount = "SELECT COUNT(animal_type) FROM zoo_management WHERE animal_type='"+animalName+"'";			
			rs = stmt.executeQuery(sqlCount);
			rs.next();
			System.out.print(animalName + " : "+rs.getInt(1) + "\n");
			  
			Runtime.getRuntime().exec("clear");  
			Thread.sleep(2000);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void displayBySpeciesRecord() {
		try {
			System.out.println("[ IN ] ENTER THE SPECIES NAME: ");
			String speciesName = sc.next();
			
			String sql = "SELECT * FROM zoo_management WHERE species_type='"+speciesName+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3) +" "+rs.getString(4)+" "+rs.getInt(5));
		}catch(Exception e) {
			System.out.println(e);			
		}
	}
}
