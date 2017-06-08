
/**
 * @author Behrooz Hosseini Expert in Artificial Intelligence  Email:b.hooseini4u@gmail.com
 *   
 **/
package Zippy;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import Zippy.UserAlliace;
//import Zippy.UserAlliace;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class InfoGrabber {
	public static String Commaremover(String inputstring){
		 while (inputstring.contains(","))
		 {
			 String sect1=inputstring.substring(0, inputstring.indexOf(",")); 
			 String sect2=inputstring.substring((inputstring.indexOf(",")+1), inputstring.length());
			 inputstring=sect1+sect2;
			 
		 }
		 return inputstring;
	}
	public static void logfilewrite(String Filename ,String data) {
		try{
			File Log_File = new File(Filename);
			FileWriter fw  = new FileWriter( Log_File, true);
			fw.write(data+"\n");
			fw.close();
			}
			catch (Exception Exc){ }//JOptionPane.showMessageDialog(null, Exc);}

		
	}
	public static String spaceremover(String tetstString)
	{
		while(tetstString.contains(" "))
		  {
			  
			  tetstString= tetstString.substring( 0,tetstString.indexOf(" "))+tetstString.substring( (tetstString.indexOf(" ")+1),tetstString.length());						 
		  }
		return tetstString ;
	}
	public static String alphabetremover ( String inputstring)
		{String value = inputstring.replaceAll("[^0-9.]","");
		 return value;
	}
	public static String AnySignremover(String inputstring){
		int i=0; 
		while (!(inputstring.matches("[0-9]+"))&& (inputstring.length()!=0))
		 {// int chk=inputstring.length();
			if ((inputstring.charAt(i)	>= '0') && (inputstring.charAt(i)<= '9' ))
				 { 
					i++;
				 }
				 else
				 { 
					// String sect1=inputstring.substring(0, i); 
					// String sect2=inputstring.substring(i+1, inputstring.length());
					 inputstring=inputstring.substring(0, i)+inputstring.substring(i+1, inputstring.length());
					 i=0;
				 }
			  }
		if ((inputstring.length()==0))
			{inputstring="00";}
		 return inputstring;
	}
	@SuppressWarnings("null")
	public static int infoGrab(String UrlContext, int Ex_type, String Zip,String Minvl, String Maxvl, ZillMessag User_Log ) {
		// Reading the html content
		//if type== 0 then buy if type =1 then sell if type =2 then rent 
			int natije=0;
			
					//String Html_Content = null;
		
		//Document doc = new Document("") ;
		try {
			/*URLConnection connection = null;
			connection =  new URL(UrlContext).openConnection();
		  Scanner scanner = new Scanner(connection.getInputStream());
		  scanner.useDelimiter("\\Z");*/
		 // Html_Content = scanner.next();
		  //System.out.println( Html_Content);
		 // scanner.close();
			ArrayList<String> Errorlist = new ArrayList<String>();
			int i = 0;
		    boolean success = false;
	     String [] UserAgent = new String[10];
	     //Find More User Agent Stringsin http://www.useragentstring.com/pages/useragentstring.php
	     UserAgent[0]="Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
	     UserAgent[1]="Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.149 Safari/537.36";
	     UserAgent[2]="Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";// Google Bot
	     UserAgent[3]="Googlebot/2.1 (+http://www.google.com/bot.html)"; //Google Bot
	     UserAgent[4]="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246"; //Edge Browser
	     UserAgent[5]="Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Trident/4.0; Avant Browser; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30618; InfoPath.1)"; //Avant
	     UserAgent[6]="Opera/9.80 (Windows NT 6.0) Presto/2.12.388 Version/12.14";//Opera
	     UserAgent[7]="NetSurf/2.0 (RISC OS; armv3l)";//netSurf
	     UserAgent[8]="Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.8.1.8pre) Gecko/20070928 Firefox/2.0.0.7 Navigator/9.0RC1";//netScape
	     UserAgent[9]="Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.1) Gecko/20090722 Firefox/3.5.1 Orca/1.2 build 2"; //Orca
	     Random rand = new Random();

			int  RandVal = rand.nextInt(9);							 
			Document doc = null;
			
	     
		    while( i < 3){
		        try {
		        	doc= Jsoup.connect(UrlContext).userAgent(UserAgent[RandVal]).maxBodySize(0).referrer("http://www.google.com").timeout(30*1000).ignoreHttpErrors(true).get();
		            success = true;
		            break;
		        } catch (SocketTimeoutException ex){
		        	Errorlist.add("Exception  Timeout in "+i+" Times of Attempts ");               
		        }
		        catch (IOException e) {
		        }           
		        i++;
		    }

		    if(success){
		    	//Elements RentElements=doc.body().getElementsByClass("zest-value");
				if(doc.select("div.error-content-block").hasText())
				{
					String ErrorMess=doc.select("div.error-content-block").text();
					try{
			    		
			    		//String songFile ="piano.wav";
			    		//InputStream in = new FileInputStream(songFile);
			    		// create an audiostream from the inputstream
						
						InputStream in = InfoGrabber.class.getResourceAsStream("/piano.wav");
			    	    AudioStream audioStream = new AudioStream(in);

			    	    // play the audio clip with the audioplayer class
			    	    AudioPlayer.player.start(audioStream);
					}
			    	catch(Exception e)
			    	{
			    		System.out.println(e);
			    	}
					JOptionPane
					.showMessageDialog(
							null,
							"<html><h1>Unfortunately  This Robot is detected by the Zillow Anti Robot Checker </h1><br> <h2>it says: &nbsp;"+ErrorMess
							+"</h2> <h3> The rest of the program can be continued in another time</h3><br><h1> !!!!!!! The Program will be closed now!!!!!!! </html>",
							"Robot is Detected! ",
							JOptionPane.ERROR_MESSAGE );
					// UserAlliace. frmSearchingForReal.dispatchEvent(new WindowEvent(gui.frmSearchingForReal, WindowEvent.WINDOW_CLOSING));
					return -1;
					
				}
				else{
			//*********************************************************************************************************
			//Runtime.getRuntime().exec("iexplore.exe "+ UrlContext);
			//*********************************************************************************************************
			if (doc.body().childNodeSize()==0 || doc.equals(null)) 
			{
				JOptionPane
				.showMessageDialog(
						null,
						"<html>A problem with your internet connection !<br> The program is working right but the network connection has problem. <br> try another time </html>",
						"no records for this zip code was found",
						JOptionPane.WARNING_MESSAGE);
			}
			if(! doc.body().select("ul.photo-cards").isEmpty() )
			{
			if(doc.body().getElementsByClass("photo-cards").first().childNodeSize()>0)
			{   // Check IF all things goes right
			Element doc_elem =doc.body().getElementsByClass("photo-cards").first();
			List<Node> childsNode= doc_elem.childNodes();
						//Iterator <Node> Ch_iterator = childsNode.iterator();
			//String ddString =doc_elem.
			int AllofNodes =childsNode.size();
			String owlNode= new String("?????");
			String StreetAddressText = new String("?????");
			String stateText= new String("?????");
			String localityText= new String ("?????");
			String ZipTextstrng= new String("-1");
			String zpidTxt= new String("-1");
			String LatitudeTxt= new String("-1");
			String LongitudeTxt = new String ("-1");
			String PriceText= new String ("-1");
			String BedRoomsText= new String ("-1");
			String areaSpaceText=new String("-1");
			String BathroomText= new String ("-1");
			String RentSaleText= new String ("?????");
			String statusTextString= new String("?????");
			String HomeDetails_Link= new String("?????");
			List <String> PriceList= new ArrayList<String>();
			List <String> BedList= new ArrayList<String>();
			int Single_Case=1;
			/*     builtinString= new String("-1");
			String HOAstring= new String("-1");
			String daysonzillowString= new String("-1");
			String viewsSinceListingString= new String("-1");
			String Index2string= new String("-100000");
			String estimatedrent= new String ("-1");
			String Mortgagestring= new String("?????");
			String Cash_Flow= new String ("?????");
			String Avg_Price= new String ("?????");
			String Avg_CashFlow= new String ("?????");
			String Avg_Rent= new String ("?????");*/
			  
			
			if (Ex_type==2)// rent in Zillow
				RentSaleText="For Rent";
			if (Ex_type==0) //Buy in Zillow
				RentSaleText="For Sale";
			if (Ex_type==1) //Sale in Zillow
				RentSaleText="possible for sale/buy/Rent";
			if (AllofNodes!=0) 
			{	
			for (int indx=0 ; indx < AllofNodes ;indx++)
			{
				try{
				Node checkerNode=childsNode.get(indx);
				owlNode= checkerNode.outerHtml();
//				System.out.print(owlNode);
				//testing if the texts of address exists or not
				//zsg-photo-card-caption
				Boolean Testingtext=owlNode.contains("itemprop=\"streetAddress\"");
				if (Testingtext)
				{  Single_Case=1; //it is TRUE that it is a single case
				Document doc2=Jsoup.parse(owlNode);//,"",Parser.xmlParser());
				//Document doc4=Jsoup.parse(owlNode);
				
						
						Element extraDataElement=null;
						
						try{
  						  if (!doc2.body().select("a").isEmpty())
						    {HomeDetails_Link=doc2.body().select("a").first().attr("href");}
						}
						catch(Exception EX)
						{
						System.out.println("Error in Scraping HomeDetails_Link"+ EX );
						}
						
						try
						{
						if (!doc2.body().select("article").isEmpty())
						{extraDataElement= doc2.body().select("article").first();}
						
						
						zpidTxt="00000";
						if(extraDataElement!=null && ! (extraDataElement.attr("data-zpid").isEmpty() )) {
						zpidTxt=extraDataElement.attr("data-zpid"); 
						zpidTxt=zpidTxt.replaceAll("'", "");
						zpidTxt=zpidTxt.replaceAll(",", "");
						}
						
						
						LatitudeTxt="0.0";
						if (extraDataElement!=null && !(extraDataElement.getElementsByTag("meta").first().attr("content").isEmpty())) {
						Element latitudeElement=extraDataElement.getElementsByTag("meta").first();
						LatitudeTxt=spaceremover(latitudeElement.attr("content"));
						}
						
						
						LongitudeTxt="0.0";
						if (extraDataElement!=null && !(extraDataElement.getElementsByTag("meta").last().attr("content").isEmpty())){
						Element longitudeElement= extraDataElement.getElementsByTag("meta").last();
						LongitudeTxt=spaceremover(longitudeElement.attr("content"));
						}
						}
						catch(Exception EX)
						{
						System.out.println("Error in Scraping ZPID / Latitude / Longitude"+ EX );
						}
						
						try{
						statusTextString="?????";
						if (doc2.getElementsByClass("zsg-photo-card-status").size()>0)
						{
						 Element StatusElement=doc2.getElementsByClass("zsg-photo-card-status").first();
						 if(StatusElement!=null)
						 {
						 statusTextString= StatusElement.text();
						 statusTextString=statusTextString.replaceAll("'", "");
						 statusTextString=statusTextString.replaceAll(",", " ");
						 }
						 }
						}
						catch(Exception EX)
						{
						System.out.println("Error in Scraping RealEstate Status"+ EX );
						}
						
						try{
						PriceText="0";
						if (doc2.getElementsByClass("zsg-photo-card-price").size()>0 )
						{
							
						Element priceElement=doc2.getElementsByClass("zsg-photo-card-price").first();
								 PriceText= priceElement.text();
						}
						else if(doc2.getElementsByClass("zsg-photo-card-info").size()>0)
						{
							
							Element priceElement=doc2.getElementsByClass("zsg-photo-card-info").first();
									 PriceText= priceElement.text();
									 if(PriceText.contains("$"))
									 {
									 PriceText=PriceText.substring(PriceText.indexOf("$")+1,PriceText.length());
									 PriceText=PriceText.substring(0,PriceText.indexOf(" "));
									 }
									 else 
									 PriceText="0"; 
						}
						 while (PriceText.contains(","))
						 {
							 String pricetext_sec1=PriceText.substring(0, PriceText.indexOf(",")); 
							 String pricetext_sec2=PriceText.substring((PriceText.indexOf(",")+1), PriceText.length());
							 PriceText=pricetext_sec1+pricetext_sec2;
						 }
						 if (PriceText.contains("K")){
								 String rrd=PriceText.substring(0,PriceText.indexOf("K"));
						          PriceText=rrd+"000";
						          }
						 if (PriceText.contains("k")){
							 String rrd=PriceText.substring(0,PriceText.indexOf("k"));
					          PriceText=rrd+"000";
					          }
						 PriceText=AnySignremover(PriceText);
						 if (PriceText.equals(""))
						 PriceText="0";
						
						}
						catch(Exception EX)
						{
						System.out.println("Error in Scraping HomeDetails_Link"+ EX );
						}
						
						try{
						BedRoomsText="0";
						if (doc2.getElementsByClass("zsg-photo-card-info").hasText() )
						{
						org.jsoup.select.Elements BedroomElement= doc2.getElementsByClass("zsg-photo-card-info");
						  String BedRoomsTextsets= BedroomElement.text();
						  if (BedRoomsTextsets.contains("bds") )
						  {
							  if (BedRoomsTextsets.substring(0, BedRoomsTextsets.indexOf("bds")+3).length()>6)
								  BedRoomsText= BedRoomsTextsets.substring(BedRoomsTextsets.indexOf("bds")-3, BedRoomsTextsets.indexOf("bds")+3 );
							  else
							  BedRoomsText= alphabetremover(BedRoomsTextsets.substring(0, BedRoomsTextsets.indexOf("bds")+3 ));
							  BedRoomsText=alphabetremover(BedRoomsText);
							  if (BedRoomsText.equals("")){BedRoomsText="-1" ;}
						  }
						  BathroomText="0";
						  if (BedRoomsTextsets.contains("ba")  )
						  {
							  if(BedRoomsTextsets.substring(BedRoomsTextsets.indexOf("bds")+4,BedRoomsTextsets.indexOf("ba")+2).length()>6)
								  BathroomText=BedRoomsTextsets.substring(BedRoomsTextsets.indexOf("ba")-4, BedRoomsTextsets.indexOf("ba")+2);
							  else 
								  BathroomText=BedRoomsTextsets.substring(BedRoomsTextsets.indexOf("bds")+6, BedRoomsTextsets.indexOf("ba")+2);
							  if (BathroomText.contains("·"))
							  {
								  String TT1= BathroomText.substring( 0,BathroomText.indexOf("·"));
								  String TT2=BathroomText.substring( (BathroomText.indexOf("·")+1),BathroomText.length());
								  BathroomText= alphabetremover(TT1+TT2); 
							  }
							  BathroomText=alphabetremover(BathroomText);
							  if (BathroomText.equals("")){BathroomText="-1";}
						  }
						  areaSpaceText="0";
						  if (BedRoomsTextsets.contains("sqft"))
						  {
							  if (BedRoomsTextsets.substring(BedRoomsTextsets.indexOf("ba")+5, BedRoomsTextsets.indexOf("sqft")+4).length()<12)
								  areaSpaceText=BedRoomsTextsets.substring(BedRoomsTextsets.indexOf("ba")+5, BedRoomsTextsets.indexOf("sqft")+4);
							  else 
								  areaSpaceText=BedRoomsTextsets.substring(BedRoomsTextsets.indexOf("sqft")-7, BedRoomsTextsets.indexOf("sqft")+4);
							  while(areaSpaceText.contains(","))
							  {
								  String SS1= areaSpaceText.substring( 0,areaSpaceText.indexOf(","));
								  String SS2=areaSpaceText.substring( (areaSpaceText.indexOf(",")+1),areaSpaceText.length());
								  areaSpaceText= SS1+SS2;						 
							  }
							  areaSpaceText= AnySignremover(areaSpaceText);
							  
						  }
						  if (BedRoomsTextsets.contains("acre")||BedRoomsTextsets.contains("acres")||BedRoomsTextsets.contains("ac"))
						  {
							  areaSpaceText="";
		 					    
							  for(int gh=0;gh<BedRoomsTextsets.length();gh++)
							  {
								  if (BedRoomsTextsets.charAt(gh)=='.' || ((BedRoomsTextsets.charAt(gh)>='0' )&& (BedRoomsTextsets.charAt(gh)<= '9')))

								  areaSpaceText=areaSpaceText+BedRoomsTextsets.charAt(gh);						 
							  }
                              // need to convert to sqft
							  Double acres_valDouble= Double.parseDouble(areaSpaceText);
							  Long multVaDouble=Math.round((acres_valDouble*43560));
							  areaSpaceText=(new DecimalFormat("#").format(multVaDouble)).toString();
							  areaSpaceText=AnySignremover(areaSpaceText);
						  }
						  
						}
						}
						catch(Exception EX)
						{
						System.out.println("Error in Scraping Bathroom / Bedroom / SQFT"+ EX );
						}
						
						Element resultelem =doc2.getElementsByClass("hide").first();
						
						try{                    
							StreetAddressText="?????";
						if (resultelem!=null && resultelem.childNodeSize()>0 )
							if (resultelem.child(0).hasText() )
								StreetAddressText=resultelem.child(0).text();
							if (StreetAddressText.contains("'")||StreetAddressText.contains(","))
								{StreetAddressText=StreetAddressText.replaceAll("'", "");
								 StreetAddressText=StreetAddressText.replaceAll(",", "");
								}
					}
					catch(Exception EX)
					{
					System.out.println("Error in Scraping StreetAddressText "+ EX );
					}

						try{	
							localityText="?????";
						if (resultelem!=null && resultelem.childNodeSize()>1 )
							if (resultelem.child(1).hasText())
							{
								localityText=resultelem.child(1).text();
								String r1=localityText.substring(0,1).toUpperCase();
								String r2=localityText.substring(1).toLowerCase();
								localityText=r1+r2;
								localityText=localityText.replaceAll("'", "");
								localityText=localityText.replaceAll(",", "");
							}
						}
						catch(Exception EX)
						{
						System.out.println("Error in Scraping localityText "+ EX );
						}
						
						
						try{
						stateText="?????";
						if (resultelem!=null && resultelem.childNodeSize()>2 )
							if (resultelem.child(2).hasText())
								{stateText=resultelem.child(2).text();
								stateText.replaceAll(",", "");
								stateText.replaceAll("'", "");}
						}
						catch(Exception EX)
						{
						System.out.println("Error in Scraping stateText "+ EX );
						}
						
						try{
						ZipTextstrng="?????";
						if (resultelem!=null && resultelem.childNodeSize()>3 )
							if (resultelem.child(3).hasText()){
								ZipTextstrng=resultelem.child(3).text();
								ZipTextstrng=AnySignremover(ZipTextstrng);
								}
						if (ZipTextstrng=="?????"){ZipTextstrng="0";}
					}
					catch(Exception EX)
					{
					System.out.println("Error in Scraping  ZipTextstrng"+ EX );
					}
						
						
							//Mortgagestring="00$/mo";
						 
							// UserAlliace jjAlliace= new UserAlliace();
							 int pricecheck=Integer.parseInt(AnySignremover(PriceText));
							 int Min_val=Integer.parseInt(AnySignremover(Minvl ));
							 int Max_val=Integer.parseInt(AnySignremover(Maxvl));
						if ( Integer.parseInt(Zip)==Integer.parseInt(ZipTextstrng)&&((pricecheck>=Min_val) &&(pricecheck<=Max_val)&& (RentSaleText.equals("For Sale")&& (!statusTextString.contains("Rent")))))
						{ 	
							try {UserAlliace.Add_Sale_SQL ("'"+zpidTxt+"','"+StreetAddressText+"','"+localityText+"','"+stateText+"' ,"+ ZipTextstrng+","+LatitudeTxt+","+LongitudeTxt+","+PriceText+","+ BedRoomsText +","+BathroomText +","+areaSpaceText+",'"+statusTextString+"'"+",'"+HomeDetails_Link+"'");
								natije=1;} 
							catch (Exception e) {System.out.println(e.getCause().getMessage());e.printStackTrace();JOptionPane.showMessageDialog(null,e );}
						}
						else if ( Integer.parseInt(Zip)==Integer.parseInt(ZipTextstrng)&&(RentSaleText.equals("For Rent"))){
							
							try {UserAlliace.Add_Rent_SQL("'"+zpidTxt+"','"+StreetAddressText+"','"+localityText+"','"+stateText+"' ,"+ ZipTextstrng+","+LatitudeTxt+","+LongitudeTxt+","+PriceText+","+alphabetremover(BedRoomsText) +","+ alphabetremover(BathroomText) +","+areaSpaceText+",'"+statusTextString+"'"+",'"+HomeDetails_Link+"'"+","+Single_Case);
								natije=1;}
							catch (Exception ex){System.out.println(ex.getCause().getMessage()); JOptionPane.showMessageDialog(null,ex );}
						}
						else if (Integer.parseInt(Zip)!=Integer.parseInt(ZipTextstrng)){natije=0; break;}
						
			}
				else  //This is an appartment with different bed and bath options
				{  
					Document doc2=Jsoup.parse(owlNode);
					Single_Case=0; // It is not a single case SingleCase= False
					Element extraDataElement=null;
					try{
					if (!doc2.body().select("article").isEmpty())
					{extraDataElement= doc2.body().select("article").first();}
					}
					catch(Exception ex)
					{
						System.out.println("Error in extraDataElement "+ex);
					}
					
					
					try{
					if (!doc2.body().select("a").isEmpty())
					{HomeDetails_Link=doc2.body().select("a").first().attr("href");}
					}
					catch(Exception ex)
					{
						System.out.println("HomeDetails_Link: "+ex);
					}
					zpidTxt="";
					try{
					if(extraDataElement!=null && !(extraDataElement.attr("data-zpid").isEmpty() )) 
					{  
					   zpidTxt=extraDataElement.attr("data-zpid"); 
					   zpidTxt=zpidTxt.replaceAll("'", "");
					   zpidTxt=zpidTxt.replaceAll(",", "");
					 }
					}
					catch(Exception ex)
					{
						System.out.println("zpidTxt: "+ex);
					}
					
					
					try{
					LatitudeTxt="0.0"; //data-latitude
					if (extraDataElement!=null &&!extraDataElement.attr("data-latitude").isEmpty()) {
					LatitudeTxt=spaceremover(extraDataElement.attr("data-latitude"));
					}
					}
					catch(Exception ex)
					{
						System.out.println("LatitudeTxt: "+ex);
					}
					
					
					try{
					LongitudeTxt="0.0";
					if (extraDataElement!=null &&!extraDataElement.attr("data-longitude").isEmpty()) {
					LongitudeTxt=spaceremover(extraDataElement.attr("data-longitude"));
					}
					}
					catch(Exception ex)
					{
						System.out.println("LongitudeTxt: "+ex);
					}
					
					
					
					try 
					{
					statusTextString="?????";
					if (doc2.getElementsByClass("zsg-photo-card-status").size()>0)
					{
					 Element StatusElement=doc2.getElementsByClass("zsg-photo-card-status").first();
					 statusTextString= StatusElement.text();
					 statusTextString=statusTextString.replaceAll("'", "");
					 statusTextString=statusTextString.replaceAll(",", " ");
					 }
					}
					catch(Exception ex)
					{
						System.out.println("statusTextString: "+ex);
					}
					
					
					//zsg-photo-card-address
					if (doc2.select("span.zsg-photo-card-address").hasText())
					{String [] AddressArray=doc2.select("span.zsg-photo-card-address").text().split(",");
						try{
						
						StreetAddressText="?????";
						StreetAddressText=UserAlliace.FirstSpaceRemover(AddressArray[0]);
						if (StreetAddressText.contains("'")||StreetAddressText.contains(","))
						{StreetAddressText=StreetAddressText.replaceAll("'", "");
						 StreetAddressText=StreetAddressText.replaceAll(",", "");
						}
						}
						catch(Exception ex)
						{
							System.out.println("StreetAddressText: "+ex);
						}
						
						
						try{
						localityText="?????";
						localityText=UserAlliace.FirstSpaceRemover( AddressArray[1]);
						String r1=localityText.substring(0,1).toUpperCase();
						String r2=localityText.substring(1).toLowerCase();
						localityText=r1+r2;
						localityText=localityText.replaceAll("'", "");
						localityText=localityText.replaceAll(",", "");
						}
						catch(Exception ex)
						{
							System.out.println("localityText: "+ex);
						}
						
						try{
						stateText="?????";						
						stateText=AddressArray[2];	
						stateText=stateText.replaceAll(" ", "");
						stateText.replaceAll(",", "");
						stateText.replaceAll("'", "");
						}
						catch(Exception ex)
						{
							System.out.println("stateText: "+ex);
						}
						
						//span class="zsg-photo-card-info
						
						try{
						BedList.clear();
						PriceList.clear();
						Element priceStat= doc2.select("div.zsg-photo-card-caption").select("span.zsg-photo-card-info").first();
						Elements contexts= priceStat.getElementsByClass("zsg-photo-card-unit");
						for (int RT=0;RT<contexts.size();RT++)
						{ if(contexts.get(RT).select("strong").hasText())
						{
							BedList.add(contexts.get(RT).select("strong").text());
							PriceList.add(AnySignremover(BedRemover(contexts.get(RT).text(),BedList.get(RT))));
						}
						else 
						{
							BedList.add("0");
						if(contexts.get(RT).hasText())
							{PriceList.add(AnySignremover(BedRemover(contexts.get(RT).text(),"" )));}
						else 
							PriceList.add("0");
						}
						}
						}
						catch(Exception ex)
						{
							System.out.println("BED / PRICE: "+ex);
						}
						ZipTextstrng="0";
						/*@SuppressWarnings("unused")
						String rawdata="";
						 if(doc2.select("div.minibubble.template.hide")!=null )
						 { Elements RawD=doc2.select("div.minibubble.template.hide");
						 rawdata=RawD.first().toString();
						 int  y=0;
						 }
						*/
					}
					for(int ind=0;ind<PriceList.size();ind++){
					if ((RentSaleText.equals("For Sale")&& (!statusTextString.contains("Rent")&& !(zpidTxt.equals("")))))
					{ 	
						try {
							UserAlliace.Add_Sale_SQL ("'"+zpidTxt+"','"+StreetAddressText+"','"+localityText+"','"+stateText+"' ,"+ ZipTextstrng+","+LatitudeTxt+","+LongitudeTxt+","+PriceList.get(ind)+","+ alphabetremover(BedList.get(ind)) +","+BathroomText +","+areaSpaceText+",'"+statusTextString+"'"+",'"+HomeDetails_Link+"'");
							natije=1;
							} 
						catch (Exception e) {System.out.println(e.getCause().getMessage());e.printStackTrace();JOptionPane.showMessageDialog(null,e );}
					}
					else if ( RentSaleText.equals("For Rent")&& !(zpidTxt.equals(""))){
						
						try {
							UserAlliace.Add_Rent_SQL("'"+zpidTxt+"','"+StreetAddressText+"','"+localityText+"','"+stateText+"' ,"+ ZipTextstrng+","+LatitudeTxt+","+LongitudeTxt+","+PriceList.get(ind)+","+alphabetremover(BedList.get(ind)) +","+ alphabetremover(BathroomText) +","+areaSpaceText+",'"+statusTextString+"'"+",'"+HomeDetails_Link+"'"+","+Single_Case);
							natije=1;
							}
						catch (Exception ex){JOptionPane.showMessageDialog(null,ex );}
					}
					}
				}
				System.out.println("Success in loop with index: "+ indx);	
				if (indx>=AllofNodes){break;}
				
				}
				catch(Exception Ex)
				{
					System.out.println("Error in Scraping with index "+indx +"  "+Ex);
					
				}
				
				finally 
				{
						
				}
				}//end of for
			
			}
			else 
			{natije=0;} // not found
			}  //if all things goes right 
		}
		    }   //problem in connection
		    } //if (success)
		    else // Unsuccessful Jsoup.connect
		    {
		    	JOptionPane.showMessageDialog(null,"<html><h1>Problems with internet Connection happened : <br>"+Errorlist+"</h1></html>");
		    	
		    }
		}
		catch ( Exception ex ) {
			System.out.println(ex.getCause().getMessage());
		    ex.printStackTrace();
		    User_Log.addert(ex.toString());
		    
		    if(Ex_type!=2)
	    	{
		    	System.out.println(" ERRORR --> in Sale for: Zip:"+Zip+ "  becauseof: "+ex.toString());
		    	logfilewrite("C:\\Zill_Results\\Log_Files\\SaleTotalLOG.csv"," ERRORR --> in Sale for: Zip:"+Zip+ "  becauseof: "+ex.toString());
		    }
			 else
			{
				 System.out.println(" ERRORR --> in Rent for: Zip:"+Zip+ "  becauseof: "+ex.toString());
				 logfilewrite("C:\\Zill_Results\\Log_Files\\RentTotalLOG.csv"," ERRORR --> in Rent for: Zip:"+Zip+ "  becauseof: "+ex.toString());
            }
	
		    //JOptionPane.showMessageDialog(null,ex );
		}
		finally
		{
			System.out.println("Finally  in InfoGrab Loop Finished: "+Zip );	
		}
		  // attr("class=\"listing-street-address\" itemprop=\"streetAddress\"");
		// return one if any data was found or Zero if no data was grabbed
		
		return natije ;
	}
	private static String BedRemover(String Input, String DeletingChar) {
		// TODO Auto-generated method stub
		
		return Input.substring(DeletingChar.length())  ;
	}

}
//if (!ins_Successful){JOptionPane.showMessageDialog(null,"Error in Connection With Data base", "Records were not saved successfully in the Data base Sth gone wrong  try again for  Zipcode:",JOptionPane.ERROR_MESSAGE); }
/*//boolean firstTimeRent=true;
boolean checkfirstfilerent=true;
File Rent_Search_File = new File("Rent_Search_Results.csv");
if(!Rent_Search_File.exists()) 
{
	try {
		Rent_Search_File.createNewFile();
	} 
	catch (IOException e) 
	{
		System.out.println(e.getCause().getMessage());
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,e );
	}
} else 
{
checkfirstfilerent=false;
}
FileWriter fw  = new FileWriter( Rent_Search_File, true);
natije=1; 
if (checkfirstfilerent){//  && firstTimeRent){
	fw.write(UserAlliace.SAR_Head_File);
	//firstTimeRent=false;
}
//"ZPID (Zillow_ID),     Address,        Locality,       State,     ZipCode,    Latitude,    Longitude,    Estimated Price / Rent ,    Bedrooms,  Bathrooms,  Area Space,   Status,  Estimated Rent (/mo),  Year Built,   HOA Fee,   Days on Zillow,  Views Since Listing, Index2,  Mortgage,  Rent/Sale, Avg Index2 , Cash Flow, Avg Price, Avg CashFlow, Avg Rent, Price per SQFT,Avg Price/SQFT, Standard Deviation psqrft,   Standard Deviation Price, School, MLS"+"\n"
fw.write(zpidTxt+","+StreetAddressText + "," + localityText+","+ stateText +","+ZipTextstrng+","+ LatitudeTxt +","+LongitudeTxt +","+ PriceText +","+BedRoomsText+","+ BathroomText+","+areaSpaceText+" sqft" +","+ statusTextString +","+estimatedrent+","+builtinString+","+HOAstring+","+daysonzillowString +","+ viewsSinceListingString+","+Index2string+","+Mortgagestring+","+RentSaleText+","+"0"+","+Cash_Flow+","+ Avg_Price+","+Avg_CashFlow+","+Avg_Rent+",0,0,0,0,?????,0"+"\n");

fw.close();	*/

