/*
 *  AUTHOR : PULKIT SHARMA
 */

import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.*;

/*
 *  sql SCHEMA
 *  create table ageCalculator ( id int not null AUTO_INCREMENT, name varchar(255), dob DATE);
 *  insert into ageCalculator(name,dob) values ('Pulkit Sharma','YYYY/MM/DD');
 */

public class AgeCalculator {
	
	/*
	 *  STATIC VARIABLE INIT.
	 */
	static String connectionDB = "jdbc:mysql://localhost:3306/college_assignment"; 
	static String connectionUser = "root";
	static String connectionPassword = "3911";
	static String dbName = "ageCalculator";
	static Scanner sc = new Scanner(System.in);
	/*
	 *  PUBLIC Methods
	 */
	public static void insertRecord(Statement stmt) {
		try {
			System.out.print("Enter a Name => ");
			String name=sc.next();
			System.out.print("Enter the Date ");
			String date = sc.next();
//			Date dob = new SimpleDateFormat("YYYY/MM/DD").parse(date);
			
			String sql = "INSERT INTO ageCalculator(name,dob) values ('"+name+"' , '" + date + " ' )";
			stmt.executeUpdate(sql);
		}catch(Exception e) {
			System.out.print(e);
		}		
	}
	
	public static void displayRecord(Statement stmt) {
		try {					
			String sql = "SELECT * FROM ageCalculator";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				System.out.print(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDate(3) );				
			}
		}catch(Exception e) {
			System.out.print(e);
		}	
	}
	
	public static void findAgeAndDisplayRecord(Statement stmt) {
		try {
			System.out.print("Enter an id number => ");
			int id = sc.nextInt();

			String sql = "select name,timestampdiff(YEAR,dob,now()) from ageCalculator where id="+id;
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.print(rs.getString(1) + " Age => "+rs.getInt(2));
			}
		}catch(Exception e) {
			System.out.print(e);
		}
	}
	
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(connectionDB,connectionUser,connectionPassword);
			Statement stmt = con.createStatement();
			

//			insertRecord(stmt);
//			displayRecord(stmt);
			findAgeAndDisplayRecord(stmt);
		}catch(Exception e) {
			System.err.println(e);
		}
	}	
}
