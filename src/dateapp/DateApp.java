/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dateapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author TATARAO
 */
public class DateApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        

		Class.forName("org.gjt.mm.mysql.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rao","root","");
	PreparedStatement ps=null;
		// Converting String date values to java.util.Date class obj
		
		Scanner sc=new Scanner(System.in);
		System.out.println("enter student no : ");
		int no= sc.nextInt();

		System.out.println("enter student name : ");
		String name= sc.next();

        System.out.println("enter student dob(dd-MMM-YY) : ");
		String dob= sc.next();

		SimpleDateFormat sfd= new SimpleDateFormat("dd-MMM-yy");
		java.util.Date ud= sfd.parse(dob); 

		System.out.println(" "+ud.toString());

       // Converting java.util.Date value to java.sql.Date class obj

	   long ms = ud.getTime();

	   java.sql.Date sqld= new java.sql.Date(ms); 

//	   java.sql.Date sqld=java.sql.Date.valueOf(dob);
	   System.out.println("sql date is "+sqld.toString());


	   // create prepare statement obj
           try
           {

	   ps = con.prepareStatement("insert into date_tab values(?,?,?)");

	   ps.setInt(1,no);
	   ps.setString(2,name);
	   ps.setDate(3,sqld);

	   int res= ps.executeUpdate();

	   if(res==0)
		   System.out.println(" insertion problem");
	   else
	   System.out.println(" insertion successful");
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
	   ps.close();
	   con.close();
    }
}
