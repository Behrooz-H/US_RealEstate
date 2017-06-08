package Zippy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JButton;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.DecimalFormat;



public class Mortgage_calc extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3782992565841294996L;
	private JPanel contentPane;
	private JTextField PeriodYerastextField;
	private JTextField DownPaytextField;
	private JTextField interest_textField;
	private JTextField insurance_textField;
	private JTextField Tax_textField;
	private JButton btnexit;
	private JLabel lblDefault;
	private JLabel lblDefaultIf;
	private JLabel lblYearsDefault;
	private JLabel lblZeroAsDefault;
	private JTextField W_TextField;
	private JTextField X_TextField;
	private JTextField Y_TextField;
	private JTextField Z_TextField;
	//private JLabel ZipLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mortgage_calc frame = new Mortgage_calc(  );
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getCause().getMessage());
					JOptionPane.showMessageDialog(null,e );
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */


	public static String Commaremover(String inputstring){
		while (inputstring.contains(","))
		{
			String sect1=inputstring.substring(0, inputstring.indexOf(",")); 
			String sect2=inputstring.substring((inputstring.indexOf(",")+1), inputstring.length());
			inputstring=sect1+sect2;

		}
		return inputstring;
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
	public static String spaceremover(String tetstString)
	{
		while(tetstString.contains(" "))
		{
			String SS1= tetstString.substring( 0,tetstString.indexOf(" "));
			String SS2=tetstString.substring( (tetstString.indexOf(" ")+1),tetstString.length());
			tetstString= SS1+SS2;						 
		}
		return tetstString ;
	}
	

	public Mortgage_calc( ) {
		setTitle("Mortgage  & Cash Flow Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 445);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		PeriodYerastextField = new JTextField();
		PeriodYerastextField.setToolTipText("between 2 to 30 years");
		PeriodYerastextField.setForeground(Color.BLACK);
		PeriodYerastextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PeriodYerastextField.setColumns(10);
		PeriodYerastextField.setBounds(253, 234, 70, 37);
		contentPane.add(PeriodYerastextField);

		DownPaytextField = new JTextField();
		DownPaytextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DownPaytextField.setColumns(10);
		DownPaytextField.setBounds(219, 165, 99, 39);
		contentPane.add(DownPaytextField);

		JLabel lblNewLabel_1 = new JLabel("Down Payment in $");
		lblNewLabel_1.setBackground(SystemColor.window);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(50, 174, 144, 21);
		contentPane.add(lblNewLabel_1);

		JLabel lblPeriodOfPay = new JLabel("period of pay back in years");
		lblPeriodOfPay.setForeground(Color.BLACK);
		lblPeriodOfPay.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPeriodOfPay.setBackground(Color.WHITE);
		lblPeriodOfPay.setBounds(50, 234, 193, 26);
		contentPane.add(lblPeriodOfPay);
		final JLabel ZipLabel = new JLabel("");
		ZipLabel.setBackground(Color.WHITE);
		ZipLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		ZipLabel.setForeground(new Color(0, 204, 153));
		ZipLabel.setBounds(441, 21, 78, 38);
		
		
		
		JButton btnNewButton = new JButton("<html>Calculate Mortgage<br>Cash Flow</html>");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAlliace use2=new UserAlliace();
				String received_Zip=use2.Return_Zip_TextBox() ;
				ZipLabel.setText(received_Zip);
				//NotationLabel.setVisible(false);
				ZipLabel.setVisible(false);
				if (!(received_Zip.equals("")))
				{
//				NotationLabel.setVisible(true);
				ZipLabel.setVisible(true);
				}
				JOptionPane.showMessageDialog(null, "The Mortgage and Cashflow will be calculated for all real estets located in file '3rdPhase.csv' ", "Mortgage Calculation",JOptionPane.INFORMATION_MESSAGE );
				//contentPane.removeAll();
				String interString  = interest_textField.getText();
				String PeriodString=PeriodYerastextField.getText();
				String downPayString=DownPaytextField.getText();
				String mortgagetaxString=Tax_textField.getText();
				String insuranceString=insurance_textField.getText();
				String PriceText="00";
				//int found=0;
				//String Mortgagestring="0";
				if (insuranceString.matches(""))
					insuranceString="00";
				if (mortgagetaxString.matches(""))
					mortgagetaxString="00";
				if (downPayString.matches(""))
					downPayString="45000";
				if (PeriodString.matches(""))
					PeriodString="30";
				if (interString.matches(""))
					interString="3.27";

				if ((PeriodString.matches("[0-9]+")) && Integer.parseInt(PeriodString)>1 &&Integer.parseInt(PeriodString)<=30 ){
					int Periodint=Integer.parseInt(PeriodString);
					double interest_double=Double.parseDouble(interString);
					double down_pay=  Double.parseDouble(downPayString);

					String csvFile = "3rdPhase.csv";
					BufferedReader br = null;
					String line = "";
					String cvsSplitBy = ",";
					int line_number=0;
					try {LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(csvFile)));
					lnr.skip(Long.MAX_VALUE);
					 line_number= lnr.getLineNumber() ; 
					// Finally, the LineNumberReader object should be closed to prevent resource leak
					lnr.close();}
					catch (Exception exc)
					{
						System.out.println(exc.getCause().getMessage());
						JOptionPane.showMessageDialog(null,exc );
					}
					 String[][] estate = new  String [(line_number-1)][];// the amount of lines of first collected csv file
			        int choice=0;

					try {
						
						
						br = new BufferedReader(new FileReader(csvFile));
						br.readLine();
						while ((line = br.readLine()) != null&& (choice<(line_number-1))) {

							// use comma as separator
							 estate[choice] = line.split(cvsSplitBy);
							 if (!(received_Zip.equals(""))){
							if ((AnySignremover(estate[choice][4]).matches(received_Zip))){   
								//found=1;
								if ((!(AnySignremover( estate[choice][7]).equals("")))&&(!(AnySignremover( estate[choice][7]).equals("?????"))))
								{
								PriceText=AnySignremover( estate[choice][7]);
								String RentText=AnySignremover(estate[choice][12]);
								double Rent_Double=Double.parseDouble(RentText);
								double estimated_price_int= Double.parseDouble(PriceText);	
								if(down_pay<estimated_price_int)
								{
									double p=estimated_price_int - down_pay ;
									double r= interest_double/1200;
									int n = 12*Periodint;
									Double mortgageDouble=(p*(r*( java.lang.Math.pow((1+r), n))))/( java.lang.Math.pow((1+r), n)-1);
									long mormor=   Math.round(mortgageDouble) ;
									long mormor2=Math.round(Math.round(Double.parseDouble(mortgagetaxString)/12))+( Math.round(Double.parseDouble(insuranceString)/12)) ;
									long mormor1= mormor2+mormor;
									estate[choice][18]=  Long.toString(mormor1) +" $/mo";
									int w_coef=11; int x_coef=12;double y_coef=0.01;int z_coef=2;
									if (W_TextField.getText() .matches("[0-9]+") )
										w_coef=Integer.parseInt(W_TextField.getText() );	
									if (X_TextField.getText() .matches("[0-9]+") )
										x_coef=Integer.parseInt(X_TextField.getText() );
									if (Z_TextField.getText() .matches("[0-9]+") )
										z_coef=Integer.parseInt(Z_TextField.getText() );
									if (!(Y_TextField.getText().equals("")))
										y_coef=Integer.parseInt(Y_TextField.getText() );
								
									double midCashFlow= ((w_coef*Rent_Double)-(mormor1*x_coef)-(y_coef *estimated_price_int)-(Rent_Double/z_coef))/12;
									estate[choice][21]=(new DecimalFormat("##.###").format(midCashFlow)).toString();
								}
								else 
								{
									estate[choice][18]="00$/mo";
									estate[choice][21]="0";
									/*JOptionPane
									.showMessageDialog(
											null,
											" Downpayment is bigger than the price of the home mortgage was saved zero for this record",
											"your down payment is more than your principle loan",
											JOptionPane.WARNING_MESSAGE );*/
								}
								}

							}} 
							 else {
								 if (spaceremover(estate[choice][18]).equals("00$/mo")) 
								 {
									 if ((!(spaceremover(AnySignremover( estate[choice][7])).equals("")))&&(!(spaceremover(AnySignremover( estate[choice][7])).equals("?????"))))
										{
										PriceText=AnySignremover( estate[choice][7]);
										double estimated_price_int= Double.parseDouble(PriceText);	
										String RentText=AnySignremover(estate[choice][12]);
										double Rent_Double=Double.parseDouble(RentText);
										if(down_pay<estimated_price_int)
										{
											double p=estimated_price_int - down_pay ;
											double r= interest_double/1200;
											int n = 12*Periodint;
											Double mortgageDouble=(p*(r*( java.lang.Math.pow((1+r), n))))/( java.lang.Math.pow((1+r), n)-1);
											long mormor=   Math.round(mortgageDouble) ;
											long mormor2=Math.round(Math.round(Double.parseDouble(mortgagetaxString)/12))+( Math.round(Double.parseDouble(insuranceString)/12)) ;
											long mormor1= mormor2+mormor;
											estate[choice][18]=  Long.toString(mormor1) +" $/mo";
											double midCashFlow= ((11*Rent_Double)-(mormor1*12)-(0.01*estimated_price_int)-(Rent_Double/2))/12;
											estate[choice][21]=(new DecimalFormat("##.###").format(midCashFlow)).toString();
										}
										else 
										{
											estate[choice][18]="00$/mo";
											estate[choice][21]="0";
											/*JOptionPane
											.showMessageDialog(
													null,
													" Downpayment is bigger than the price of the home mortgage was saved zero for this record",
													"your down payment is more than your principle loan",
													JOptionPane.WARNING_MESSAGE );*/
										}
										} 
								 }
								 
							 }
							choice++;
						} br.close();
						
						int j=0; 
						int ind=0;
						while(j<choice)
						{
							Double total_Cashflows=0.0;
						    int Cash_Flow_Cunts=0;
						    String sample_zip=estate[j][4];
						    //String [][] gggString= new String [156][1];
						for (int i=j;(estate[i][4].equals(sample_zip) && i<choice);i++)
						{
							//gggString[i][1]=estate[i][21];
							total_Cashflows+= Double.parseDouble(estate[i][21]); // Cash Flow
							Cash_Flow_Cunts++;ind++;
							if ((i+1)==choice)break;
							
						}
						    for (int u=j;u<ind;u++)
						    {
						    	estate[u][23]= (new DecimalFormat("##.###").format((total_Cashflows/Cash_Flow_Cunts))).toString(); // writing Avg of Cash Flow
						    }
							
							j=ind;
						}

					} catch (FileNotFoundException ee) {
						ee.printStackTrace();
						System.out.println(ee.getCause().getMessage());
						JOptionPane.showMessageDialog(null,ee );
					} catch (IOException ee) {
						ee.printStackTrace();
						System.out.println(ee.getCause().getMessage());
						JOptionPane.showMessageDialog(null,ee );
					} finally {
						if (br != null) {
							try {
								br.close();
							} catch (IOException ee) {
								ee.printStackTrace();
								System.out.println(ee.getCause().getMessage());
								JOptionPane.showMessageDialog(null,ee );
							}
						}
					}

					try{
				        FileWriter fileOut = new FileWriter("3rdPhase.csv",false);
				        fileOut.write(UserAlliace.SAR_Head_File);
				        for (int step=0; step <choice;step++)
				        {
				        	String matnString=new String ("");
				        	for (int shomar=0;shomar<(estate[step].length-1);shomar++)
				        		matnString+=estate[step][shomar]+",";
				        	matnString=matnString+estate[step][(estate[step].length-1)]+"\n";
				        	fileOut.write(matnString);	
				        } 
				        fileOut.close();
				        JOptionPane
						.showMessageDialog(
								null,
								"<html><h2>the mortgage field in file (3rdPhase.csv) has been successfully filled completely !</h2></html>",
								"Succeccful Mortgage calculations ! ",
								JOptionPane.INFORMATION_MESSAGE );
				        }
				        catch(Exception exce)
				        {
				        	JOptionPane
							.showMessageDialog(
									null,
									"the output file (2Phase.csv) is in use by another program and OUTPUT could not be written",
									"Error ! Please try again"+ exce,
									JOptionPane.ERROR_MESSAGE );
				        	System.out.println(exce.getCause().getMessage());
				        }
				
				
				}
				else // payment value is not right
				{JOptionPane
					.showMessageDialog(
							null,
							" Wrong value entered for the payment years",
							"enter only a number between 1 to 30",
							JOptionPane.WARNING_MESSAGE );
				}
				
				//NotationLabel.setVisible(false);
				ZipLabel.setVisible(false);
			}
			
			
			
		});
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setToolTipText("click to calculate the mortgage");
		btnNewButton.setIcon(null);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setForeground(new Color(0, 153, 0));
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.setBounds(373, 231, 117, 59);
		contentPane.add(btnNewButton);

		interest_textField = new JTextField();
		interest_textField.setToolTipText("between 0 to 99");
		interest_textField.setForeground(Color.BLUE);
		interest_textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		interest_textField.setColumns(10);
		interest_textField.setBounds(499, 165, 51, 37);
		contentPane.add(interest_textField);

		JLabel lblInterestRateIn = new JLabel("interest rate in %");
		lblInterestRateIn.setForeground(Color.BLUE);
		lblInterestRateIn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInterestRateIn.setBackground(Color.WHITE);
		lblInterestRateIn.setBounds(357, 170, 157, 26);
		contentPane.add(lblInterestRateIn);

		JLabel label = new JLabel("insurance payment per year");
		label.setToolTipText("insurance ");
		label.setForeground(new Color(102, 0, 51));
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(38, 354, 217, 25);
		contentPane.add(label);

		insurance_textField = new JTextField();
		insurance_textField.setForeground(new Color(102, 0, 0));
		insurance_textField.setFont(new Font("Tahoma", Font.BOLD, 17));
		insurance_textField.setColumns(10);
		insurance_textField.setBounds(257, 345, 66, 32);
		contentPane.add(insurance_textField);

		JLabel label_1 = new JLabel("mortgage tax fee per year");
		label_1.setToolTipText("mortgage tax fee");
		label_1.setForeground(new Color(102, 0, 51));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(38, 297, 217, 25);
		contentPane.add(label_1);

		Tax_textField = new JTextField();
		Tax_textField.setForeground(new Color(102, 0, 0));
		Tax_textField.setFont(new Font("Tahoma", Font.BOLD, 17));
		Tax_textField.setColumns(10);
		Tax_textField.setBounds(257, 293, 66, 32);
		contentPane.add(Tax_textField);

		btnexit = new JButton("<html>Exit</html>");
		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnexit.setToolTipText("click to calculate the mortgage");
		btnexit.setForeground(Color.BLACK);
		btnexit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnexit.setBackground(new Color(0, 139, 139));
		btnexit.setBounds(437, 321, 99, 43);
		contentPane.add(btnexit);

		lblDefault = new JLabel("3.27 % default if left blank");
		lblDefault.setForeground(Color.BLUE);
		lblDefault.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDefault.setBackground(Color.WHITE);
		lblDefault.setBounds(357, 188, 193, 26);
		contentPane.add(lblDefault);

		lblDefaultIf = new JLabel("45,000$ default if left blank");
		lblDefaultIf.setForeground(Color.BLACK);
		lblDefaultIf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDefaultIf.setBackground(Color.WHITE);
		lblDefaultIf.setBounds(50, 194, 193, 26);
		contentPane.add(lblDefaultIf);

		lblYearsDefault = new JLabel("30 years default if left blank");
		lblYearsDefault.setForeground(Color.BLACK);
		lblYearsDefault.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblYearsDefault.setBackground(Color.LIGHT_GRAY);
		lblYearsDefault.setBounds(50, 260, 193, 26);
		contentPane.add(lblYearsDefault);

		lblZeroAsDefault = new JLabel("Zero as default if left blank (TAX/Insurance)");
		lblZeroAsDefault.setForeground(new Color(102, 0, 51));
		lblZeroAsDefault.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblZeroAsDefault.setBackground(Color.WHITE);
		lblZeroAsDefault.setBounds(38, 321, 217, 26);
		contentPane.add(lblZeroAsDefault);
		
		JButton btnresetMortgage = new JButton("<html><t> Reset  Mortgage<br>Cash Flow</html>");
		btnresetMortgage.setHorizontalAlignment(SwingConstants.TRAILING);
		btnresetMortgage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String csvFile = "3rdPhase.csv";
				BufferedReader br = null;
				String line = "";
				String cvsSplitBy = ",";
				int line_number=0;
				try {LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(csvFile)));
				lnr.skip(Long.MAX_VALUE);
				 line_number= lnr.getLineNumber() ; 
				// Finally, the LineNumberReader object should be closed to prevent resource leak
				lnr.close();}
				catch (Exception exc)
				{
					System.out.println(exc.getCause().getMessage());
					JOptionPane.showMessageDialog(null,exc );
				}
				 String[][] estate = new  String [(line_number-1)][];// the amount of lines of first collected csv file
		        int choice=0;

				try {
					
					
					br = new BufferedReader(new FileReader(csvFile));
					br.readLine();
					while ((line = br.readLine()) != null&& (choice<(line_number-1))) {

						// use comma as separator
						 estate[choice] = line.split(cvsSplitBy);
						 estate[choice][18]="00$/mo";
						 estate[choice][21]="00";
						 estate[choice][23]="00";
						 choice++;
					}
					FileWriter fileOut = new FileWriter("3rdPhase.csv",false);
			        fileOut.write(UserAlliace.SAR_Head_File);
			        for (int step=0; step <choice;step++)
			        {
			        	String matnString=new String ("");
			        	for (int shomar=0;shomar<((estate[step].length)-1);shomar++)
			        		matnString+=estate[step][shomar]+",";
			        	matnString=matnString+estate[step][((estate[step].length)-1)]+"\n";
			        	fileOut.write(matnString);
			        }
			        fileOut.close();
			        JOptionPane
					.showMessageDialog(
							null,
							"<html><h2>the mortgage field in file (3rdPhase.csv) has been successfully reset to zero value (0) completely for all records !</h2></html>",
							"Succeccful Mortgage Reset to 00$/mn ! ",
							JOptionPane.INFORMATION_MESSAGE );
					}
				catch (Exception ecx)
				{
					System.out.println(ecx.getCause().getMessage());
					JOptionPane.showMessageDialog(null,ecx );
				}
			}
		});
		btnresetMortgage.setVerticalAlignment(SwingConstants.TOP);
		btnresetMortgage.setToolTipText("click to reset the mortgage");
		btnresetMortgage.setForeground(new Color(255, 0, 0));
		btnresetMortgage.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnresetMortgage.setBackground(new Color(255, 204, 204));
		btnresetMortgage.setBounds(516, 231, 120, 59);
		contentPane.add(btnresetMortgage);
		
		JLabel lblwrentxmortgageypricerentz = new JLabel("<html><b style= \"color:yellow\">Cash Flow </b>=((<b style= \"color:green\">W </b>*Rent)-(<b style= \"color:blue\">X</b>*Mortgage)-(<b style= \"color:red\">Y</b>*Price)-(Rent/<b style= \"color:#660066 \">Z</b>))/12</html>");
		lblwrentxmortgageypricerentz.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblwrentxmortgageypricerentz.setBounds(38, 11, 607, 32);
		contentPane.add(lblwrentxmortgageypricerentz);
		
		W_TextField = new JTextField();
		W_TextField.setForeground(new Color(0, 153, 51));
		W_TextField.setFont(new Font("Tahoma", Font.BOLD, 20));
		W_TextField.setBounds(120, 66, 41, 37);
		contentPane.add(W_TextField);
		W_TextField.setColumns(10);
		
		X_TextField = new JTextField();
		X_TextField.setForeground(new Color(0, 0, 204));
		X_TextField.setFont(new Font("Tahoma", Font.BOLD, 20));
		X_TextField.setColumns(10);
		X_TextField.setBounds(271, 66, 41, 37);
		contentPane.add(X_TextField);
		
		Y_TextField = new JTextField();
		Y_TextField.setForeground(new Color(255, 0, 0));
		Y_TextField.setFont(new Font("Tahoma", Font.BOLD, 20));
		Y_TextField.setColumns(10);
		Y_TextField.setBounds(452, 66, 41, 37);
		contentPane.add(Y_TextField);
		
		Z_TextField = new JTextField();
		Z_TextField.setForeground(new Color(0, 255, 0));
		Z_TextField.setFont(new Font("Tahoma", Font.BOLD, 20));
		Z_TextField.setColumns(10);
		Z_TextField.setBounds(595, 66, 41, 37);
		contentPane.add(Z_TextField);
		
		JLabel lblNewLabel = new JLabel("<html><b>W</b> <br>default: <b>11</html>");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(0, 102, 51));
		lblNewLabel.setBounds(38, 55, 82, 48);
		contentPane.add(lblNewLabel);
		
		JLabel lblxdefault = new JLabel("<html><b>X</b> <br>default: <b>12</html>");
		lblxdefault.setForeground(new Color(0, 0, 255));
		lblxdefault.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblxdefault.setBounds(185, 55, 81, 48);
		contentPane.add(lblxdefault);
		
		JLabel lblydefault = new JLabel("<html><b>Y</b> <br>default<b>: 0.01</html>");
		lblydefault.setForeground(new Color(255, 0, 0));
		lblydefault.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblydefault.setBounds(353, 55, 99, 48);
		contentPane.add(lblydefault);
		
		JLabel lblzdefault = new JLabel("<html><b>Z</b> <br>default: <b>2</html>");
		lblzdefault.setForeground(new Color(102, 0, 102));
		lblzdefault.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblzdefault.setBounds(515, 54, 82, 48);
		contentPane.add(lblzdefault);
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{DownPaytextField, interest_textField, PeriodYerastextField, Tax_textField, insurance_textField, btnNewButton}));
	}
}
