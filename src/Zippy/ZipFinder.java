/**
 * 
 */
package Zippy;

import java.io.File;

import java.io.FileWriter;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import org.jsoup.*;
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;


/**
 * @author Behrooz Hosseini Expert in Artificial Intelligence  Email:b.hooseini4u@gmail.com
 *   
 **/
public class ZipFinder {

	/**
	 * @param args
	 */
	
	@SuppressWarnings("null")
	public static int search(String Zip ,int Ex_type,  JProgressBar JPBZILLOW , String min, String max , ZillMessag Userlog ) {
		// Reading the html content
		//if type== 0 then buy if type =1 then sell if type =2 then rent 
		int natije=-1;
		int totalloops=0 ;
		//String Html_Content = null;
		String GrabedURL= new String("");
		boolean anyresult=false;
		try {
			if (Ex_type==0) // buy
			{
				GrabedURL = "http://www.zillow.com/homes/for_sale/" + Zip +"_rb/";
				// doc = Jsoup.connect("http://www.zillow.com/homes/for_sale/" + Zip+"_rb/").get(); //http://www.zillow.com/homes/for_rent/28110_rb/  http://www.zillow.com/homes/for_sale/98208_rb/
				//Runtime.getRuntime().exec("iexplore.exe http://www.realtor.com/realestateandhomes-search/"+ Zip);
			}
			else if (Ex_type==1)//sell
			{
				GrabedURL=  "http://www.zillow.com/homes/" + Zip +"_rb/";
				//doc = Jsoup.connect("http://www.zillow.com/homes/" + Zip+"_rb/").get();
				//Runtime.getRuntime().exec("iexplore.exe http://www.realtor.com/apartments/"+ Zip);
			}
			else if (Ex_type==2)// rent
			{
				GrabedURL =  "http://www.zillow.com/homes/for_rent/" + Zip +"_rb/";
				// doc = Jsoup.connect("http://www.zillow.com/homes/for_rent/" + Zip+"_rb/").get();
				//Runtime.getRuntime().exec("iexplore.exe http://www.realtor.com/apartments/"+ Zip);
			}
			//doc= Jsoup.connect(UrlContext).userAgent(UserAgent[RandVal]).maxBodySize(0).referrer("http://www.google.com").timeout(30*1000).ignoreHttpErrors(true).get();
			Document doc=Jsoup.connect(GrabedURL).maxBodySize(0).referrer("http://www.google.com").timeout(12*1000).ignoreHttpErrors(true).get();	
			//Runtime.getRuntime().exec("iexplore.exe "+ GrabedURL);		
			Node nextnode=null;
			Node  extnode=null;
			boolean noresults=false;
			if (doc.select("h3.zsg-content_collapsed").hasText())
				if(!doc.select("h3.zsg-content_collapsed").text().equals("No matching results..."))
			{noresults=true;}
				if (!noresults )
			{	
			String tedad_String=new String("");
			String tedad_txtString=new String(" ");
			Document Doc_akhar=null;
			Element elem1 =doc.body().getElementsByClass("zsg-pagination").first();
			int tedad=0;
			int TakPage=0;
			if (elem1!=null)
			{
			if (elem1.hasText())
			tedad= elem1.childNodeSize();
			}
			else
				{
				tedad=0;
				tedad_txtString="1";
				TakPage=1;
				}
			if (tedad >0)
			{
				nextnode= elem1.childNode((elem1.childNodeSize()-1));
				if (nextnode.toString().contains(">Next<") && nextnode.toString().contains("zsg-pagination-next")&& nextnode.toString().contains("onclick=\"SearchMain.changePage(2)"))
				{
					extnode= elem1.childNode((elem1.childNodeSize()-2)).childNode(0);
					tedad_String=extnode.outerHtml();
					Doc_akhar= Jsoup.parse(tedad_String);
					tedad_txtString=Doc_akhar.getElementsByTag("a").first().text();
				}
			}
			
			if ( tedad_txtString.matches("[0-9]+") )
			{
				totalloops= Integer.parseInt(tedad_txtString);
			//	UserAlliace jjAlliace= new UserAlliace();
				int FirstPhaseprogressVal=0;
				
		// **********************************Main Loop*********************************
				
				for (int ii=1; ii<=totalloops && natije!=0 ;ii++)

				{
					try{
					String UrlContext="";
				if ( TakPage==0)
				{
					FirstPhaseprogressVal=(Math.round((ii*100)/totalloops))-1;
					if (FirstPhaseprogressVal>0)
						JPBZILLOW.setValue(FirstPhaseprogressVal);
					//SwingUtilities.invokeLater();
					//	jjAlliace. (Math.round((ii/totalloops))*100);
					String Temp1=Doc_akhar.getElementsByTag("a").first().toString() ;
					String UrlContext0="http://www.zillow.com"+Temp1.substring(9, (Temp1.length()-7)); //makeAddress
					String UrlContext1="";
					String UrlContext2="";
                if (totalloops>9)
					{UrlContext1=UrlContext0.substring(0,(UrlContext0.indexOf("_p/")-2));}
					else 
					   {UrlContext1=UrlContext0.substring(0,(UrlContext0.indexOf("_p/")-1));}
					UrlContext2= Integer.toString(ii)+ UrlContext0.substring((UrlContext0.indexOf("_p/")),(UrlContext0.indexOf("SearchMain.changePage(")+22))+ Integer.toString(ii)+UrlContext0.substring((UrlContext0.indexOf(");return false;")),(UrlContext0.indexOf(");return false;")+15)) ;//
					UrlContext= UrlContext1+UrlContext2;
				}
				else  //Results are only one Page
				{UrlContext=GrabedURL;}
				//InfoGrabber infogrbs= new InfoGrabber();
				if (min.equals("") )
					    {min="10000";}
				else if (UserAlliace.AnySignremover( min).equals("") )
				        {min="10000";}
				if (max.equals(""))
					    {max="2500000";}
				else if (UserAlliace.AnySignremover(max).equals(""))
					    {max="2500000";}
				
				// Main  Extraction Procedure	
				natije= InfoGrabber.infoGrab(UrlContext, Ex_type, Zip, min,max, Userlog);
				
				if (natije==1)
				   {anyresult=true;}
				}
				catch (Exception Exc)
				{
					System.out.println("Error in Scraping big Loop"+Exc.getCause().getMessage());
				}
					finally
					{
						System.out.println(" Finally in Scraping big Loop: "+ii);	
					}
				}
			}
      } // if ("No Results")
		}
		catch ( Exception ex ) 
		{ 
			System.out.println(ex.getCause().getMessage());	
		JOptionPane.showMessageDialog(null,ex );	
			ex.printStackTrace();Userlog.addert(ex.toString());	
		}
		JPBZILLOW.setValue(100);
		if (natije==1 || anyresult)
		{ if (Ex_type==0) // Buy RadioBox
			{   FinalSale_write(Userlog);
				fileupdate(Zip,"C:\\Zill_Results\\Sale_Report\\Visited_Sale.csv",Userlog);
				//boolean ins_Successful =sqliteConn.Sale_insert_phase1("'"+zpidTxt+"','"+StreetAddressText+"','"+localityText+"','"+stateText+"' ,"+ ZipTextstrng+","+LatitudeTxt+","+LongitudeTxt+","+PriceText+","+alphabetremover( BedRoomsText) +","+alphabetremover(BathroomText) +","+areaSpaceText+",'"+statusTextString+"'");
			}
			else if (Ex_type==2) // Rent RadioBox  Update Visited_Rent.csv
			{   FinalRent_write(Userlog);
				fileupdate(Zip,"C:\\Zill_Results\\Rent_Report\\Visited_Rent.csv",Userlog);
				//boolean ins_Successful =sqliteConn.Rent_insert_Phase1("'"+zpidTxt+"','"+StreetAddressText+"','"+localityText+"','"+stateText+"' ,"+ ZipTextstrng+","+LatitudeTxt+","+LongitudeTxt+","+PriceText+","+alphabetremover(BedRoomsText) +","+ alphabetremover(BathroomText) +","+areaSpaceText+",'"+statusTextString+"'");
			}
		}
		else // natije !=1 and !anyresult
		{ if (Ex_type==0) // Buy RadioBox
		         {fileupdate(Zip,"C:\\Zill_Results\\Sale_Report\\MissedSale.csv",Userlog);}
		  else if (Ex_type==2) // Rent RadioBox
		         {fileupdate(Zip,"C:\\Zill_Results\\Rent_Report\\MissedRent.csv",Userlog);}
		}
	return natije ;
	}
   private static void FinalSale_write(ZillMessag Userlog) {
		UserAlliace.lblDontExit.setVisible(true);
		for (int i=0 ; i< UserAlliace.Sale_SQL_list.size(); i++)
		{sqliteConn.Sale_insert_phase1( UserAlliace.Sale_SQL_list.get(i) ,Userlog );}
		UserAlliace.lblDontExit.setVisible(false);
		UserAlliace.Sale_SQL_list.clear();
	}

	private static void FinalRent_write(ZillMessag Userlog ) {
		UserAlliace.lblDontExit.setVisible(true);
		for (int i=0 ; i< UserAlliace.Rent_SQL_list.size(); i++)
		{sqliteConn.Rent_insert_Phase1( UserAlliace.Rent_SQL_list.get(i),Userlog);}
		UserAlliace.lblDontExit.setVisible(false);
		UserAlliace.Rent_SQL_list.clear();
	}

	private static void fileupdate(String Zip,String file_location, ZillMessag userlog) {
		// TODO Auto-generated method stub
		try{
		File Rent_Search_File = new File(file_location);
		FileWriter fw  = new FileWriter( Rent_Search_File, true);
		fw.write(Zip+","+"\n");
		fw.close();
		}
		catch (Exception Exc){ userlog.addert("could not find the"+file_location+"for the zip"+Zip+"\n"+Exc.toString()); }//JOptionPane.showMessageDialog(null, Exc);}
		  


	}

}
