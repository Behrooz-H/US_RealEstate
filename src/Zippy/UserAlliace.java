
/**
 * @author Behrooz Hosseini PhD in Artificial Intelligence  Email:b.hooseini4u@gmail.com 
 **/
package Zippy;

import java.awt.EventQueue;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

import java.io.BufferedReader;
import java.io.File;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.LineNumberReader;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractButton;

import java.awt.Color;

import javax.swing.JProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


//import java.sql.*;

public class UserAlliace {
	public enum header { ZPID, Address, Locality, State, ZipCode, Latitude, Longitude, Price , Bedrooms, Bathrooms, AreaSpace_SQFT, Status, Estimated_Rent, Year_Built, HOAFee, Days_On_Zillow, Views_Since_Listing, Index2, Mortgage, Rent_Sale, Avg_Index2 , Cash_Flow, Avg_Price, Avg_CashFlow, Avg_Rent, Price_Per_SQFT,Avg_Price_PerSQFT, StdDev_PerSQFT, StdDev_Price, Schools, MLSNo }
	public JButton btnMortgageButton ;
	public JLabel lblfinish1 ;public JLabel lblfinalized;
	public JLabel lblState;
	public JButton Omit_Button;
    public JFrame frmSearchingForReal;
	public JLabel waitLabel;
	public JLabel lblZipsSeen ;
	public JLabel lblShowsHowMuch;
	public JProgressBar progressBarZillow;
	public JProgressBar TotalprogressBar;
	public JFormattedTextField Min_formattedTextField;
	public JFormattedTextField Max_formattedTextField;
	public JRadioButton Buy_RadioButton; 
	public JRadioButton Rent_RadioButton_2;
	public JRadioButton Sell_RadioButton;
	public int progressVal=0; 
    public int final_radio_resBut=0;
    public int result= 0;
    public int Sth_founded=0; 
    public String enteredTXtString =new String ("");
    public static String interestrateString;
    public static String downPaymentString;
    public static String return_PeriodString;
    public static String insuranceString;
    public static String mortgageString;
    public String estimatedrent=new String ("?????");
    String builtinString= new String("?????");
    String HOAstring= new String("?????");
    String daysonzillowString= new String("-1");
    String viewsSinceListingString= new String("?????");
    String Index2string= new String("?????");
    String Near_Schools= new String("?????");
    String MLS= new String ("");
    public static final String SAR_Head_File="ZPID (Zillow_ID), Address, Locality, State, ZipCode, Latitude, Longitude, Estimated Price/Rent, Bedrooms, Bathrooms, Area Space, Status, Estimated Rent (/mo), Year Built, HOA Fee, Days on Zillow, Views Since Listing, Index2, Mortgage, Rent/Sale, Avg Index2 , Cash Flow, Avg Price, Avg CashFlow, Avg Rent, Price per SQFT,Avg Price/SQFT, StdDev/SQFT, StdDev Price, Schools, MLS#"+"\n";
    public JFormattedTextField OmitRate_TextField;
    public JLabel lblcurrentzip;
    public JLabel giflabel;
    private JLabel lblMax;
    public JLabel lblTotalProgress;
    public static JLabel lblDontExit;
    private JLabel lblminValueDefault;
    public static  List<String> Sale_SQL_list= new  Vector<String>();
    public  static List<String> Rent_SQL_list = new  Vector<String>();
    public JComboBox<String> comboBoxABV = new JComboBox<String>();
    
    public String Return_Zip_TextBox()
    {return enteredTXtString ;     }
    /**
	 * Launch the application.
	 */
    public void setSth (int r)
    {  Sth_founded=r; }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Path path = Paths.get("C:\\Zill_Results"); // It  checks if the Folder for the results exists or not
					if (!Files.exists(path)) 
					   {Files.createDirectories(path);}
					Path path2 = Paths.get("C:\\Zill_Results\\Price_Average_PerZip"); // It  checks if the Folder for the results exists or not
					if (!Files.exists(path2)) 
					   {Files.createDirectories(path2);}
					path2 = Paths.get("C:\\Zill_Results\\Log_Files"); // It  checks if the Folder for the results exists or not
					if (!Files.exists(path2)) 
					   {Files.createDirectories(path2);}
					path2 = Paths.get("C:\\Zill_Results\\Rent_Report"); // It  checks if the Folder for the results exists or not
					if (!Files.exists(path2)) 
					   {Files.createDirectories(path2);}
					path2 = Paths.get("C:\\Zill_Results\\Sale_Report"); // It  checks if the Folder for the results exists or not
					if (!Files.exists(path2)) 
					   {Files.createDirectories(path2);}
				
