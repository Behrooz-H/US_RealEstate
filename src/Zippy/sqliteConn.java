package Zippy;
import java.io.File;
import java.io.FileWriter;
import java.sql.*; 
import javax.swing.*;
public class sqliteConn {
Connection Conn=null;
private static void logfilewrite(String Filename ,String data) {
	try{
		File Log_File = new File(Filename);
		FileWriter fw  = new FileWriter( Log_File, true);
		fw.write(data+"\n");
		fw.close();
		}
		catch (Exception Exc){ }//JOptionPane.showMessageDialog(null, Exc);}
}

public static Connection DBConnection()
   {try
	{Class.forName("org.sqlite.JDBC");
		Connection Connec=DriverManager.getConnection("jdbc:sqlite:C:\\Zill_Results\\MaryZillow.sqlite") ;
		//JOptionPane.showMessageDialog(null, "Sucess connect");
		return Connec;}
	  catch(Exception c){JOptionPane.showMessageDialog(null, c); return null; }
	}
public static boolean Sale_insert_phase1 (String inputStrng , ZillMessag Userlog)
   {Connection conct = null;
   Statement stmt = null;
	try
	  {  Class.forName("org.sqlite.JDBC");
		 conct = DriverManager.getConnection("jdbc:sqlite:C:\\Zill_Results\\MaryZillow.sqlite");
	     conct.setAutoCommit(false);
	     System.out.println("Opened database successfully for Writing in Estate4Sale"); // successful Connection
	     stmt = conct.createStatement();
	     //String sql = "INSERT INTO Estate4Sale (ZPID,Address,Locality,State,ZipCode,Latitude,Longitude,Price,Bedrooms,Bathrooms,AreaSpace_SQFT,Status,EstimatedRent,YearBuilt,HOAFee,DaysOnZillow,ViewsSinceListing,Index2,Mortgage,Avg_Index2,CashFlow,Avg_Price,Avg_CashFlow,Avg_Rent,Price_PerSQFT,Avg_Price_PerSQFT,StdDev_SQFT,StdDevPrice,Schools,MLS_NO) "+" VALUES ("+inputStrng+");";
	     String sql = "INSERT INTO Estate4Sale (ZPID,Address,Locality,State,ZipCode,Latitude,Longitude,Price,Bedrooms,Bathrooms,AreaSpace_SQFT,Status,URL_Link) "+" VALUES ("+inputStrng+");";
	     //'hjkjkj' , 'tast @ test Street' , 'TestCity', 'TEST STATE' , 9999, 87.787445 , 74.235698 , 56987455 , 5 , 1 , 4578 , 'Condo' , 2340 , 1395 , 100 ,100 , 23456 , 7.25584 , 'test Schools' , '7887988n'
	     // stmt.executeQuery(sql);
	     int i=0;
	     i=stmt.executeUpdate(sql);
	     if (i==1){System.out.println("Sale Successfully Saved: "+inputStrng);Userlog.addert("Sale Successfully Saved: "+inputStrng); logfilewrite("C:\\Zill_Results\\Log_Files\\Sale_Detail_Log.csv","Sale Successfully Saved: "+inputStrng); }
	     stmt.close();
	     conct.commit();
	     conct.close();
	     return true;
	  }
	catch(Exception exc){Userlog.addert(" !! ERROR!! ZIPCODE: "+inputStrng.split(",")[4]+"  Not saved in DataBase: "+inputStrng+"\n the System says: "+ exc);return false; }
	}
public static boolean Rent_insert_Phase1 (String inputStrng ,ZillMessag Userlog)
{   Connection conct = null;
    Statement stmt = null;
	try
	  {
		Class.forName("org.sqlite.JDBC");
		conct = DriverManager.getConnection("jdbc:sqlite:C:\\Zill_Results\\MaryZillow.sqlite");
	     conct.setAutoCommit(false);
	     System.out.println("Estate4Rent Opened successfully"); // successful Connection
	     stmt = conct.createStatement();
	     //String sql = "INSERT INTO Estate4Rent (ZPID,Address,Locality,State,ZipCode,Latitude,Longitude,Rent,Bedrooms,Bathrooms,AreaSpace_SQFT,Status,EstimatedRent,YearBuilt,HOAFee,DaysOnZillow,ViewsSinceListing,Index2,Mortgage,Avg_Index2,CashFlow,Avg_RentPrice,Avg_CashFlow,Avg_RentEstimate,Price_PerSQFT,Avg_Price_PerSQFT,StdDev_SQFT,StdDevPrice,Schools,MLS_NO,Posted,Laundry,Rent_PerSQFT,Cooling,Heating,ExtraData) "+" VALUES ("+inputStrng+");";
	     String sql = "INSERT INTO Estate4Rent(ZPID,Address,Locality,State,ZipCode,Latitude,Longitude,Rent,Bedrooms,Bathrooms,AreaSpace_SQFT,Status,URL_Link,Single_Case) "+" VALUES ("+inputStrng+");";
	     //'hjkjkj' , 'tast @ test Street' , 'TestCity', 'TEST STATE' , 9999, 87.787445 , 74.235698 , 56987455 , 5 , 1 , 4578 , 'Condo' , 2340 , 1395 , 100 ,100 , 23456 , 7.25584 , 'test Schools' , '7887988n'
	     // stmt.executeQuery(sql);
	     int i=0;
	     i=stmt.executeUpdate(sql);
	     if (i==1){     System.out.println(" Successfully Saved: "+inputStrng);
		                Userlog.addert("Rent Successfully Saved: "+inputStrng);
		                logfilewrite("C:\\Zill_Results\\Log_Files\\Rent_Detail_Log.csv","Rent Successfully Saved: "+inputStrng);
		  } 
	     stmt.close();
	     conct.commit();
	     conct.close();
	     return true;
	  }
	catch(Exception exc){logfilewrite("C:\\Zill_Results\\Log_Files\\Error_Log.csv","Error in "+inputStrng);
		Userlog.addert(" !! ERROR!! ZIPCODE: "+inputStrng.split(",")[4]+"  Not saved in DataBase: "+inputStrng+"\n the System says: "+ exc);return false; }
}
public static ResultSet Sale_Select(String Condition , ZillMessag Userlog)   
{Connection conct = null;   //Selecting from DB
Statement stmt = null;
String Query="";
	try
	  {  Class.forName("org.sqlite.JDBC");
		 conct = DriverManager.getConnection("jdbc:sqlite:C:\\Zill_Results\\MaryZillow.sqlite");
	     conct.setAutoCommit(false);
	     System.out.println("Opened database successfully"); // successful Connection
	     stmt = conct.createStatement();
	     String sql = "SELECT * FROM Estate4Sale  WHERE ";
	     //'hjkjkj' , 'tast @ test Street' , 'TestCity', 'TEST STATE' , 9999, 87.787445 , 74.235698 , 56987455 , 5 , 1 , 4578 , 'Condo' , 2340 , 1395 , 100 ,100 , 23456 , 7.25584 , 'test Schools' , '7887988n'
	     Query=sql+"("+Condition+")"+";";
	     ResultSet Outcome= stmt.executeQuery(Query); 
	     stmt.close();
	     conct.commit();
	     conct.close();
	     return Outcome;
	  }
	catch(Exception exc){Userlog.addert(" !! ERROR in Selecting from DB!! for query "+Query+"\n the System says: "+ exc);return null; }
}
}

