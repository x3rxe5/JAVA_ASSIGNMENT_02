/*
 *  AUTHOR : PULKIT SHARMA
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Transactions {
	public static void main(String[] args) {


		Connection con;
		try {
			Scanner sc = new Scanner(System.in);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college_assignment", "root", "3911");
			con.setAutoCommit(false);			
			String sql = "Insert into employee(emp_id,emp_name,sal)values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			while (true) {
				System.out.print("[ + ] ENTER THE EMPLOYEE ID:");
				int id = sc.nextInt();
				pstmt.setInt(1, id);

				System.out.print("[ + ] ENTER THE EMPLOYEE NAME:");
				String name = sc.next();
				pstmt.setString(2, name);

				System.out.print("[ + ] ENTER THE EMPLOYEE SALARY:");
				int sal = sc.nextInt();
				pstmt.setDouble(3, sal);

				pstmt.executeUpdate();

				System.out.print("COMMIT/ROLLBACK");
				String cr = sc.next();

				if (cr.equals("COMMIT") || cr.equals("Commit") || cr.equals("commit")) {
					con.commit();
				}
				if (cr.equals("rollback") || cr.equals("ROLLBACk") || cr.equals("Rollback")) {
					con.rollback();
				}
				
				System.out.println("DO YOU WANT TO CONTINUE ? 0->yes 1->no ");

				int ans = sc.nextInt();
				if (ans == 1) {
					break;
				}

			}
			con.commit();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}