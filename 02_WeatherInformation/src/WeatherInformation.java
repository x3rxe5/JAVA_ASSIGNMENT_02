import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.Vector;

public class WeatherInformation {
	
	static String connectionDB = "jdbc:mysql://localhost:3306/college_assignment"; 
	static String connectionUser = "root";
	static String connectionPassword = "3911";
	static String dbName = "WeatherInformation";
	
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(connectionDB,connectionUser,connectionPassword);
			Statement stmt = con.createStatement();
			
			// For database metadata
			DatabaseMetaData databaseMetaData = con.getMetaData();
			
			// For fetching Results
			ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[] {"TABLE"});
			Vector<String> vc = new Vector<String>();
			while (resultSet.next()) {
				String name = resultSet.getString("TABLE_NAME");
				vc.add(name);
			}
			
			boolean isTableExists = tableExists(vc);
			// TABLE CREATION
			tableCreation(isTableExists,stmt);
			
			// MAIN MENU 
			UtilClass uc = new UtilClass(stmt,con);
			while(true) {
				uc.menu();
			}
		}catch(Exception e) {
			System.out.print(e);
		}
	}
	
	// PRIVATE METHODS 
	public static boolean tableExists(Vector<String> arr) {
		// If Table Exists then return true else return false;
		for(int i=0;i<arr.size();i++) {
			if(arr.elementAt(i).equals(dbName)) {
				return true;
			}
		}	
		return false;
	}
	
	// FOR TABLE CREATION 
	public static void tableCreation(boolean str,Statement stmt) {
		if(!str) {
			try {
				String sql = "CREATE TABLE "+dbName+ "( "+
						   "id int NOT NULL AUTO_INCREMENT,"+
		                   "country varchar(255) NOT NULL, "+
		                   "city varchar(255) NOT NULL, "+
		                   "atm_oxygen float NOT NULL, "+
		                   "atm_nitrogen float NOT NULL, "+
		                   "atm_co2 float NOT NULL, "+
		                   "humidity float NOT NULL, "+
		                   "date datetime default CURRENT_TIMESTAMP);";
				stmt.executeUpdate(sql);
				System.out.println("[ + ] TABLE CREATED SUCCESSFULLY ");
			}catch(Exception e) {
				System.out.print(e);
			}				
		}
	}
}
