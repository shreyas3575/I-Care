
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Hoapital {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("::::::::::::::Welcome:::::::::::::::::::::::");
		System.out.println("Enter Your User Name:");
		String Uname=sc.next();
		if(Uname.equals("shreyas"))
		{
			System.out.println("Enter Your Password:");
			String pass=sc.next();
			if(pass.equals("pass123"))
			{
				System.out.println("Login Succesful");
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
					try {
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/I_Care","root","root");
							
						Statement st=con.createStatement();
					
						String qu;
						ResultSet rs=null;
						
						int run=1;
						while(run==1)
						{
						System.out.println("\n 1.Show All Pationt \n 2.Enter New Patiant \n 3.Search By Id \n 4.Discharge Patiant \n 5.Discharge List \n 6.Exit");
						int ch=sc.nextInt();
						switch(ch){
						case 1:
							
								qu="select * from Patiant";
								 rs=st.executeQuery(qu);
								//System.out.println("ID \t Name \t\t Gender \t Age \t Disease \t Adress \t Companion Phone Number \t\t Bed Number");
								
								System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
								System.out.printf("                                                                 ALL Patiants                                                                                      \n");
								System.out.printf("--------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
								System.out.printf("| %-10s  | %-30s | %-10s | %-5s | %-30s | %-30s | %-20s | %-5s |\n","ID","Name","Gender","Age","Disease","Adress","Companion Phone No.","Bed No.");
								System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

								while(rs.next())
								{
									System.out.printf("| %-10d  | %-30s | %-10s | %-5d | %-30s | %-30s | %-20s | %-5d |\n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8));
									
									
									
									//System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7)+"\t\t"+rs.getInt(8));
								}
								System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
									
							break;	
						case 2:
							
							System.out.println("Enter Patiant Name :");
							String pname=br.readLine();
							System.out.println("Enter Gender :");
							String pgen=sc.next();
							System.out.println("Enter Age :");
							int age=sc.nextInt();
							System.out.println("Enetr Disease :");
							String dis=br.readLine();
							System.out.println("Enter Adress :");
							String add=br.readLine();
							System.out.println("Enter Companion Phone Number :");
							String Com=br.readLine();
							System.out.println("Enter Bed Number :");
							int bed=sc.nextInt();
							
							String ins="insert into Patiant(Pname,Pgender,Page,disease,Paddress,ComPhoneNo,bedNo) values('"+pname+"','"+pgen+"',"+age+",'"+dis+"','"+add+"','"+Com+"',"+bed+")";
							
							PreparedStatement ps=con.prepareStatement(ins);
							if(!ps.execute())
							{
								System.out.println("Data Enter Sucsesfully!!!!");
							}
							else
							{
								System.out.println("Data Not Enter");
							}
							
							break;
						case 3:
							System.out.println("Enter Patiant ID :");
							int id=sc.nextInt();
							qu="select * from Patiant where PId="+id;
							rs=st.executeQuery(qu);
							System.out.printf("--------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
							System.out.printf("| %-10s  | %-30s | %-10s | %-5s | %-30s | %-30s | %-20s | %-5s |\n","ID","Name","Gender","Age","Disease","Adress","Companion Phone No.","Bed No.");
							System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

							rs.next();
							System.out.printf("| %-10d  | %-30s | %-10s | %-5d | %-30s | %-30s | %-20s | %-5d |\n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8));
								
								
								
								//System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7)+"\t\t"+rs.getInt(8));
							System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
							
							break;
							
							
						case 4:
							
							System.out.println("Enter patiant Id :");
							int did=sc.nextInt();
							
							System.out.println("Enter Discharge Status :");
							String discharge=sc.next();
							
							
							/*
							 * SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd");
							 * 
							 * Date d=new Date(); String date=df.format(d);
							 */
							
							System.out.println("Enter Date in yyyy-mm-dd");
							String date=br.readLine();
							
							System.out.println("Total Charges :");
							int charge=sc.nextInt();
							
							System.out.println("Payment Status :");
							String sta=br.readLine();
							
							String in="insert into Discharge values ("+did+",'"+discharge+"','"+date+"',"+charge+",'"+sta+"')";
							
							PreparedStatement s=con.prepareStatement(in);
							s.execute();							
							
							
							
							break;
							
						case 5:
							qu="select p.Pid,p.Pname,p.Pgender,p.Paddress,p.bedNo,d.dischargeStatus,d.Ddate,d.Total_Exp,d.payment_Status from patiant p inner join discharge d on p.PId=d.PId";
							rs=st.executeQuery(qu);
							
							System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
							System.out.printf("                                                                 Discharge Patiants                                                                                      \n");
							System.out.printf("--------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
							System.out.printf("| %-10s | %-30s | %-10s | %-30s | %-5s | %-10s | %-10s | %-10s | %-10s |\n","ID","Name","Gender","Adress","Bed No.","Discharge Status","Date","Expenses","Payment Status");
							System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

							while(rs.next())
							{
								System.out.printf("| %-10s | %-30s | %-10s | %-30s | %-8d | %-15s | %-10s | %-10d | %-10s |\n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9));
							}
							
							break;
							
						case 6:
							System.out.println("::::::::::::::::::::::::::::Exit::::::::::::::::::::::::::::::");
							run=2;
							break;
						}
						
						}
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch(IOException e)
					{
						e.printStackTrace();
					}
				
			}
			else
			{
				System.out.println("PassWord is Wrong!!");
			}
		}else
		{
			System.out.println("User Name is Wrong!!");
		}
		
	
	}

}
