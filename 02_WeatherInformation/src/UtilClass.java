import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Scanner;


public class UtilClass {
	/*
	 * STATIC VARIABLE INIT.
	 */
	static Statement stmt;
	static Connection con;
	static Scanner sc = new Scanner(System.in);
	static String sqlStringBefore = "UPDATE WeatherInformation SET ";
	static String sqlStringAfter = " WHERE id=";
	
	/*
	 *  CONSTRUCTOR
	 */
	
	public UtilClass(Statement st,Connection conn) {
		this.stmt = st;
		this.con = conn;
	}
	
	/*
	 * PRIVATE METHODS
	 */
	
	// menu for showing
		public void menu() {
			try {
				System.out.println("Welcome to the WEATHER REPORT ");
				System.out.println(" ---------------------------------- ");
				System.out.print("\n");
				System.out.println("[ 1 ] ENTER THE WEATHER RECORD");
				System.out.println("[ 2 ] DELETE THE RECORD");
				System.out.println("[ 3 ] UPDATE THE WEATHER RECORD");
				System.out.println("[ 4 ] DISPLAY ALL RECORD");
				System.out.println("[ 5 ] DISPLAY SPECIFIC RECORD BY CITY");
				System.out.println("[ 6 ] DISPLAY SPECIFIC RECORD BY COUNTRY");
				System.out.println("[ 7 ] RANKING REPORT BY HIGHEST OXYGEN");
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
						displayRecord();
						break;
					case 5:
						specificRecord();
						break;
					case 6:
						countryRecord();
						break;
					case 7:
						highestOxygen();
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
		
		public static void insertRecord() {
			try {
				
				System.out.print("Enter city name => ");
				String cityName = sc.next();
				System.out.print("Enter country Name => ");
				String countryName = sc.next();
				System.out.print("Enter atmospheric Oxygen (g/m) => ");
				float atmOxygen = sc.nextFloat();
				System.out.print("Enter atmospheric Nitrogen (g/m) => ");
				float atmNitrogen = sc.nextFloat();
				System.out.print("Enter atmospheric Carbon Dioxide (g/m) => ");
				float atmCarbonDioxide = sc.nextFloat();
				System.out.print("Enter Humidity => ");
				float humidity = sc.nextFloat();
	 
				String sql = "INSERT INTO WeatherInformation (city,country,atm_oxygen,atm_nitrogen,atm_co2,humidity) VALUES ('" + cityName + "' , '" + countryName + "' , '" + atmOxygen + "' , '" + atmNitrogen +"' , '"+ atmCarbonDioxide + "' , '" + humidity +"')";
				stmt.executeUpdate(sql);
				System.out.println("[+] RECORD INSERTED SUCCESSFULLY");
				Thread.sleep(3000);
			}catch(Exception e) {
				System.out.print(e);
			}
			
		}
		
		public static void deleteRecord() {
			try {
				System.out.print("Enter id number to delete => ");
				int idNumber = sc.nextInt();			
				String sql = "DELETE FROM WeatherInformation WHERE id="+(char)idNumber;
				stmt.executeUpdate(sql);				
				System.out.println("[+] RECORD DELETED SUCCESSFULLY");
				Thread.sleep(3000);
			}catch(Exception e) {
				System.out.print(e);
			}			
		}
		
		public static void updateRecord() {
			try {				
				System.out.println("Which Field do you want to update ? ");
				System.out.println("1) CITY NAME");
				System.out.println("2) COUNTRY NAME");
				System.out.println("3) ATMOSPHERIC OXYGEN ");
				System.out.println("4) ATMOSPHERIC NITROGEN ");
				System.out.println("5) ATMOSPHERIC CARBON DIOXIDE ");
				System.out.println("6) HUMIDITY ");
				System.out.print("Please Enter a choice => ");
				int choice = sc.nextInt();
				System.out.print("Please Provide the update/replace value to change => ");
				String change = sc.next();
				System.out.print("Please provide id of the CITY => ");
				int idNumber = sc.nextInt();
				String sql = new String();
				
				if(choice == 1) {
					sql = sqlStringBefore+"city="+change+sqlStringAfter+(char)idNumber;				
				}else if(choice == 2) {
					sql = sqlStringBefore+"country="+change+sqlStringAfter+(char)idNumber;
				}else if(choice == 3) {
					sql = sqlStringBefore+"atm_oxygen="+change+sqlStringAfter+(char)idNumber;
				}else if(choice == 4) {
					sql = sqlStringBefore+"atm_nitrogen="+change+sqlStringAfter+(char)idNumber;
				}else if(choice == 5) {
					sql = sqlStringBefore+"atm_co2="+change+sqlStringAfter+(char)idNumber;
				}else if(choice == 6) {
					sql = sqlStringBefore+"humidity="+change+sqlStringAfter+(char)idNumber;
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
		
		public static void displayRecord() {
			try {
				String sql = "SELECT * FROM WeatherInformation"+" ORDER BY city ASC";
				
				ResultSet rs=stmt.executeQuery(sql);
				
				while(rs.next())  
					System.out.println(rs.getInt(1)+ " " +rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4) +" "+rs.getFloat(5) + " " + rs.getFloat(6) + " " +rs.getFloat(7)  );
				Thread.sleep(3000);
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		
		// THIS METHODS REPRESENTS THE RECORD BY CITY NAME 
		public static void specificRecord() {
			try {
				System.out.print("Enter a City Name => ");
				String cityName = sc.next();
				String sql = "SELECT * FROM WeatherInformation WHERE city="+cityName;
				
				ResultSet rs=stmt.executeQuery(sql);
				
				while(rs.next())  
					System.out.println(rs.getInt(1)+ " " +rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4) +" "+rs.getFloat(5) + " " + rs.getFloat(6) + " " +rs.getFloat(7)  );
				Thread.sleep(3000);				
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		
		public static void countryRecord() {
			try {
				System.out.print("Enter a Country Name => ");
				String countryName = sc.next();
				String sql = "SELECT * FROM WeatherInformation WHERE country="+countryName+" ORDER BY country ASC";
				
				ResultSet rs=stmt.executeQuery(sql);
				
				while(rs.next())  
					System.out.println(rs.getInt(1)+ " " +rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4) +" "+rs.getFloat(5) + " " + rs.getFloat(6) + " " +rs.getFloat(7)  );
				Thread.sleep(3000);				
			}catch(Exception e) {
				System.out.print(e);
			}	
		}
		
		public static void highestOxygen() {
			try {		
				String sql = "SELECT * FROM WeatherInformation ORDER BY atm_oxygen DESC";
				
				ResultSet rs=stmt.executeQuery(sql);
				
				while(rs.next()) {
					if(rs.getInt(1) == 4)
						break;
					System.out.println(rs.getInt(1)+ " " +rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4) +" "+rs.getFloat(5) + " " + rs.getFloat(6) + " " +rs.getFloat(7)  );
				}
				Thread.sleep(3000);				
			}catch(Exception e) {
				System.out.print(e);
			}	
		}
}
