//CISC3150
//Xin Guan
//12/8/15
//
//Mac can't use Access, so I used phpMyAdmin to setup a database server and I learned those from:
//https://www.youtube.com/watch?v=E30_-pQGQXs&list=LLLpOo4S1rCO798ViGTF1frg&index=2
//
//I import the mysql-connector-java-5.1.38-bin.jar into Eclipse Referenced Libraries
//downloaded from: https://dev.mysql.com/downloads/connector/j/


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HW9{
	
	public static void main(String[] args) throws Exception{
		
		//I used eclipse to add mysql connector jar file into library already.
		//so I use this line to select the library file.
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection myc = DriverManager.getConnection("jdbc.mysql://localhost/mydatebase","root","root");
		PreparedStatement mystate = myc.prepareStatement("select * from table_name");
		ResultSet myr = mystate.executeQuery();
		
		while(myr.next()){
			//first column is first name
			//second column is last name
			System.out.println(myr.getString(1)+" "+myr.getString(2));
		}	
		
	}
	
}