					UserAlliace window = new UserAlliace();
					window.frmSearchingForReal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getCause().getMessage());
					JOptionPane.showMessageDialog(null,e );
				}
			}
		});
	}

	/**
	 * Creating the application. The application will be initialized here
	 */
	public UserAlliace() {	initialize();}
	
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons
				.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
				return button.getText();
			}
		}
		return null;
	}
	// Updating GUI
	public void updateGUI() {
		   if (!SwingUtilities.isEventDispatchThread()) {
		     SwingUtilities.invokeLater(new Runnable() {
		       @Override
		       public void run() {
		          updateGUI();
		       }
		     });
		   }
		   //The  gui objects are editted here
		   frmSearchingForReal.getContentPane().repaint();   
		}
	public static String Commaremover(String inputstring){
		 while (inputstring.contains(","))
		 { inputstring=inputstring.substring(0, inputstring.indexOf(","))+inputstring.substring((inputstring.indexOf(",")+1), inputstring.length()); 
		 }
		 return inputstring;
	}
	public static void Add_Sale_SQL(String InString){
		Sale_SQL_list. add(InString);
	}
	public static void Add_Rent_SQL(String InString){
		Rent_SQL_list.add(InString);
	}
	public static String spaceremover(String tetstString)  // A function for removing possible spaces
	{while(tetstString.contains(" "))
		  {tetstString= tetstString.substring( 0,tetstString.indexOf(" "))+tetstString.substring( (tetstString.indexOf(" ")+1),tetstString.length());						 
		  }
	return tetstString ;}
	public static String dollarremover(String inputstring) {
		 while (inputstring.contains("$"))
		 { inputstring=inputstring.substring(0, inputstring.indexOf("$"))+inputstring.substring((inputstring.indexOf("$")+1), inputstring.length()); 
		 }
		 return inputstring;
	}
	public static String FirstSpaceRemover(String tetstString) // 
	{int i=0;
		for(i =0; i<tetstString.length() ;i++)
		  {	if (tetstString.charAt(i)!= ' ')
				break;  } 
		tetstString=tetstString.substring( i,tetstString.length());
		return tetstString ;
	}
	public static String spaceConvertToHyphen(String tetstString)
	{
		while(tetstString.contains(" "))
		  {
			  String SS1= tetstString.substring( 0,tetstString.indexOf(" "));
			  String SS2=tetstString.substring( (tetstString.indexOf(" ")+1),tetstString.length());
			  tetstString= SS1+"-"+SS2;						 
		  }
		return tetstString ;
	}
	public static String AnySignremover(String inputstring){
		int i=0; 
		while (!(inputstring.matches("[0-9]+"))&& (inputstring.length()!=0))
		 {	 if ((inputstring.charAt(i)	>= '0') && (inputstring.charAt(i)<= '9' ))
				 { 	i++;
				 }
				 else
				 { 	 inputstring=inputstring.substring(0, i)+inputstring.substring(i+1, inputstring.length());
					 i=0;
				 }		 
		 }
		if ((inputstring.length()==0))
			{inputstring="00";}
		 return inputstring;
	}
	public void extra_grab(String zpidTxt, String StreetAddress, String localityText, String stateText, String ZipTextstrng, String PriceText)
	{String URL_DetailString="http://www.zillow.com/homedetails/"+StreetAddress+"-"+localityText+"-"+stateText+"-"+ZipTextstrng+"/"+zpidTxt+"_zpid/";
		try {
			
		  /*if(Desktop.isDesktopSupported())
			{
			  Desktop.getDesktop().  browse(new URI(URL_DetailString)) ;
			}*/
		  //final WebClient webClient = new WebClient();
		  // final HtmlPage page = webClient.getPage(URL_DetailString);
		  //browser.setVisible(false);
			/*Browser_Simul_Beh gg = new Browser_Simul_Beh();
			gg.jav_webEngine.load(URL_DetailString);
			org.w3c.dom.Document RentDocl=gg.jav_webEngine.getDocument();*/
	/*		DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setJavascriptEnabled(true);
		    caps.setCapability(
		            PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		             //"/home/p/phantomjs-1.9.2-linux-x86_64/bin/phantomjs"
		        );
			WebDriver ghostDriver = new PhantomJSDriver(caps);*/
			/*File file = new File("chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			DesiredCapabilities capabilities = DesiredCapabilities.chrome() ;
			capabilities.setJavascriptEnabled(true);
			//capabilities.setCapability(ChromeDriver.  INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			WebDriver ghostDriver = new ChromeDriver (capabilities);
            //WebDriver ghostDriver = new PhantomJSDriver();
			ghostDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			ghostDriver.get(URL_DetailString);
			// inja bayad hatman dataha grab shavand
			WebElement hhElement= ghostDriver.findElement(BY)
			Document RentDoc= Jsoup.parse(ghostDriver.getPageSource());*/			
		//java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL_DetailString));
		//org.jsoup.select.Elements
			
		Document RentDoc=Jsoup.connect(URL_DetailString).timeout(10*1000).ignoreHttpErrors(true).get();
		Elements RentElements=RentDoc.body().getElementsByClass("zest-value");
		for (int ff=0;ff<RentElements.size();ff++){
			estimatedrent="0";
		if ( RentElements.get(ff).text().contains("/mo") )
		{	estimatedrent=  RentElements.get(ff).text();
			break;
		}
		}
		 estimatedrent=AnySignremover(estimatedrent);
		 String  factstrings="";
		 if (!(RentDoc.body().getElementsByClass("hdp-facts").isEmpty()) ){
	         Element FactElement=RentDoc.body().getElementsByClass("hdp-facts").first();
	         factstrings=FactElement.text();	
			builtinString="0";
		if (factstrings.contains("Built in "))
		{
			builtinString=factstrings.substring(factstrings.indexOf("Built in ")+9, factstrings.indexOf("Built in ")+13);	
		}
		     HOAstring="0";
		if (factstrings.contains("HOA Fee: "))
		{
			HOAstring=factstrings.substring(factstrings.indexOf("HOA Fee: ")+9, factstrings.indexOf("/mo")+3);
			HOAstring=Commaremover(HOAstring);
		}
     		MLS="0";
		if (factstrings.contains("MLS #:"))
		{
			int beg_indx=factstrings.indexOf("MLS #:")+7;
			int str_indx=beg_indx;
			while (factstrings.charAt(str_indx)!= ' '  )
				str_indx++;
			MLS=factstrings.substring(beg_indx,str_indx );
			MLS=Commaremover(MLS);
		}
		daysonzillowString="-1";
		if (factstrings.contains("days on Zillow"))
		{
			String teststring="";
	    	String test2="";
		
			for (int ind=2;ind<6;ind++){
			 test2=factstrings.substring((factstrings.indexOf("days on Zillow")-ind), factstrings.indexOf("days on Zillow")-(ind-1));
			if (test2.matches("[0-9]+"))
			{
				teststring=teststring+test2;
			}
			else if (test2.matches(","))
			{continue;}
			else 
			{break;}

			}
			teststring=Commaremover(teststring);
			StringBuilder dest = new StringBuilder(teststring.length());
			for (int ki=teststring.length()-1;ki>=0;ki--)
			{
				dest.append(teststring.charAt(ki));
			}
         daysonzillowString = dest.toString();
		}
		viewsSinceListingString="0";
		if (factstrings.contains("Views since listing: "))
		
			{
			String teststring1="";
			String teststring2="";
			for (int ind=21;ind<34;ind++)
			{
			 teststring1=factstrings.substring((factstrings.indexOf("Views since listing: ")+ind), (factstrings.indexOf("Views since listing: ")+ind+1));
			if (teststring1.matches("[0-9]+")){
				teststring2=teststring2+teststring1;
				continue;}
			else if (teststring1.matches(",")) 
				continue;
			else 
			  {break;}
				
			}
										
			viewsSinceListingString=teststring2;
		}
		}
		 Index2string="0";
		String rentvalueString= AnySignremover(estimatedrent);
		String pricevalue= AnySignremover(PriceText);
		 double index2=0.0;
		if ((Double.parseDouble(pricevalue))>0)
		index2=((1000*Double.parseDouble(rentvalueString))/(Double.parseDouble(pricevalue))); 
		Index2string=(new DecimalFormat("##.###").format(index2)).toString();
		
		 //****************SEARCHING FOR SCHOOLS***********************
		File School_File = new File("Neighbor_Schools.csv");
		FileWriter fw = null;
		int School_File_Count=0;
		int first_time_seen=0;
		if(!School_File.exists()) 
		{
			try {
				School_File.createNewFile();
				first_time_seen=1;
			} 
			catch (IOException e) 
			{
				System.out.println(e.getCause().getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e );
			}
		} 
		
		try {
			
			if (first_time_seen==1)
			{   fw = new FileWriter(School_File, true);
				fw.write("Zpid , Zip , Street Adress , Locality , State , School Rate (Out of 10), School Grades , School Distance, School Name, School Web URL "+"\n");
				fw.close();
			}
			else 
			{
				try {LineNumberReader  lnr2 = new LineNumberReader(new FileReader(new File("Neighbor_Schools.csv")));
				lnr2.skip(Long.MAX_VALUE);
				School_File_Count= lnr2.getLineNumber() ; 
				// Finally, the LineNumberReader object should be closed to prevent resource leak
				lnr2.close();}
	            catch (Exception exc)
	            {
	            	System.out.println(exc.getCause().getMessage());
	            	JOptionPane.showMessageDialog(null,exc );
	            }
			}
		} catch (IOException e) {
			System.out.println(e.getCause().getMessage());
			JOptionPane.showMessageDialog(null,e );
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String School_Info="";
		
		if ((RentDoc.body().getElementById("nearbySchools")!=(null)))

		{  
			if (RentDoc.body().getElementById("nearbySchools").outerHtml().contains("nearby-schools-rating"));	
			{
		fw = new FileWriter(School_File, true);
		Document school_doc= Jsoup.parse(RentDoc.body().getElementById("nearbySchools").outerHtml());
		Document School_sub_Elems= Jsoup.parse(school_doc.getElementsByClass("nearby-schools-list").outerHtml());
		Elements School_rate_list=School_sub_Elems.body().getElementsByClass("nearby-schools-rating");
		Elements School_list=School_sub_Elems.body().getElementsByClass("nearby-schools-info");
		for (int ii=1;ii<School_list.size();ii++)
		{
		String TempString ="";
		Element finelem=School_list.get(ii);
		Element ratelem=School_rate_list.get(ii);
		String School_Name=finelem.getElementsByClass("nearby-schools-name").get(0).child(0).text();
		String school_Web_UrlString=("www.zillow.com"+finelem.getElementsByClass("nearby-schools-name").get(0).child(0).attr("href")) ;
		String School_Grades=finelem.getElementsByClass("nearby-schools-grades").get(0).text();
		String School_Distance=finelem.getElementsByClass("nearby-schools-distance").get(0).text();
		String school_rate=ratelem.child(0).text();
		if (School_Grades.contains("-"))
			School_Grades=School_Grades.replaceFirst("-", " to ");
		
		TempString=School_Name+"$$"+school_rate+"$$"+school_Web_UrlString+"$$"+School_Grades+"$$"+School_Distance;
		try{
//"Zpid , Zip , Street Adress , Locality , State , School Rate (Out of 10), School Grades , School Distance, School Name, School Web URL ");
			if (!(first_time_seen==1) )
			{ BufferedReader buffschol = null;
			buffschol = new BufferedReader(new FileReader("Neighbor_Schools.csv"));
			buffschol.readLine();
			int School_existbefore=0;
				for (int g=0;g<(School_File_Count-2);g++)
			 {
					String bufstrString=buffschol.readLine();
					String [] checkschoolStrings=bufstrString.split(",");
					if(checkschoolStrings[0].equals(zpidTxt))
					{
						School_existbefore=1;
						break;
					}
					
			 }
				buffschol.close();
				if (School_existbefore==0)
				{
			fw.write(zpidTxt+","+ ZipTextstrng+","+StreetAddress+","+ localityText+","+ stateText+","+school_rate+","+School_Grades+","+School_Distance+","+School_Name+","+school_Web_UrlString+"\n");
				}
				
			}
			else {
				fw.write(zpidTxt+","+ ZipTextstrng+","+StreetAddress+","+ localityText+","+ stateText+","+school_rate+","+School_Grades+","+School_Distance+","+School_Name+","+school_Web_UrlString+"\n");
			}
			
			
		}
		catch(Exception ex)
		{JOptionPane.showMessageDialog(null,ex );}
		School_Info+=TempString+"@@@";
		}
		
		fw.close();
		}}
		else
		{
			School_Info="?????";
		}
		
		Near_Schools=School_Info;
	}
		catch( Exception ex ) {
		    ex.printStackTrace();
		    System.out.println(ex.getCause().getMessage());
		    
		    //JOptionPane.showMessageDialog(null,ex );
		}
		
		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmSearchingForReal = new JFrame();
		frmSearchingForReal.getContentPane().setBackground(
				new Color(230, 230, 250));
		frmSearchingForReal.getContentPane().setLayout(null);
		lblShowsHowMuch = new JLabel("<html>Zips Done  for State of</html>");
		lblShowsHowMuch.setBackground(SystemColor.activeCaption);
		lblShowsHowMuch.setHorizontalAlignment(SwingConstants.LEFT);
		lblShowsHowMuch.setForeground(new Color(102, 153, 255));
		lblShowsHowMuch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblShowsHowMuch.setVisible(false);
		lblShowsHowMuch.setBounds(14, 381, 327, 76);
		frmSearchingForReal.getContentPane().add(lblShowsHowMuch);
		
		Min_formattedTextField = new JFormattedTextField();
		Min_formattedTextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		Min_formattedTextField.setBounds(506, 56, 99, 32);
		frmSearchingForReal.getContentPane().add(Min_formattedTextField);
		Max_formattedTextField = new JFormattedTextField();
		Max_formattedTextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		Max_formattedTextField.setBounds(506, 124, 99, 32);
		frmSearchingForReal.getContentPane().add(Max_formattedTextField);
		
		progressBarZillow = new JProgressBar();
		progressBarZillow.setBackground(new Color(204, 255, 255));
		progressBarZillow.setForeground(new Color(0, 102, 102));
		progressBarZillow.setToolTipText("Shows THe Progressed Task till now");
		progressBarZillow.setBounds(328, 399, 386, 32);
		progressBarZillow.setMaximum(100);
		progressBarZillow.setMinimum(0);
		progressBarZillow.setVisible(false);
		frmSearchingForReal.getContentPane().add(progressBarZillow) ;
		
		waitLabel = new JLabel("<HTML>Searching is begun<br> The Earth is rotating <br>it means Program is Busy Now <br>Please Wait while fetching <br>the data from the website ....</HTML>");
		waitLabel.setFont(new Font("Tahoma", Font.BOLD, 14));		waitLabel.setForeground(Color.RED);
		waitLabel.setBounds(14, 177, 267, 108);
		waitLabel.setVisible(false);
		frmSearchingForReal.getContentPane().add(waitLabel);
		
		Sell_RadioButton = new JRadioButton("Sell Estate");
		Sell_RadioButton.setBackground(new Color(250, 250, 210));
		Sell_RadioButton.setBounds(629, 68, 94, 23);
		frmSearchingForReal.getContentPane().add(Sell_RadioButton);

		Rent_RadioButton_2 = new JRadioButton("Rent Estate");
		Rent_RadioButton_2.setBackground(new Color(250, 250, 210));
		Rent_RadioButton_2.setBounds(629, 120, 94, 23);
		frmSearchingForReal.getContentPane().add(Rent_RadioButton_2);

		Buy_RadioButton = new JRadioButton("Buy Estate");
		Buy_RadioButton.setBackground(new Color(250, 250, 210));
		Buy_RadioButton.setBounds(629, 94, 94, 23);
		frmSearchingForReal.getContentPane().add(Buy_RadioButton);
		
		ButtonGroup BottonBunch = new ButtonGroup();
		BottonBunch.add(Sell_RadioButton);
		BottonBunch.add(Buy_RadioButton);
		BottonBunch.add(Rent_RadioButton_2);
		Buy_RadioButton.setSelected(true);

			 final JButton btnPhaseSearch = new JButton("1st Phase ");
			 btnPhaseSearch.setIcon(new ImageIcon(UserAlliace.class.getResource("/Zippy/Playicon.png")));
				btnPhaseSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						phaseonex callphaseonex= new phaseonex(UserAlliace.this);
						callphaseonex.execute();
						btnPhaseSearch.setFocusable(false);				
					}
				});
				btnPhaseSearch.setToolTipText("click to search for zip code");
				btnPhaseSearch.setBackground(new Color(204, 204, 255));
				btnPhaseSearch.setBounds(33, 53, 145, 44);
				frmSearchingForReal.getContentPane().add(btnPhaseSearch);
		
		btnMortgageButton = new JButton("Mortgage & CashFlow");
		btnMortgageButton.setForeground(new Color(0, 153, 0));
		btnMortgageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mortgage_calc MCalc= new Mortgage_calc(); 
				MCalc.setVisible(true);
			}
		});
		btnMortgageButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMortgageButton.setBounds(490, 184, 185, 53);
		frmSearchingForReal.getContentPane().add(btnMortgageButton);
		
		Omit_Button = new JButton("Cleaning");
		Omit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clean_Phase cleaner= new Clean_Phase(UserAlliace.this);
				cleaner.execute();
				Omit_Button.setFocusable(false);
			}
		});
		Omit_Button.setFont(new Font("Tahoma", Font.BOLD, 11));
		Omit_Button.setBackground(new Color(204, 255, 153));
		Omit_Button.setBounds(587, 263, 89, 44);
		frmSearchingForReal.getContentPane().add(Omit_Button);
		
		OmitRate_TextField = new JFormattedTextField();
		OmitRate_TextField.setText("1.1");
		OmitRate_TextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		OmitRate_TextField.setBounds(529, 269, 46, 32);
		frmSearchingForReal.getContentPane().add(OmitRate_TextField);
		
		lblcurrentzip = new JLabel("<html>Please Select a <b>State From Combo box below </b>and click 1st Phase</html>");
		lblcurrentzip.setFont(new Font("Tahoma", Font.PLAIN, 13));
		//lblNewLabel_1.setBounds(181ont("Tahoma", Font.PLAIN, 13));
		lblcurrentzip.setBounds(23, 7, 791, 32);
		//frmSearchingForReal.getContentPane().add(lblcurrentzip.setBounds(170, 55, 32, 32);
		frmSearchingForReal.getContentPane().add(lblcurrentzip);
		
		lblMax = new JLabel("<html><b>Max Value:</b><br> Default = 2.5Million $</html>");
		lblMax.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMax.setBounds(398, 119, 102, 44);
		frmSearchingForReal.getContentPane().add(lblMax);
		
		lblminValueDefault = new JLabel("<html><b>Min Value:</b><br> Default = 10,000 $</html>");
		lblminValueDefault.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblminValueDefault.setBounds(398, 52, 94, 44);
		frmSearchingForReal.getContentPane().add(lblminValueDefault);
		
		JButton btnNewButton_1 = new JButton("  Pause");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSearchingForReal.dispatchEvent(new WindowEvent(frmSearchingForReal, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnNewButton_1.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_1.setIcon(new ImageIcon(UserAlliace.class.getResource("/Zippy/pause.png")));
		btnNewButton_1.setBounds(210, 54, 145, 44);
		frmSearchingForReal.getContentPane().add(btnNewButton_1);
		
		giflabel = new JLabel("New label");
		giflabel.setIcon(new ImageIcon(UserAlliace.class.getResource("/Zippy/Earth.gif")));
		giflabel.setBounds(293, 184, 99, 101);
		giflabel.setVisible(false);
		frmSearchingForReal.getContentPane().add(giflabel);
		
		TotalprogressBar = new JProgressBar();
		TotalprogressBar.setBounds(329, 459, 389, 44);
		TotalprogressBar.setVisible(false);
		frmSearchingForReal.getContentPane().add(TotalprogressBar);
		
		 lblTotalProgress = new JLabel("Whole of Zips Done till now");
		lblTotalProgress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalProgress.setForeground(new Color(70, 130, 180));
		lblTotalProgress.setBounds(46, 467, 262, 36);
		lblTotalProgress.setVisible(false);
		frmSearchingForReal.getContentPane().add(lblTotalProgress);
		
		lblDontExit = new JLabel("Writing in DataBase Dont Exit !");
		lblDontExit.setIcon(new ImageIcon(UserAlliace.class.getResource("/Zippy/Stop.png")));
		lblDontExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDontExit.setForeground(Color.RED);
		lblDontExit.setBounds(156, 310, 398, 76);
		lblDontExit.setVisible(false);
		frmSearchingForReal.getContentPane().add(lblDontExit);
		
		lblfinish1 = new JLabel("");
		lblfinish1.setIcon(new ImageIcon(UserAlliace.class.getResource("/Zippy/finisherzhast.png")));
		lblfinish1.setBounds(59, 202, 240, 203);
		lblfinish1.setVisible(false);
		frmSearchingForReal.getContentPane().add(lblfinish1);
		
		lblfinalized = new JLabel("");
		lblfinalized.setIcon(new ImageIcon(UserAlliace.class.getResource("/Zippy/finalizerhast.png")));
		lblfinalized.setBounds(443, 195, 245, 225);
		lblfinalized.setVisible(false);
		frmSearchingForReal.getContentPane().add(lblfinalized);
		
		comboBoxABV.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		comboBoxABV.setModel(new DefaultComboBoxModel<String>(new String[] {"", "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"}));
		comboBoxABV.setToolTipText("Select One of The States");
		comboBoxABV.setBounds(297, 123, 70, 35);
		comboBoxABV.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ChangelblState tt= new ChangelblState(UserAlliace.this);
				tt.execute();
			}
       //private void ChangelblState() {} // TODO Auto-generated method stub
		});
		frmSearchingForReal.getContentPane().add(comboBoxABV);
		
		lblState = new JLabel("Select a State from the List:");
		lblState.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblState.setForeground(new Color(51, 0, 102));
		lblState.setBounds(31, 118, 268, 44);
		frmSearchingForReal.getContentPane().add(lblState);
		
		lblZipsSeen = new JLabel("");
		lblZipsSeen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblZipsSeen.setBounds(33, 514, 700, 23);
		frmSearchingForReal.getContentPane().add(lblZipsSeen);
		
		frmSearchingForReal.setTitle("Batch Searching for real estates in US");
		frmSearchingForReal
		.setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						UserAlliace.class
						.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		frmSearchingForReal.setBounds(100, 100, 855, 589);
		frmSearchingForReal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frmSearchingForReal.addWindowListener(new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		          //Search_formattedTextField.requestFocus();
		    }
		});
		Omit_Button.setVisible(false);
		btnMortgageButton.setVisible(false);
		OmitRate_TextField.setVisible(false);
	}
}
 class ChangelblState extends SwingWorker<Void, Void> {
	  private UserAlliace gui;

	    public ChangelblState(UserAlliace gui){
	        this.gui = gui;
	    }
		@Override
	    protected Void doInBackground() throws Exception {
			
			if(gui.comboBoxABV.getSelectedIndex()!=0)
			{
			     int i=0;
			     String[] ABV="AL,AK,AZ,AR,CA,CO,CT,DC,DE,FL,GA,HI,ID,IL,IN,IA,KS,KY,LA,ME,MD,MA,MI,MN,MS,MO,MT,NE,NV,NH,NJ,NM,NY,NC,ND,OH,OK,OR,PA,RI,SC,SD,TN,TX,UT,VT,VA,WA,WV,WI,WY".split(",");
			     String [] Statename="ALABAMA,ALASKA,ARIZONA,ARKANSAS,CALIFORNIA,COLORADO,CONNECTICUT,WASHINGTON D.C.,DELAWARE,FLORIDA,GEORGIA,HAWAII,IDAHO,ILLINOIS,INDIANA,IOWA,KANSAS,KENTUCKY,LOUISIANA,MAINE,MARYLAND,MASSACHUSETTS,MICHIGAN,MINNESOTA,MISSISSIPPI,MISSOURI,MONTANA,NEBRASKA,NEVADA,NEW HAMPSHIRE,NEW JERSEY,NEW MEXICO,NEW YORK,NORTH CAROLINA,NORTH DAKOTA,OHIO,OKLAHOMA,OREGON,PENNSYLVANIA,RHODE ISLAND,SOUTH CAROLINA,SOUTH DAKOTA,TENNESSEE,TEXAS,UTAH,VERMONT,VIRGINIA,WASHINGTON,WEST VIRGINIA,WISCONSIN,WYOMING".split(",");
			for(i=0;i<50;i++)
			{   if (ABV[i].equals(gui.comboBoxABV.getSelectedItem()))
				   {break;}
			}
			     gui.lblState.setText(Statename[i]);
			     Font labelFont = gui.lblState.getFont();
			     gui.lblState.setFont(new Font(labelFont.getName(), Font.PLAIN, 30));
			     
			}
			else
			{
				 gui.lblState.setText("Please  Select State");
			}
	    return null;
	    }
        
	}
 
 class phasetwoRent extends SwingWorker<Void, Void> {
	    private UserAlliace gui;

	    public phasetwoRent(UserAlliace gui){
	        this.gui = gui;
	    }
		@Override
	    protected Void doInBackground() throws Exception {
	    	gui.lblShowsHowMuch.setVisible(true);
	    	gui.progressBarZillow.setVisible(true);
	    	gui.giflabel.setVisible(true);
	    	gui.waitLabel.setVisible(true);
	    	gui.progressBarZillow.setValue(0);
	    	gui.progressBarZillow.setStringPainted(true);
	    	String csvFile = "Rent_Search_Results.csv";
	    	String Rent_2nd_Phase="Rent_2ndPhase.csv";
	    	String Rent_File_Header="ZPID (Zillow_ID), Address, Locality, State, ZipCode, Latitude, Longitude, Rent, Bedrooms, Bathrooms, Area Space, Status, Estimated Rent (/mo), Year Built, HOA Fee, Days on Zillow, Views Since Listing, Index2, Mortgage, Rent/Sale, Avg Index2 , Cash Flow, Avg Price, Avg CashFlow, Avg Rent, Price per SQFT,Avg Price/SQFT, StdDev/SQFT, StdDev Price, Schools, MLS#, Posted, Laundry, Rent/sqft , Cooling,Heating, Extra Data"+"\n";
	    	//String line = "";
	    	String csvSplitBy = ",";
	    	int line_number=0;
	    	
	    	 try { 
		        	File First_phase_File = new File(csvFile);
		        	if(!(First_phase_File.exists()))
		            {
		            	JOptionPane
						.showMessageDialog(
								null,
								"<html><h1> First phase file (Rent_Search_Results.csv) was not found <br> at first step please select the Rent option and <br> click on first phase search button </h1></html>",
								"Please first run phase one for Rent!",
								JOptionPane.ERROR_MESSAGE );
		            }
		            else // First phase has not been completed for Rent in before tries
		            { 
		            	try 
		            
		        	{LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(csvFile)));
		        	lnr.skip(Long.MAX_VALUE);
		        	line_number= lnr.getLineNumber() ; 
		        	// Finally, the LineNumberReader object should be closed to prevent resource leak
		        	lnr.close();
		        	}
		        	catch (Exception exc)
		        	    {
		        		System.out.println(exc.getCause().getMessage());
		        		JOptionPane.showMessageDialog(null,exc );
						}
		            	
                    int khatcheck=1;
		            File Rent_Sec_phase_File = new File(Rent_2nd_Phase);
		            if (Rent_Sec_phase_File.exists()) // the 2nd Phase of Rent Exists 
		             {  
		            	int line_Num_SecPhase=0;
			            try {LineNumberReader  lnr2 = new LineNumberReader(new FileReader(new File(Rent_2nd_Phase)));
						lnr2.skip(Long.MAX_VALUE);
						line_Num_SecPhase= lnr2.getLineNumber() ; 
						// Finally, the LineNumberReader object should be closed to prevent resource leak
						lnr2.close();}
			            catch (Exception exc)
			            {
			            	System.out.println(exc.getCause().getMessage());
			            	JOptionPane.showMessageDialog(null,exc );
			            }
			        	int i=0;
		            	String[][] estate = new  String [1][];
			            BufferedReader	 brj = new BufferedReader(new FileReader(Rent_2nd_Phase));
		            	String khat= brj.readLine(); 		            
		            	while(((khat=brj.readLine())!=null)&& (i<line_Num_SecPhase))
		            	{
		            	estate[0] = khat.split(csvSplitBy);
		            	if(( estate[0][12].equals("?????"))&&( estate[0][13].equals("?????"))&&(( estate[0][17].equals("?????")))&&(( estate[0][15].equals("?????"))&&(( estate[0][31].equals("?????"))))) // Checks if any data has been grabbed before 
		            		{break;}
		            		i++;
		            		if (i==line_Num_SecPhase)break;
		            	}
		            	brj.close();
		            	if (i<(line_number-1)) // Checks If There Exists any ungrabbed data yet
		            		{ BufferedReader	 csvbuff = new BufferedReader(new FileReader(csvFile));
		            		for (int hh=0;hh<=(i+1);hh++)
		            			khat=csvbuff.readLine();
		            		FileWriter fileOut = new FileWriter(Rent_2nd_Phase,true);
	        		          //if (!(Rent_Sec_phase_File.exists())) 
			                   //fileOut.write(Rent_File_Header);
		            		for (int kk=i;kk<line_number && (khat!=null);kk++)
		            		{   khatcheck=kk; 
		            			estate[0] = khat.split(csvSplitBy);
				                 String ModifString=UserAlliace.spaceConvertToHyphen(replaceNumSign(UserAlliace.FirstSpaceRemover(estate[0][1])));
				                 if  (UserAlliace.FirstSpaceRemover(estate[0][19]).equals("For Rent"))
				               	 {
				               		 ModifString=UserAlliace.spaceConvertToHyphen(UserAlliace.FirstSpaceRemover(estate[0][1]));
				               		 String [] RentArr1= estate[0];                          
				               		 List<String>  RentArr2=   rentgrab(RentArr1[0],ModifString ,UserAlliace.spaceremover(RentArr1[2]),UserAlliace.spaceremover(RentArr1[3]),UserAlliace.spaceremover(RentArr1[4]), UserAlliace.spaceremover( UserAlliace.AnySignremover(RentArr1[7])));
				               		 float Remainedfloat=((khatcheck*100)/line_number); // check how many lines of the total file has been reached
				                        gui.progressVal= Math.round(Remainedfloat)-1;
				                        if (gui.progressVal>0)
				                       // setProgressbarZil.execute();
				                        gui.progressBarZillow.setValue(gui.progressVal);				                  
				                        khatcheck++;
				                        String matnString=new String ("");
				                        if (RentArr2.size()<=1)
				                        { for (int shomar=0;shomar<=11;shomar++)
				                            { matnString+=RentArr1[shomar]+",";}
				                        for (int shomar=12;shomar<=36;shomar++)
			                                { matnString+="000"+",";}
				                            matnString= matnString.substring(0,matnString.length()-1)+"\n"; //20 Avg indx 21th element
				                            fileOut.write(matnString);
				                        } // data not founfd possiblly the home is not for rent aymore on zillow
				                        else //Normal Fetching
				                        {     for (int shomar=0;shomar<=17;shomar++)
				                               { 
				                        	      if (shomar <= 11)       	{matnString+=RentArr1[shomar]+",";}	
				                         	        else          { matnString+=RentArr2.get(shomar-12)+","; }
				                               }

				                        matnString+=RentArr1[18]+","+RentArr1[19]+",";
			                        	for (int j=20;j<=28;j++) {
			                        	if(j==25){
			                        		if(!(UserAlliace.AnySignremover( estate[0][10]).equals("")))
			                       	   {     //  [25] Price per Sqrfeet     matnString+=
			                        		if (Integer.parseInt(UserAlliace.AnySignremover( estate[0][10]))>0)
			                        			matnString+=(new DecimalFormat("##.##").format((Integer.parseInt(UserAlliace.AnySignremover( estate[0][7])))/(Integer.parseInt(UserAlliace.AnySignremover( estate[0][10]))))).toString()+",";
			                        		else
			                        			matnString+="0"+",";		
			                        	}
			                        	else       	{	matnString+="0"+",";   	}
			                        	 }
			                        	 else {if   (j==21 || j==23 ){matnString+="?????"+",";}
			                        		              else                {matnString+="0"+",";}
			                        		  }
			                        	 }
			                        	for (int j=6; j<RentArr2.size() ;j++)
			                        		{matnString+=RentArr2.get(j)+",";}			                        	
				        		        matnString= matnString.substring(0,matnString.length()-1)+"\n"; //20 Avg indx 21th element
                         		        fileOut.write(matnString);
				                 	 }                 
				                } // end of checking if it is a rent record
				                 khat=csvbuff.readLine();
		            		} // End of FOR  (kk) Main loop of checking the unvisited remainders
		            		csvbuff.close();
	                        fileOut.close();
	                     } //if (i<line_number-1) 
		             }
		            else // Else of If (Rent_sec_Phase_File.exist )
		            {   String line="";
		            	BufferedReader	 br = new BufferedReader(new FileReader(csvFile));		            	
			            br.readLine();
			            FileWriter fileOut = new FileWriter(Rent_2nd_Phase,false);
      		          //if (!(Rent_Sec_phase_File.exists())) 
		                   fileOut.write(Rent_File_Header);
			            while ((line = br.readLine()) != null) {
			            	String[][] estate = new  String [1][];// the amount of lines of first collected csv file
			                 estate[0] = line.split(csvSplitBy);   // use comma as separator
			                 String EtimatedrentingString= UserAlliace.spaceremover(estate[0][12]);
			                 String YearBuiltinString= UserAlliace.spaceremover(estate[0][13]);
			                 String DaysOnZillowString=UserAlliace.spaceremover(estate[0][15]);
			                 
			                // Replace CSV@ fields in zipfind to prevent again workings
			                 
			                 if ((EtimatedrentingString .equals("?????"))||((YearBuiltinString.equals("?????"))&&(DaysOnZillowString.equals("?????"))))
			                 {
			                	 if  (UserAlliace.FirstSpaceRemover(estate[0][19]).equals("For Rent"))
			                	 {   String ModifString=UserAlliace.spaceConvertToHyphen(UserAlliace.FirstSpaceRemover(estate[0][1]));
				               		 String [] RentArr1= estate[0];
				               		 List<String>  RentArr2=   rentgrab(RentArr1[0],ModifString ,UserAlliace.spaceremover(RentArr1[2]),UserAlliace.spaceremover(RentArr1[3]),UserAlliace.spaceremover(RentArr1[4]), UserAlliace.spaceremover( UserAlliace.AnySignremover(RentArr1[7])));
				               		 float Remainedfloat=((khatcheck*100)/line_number); // check how many lines of the total file has been reached
				                        gui.progressVal= Math.round(Remainedfloat)-1;
				                        if (gui.progressVal>0)
				                       // setProgressbarZil.execute();
				                        gui.progressBarZillow.setValue(gui.progressVal);				                  
				                        khatcheck++;
				                        String matnString=new String ("");
				                        if (RentArr2.size()<=1)
				                        { for (int shomar=0;shomar<=11;shomar++)
			                                   {matnString+=RentArr1[shomar]+",";}
			                              for (int shomar=12;shomar<=36;shomar++)
		                                       {matnString+="000"+",";}
			                               matnString= matnString.substring(0,matnString.length()-1)+"\n"; //20 Avg indx 21th element
			                               fileOut.write(matnString);
			                            } // data not founfd possiblly the home is not for rent aymore on zillow
				                        else //Normal Fetching
				                        {
				                        for (int shomar=0;shomar<=17;shomar++)
				                        { 
				                        	if (shomar <= 11)       	{matnString+=RentArr1[shomar]+",";}	
				                        	else          { matnString+=RentArr2.get(shomar-12)+","; }
				                        }
				                       		                        	
			                        	 matnString+=RentArr1[18]+","+RentArr1[19]+",";
			                        	for (int l=20;l<=28;l++)
			                        {
			                        	if(l==25){	
			                        	 if(!(UserAlliace.AnySignremover( estate[0][10]).equals("")))
			                        	{     //  [25] Price per Sqrfeet     matnString+=
			                        		if (Integer.parseInt(UserAlliace.AnySignremover( estate[0][10]))>0)
			                        			{matnString+=(new DecimalFormat("##.##").format((Integer.parseInt(UserAlliace.AnySignremover( estate[0][7])))/(Integer.parseInt(UserAlliace.AnySignremover( estate[0][10]))))).toString()+",";}
			                        		else  {matnString+="0"+",";}		
			                        	}
			                        	else {matnString+="0"+",";}
			                        	 }			                        	
			                        	 else {
			                        		 if (l==21 || l==23 ){matnString+="?????"+",";}
			                        		 else  {matnString+="0"+",";}
			                        		  }
			                         }
			                        	for (int p=6; p<RentArr2.size() ;p++)
			                        		{matnString+=RentArr2.get(p)+",";}	
				        		        matnString= matnString.substring(0,matnString.length()-1)+"\n"; //20 Avg indx 21th element
				        		        fileOut.write(matnString);
				                        }
			                	 }
			                 }
			            }
			            fileOut.close();
			            br.close();
		           }// the end of  Else of If (Rent_sec_Phase_File.exist )
		            Rent_AVG_Update();
	            }
	    	 }

	    	 
	    	  catch(Exception eccd ){JOptionPane.showMessageDialog(null,eccd );}
		      // Rent_Update_DaysOnZillow(); // Calculation of Days on Zillow per Zipcode	 for rent    	 
	    	   gui.lblShowsHowMuch.setVisible(false);
		       gui.progressBarZillow.setVisible(false);
		       gui.waitLabel.setVisible(false);
			   gui.progressBarZillow.setValue(0);
			   gui.giflabel.setVisible(false);
				JOptionPane
				.showMessageDialog(
						null,
						"<html><h1> Second phase file (Rent_2ndPhase.csv) was Successfully updated <br>  </h1></html>",
						"Successfull Run of Rent Phas TWO !",
						JOptionPane.INFORMATION_MESSAGE  );
		   
	    return null;}


		private void Rent_AVG_Update() {
		        
		//******************** Calculating Rent Averages (AVg index2 , Avg Price, Avg Rent, Avg Price per SQUAREFEET)
        String Sale_2nd_Phase="Rent_2ndPhase.csv";
        String line="";
        String csvSplitBy=",";
        int line_Num2nd=0;
        try {LineNumberReader  lnr2 = new LineNumberReader(new FileReader(new File(Sale_2nd_Phase)));
		lnr2.skip(Long.MAX_VALUE);
		line_Num2nd= lnr2.getLineNumber() ; 
		// Finally, the LineNumberReader object should be closed to prevent resource leak
		lnr2.close();}
        catch (Exception exc)
        {
        	System.out.println(exc.getCause().getMessage());
        	JOptionPane.showMessageDialog(null,exc );
        }
        try{
        	BufferedReader buff = null;
        	buff = new BufferedReader(new FileReader(Sale_2nd_Phase));
        	String[][] estatem = new  String[(line_Num2nd-1)] [];
        	estatem[0]= buff.readLine().split(","); // The first row of file is header so it will be overwrited next
        	int opt=0;
            
            while ((line = buff.readLine()) != null)
            {
            	estatem[opt] = line.split(csvSplitBy);
            	opt++;
            	}
        	int indx=0;
        	while(!(UserAlliace.AnySignremover( estatem[indx][20]).equals("0"))){indx++;     	if (indx>=estatem.length) {break;}}
            while (true) 
            {   
            	double total_index2=0.0;
            	long total_Price=0;
            	long total_rent=0;
            	long Total_Price_per_Sqrft=0;
            	
            	if( ( indx<opt) ){ 		
            		int shorou=indx;
            		String zip_sample= UserAlliace.AnySignremover(estatem[indx][4]);
            		int zipcount=0;
            		int zip_pricesqftcount=0;
            		int zippricecount=0;
            		int ziprentcount=0;
            		while ( (indx<opt)&&UserAlliace.AnySignremover(estatem[indx][4]).equals(zip_sample) )
            		{
            			
            			total_index2+= Double.parseDouble(UserAlliace.spaceremover( estatem[indx][17])); //zigma index2
            			zipcount++;
            			if (!(UserAlliace.AnySignremover( estatem[indx][7]).equals("0")))
            			{
            				total_Price+=Long.parseLong((UserAlliace.AnySignremover( estatem[indx][7]))) ; //zigma Price
            				zippricecount++;
            			}
            			if (!(UserAlliace.AnySignremover( estatem[indx][12]).equals("0")))
            			{
            			    total_rent+=Long.parseLong((UserAlliace.AnySignremover( estatem[indx][12]))) ; //zigma Rent
            			    ziprentcount++;
            			}
            			if (!(UserAlliace.AnySignremover( estatem[indx][25]).equals("0")))
            			{
            			     Total_Price_per_Sqrft+=  Long.parseLong((UserAlliace.AnySignremover( estatem[indx][25])));// Zigma Price per sqft
            			     zip_pricesqftcount++;
            			}
            			  indx++;
            			if(indx>=opt){break;
            			}
            			
            		}
            		gui.progressBarZillow.setValue(98);
            		Double Avg_Index2=0.0;
            		long Avg_Price=0;
            		long Avg_Rent=0;
            		long Avg_Pricesqft=0;
            		if (zipcount!=0)
            			{
            			Avg_Index2= (total_index2/zipcount);
            			Avg_Price= Math.round((total_Price/zippricecount));
            			Avg_Rent= Math.round((total_rent/ziprentcount));
            			Avg_Pricesqft= Math.round((Total_Price_per_Sqrft/zip_pricesqftcount));
            			}
            		String Round_Avg_Index2=(new DecimalFormat("##.###").format(Avg_Index2)).toString();
            		String Round_Avg_Price= Long.toString(Avg_Price); 
            		String Round_Avg_Rent=Long.toString(Avg_Rent);
            		String Round_Avg_PriceSqft=Long.toString(Avg_Pricesqft);
            		for (int jj=shorou;jj<zipcount+shorou;jj++)
            		{
            			estatem[jj][20]= Round_Avg_Index2;
            			estatem[jj][22]= Round_Avg_Price;
            			estatem[jj][24]= Round_Avg_Rent;
            			estatem[jj][26]= Round_Avg_PriceSqft;

            		}
            	}
            	
            	else
            	{break;}
            }
            buff.close();
            indx=0;
            
//***************************** CALCULATING The STANDARD DEVIATION  ******************************************
            
        	 while (!(UserAlliace.AnySignremover( estatem[indx][27]).equals("0"))&& !(UserAlliace.AnySignremover( estatem[indx][28]).equals("0"))){indx++; if (indx>=estatem.length){break;}}
         while (true){
        	double total_StdDev=0;
        	double price_StdDev=0;
        	if(indx<(estatem.length)){ 		
        		int shorou=indx;
        		String zip_sample= UserAlliace.AnySignremover(estatem[indx][4]);
        		int zipcount=0;
        		while ( (indx<(estatem.length))&&UserAlliace.AnySignremover(estatem[indx][4]).equals(zip_sample) )
        		{  if (!(UserAlliace.AnySignremover( estatem[indx][25]).equals("0"))&&!(UserAlliace.AnySignremover( estatem[indx][7]).equals("0"))){
        			 total_StdDev+=Math.pow((Integer.parseInt(UserAlliace.AnySignremover( estatem[indx][25]))-Integer.parseInt(UserAlliace.spaceremover( estatem[indx][26]))),2); //zigma index2
        			 price_StdDev+=Math.pow((Integer.parseInt(UserAlliace.AnySignremover( estatem[indx][7]))-Integer.parseInt(UserAlliace.spaceremover( estatem[indx][22]))),2); //zigma index2
        			 zipcount++;
        			}
        		   indx++;
        		   if(indx>=(estatem.length)) {break;}
        		}
        		gui.progressBarZillow.setValue(99);
        		Double STD_DEV=0.0;
        		Double PRICE_STD_DEV=0.0;
        		if (zipcount!=0){
        			STD_DEV= Math.pow((total_StdDev/zipcount),0.5);
        			PRICE_STD_DEV= Math.pow((price_StdDev/zipcount),0.5);
        			}
        		String Round_STD_DEV=(new DecimalFormat("##.#").format(STD_DEV)).toString();
        		String Round_PRICE_STD_DEV=(new DecimalFormat("##.#").format(PRICE_STD_DEV)).toString();
        	for (int jj=shorou;jj<(indx);jj++){
        		estatem[jj][27]= Round_STD_DEV;
        		estatem[jj][28]=Round_PRICE_STD_DEV;
        	}
        }
        	else {break;}
     }
           // gui.standarddeviation(estatem);
            int estatem_length=estatem.length;
	    	String Rent_File_Header="ZPID (Zillow_ID), Address, Locality, State, ZipCode, Latitude, Longitude, Rent, Bedrooms, Bathrooms, Area Space, Status, Estimated Rent (/mo), Year Built, HOA Fee, Days on Zillow, Views Since Listing, Index2, Mortgage, Rent/Sale, Avg Index2 , Cash Flow, Avg Price, Avg CashFlow, Avg Rent, Price per SQFT,Avg Price/SQFT, StdDev/SQFT, StdDev Price, Schools, MLS#, Posted, Laundry, Rent/sqft , Cooling,Heating, Extra Data"+"\n";
            FileWriter fileOut2 = new FileWriter(Sale_2nd_Phase,false);
		        fileOut2.write(Rent_File_Header);
		        for (int eghd=0;((!(estatem[eghd].equals(null)))&&(!(estatem[eghd][1].equals(null))));eghd++){
		        	String matnString2="";
		        	for (int shomar=0;shomar<((estatem[eghd].length)-1);shomar++)
		        	{matnString2+=estatem[eghd][shomar]+",";}
		        	matnString2=matnString2+estatem[eghd][((estatem[eghd].length)-1)]+"\n"; //21sth element or [20] is avgindex2 25th element [24] is Avg Rent  
		        	fileOut2.write(matnString2);
		        	if((eghd+1)>=estatem_length)
		        		break;
		        }
		       fileOut2.close();
		       Rent_Update_DaysOnZillow(); // Calculation of Days on Zillow per Zipcode  
		       gui.progressBarZillow.setValue(100);
		       JOptionPane
				.showMessageDialog(
						null,
						"<html><h2>the output file (Rent_2ndPhase.csv) is successfully built</h2></html>",
						"Successful secondary data grabbed",
						JOptionPane.INFORMATION_MESSAGE );
		     }
        catch(Exception rex){
        	 System.out.println(rex.getCause().getMessage());
        	 JOptionPane.showMessageDialog(null,rex );
        } 		}
		private void Rent_Update_DaysOnZillow() {
			// This Function Looks for the file Sale_2nd_Phase and calculates the percentage of Days on Zillow in for intervals
			// less than 15 days; 16-30 days; 31 - 60 days; More than 61 days on Zillow and write the results in 
			String Sale_2nd_Phase= "Rent_2ndPhase.csv";
			File Sec_File = new File(Sale_2nd_Phase);
			if(Sec_File.exists()) 
			{
				int line_Num2nd=0;
	            try {LineNumberReader  lnr2 = new LineNumberReader(new FileReader(new File(Sale_2nd_Phase)));
				lnr2.skip(Long.MAX_VALUE);
				line_Num2nd= lnr2.getLineNumber() ; 
				// Finally, the LineNumberReader object should be closed to prevent resource leak
				lnr2.close();}
	            catch (Exception exc)
	            {
	            	System.out.println(exc.getCause().getMessage());
	            	JOptionPane.showMessageDialog(null,exc );
	            }
	            String [][]Arr= new String [line_Num2nd-1][]; 
	            try{
	            BufferedReader	 br = new BufferedReader(new FileReader(Sale_2nd_Phase));
           	     br.readLine();
           	     for (int i=0;i<Arr.length;i++)
           	     {
           	    	 Arr[i]= br.readLine().split(",");
           	     }
           	     br.close();
	            }
	            catch(Exception e ){JOptionPane.showMessageDialog(null,e );}
	            int jdex=0;// an index on Arr
	            List<String> DaysonZillow = new ArrayList<String>();
	            while (jdex<(Arr.length-1))
	            {
	            	String zip=Arr[jdex][4];
	            	int Movedex=jdex; // Moving Index on Arr to find the first and last index of zipcode 
	            	while(Arr[Movedex][4].equals(zip)){
	            		Movedex++;
	            		if (Movedex>(Arr.length-1))
	            			break;
	            	}
	            	int count=0; double Days15=0.0 ,Days30=0.0 ,Days60=0.0 ,Days60More=0.0;
	            	for (int i=jdex;i<Movedex;i++)
	            	{ 
	            		if ((!(Arr[i][31].equals("?????"))) && UserAlliace.AnySignremover(Arr[i][31]).matches("[0-9]+")) // check the Validity of field Posted on the Rent_2nd
	            		{count++; 
	            		int Days=Integer.parseInt( UserAlliace.AnySignremover(Arr[i][31]));
	            		if (Days>=0 &&Days<=15){Days15++;}	
	            		else if (Days>15 &&Days<=30){Days30++;}
	            		else if (Days>30 &&Days<=60){Days60++;}
	            		else if (Days>60){Days60More++;}
	            		}
	            	}
	            	jdex=Movedex;					
	            	DaysonZillow.add(zip+","+String.valueOf(new DecimalFormat("##.##").format((double)(Days15*100/count)))+","+String.valueOf(new DecimalFormat("##.##").format((double)(Days30*100/count)))+","+String.valueOf(new DecimalFormat("##.##").format((double)(Days60*100/count)))+","+String.valueOf(new DecimalFormat("##.##").format((double)(Days60More*100/count))));	            	
	            }
				File DaysOnZillow = new File("Rent_Avg_DaysOnZillow.csv");
				
				try {
					FileWriter fw  = new FileWriter(DaysOnZillow, false);
				   fw.write("Zipcode,% in 1-15 Days, % in 16-30 Days, % in 31-60 days, % in More than 60 Days \n");
				   for (int i=0;i<DaysonZillow.size();i++)
					   fw.write(DaysonZillow.get(i)+"\n");
				   fw.close();
				} catch (IOException e) {
					System.out.println(e.getCause().getMessage());
					JOptionPane.showMessageDialog(null,e );
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		private List<String> rentgrab (String zpidTxt, String StreetAddress, String localityText, String stateText, String ZipTextstrng, String PriceText) 
		{
			String URL_DetailString="http://www.zillow.com/homedetails/"+replaceNumSign(StreetAddress)+"-"+localityText+"-"+stateText+"-"+ZipTextstrng+"/"+zpidTxt+"_zpid/";
			   //List <String>  = null ;
			   ArrayList<String> retresult = new ArrayList<String>();
			   try {
				   Document RentDoc=Jsoup.connect(URL_DetailString).timeout(10*1000).get();
					Elements RentElements=RentDoc.body().getElementsByClass("zest-value");
					String estimatedrent="0";
					for (int ff=0;ff<RentElements.size();ff++){
						
					if ( RentElements.get(ff).text().contains("/mo") )
					{	estimatedrent=  RentElements.get(ff).text();
						break;
					}
					}
					 estimatedrent=UserAlliace.AnySignremover(estimatedrent);
					 retresult.add(estimatedrent);
					 String  factstrings="";
					 if (!(RentDoc.body().getElementsByClass("hdp-facts").isEmpty()) ){
				         Element FactElement=RentDoc.body().getElementsByClass("hdp-facts").first();
				         factstrings=FactElement.text();	
						String builtinString="0";
					if (factstrings.contains("Built in "))
					{
						builtinString=factstrings.substring(factstrings.indexOf("Built in ")+9, factstrings.indexOf("Built in ")+13);	
					}
					retresult.add(UserAlliace.Commaremover(builtinString));
					    String HOAstring="0";
					if (factstrings.contains("HOA Fee: "))
					{
						HOAstring=factstrings.substring(factstrings.indexOf("HOA Fee: ")+9, factstrings.indexOf("/mo")+3);
						HOAstring=UserAlliace.Commaremover(HOAstring);
					}
					
					retresult.add(HOAstring);
			     	
					String daysonzillowString="-1";
					if (factstrings.contains("days on Zillow"))
					{
						String teststring="";
				    	String test2="";
					
						for (int ind=2;ind<6;ind++){
						 test2=factstrings.substring((factstrings.indexOf("days on Zillow")-ind), factstrings.indexOf("days on Zillow")-(ind-1));
						if (test2.matches("[0-9]+"))
						{
							teststring=teststring+test2;
						}
						else if (test2.matches(","))
						{continue;}
						else 
						{break;}

						}
						teststring=UserAlliace.Commaremover(teststring);
						StringBuilder dest = new StringBuilder(teststring.length());
						for (int ki=teststring.length()-1;ki>=0;ki--)
						{
							dest.append(teststring.charAt(ki));
						}
			         daysonzillowString = dest.toString();
			         
					}
					retresult.add(daysonzillowString);
					String viewsSinceListingString="0";
					if (factstrings.contains("Views since listing: "))
					
						{
						String teststring1="";
						String teststring2="";
						for (int ind=21;ind<34;ind++)
						{
						 teststring1=factstrings.substring((factstrings.indexOf("Views since listing: ")+ind), (factstrings.indexOf("Views since listing: ")+ind+1));
						if (teststring1.matches("[0-9]+")){
							teststring2=teststring2+teststring1;
							continue;}
						else if (teststring1.matches(",")) 
							continue;
						else 
						  {break;}
							
						}
													
						viewsSinceListingString=UserAlliace.Commaremover(teststring2);
						
					}
					retresult.add(viewsSinceListingString);
					
					String Index2string="0";
					String rentvalueString= UserAlliace.AnySignremover(estimatedrent);
					String pricevalue= UserAlliace.AnySignremover(PriceText);
					 double index2=0.0;
					if ((Double.parseDouble(pricevalue))>0)
					index2=((1000*Double.parseDouble(rentvalueString))/(Double.parseDouble(pricevalue))); 
					Index2string=(new DecimalFormat("##.###").format(index2)).toString();
					retresult.add(Index2string);
					//retresult.add("00/Mo");// Mortgage=

					}
					 										
					
					 //****************SEARCHING FOR SCHOOLS***********************
					File School_File = new File("Neighbor_Schools.csv");
					FileWriter fw = null;
					int School_File_Count=0;
					int first_time_seen=0;
					if(!School_File.exists()) 
					{
						try {
							School_File.createNewFile();
							first_time_seen=1;
						} 
						catch (IOException e) 
						{
							System.out.println(e.getCause().getMessage());
							JOptionPane.showMessageDialog(null,e );
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} 
					
					try {
						
						if (first_time_seen==1)
						{   fw = new FileWriter(School_File, true);
							fw.write("Zpid , Zip , Street Adress , Locality , State , School Rate (Out of 10), School Grades , School Distance, School Name, School Web URL "+"\n");
							fw.close();
						}
						else 
						{
							try {LineNumberReader  lnr2 = new LineNumberReader(new FileReader(new File("Neighbor_Schools.csv")));
							lnr2.skip(Long.MAX_VALUE);
							School_File_Count= lnr2.getLineNumber() ; 
							// Finally, the LineNumberReader object should be closed to prevent resource leak
							lnr2.close();}
				            catch (Exception exc)
				            {
				            	System.out.println(exc.getCause().getMessage());
				            	JOptionPane.showMessageDialog(null,exc );
				            }
						}
					} catch (IOException e) {
						System.out.println(e.getCause().getMessage());
						JOptionPane.showMessageDialog(null,e );
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String School_Info="";
					
					if ((RentDoc.body().getElementById("nearbySchools")!=(null)))

					{  
						if (RentDoc.body().getElementById("nearbySchools").outerHtml().contains("nearby-schools-rating"));	
						{
					fw = new FileWriter(School_File, true);
					Document school_doc= Jsoup.parse(RentDoc.body().getElementById("nearbySchools").outerHtml());
					Document School_sub_Elems= Jsoup.parse(school_doc.getElementsByClass("nearby-schools-list").outerHtml());
					Elements School_rate_list=School_sub_Elems.body().getElementsByClass("nearby-schools-rating");
					Elements School_list=School_sub_Elems.body().getElementsByClass("nearby-schools-info");
					for (int ii=1;ii<School_list.size();ii++)
					{
					String TempString ="";
					Element finelem=School_list.get(ii);
					Element ratelem=School_rate_list.get(ii);
					String School_Name=finelem.getElementsByClass("nearby-schools-name").get(0).child(0).text();
					String school_Web_UrlString=("www.zillow.com"+finelem.getElementsByClass("nearby-schools-name").get(0).child(0).attr("href")) ;
					String School_Grades=finelem.getElementsByClass("nearby-schools-grades").get(0).text();
					String School_Distance=finelem.getElementsByClass("nearby-schools-distance").get(0).text();
					String school_rate=ratelem.child(0).text();
					if (School_Grades.contains("-"))
						School_Grades=School_Grades.replaceFirst("-", " to ");
					
					TempString=School_Name+"$$"+school_rate+"$$"+school_Web_UrlString+"$$"+School_Grades+"$$"+School_Distance;
					try{
			//"Zpid , Zip , Street Adress , Locality , State , School Rate (Out of 10), School Grades , School Distance, School Name, School Web URL ");
						if (!(first_time_seen==1) )
						{ BufferedReader buffschol = null;
						buffschol = new BufferedReader(new FileReader("Neighbor_Schools.csv"));
						buffschol.readLine();
						int School_existbefore=0;
							for (int g=0;g<(School_File_Count-2);g++)
						 {
								String bufstrString=buffschol.readLine();
								String [] checkschoolStrings=bufstrString.split(",");
								if(checkschoolStrings[0].equals(zpidTxt))
								{
									School_existbefore=1;
									break;
								}
								
						 }
							buffschol.close();
							if (School_existbefore==0)
							{
						fw.write(zpidTxt+","+ ZipTextstrng+","+StreetAddress+","+ localityText+","+ stateText+","+school_rate+","+School_Grades+","+School_Distance+","+School_Name+","+school_Web_UrlString+"\n");
							}
							
						}
						else {
							fw.write(zpidTxt+","+ ZipTextstrng+","+StreetAddress+","+ localityText+","+ stateText+","+school_rate+","+School_Grades+","+School_Distance+","+School_Name+","+school_Web_UrlString+"\n");
						}
						
						
					}
					catch(Exception ex)
					{JOptionPane.showMessageDialog(null,ex );}
					School_Info+=TempString+"@@@";
					}
					
					fw.close();
					}}
					else
					{
						School_Info="?????";
					}
					
					
 if (!(RentDoc.body().getElementsByClass("hdp-facts").isEmpty()))
					{
	                    retresult.add(School_Info);
					String MLS="0";
					if (factstrings.contains("MLS #:"))
					{
						int beg_indx=factstrings.indexOf("MLS #:")+7;
						int str_indx=beg_indx;
						while (factstrings.charAt(str_indx)!= ' '  )
							str_indx++;
						MLS=factstrings.substring(beg_indx,str_indx );
						MLS=UserAlliace.Commaremover(MLS);
					}
					retresult.add(MLS);
					String Posted="?????" ;
					if (factstrings.contains("Posted: "))  //31 th
					{
						int beg_indx=factstrings.indexOf("Posted: ")+8;
						//int str_indx=beg_indx;
						int str_end=0;
						if (factstrings.contains("days ago"))
						   str_end= factstrings.indexOf("days ago")+8;
						if (str_end>8) {
						 Posted=factstrings.substring(beg_indx,str_end );
						 Posted=UserAlliace.Commaremover(Posted);}
					}
					retresult.add(Posted);

					String Laundry="?????" ;
					if (factstrings.contains("Laundry: ")) // 32th
					{
						int beg_indx=factstrings.indexOf("Laundry: ")+9;
						int str_indx=beg_indx;
						while (factstrings.charAt(str_indx)!= ':'  )
							str_indx++;
						Laundry=factstrings.substring(beg_indx,str_indx );
						Laundry = lastwordremover(Laundry);
						Laundry=UserAlliace.Commaremover(Laundry);
					}
					retresult.add(Laundry);
					 
						String Rent_SQFT="?????" ;
					if (factstrings.contains("Rent/sqft: ")) //33th record 
					{
						int beg_indx=factstrings.indexOf("Rent/sqft: ")+11;
						int str_indx=beg_indx;
						while (factstrings.charAt(str_indx)!= ' '  )
							str_indx++;
						Rent_SQFT=factstrings.substring(beg_indx,str_indx );
						Rent_SQFT=UserAlliace.dollarremover(Rent_SQFT);
					}
					retresult.add(UserAlliace.Commaremover(Rent_SQFT));
					String Cooling="?????" ;
					if (factstrings.contains("Cooling: "))   //34th 
					{
						int beg_indx=factstrings.indexOf("Cooling: ")+9;
						int str_indx=beg_indx;
						while (factstrings.charAt(str_indx)!= ':'  )
							str_indx++;
						Cooling=factstrings.substring(beg_indx,str_indx );
						Cooling = lastwordremover(Cooling);
						Cooling=UserAlliace.dollarremover(Cooling);
					}
					retresult.add(UserAlliace.Commaremover(Cooling));
					String Heating="?????" ;
					if (factstrings.contains("Heating: ")) //35th
					{
						int beg_indx=factstrings.indexOf("Heating: ")+9;
						int str_indx=beg_indx;
						while (factstrings.charAt(str_indx)!= ':'  )
							str_indx++;
						Heating=factstrings.substring(beg_indx,str_indx );
						Heating = lastwordremover(Heating);
						Heating=UserAlliace.dollarremover(Heating);
					}
					retresult.add(UserAlliace.Commaremover(Heating));
					retresult.add(UserAlliace.Commaremover( factstrings.substring(0,factstrings.indexOf("{"))));}
 else{
	 if (RentDoc.body().hasClass("zsg-content-component.bdp-module") )
	 {
	 Element FactElement=RentDoc.body().getElementsByClass("zsg-content-component.bdp-module").first();
     factstrings=FactElement.text();
	 retresult.add(factstrings);
	 for (int i=13;i<36;i++)
	 {
		 retresult.add("00"+",");
	 }
	 }
 }
			   }
			   catch(Exception Exc ){JOptionPane.showMessageDialog(null,Exc );}
					// TODO Auto-generated method stub
					return retresult;
				
			
		}
		private String replaceNumSign(String streetAddress) {
			String Res="";
			if (streetAddress.contains("#") )
			{Res=streetAddress.replaceAll("#", ".num."); }
			// TODO Auto-generated method stub
			return Res;
		}
		private String lastwordremover(String inputString) {
			int index=0; 
			for (int i=inputString.length()-1;i>2;i--)
			{
				if(inputString.charAt(i)==' ' ){index=i;break;}
			}
			// TODO Auto-generated method stub
			return (inputString.substring(0, index));

		}
		}
	    	
	    	
 class Clean_Phase extends SwingWorker<Void, Void> {
	    private UserAlliace gui;

	    public Clean_Phase(UserAlliace gui){
	        this.gui = gui;
	    }

	    @Override
	    protected Void doInBackground() throws Exception {
	    	String Sale_2nd_Phase="";
	    	if (gui.Buy_RadioButton.getSelectedObjects() != null ||gui.Sell_RadioButton.getSelectedObjects() != null)
	    	 Sale_2nd_Phase= "Sale_2ndPhase.csv";
	    	else if (gui.Rent_RadioButton_2.getSelectedObjects()!=null)
	    	{
	    	Sale_2nd_Phase= "Rent_2ndPhase.csv";
	    	}
	    	File Sec_phase_File = new File(Sale_2nd_Phase);
	    	if (Sec_phase_File.exists())
            {
            	int line_Num_SecPhase=0;
            	
	            try 
	            {
	            LineNumberReader  lnr2 = new LineNumberReader(new FileReader(new File(Sale_2nd_Phase)));
				lnr2.skip(Long.MAX_VALUE);
				line_Num_SecPhase= lnr2.getLineNumber() ;
				lnr2.close();
				}
	            catch (Exception exc)
	            {
	            	System.out.println(exc.getCause().getMessage());
	            	JOptionPane.showMessageDialog(null,exc );
	            }
            	BufferedReader	 brj = new BufferedReader(new FileReader(Sale_2nd_Phase));
            	brj.readLine(); 
            	double rate=1.1;
            	
            	if (!(gui.OmitRate_TextField.getText().equals(""))  )
            	{
            		rate= Double.parseDouble(gui.OmitRate_TextField.getText());
            	}
            	FileWriter fileOut3 = new FileWriter("3rdPhase.csv",false);
            	FileWriter file_Clean2nd = new FileWriter("Cleaned_From_Sale_2nd_Phase",false);
            	fileOut3.write(UserAlliace.SAR_Head_File);
            	file_Clean2nd.write(UserAlliace.SAR_Head_File);
            	
            	for (int i=1;i<line_Num_SecPhase;i++)
            	 {
            		String [] SecPhase_rows=brj.readLine().split(",");
            		int the_price=Integer.parseInt(UserAlliace.AnySignremover( SecPhase_rows[7]));
            		double under_mid= Double.parseDouble(SecPhase_rows[22])- (rate*Double.parseDouble( SecPhase_rows[28])) ;
            		double up_mid= Double.parseDouble(SecPhase_rows[22])+ (rate*Double.parseDouble( SecPhase_rows[28])) ;
            		//if ((Integer.parseInt(SecPhase_rows[25])> (Integer.parseInt( SecPhase_rows[26])-(rate*Integer.parseInt( SecPhase_rows[27])))) && (Integer.parseInt(SecPhase_rows[25])< (Integer.parseInt( SecPhase_rows[26])+(rate*Integer.parseInt( SecPhase_rows[27])))))
            		if ( the_price>Math.round(under_mid)  && the_price<Math.round(up_mid) )
            		{  String matnString="";
            		for (int shomar=0;shomar<((SecPhase_rows.length)-1);shomar++)
            		{
            			matnString+=SecPhase_rows[shomar]+",";
            		}
 			        	matnString=matnString+SecPhase_rows[((SecPhase_rows.length)-1)]+"\n"; //21sth element or [20] is avgindex2 25th element [26]=  ; [27]=  
 			        	fileOut3.write(matnString);
 			        }
            		else 
            		{  String matnString="";
            		  for (int shomar=0;shomar<((SecPhase_rows.length)-1);shomar++)
            		{
            			matnString+=SecPhase_rows[shomar]+",";
            		}
 			        	matnString=matnString+SecPhase_rows[((SecPhase_rows.length)-1)]+"\n"; //21sth element or [20] is avgindex2 25th element [26]=  ; [27]=  
 			        	file_Clean2nd.write(matnString);
 			        	
					}
            		
            	 }
            	 brj.close();
            	 fileOut3.close();
            	 file_Clean2nd.close();
            	 
            	 
            	//******************** Calculating Averages (AVg index2 , Avg Price, Avg Rent, Avg Price per SQUAREFEET)
		            
		            int line_Num2nd=0;
		            try {LineNumberReader  lnr2 = new LineNumberReader(new FileReader(new File("3rdPhase.csv")));
					lnr2.skip(Long.MAX_VALUE);
					line_Num2nd= lnr2.getLineNumber() ; 
					// Finally, the LineNumberReader object should be closed to prevent resource leak
					lnr2.close();}
		            catch (Exception exc)
		            {
		            	System.out.println(exc.getCause().getMessage());
		            	JOptionPane.showMessageDialog(null,exc );
		            }
		            try{
		            	BufferedReader buff = null;
		            	buff = new BufferedReader(new FileReader("3rdPhase.csv"));
		            	buff.readLine();
		            	int opt=0;
		            	String line="";
			            String[][] estatem = new  String[(line_Num2nd-1)] [];
			            
			            while ((line = buff.readLine()) != null)
			            {
			            	estatem[opt] = line.split(",");
			            	opt++;
			            	}
			        	int indx=0;
			            while (true) 
			            {   
			            	double total_index2=0.0;
			            	long total_Price=0;
			            	long total_rent=0;
			            	long Total_Price_per_Sqrft=0;
			            	if( indx<opt) 
			            	
			            	{ 		
			            		int shorou=indx;
			            		String zip_sample= UserAlliace.AnySignremover(estatem[indx][4]);
			            		int zipcount=0;
			            		int zip_pricesqftcount=0;
			            		int zippricecount=0;
			            		int ziprentcount=0;
			            		while ( (indx<opt)&&UserAlliace.AnySignremover(estatem[indx][4]).equals(zip_sample) ){
			            			total_index2+= Double.parseDouble(UserAlliace.spaceremover( estatem[indx][17])); //zigma index2
			            			zipcount++;
			            			if (!(UserAlliace.AnySignremover( estatem[indx][7]).equals("0"))){
			            				total_Price+=Long.parseLong((UserAlliace.AnySignremover( estatem[indx][7]))) ; //zigma Price
			            				zippricecount++;
			            			}
			            			if (!(UserAlliace.AnySignremover( estatem[indx][12]).equals("0"))){
			            			    total_rent+=Long.parseLong((UserAlliace.AnySignremover( estatem[indx][12]))) ; //zigma Rent
			            			    ziprentcount++;
			            			}
			            			if (!(UserAlliace.AnySignremover( estatem[indx][25]).equals("0"))){
			            			     Total_Price_per_Sqrft+=  Long.parseLong((UserAlliace.AnySignremover( estatem[indx][25])));// Zigma Price per sqft
			            			     zip_pricesqftcount++;
			            			}
			            			indx++;
			            			if(indx>=opt){break;}
			            		}
			            		gui.progressBarZillow.setValue(98);
			            		Double Avg_Index2=0.0;
			            		long Avg_Price=0;
			            		long Avg_Rent=0;
			            		long Avg_Pricesqft=0;
			            		if (zipcount!=0)
			            			{
			            			Avg_Index2= (total_index2/zipcount);
			            			Avg_Price= Math.round((total_Price/zippricecount));
			            			Avg_Rent= Math.round((total_rent/ziprentcount));
			            			Avg_Pricesqft= Math.round((Total_Price_per_Sqrft/zip_pricesqftcount));
			            			}
			            		String Round_Avg_Index2=(new DecimalFormat("##.###").format(Avg_Index2)).toString();
			            		String Round_Avg_Price= Long.toString(Avg_Price); 
			            		String Round_Avg_Rent=Long.toString(Avg_Rent);
			            		String Round_Avg_PriceSqft=Long.toString(Avg_Pricesqft);
			            	for (int jj=shorou;jj<zipcount+shorou;jj++)
			            	{
			            		estatem[jj][20]= Round_Avg_Index2;
			            		estatem[jj][22]= Round_Avg_Price;
			            		estatem[jj][24]= Round_Avg_Rent;
			            		estatem[jj][26]= Round_Avg_PriceSqft;
			            		
			            	}
			            	/*if (indx<opt-1)
			            	indx--;*/
			            	}
			            	else
			            		{break;}
			            }// While (True)
			            buff.close();
			            indx=0;
			            //*************** CALCULATING The STANDARD DEVIATION  ******************************************
			         while (true){   
		            	double total_StdDev=0;
		            	double price_StdDev=0;
		            	if( ( indx<(estatem.length) ) )
		            	
		            	{ 		
		            		int shorou=indx;
		            		String zip_sample= UserAlliace.AnySignremover(estatem[indx][4]);
		            		int zipcount=0;
		            		
		            		while ( (indx<(estatem.length))&&UserAlliace.AnySignremover(estatem[indx][4]).equals(zip_sample) )
		            		{  if (!(UserAlliace.AnySignremover( estatem[indx][25]).equals("0"))&&!(UserAlliace.AnySignremover( estatem[indx][7]).equals("0")))
		            			{
		            			total_StdDev+=Math.pow((Integer.parseInt(UserAlliace.AnySignremover( estatem[indx][25]))-Integer.parseInt(UserAlliace.spaceremover( estatem[indx][26]))),2); //zigma index2
		            			price_StdDev+=Math.pow((Integer.parseInt(UserAlliace.AnySignremover( estatem[indx][7]))-Integer.parseInt(UserAlliace.spaceremover( estatem[indx][22]))),2); //zigma index2
		            			zipcount++;
		            			}
		            			indx++;
		            			if(indx>=(estatem.length))
		            			{
		            				break;
		            			}
		            			
		            		}
		            		gui.progressBarZillow.setValue(99);
		            		Double STD_DEV=0.0;
		            		Double PRICE_STD_DEV=0.0;
		            		if (zipcount!=0)
		            			{
		            			STD_DEV= Math.pow((total_StdDev/zipcount),0.5);
		            			PRICE_STD_DEV= Math.pow((price_StdDev/zipcount),0.5);
		            			}
		            		String Round_STD_DEV=(new DecimalFormat("##.#").format(STD_DEV)).toString();
		            		String Round_PRICE_STD_DEV=(new DecimalFormat("##.#").format(PRICE_STD_DEV)).toString();
		            	for (int jj=shorou;jj<(indx);jj++)
		            	{
		            		estatem[jj][27]= Round_STD_DEV;
		            		estatem[jj][28]=Round_PRICE_STD_DEV;
		            	}
		            	}
		            	
		            	else
		            		{break;}
		            	
		            }
			           // gui.standarddeviation(estatem);
			            int estatem_length=estatem.length;
			            FileWriter fileOut2 = new FileWriter("3rdPhase.csv",false);
	 			        fileOut2.write(UserAlliace.SAR_Head_File);
	 			        for (int eghd=0;((!(estatem[eghd].equals(null)))&&(!(estatem[eghd][1].equals(null))));eghd++){
	 			        	
	 			        	String matnString2="";
	 			        	for (int shomar=0;shomar<((estatem[eghd].length)-1);shomar++)
	 			        	{
	 			        		matnString2+=estatem[eghd][shomar]+",";
	 			        		}
	 			        	
	 			        	matnString2=matnString2+estatem[eghd][((estatem[eghd].length)-1)]+"\n"; //21sth element or [20] is avgindex2 25th element [24] is Avg Rent  
	 			        	fileOut2.write(matnString2);
	 			        	if((eghd+1)>=estatem_length)
	 			        		break;
	 			        }
	 			        fileOut2.close();
	 			       gui.progressBarZillow.setValue(100);
	 			      
	 			      String zIP_CurrentString="22063";
	 			      int numi=1;
	             	 for ( numi=1; numi<(estatem.length-1) ;numi++ )
	             	 {
	             		 if (estatem[numi][4].equals(zIP_CurrentString))
	             			 break;
	             		 }
	 		            JOptionPane
	 					.showMessageDialog(
	 							null,
	 							"<html><h2> Abnormal Records were Cleaned </h2> <br> 3rdphase and Cleaned_From_2ndPhase are created <br> <h1> "+"for Zip Code= "+estatem[numi][4]+",   The Average of Price is: "+estatem[numi][22]+" $ ,   and The Average of Rent is: "+estatem[numi][24]+" $ </h1></html>",
	 							"successfull Cleaning !",
	 							JOptionPane.INFORMATION_MESSAGE);
	 			      
	 			      

		            }
		            catch(Exception rex){
		            	 System.out.println(rex.getCause().getMessage());
		            	 JOptionPane.showMessageDialog(null,rex );
		            }
            	 
       //****************************************8     	 
            	 
            }
	    	else 
	    	{
	    		JOptionPane
					.showMessageDialog(
							null,
							"<html><h2>the output file (2Phase.csv) was not found </h2> <br> First run phase one and then phase two</html>",
							"Try after the 2nd phase is finished successfully !",
							JOptionPane.WARNING_MESSAGE);
	    	}
	    	
	    	return null;
	    }
	    }
 class Browser_Simul_Beh extends Region {
	 
	     WebView jav_browser = new WebView();
	     WebEngine jav_webEngine = jav_browser.getEngine();
	    
	    }
 