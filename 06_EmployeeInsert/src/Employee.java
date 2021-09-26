/*
 *  AUTHOR : PULKIT SHARMA
 */

/*
 *  DATABASE SCHEMA
 *  
 *  CREATE TABLE employee( id int NOT NULL AUTO INCREMENT,emp_id int not null,emp_name varchar(255) NOT NULL,salary FLOAT NOT NULL);
 *  
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Employee {
	
	public static void insert() {
				
		int id;
		String name;
		Double sal;
		int j = 0;
		Scanner sc = new Scanner(System.in);
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college_assignment", "root", "3911");
			con.setAutoCommit(false);
			System.out.println("Connection Done...");
			String sql = "Insert into employee(emp_id,emp_name,sal)values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);

			for (int i = 1; i < 10; i++) {

				System.out.print("[ + ]ENTER THE EMPLOYEE ID =");
				id = sc.nextInt();
				pstmt.setInt(1, id);

				System.out.print("[ + ]ENTER THE EMPLOYEE NAME:");
				name = sc.next();
				pstmt.setString(2, name);

				System.out.print("[ + ]ENTER THE EMPLOYEE SALARY:");
				sal = sc.nextDouble();
				j++;
				pstmt.setDouble(3, sal);

				pstmt.addBatch();
				
				System.out.println("[ + ]ADDED TO BATCH SUCCESSFULLY ");				

			}

			pstmt.executeBatch();

			if (j == 0)
				System.out.println("[ X ] RECORD NOT INSERTED!!");
			else
				System.out.println("[ + ] INSERTED " + j + " RECORDS!!");
			con.commit();

			pstmt.close();
			con.close();

		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public static void main(String[] args) {
		insert();
	}
}