/*to execute the following statement(s):
SELECT FROM "main"."Estate4Sale" ("ZPID"," Address"," Locality"," State"," ZipCode"," Latitude"," Longitude","Price"," Bedrooms"," Bathrooms"," AreaSpace_SQFT"," Status"," EstimatedRent"," Year Built"," HOAFee"," DaysOnZillow"," ViewsSinceListing"," Index2","Schools","MLS_NO") VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14,?15,?16,?17,?18,?19,?20)
Parameters:
param 1 (text): s1d1we
param 2 (text): tast @ test Street
param 3 (text): TestCity
param 4 (text): TEST STATE
param 5 (integer): 9999
param 6 (real): 87.787445
param 7 (real): 74.235698
param 8 (integer): 56987455
param 9 (integer): 5
param 10 (integer): 1
param 11 (integer): 4578
param 12 (text): Condo
param 13 (integer): 2340
param 14 (integer): 1395
param 15 (integer): 100
param 16 (integer): 100
param 17 (integer): 23456
param 18 (real): 7.25584
param 19 (text): test Schools
param 20 (text): 7887988n*/

/*to execute the following statement(s):
INSERT INTO "main"."Estate4Sale" ("ZPID"," Address"," Locality"," State"," ZipCode"," Latitude"," Longitude","Price"," Bedrooms"," Bathrooms"," AreaSpace_SQFT"," Status"," EstimatedRent"," Year Built"," HOAFee"," DaysOnZillow"," ViewsSinceListing"," Index2","Schools","MLS_NO") VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14,?15,?16,?17,?18,?19,?20)
Parameters:
param 1 (text): s1d1we
param 2 (text): tast @ test Street
param 3 (text): TestCity
param 4 (text): TEST STATE
param 5 (integer): 9999
param 6 (real): 87.787445
param 7 (real): 74.235698
param 8 (integer): 56987455
param 9 (integer): 5
param 10 (integer): 1
param 11 (integer): 4578
param 12 (text): Condo
param 13 (integer): 2340
param 14 (integer): 1395
param 15 (integer): 100
param 16 (integer): 100
param 17 (integer): 23456
param 18 (real): 7.25584
param 19 (text): test Schools
param 20 (text): 7887988n*/
