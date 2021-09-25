/*
 * 	AUTHOR : PULKIT SHARMA
 */

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class ZooManagement {
	static Scanner sc = new Scanner(System.in);
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college_assignment","root","3911");  					 		
			Statement stmt = con.createStatement();
			UtilClass uc = new UtilClass(stmt,con);
			while(true) {				
				uc.menu();
			}
			
		}catch(Exception e) {
			System.out.print(e);	
		}
		
	}
}
