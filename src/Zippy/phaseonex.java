
/**
 * @author Behrooz Hosseini Expert in Artificial Intelligence  Email:b.hooseini4u@gmail.com
 *   
 **/
package Zippy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;



class phaseonex extends SwingWorker<Void, Void> {
    private UserAlliace gui;
    String AddressLOC="C:\\Zill_Results\\";
    public enum header { ZPID, Address, Locality, State, ZipCode, Latitude, Longitude, Price , Bedrooms, Bathrooms, AreaSpace_SQFT, Status, Estimated_Rent, Year_Built, HOAFee, Days_On_Zillow, Views_Since_Listing, Index2, Mortgage, Rent_Sale, Avg_Index2 , Cash_Flow, Avg_Price, Avg_CashFlow, Avg_Rent, Price_Per_SQFT,Avg_Price_PerSQFT, StdDev_PerSQFT, StdDev_Price, Schools, MLSNo }
	//int i=header.Address.ordinal();	int j=header.ZPID.ordinal();
    public phaseonex(UserAlliace gui){
        this.gui = gui;
		
    }
   @Override
    protected Void doInBackground() throws Exception {
	   
	    int result=0;
		gui.waitLabel.setVisible(true);
        gui.giflabel.setVisible(true);
		gui.lblShowsHowMuch.setVisible(true);
		gui.progressBarZillow.setVisible(true);	
		gui.TotalprogressBar.setVisible(true);
		gui.progressBarZillow.setValue(0);
		gui.TotalprogressBar.setValue(0);
		gui.progressBarZillow.setStringPainted(true);
		gui.TotalprogressBar.setStringPainted(true);
		gui.giflabel.setVisible(true);
		gui.lblTotalProgress.setVisible(true);
		//gui.enteredTXtString = gui.Search_formattedTextField.getText();
		gui.frmSearchingForReal.revalidate();
		gui.frmSearchingForReal.repaint();
		
		File firstFile = new File(AddressLOC+"Sale_Report\\Visited_Sale.csv");
		if(!firstFile.exists()) 
		{try {	firstFile.createNewFile();}	catch (IOException e){System.out.println(e.getCause().getMessage());JOptionPane.showMessageDialog(null,e );e.printStackTrace();}		}
		firstFile = new File(AddressLOC+"Rent_Report\\Visited_Rent.csv");
		if(!firstFile.exists()) 
		{try {	firstFile.createNewFile();}	catch (IOException e){System.out.println(e.getCause().getMessage());JOptionPane.showMessageDialog(null,e );e.printStackTrace();}		}
		firstFile = new File(AddressLOC+"Sale_Report\\MissedSale.csv");
		if(!firstFile.exists()) 
		{try {	firstFile.createNewFile();}	catch (IOException e){System.out.println(e.getCause().getMessage());JOptionPane.showMessageDialog(null,e );e.printStackTrace();}		}
		firstFile = new File(AddressLOC+"Rent_Report\\MissedRent.csv");
		if(!firstFile.exists()) 
		{try {	firstFile.createNewFile();}	catch (IOException e){System.out.println(e.getCause().getMessage());JOptionPane.showMessageDialog(null,e );e.printStackTrace();}		}
		
		ZillMessag User_Log=new ZillMessag("The Total saved Zip Codes Report");
		User_Log.setVisible(true);
		ZillMessag User_Log2=new ZillMessag("Logs of happenings in details ");
		//User_Log2.
		User_Log2.setVisible(true);
		//InputStream instream = UserAlliace.class.getResourceAsStream("/All_Zip.csv");
		File vis_Sale_File = new File(AddressLOC+"Sale_Report\\Visited_Sale.csv");
		File vis_Rent_File = new File(AddressLOC+"Rent_Report\\Visited_Rent.csv");
		
		if(gui.comboBoxABV.getSelectedItem().toString().equals("") )
		{   JOptionPane
			.showMessageDialog(
					null,
					"<html><h1> Please Select A State from the list First </h1></html>",
					"No State is Selected!",
					JOptionPane.ERROR_MESSAGE);
		}
		else{ //a state is selected
		List<String> All_Zip_list = new ArrayList<String>();
		
		//InputStream donestream = UserAlliace.class.getResourceAsStream("/Visited_Sale.csv");
		int Source_Size=0,DoneSize=0;
		try{
            try 
            { InputStream instream2 = UserAlliace.class.getResourceAsStream("/All_Zip.csv");
        	  BufferedReader	 VisBuff = new BufferedReader(new InputStreamReader(instream2, "UTF-8"));
        	  VisBuff.readLine();
        	  String zipliner= new String("");
        	while((zipliner=VisBuff.readLine())!=null)
            	    {All_Zip_list.add(zipliner);}
        	VisBuff.close();
        	instream2.close();
            }
            
            catch (Exception exc)
            {
              // System.out.println(exc.getCause().getMessage());
              //	JOptionPane.showMessageDialog(null,exc );
            }
            if(vis_Sale_File.exists() && !(gui.Rent_RadioButton_2.getSelectedObjects() != null))
            {
                try {LineNumberReader  lnr2 = new LineNumberReader(new FileReader((vis_Sale_File)));
    			lnr2.skip(Long.MAX_VALUE);
    			DoneSize= lnr2.getLineNumber() ; 
    			// Finally, the LineNumberReader object should be closed to prevent resource leak
    			lnr2.close();
    			
    			}
                catch (Exception exc)
                {  System.out.println(exc.getCause().getMessage());
                	JOptionPane.showMessageDialog(null,exc );
                }
            }
            else if (vis_Rent_File.exists() && !(gui.Rent_RadioButton_2.getSelectedObjects() != null))
            {
                try {LineNumberReader  lnr2 = new LineNumberReader(new FileReader((vis_Rent_File)));
    			lnr2.skip(Long.MAX_VALUE);
    			DoneSize= lnr2.getLineNumber() ; 
    			// Finally, the LineNumberReader object should be closed to prevent resource leak
    			lnr2.close();
    			}
                catch (Exception exc)
                {  System.out.println(exc.getCause().getMessage());
                	JOptionPane.showMessageDialog(null,exc );
                }
            }
            try {InputStream instreamert = UserAlliace.class.getResourceAsStream("/All_Zip.csv");
            	LineNumberReader  lnr = new LineNumberReader(new InputStreamReader(instreamert, "UTF-8"));
			lnr.skip(Long.MAX_VALUE);
			Source_Size= lnr.getLineNumber() ; 
			// Finally, the LineNumberReader object should be closed to prevent resource leak
			lnr.close();
			
			}
            catch (Exception exc)
            {   System.out.println(exc.getCause().getMessage());
            	JOptionPane.showMessageDialog(null,exc );
            }
            
            List<String> Selected_Zip_list = new ArrayList<String>();
            String State_Selected= gui.comboBoxABV.getSelectedItem().toString() ;
            for(int Co=0;Co<All_Zip_list.size();Co++)
            {
            	if(All_Zip_list.get(Co).split(",")[1].equals(State_Selected))
            	   {Selected_Zip_list.add(All_Zip_list.get(Co).split(",")[0]);}
            }
            All_Zip_list=null;
			if  (Selected_Zip_list.size()>0){
				
			for (int Cnt=0;Cnt<Selected_Zip_list.size();Cnt++)
			{   result=0;
				String Zip=Selected_Zip_list.get(Cnt);
				InputStream instream2 = UserAlliace.class.getResourceAsStream("/ZipCodes.csv");
	        	  BufferedReader	 VisBuff = new BufferedReader(new InputStreamReader(instream2, "UTF-8"));
	        	  VisBuff.readLine();
	        	  String readTxt= new String("");
	        	  while((readTxt=VisBuff.readLine())!=null)
          	            {if (readTxt.split(",")[0].equals(Zip)){break;}      }
      	           VisBuff.close();
      	           instream2.close();
	        	  
			  	boolean Vistedbefore=false;
			  	if(readTxt!=null){
			  		gui.lblcurrentzip.setText ("<html><b>Current &nbsp; Zip</b>:"+Zip+ "  &nbsp; <b>City:</b>"+readTxt.split(",")[3]+"   &nbsp; <b>County:</b>"+readTxt.split(",")[7]+"  &nbsp;  <b>State:</b>"+readTxt.split(",")[6]+"  &nbsp;  <b>Area_Code:</b>"+readTxt.split(",")[9]+"  &nbsp;  <b>Population:</b>"+readTxt.split(",")[14]+"</html>");
			  		gui.lblShowsHowMuch.setText("<html>Progress in the Zipcode: <h1>"+Zip+"</html>");
			  		gui.lblTotalProgress.setText("Progress in:"+gui.lblState.getText());
					  int final_radio_resBut=0;
					   
					  if (gui.Buy_RadioButton.getSelectedObjects() != null){final_radio_resBut =0; Vistedbefore=vistedbeforSale(Zip);}
					  else if (gui.Sell_RadioButton.getSelectedObjects() != null) {final_radio_resBut= 1;}
					  else if (gui.Rent_RadioButton_2.getSelectedObjects() != null) {final_radio_resBut= 2; Vistedbefore=vistedbeforRent(Zip);}
					  if (!Vistedbefore){
					result=ZipFinder.search( Zip ,final_radio_resBut, gui.progressBarZillow,  gui.Min_formattedTextField.getText() ,  gui.Max_formattedTextField.getText(), User_Log2);
					
					
					if (result ==1 )
					{   	if(Cnt%50==0 && Cnt!=0){User_Log.textArea_mess.setText("");  User_Log2.textArea_mess.setText("");}					 
					 User_Log.addert(" successfully Grabbed for: Zip:"+Zip+ ",  City:"+readTxt.split(",")[3]+",  County:"+readTxt.split(",")[7]+",  State:"+readTxt.split(",")[6]+",  Area Code:"+readTxt.split(",")[9]+",  Population:"+readTxt.split(",")[14]);
					 User_Log.validate();
					 if(final_radio_resBut!=2)
			    	{logfilewrite(AddressLOC+"Log_Files\\SaleTotalLOG.csv"," Sale successfully Grabbed for: Zip:"+Zip+ ",  City:"+readTxt.split(",")[3]+",  County:"+readTxt.split(",")[7]+",  State:"+readTxt.split(",")[6]+",  Area Code:"+readTxt.split(",")[9]+",  Population:"+readTxt.split(",")[14]);}
					 else
					{logfilewrite(AddressLOC+"Log_Files\\RentTotalLOG.csv"," Rent successfully Grabbed for: Zip:"+Zip+ ",  City:"+readTxt.split(",")[3]+",  County:"+readTxt.split(",")[7]+",  State:"+readTxt.split(",")[6]+",  Area Code:"+readTxt.split(",")[9]+",  Population:"+readTxt.split(",")[14]);}
					if(final_radio_resBut==0)
					{	
					 String quandlString="https://www.quandl.com/api/v3/datasets/ZILL/Z"+Zip+"_A.csv?api_key=Wwc-wEk2zFwNDrWqfvof";
						URL website = new URL(quandlString);
						 try
						 {
						   ReadableByteChannel rbc = Channels.newChannel(website.openStream());
						   FileOutputStream fos = new FileOutputStream(AddressLOC+"Price_Average_PerZip\\"+Zip+"_AVG"+".csv");
						   fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
						   fos.close();
						 }
						 catch (Exception ex)
						 {	// JOptionPane.showMessageDialog(null,ex );
							 User_Log.addert("There were problems in fetching price average for zipcode: "+Zip+ex.toString());
						 }
					 }
                       /*Runtime.getRuntime().exec("cmd.exe /c start");
					     System.out.println("ok");*/
					}
				}
			  	}
			  	if (Selected_Zip_list.size()!=0){
			  		int fin=DoneSize+Cnt;
			  	gui.TotalprogressBar.setValue(Math.round((100*Cnt)/(Selected_Zip_list.size())));
				gui.TotalprogressBar.setStringPainted(true);
				gui.lblZipsSeen.setText("<html>From the Start up to now an amount of <b>"+fin+"</b> zips from total number of <b>"+Source_Size+" </b> has been successfully Done!");
				gui.lblZipsSeen.setVisible(true);  }
			}// Main for Loop
			
			gui.TotalprogressBar.setValue(100);
			gui.lblcurrentzip.setText("First phase was successfully Done !");
			gui.lblfinish1.setVisible(true);
			gui.lblfinalized.setVisible(true);
			/*JOptionPane
			.showMessageDialog(
					null,
					"<html><h1> First phase was successfully Done </h1></html>",
					"Please first run phase one!",
					JOptionPane.INFORMATION_MESSAGE);*/
        }
	}
		catch (Exception exc)
		{User_Log.addert("There were problems in fetching price Sth gon wrong " +" "+exc.toString());}// JOptionPane.showMessageDialog(null,exc );}
		} // Main Else of State Selected
	gui.waitLabel.setVisible(false);
	gui.giflabel.setVisible(false);
	gui.lblShowsHowMuch.setVisible(false);
	gui.progressBarZillow.setValue(0);
	gui.progressBarZillow.setVisible(false);
	gui.lblTotalProgress.setText("Finished Great Job!");
	gui.frmSearchingForReal.repaint();
	gui.frmSearchingForReal.revalidate();
	
	return null;
	 
   
    }
public void logfilewrite(String Filename ,String data) {
	try{
		File Log_File = new File(Filename);
		FileWriter fw  = new FileWriter( Log_File, true);
		fw.write(data+"\n");
		fw.close();
		}
		catch (Exception Exc){ }//JOptionPane.showMessageDialog(null, Exc);}

	
}
private boolean vistedbeforRent(String zip) {
	File vis_Rent_File = new File(AddressLOC+"Rent_Report\\Visited_Rent.csv");
	File Mis_Rent_File = new File(AddressLOC+"Rent_Report\\MissedRent.csv");
	try{
    if (vis_Rent_File.exists()){
    	BufferedReader input_Zip = new BufferedReader (new FileReader (vis_Rent_File));
		//input_Zip.readLine();
		String readTxt="" ;
		while ((readTxt=input_Zip.readLine())!=null)
		{
			if (readTxt.split(",")[0].equals(zip)){input_Zip.close(); return true;}
		}
		input_Zip.close();
		}
    if (Mis_Rent_File.exists()){
    	BufferedReader input_Zip = new BufferedReader (new FileReader(Mis_Rent_File));
		//input_Zip.readLine();
		String readTxt="" ;
		while ((readTxt=input_Zip.readLine())!=null)
		{
			if (readTxt.split(",")[0].equals(zip)){ input_Zip.close();return true;}
		}
		input_Zip.close();
		}
    return false;
	}
		catch(Exception Exc){ JOptionPane.showMessageDialog(null, Exc);return false;}
	// TODO Auto-generated method stub
	
}
private boolean vistedbeforSale(String Zip) {
	File vis_Sale_File = new File(AddressLOC+"Sale_Report\\Visited_Sale.csv");
	File Mis_Sale_File = new File(AddressLOC+"Sale_Report\\MissedSale.csv");
	try{
    if (vis_Sale_File.exists()){
    	BufferedReader input_Zip = new BufferedReader (new FileReader(vis_Sale_File));
		//input_Zip.readLine();
		String readTxt="" ;
		while ((readTxt=input_Zip.readLine())!=null)
		{if (readTxt.split(",")[0].equals(Zip)){ input_Zip.close();return true;}}
		input_Zip.close();
		}
    if (Mis_Sale_File.exists()){
    	BufferedReader input_Zip = new BufferedReader (new FileReader(Mis_Sale_File));
		//input_Zip.readLine();
		String readTxt="" ;
		while ((readTxt=input_Zip.readLine())!=null)
		{if (readTxt.split(",")[0].equals(Zip)){ input_Zip.close();return true;}}
		input_Zip.close();
		}
    return false;
	}
		catch(Exception Exc){ JOptionPane.showMessageDialog(null, Exc);return false;}
	// TODO Auto-generated method stub
	
}

    
    
}