//comma dare moshke ijad mikone
//"ZPID"," Address"," Locality"," State"," ZipCode"," Latitude"," Longitude","Price"," Bedrooms"," Bathrooms"," AreaSpace_SQFT"," Status"," EstimatedRent"," Year Built"," HOAFee"," DaysOnZillow"," ViewsSinceListing"," Index2","Schools","MLS_NO") 
//"',"+ estimatedrent+","+ builtinString+","+ HOAstring+","+daysonzillowString +"," +viewsSinceListingString +"," + Index2string+ ",'test Schools' , 'testMLS'"
//if (!ins_Successful){JOptionPane.showMessageDialog(null,"Error in Connection With Data base", "Records were not saved successfully in the Data base Sth gone wrong  try again for  Zipcode:",JOptionPane.ERROR_MESSAGE); }
//	zpidTxt+","+StreetAddressText + "," + localityText+","+ stateText +","+ZipTextstrng+","+ LatitudeTxt +","+LongitudeTxt +","+ PriceText +","+BedRoomsText+","+ BathroomText+","+areaSpaceText+" sqft" +","+ statusTextString +","+estimatedrent+","+builtinString+","+HOAstring+","+daysonzillowString +","+ viewsSinceListingString+","+Index2string+","+Mortgagestring+","+RentSaleText+","+"0"+","+Cash_Flow+","+ Avg_Price+","+Avg_CashFlow+","+Avg_Rent+",0,0,0,0,?????,0"+"\n");
/*	//Boolean firstTimeSale= true; 
boolean checkfirstfilesale=true; 
File Zip_Search_File = new File("Sale_Search_Results.csv");
if(!Zip_Search_File.exists()) 
{
	try {
		Zip_Search_File.createNewFile();
	} 
	catch (IOException e) 
	{
		System.out.println(e.getCause().getMessage());
		JOptionPane.showMessageDialog(null,e );
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
} else 
{
	checkfirstfilesale=false;
}
FileWriter fw = new FileWriter(Zip_Search_File, true);

jjAlliace.setSth(1);
if (checkfirstfilesale){// && firstTimeSale){
fw.write(UserAlliace.SAR_Head_File);
//firstTimeSale=false;
}
//"ZPID (Zillow_ID),     Address,        Locality,       State,     ZipCode,    Latitude,    Longitude,    Estimated Price / Rent ,    Bedrooms,  Bathrooms,  Area Space,   Status,  Estimated Rent (/mo),  Year Built,   HOA Fee,   Days on Zillow,  Views Since Listing, Index2,  Mortgage,  Rent/Sale, Avg Index2 , Cash Flow, Avg Price, Avg CashFlow, Avg Rent, Price per SQFT,Avg Price/SQFT, Standard Deviation psqrft,   Standard Deviation Price, School, MLS"+"\n"
fw.write(zpidTxt+","+StreetAddressText + "," + localityText+","+ stateText +","+ZipTextstrng+","+ LatitudeTxt +","+LongitudeTxt +","+ PriceText +","+BedRoomsText+","+ BathroomText+","+areaSpaceText+" sqft" +","+ statusTextString +","+estimatedrent+","+builtinString+","+HOAstring+","+daysonzillowString +","+ viewsSinceListingString+","+Index2string+","+Mortgagestring+","+RentSaleText+","+"0"+","+Cash_Flow+","+ Avg_Price+","+Avg_CashFlow+","+Avg_Rent+",0,0,0,0,?????,0"+"\n");
fw.close();*/