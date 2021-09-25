/*
 *  AUTHOR : PULKIT SHARMA
 */


/*
 * 		TABLE CREATION emp_master
 * 		
 * 		CREATE TABLE emp_master (
 * 			id INT NOT NULL AUTO_INCREMENT,
 * 			name VARCHAR(255) NOT NULL,
 * 			salary FLOAT NOT NULL,
 * 			PRIMARY KEY(id) 
 * 		);
 */


/*
 *  This is store procedure reference to create in db
 *  
 *   @param1 id_number ( id number of the employee)
 *   @param2 increase_amount ( increase the wages )
 *   
 *   CREATE PROCEDURE increase_salaries(IN id_number INT,IN increase_amount FLOAT)
 *   BEGIN
 *   	UPDATE emp_master SET salary = salary + increase_amount WHERE id=id_number;
 *   END; 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.util.Scanner;
import java.sql.Statement;

class StoreProcedure {
	/*
	 *	STATIC VARIABLE INIT. 
	 */
	
	static Connection conn = null;
	static CallableStatement myStmt = null;
	static Statement stmt = null;
	static Scanner sc = new Scanner(System.in);
	
	
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/college_assignment","root","3911");
			System.out.print("Enter a id number => ");
			int id = sc.nextInt();
			System.out.print("Enter a Increase Amount => ");
			float increase_amount = sc.nextFloat();
			
			// create callable statement;
			myStmt = conn.prepareCall("{call increase_salaries(?,?)}");
			
			// set string for procedures
			
			myStmt.setInt(1, id);
			myStmt.setFloat(2, increase_amount);
			System.out.println("[ * ] BEFORE EXECUTING THE STATEMENT ");
			showRecords(conn,id);
			myStmt.execute();
			System.out.println("[ + ] AFTER EXECUTING THE STATEMENT ");
			showRecords(conn,id);
		}catch(Exception err) {
			System.err.println(err);
		}
	}
	
	public static void showRecords(Connection con,int id) {
		try {
			String sql = "SELECT * from emp_master where id="+id;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3));
			}
		}catch(Exception e) {
			System.out.print(e);
		}
	}
}
