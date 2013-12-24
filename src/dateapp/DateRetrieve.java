/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dateapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 * Technology : Java
 * Version    : 1.6
 * Database   : MySQL
 * @author TATARAO
 */
public class DateRetrieve {
    public static void main(String arg[]) throws Exception
	{
		Class.forName("org.gjt.mm.mysql.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rao","root","");
	
	        PreparedStatement ps = con.prepareStatement("select * from date_tab");
		ResultSet rs= ps.executeQuery();
		while(rs.next())
		{
		System.out.print(rs.getInt(1)+" "+rs.getString(2)+" ");
                // converting java.sql.Date format to java.util.Date format
		 java.sql.Date sqd= rs.getDate(3);
		 java.util.Date ud= (java.util.Date)sqd;
		 
		  java.sql.Timestamp stmp= rs.getTimestamp(4);
		  long ms= stmp.getTime();
		  java.util.Date udd=new java.util.Date(ms);
                
                //System.out.println(" "+ud.toString());
                /* Mysql stores date in the form of yyyy-MM-dd , timestamp in the form of yyyy-MM-dd HH:mm:ss
                 * Now we are getting the date and timestamp form DB and parsing it into required pattern  
                 * ie, dd/MMM/yyyy. we can change this format
                */
		SimpleDateFormat sfd= new SimpleDateFormat("dd/MMM/yyyy");
		SimpleDateFormat sfdd= new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
                System.out.println("Dob - "+sfd.format(ud));
                System.out.println("Creation time - "+sfdd.format(udd));
		}
		   
	   ps.close();
	   con.close();
        }
}
