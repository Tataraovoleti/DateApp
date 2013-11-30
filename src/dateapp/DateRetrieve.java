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
 * Version      : 1.6
 * Database    : MySQL
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

    System.out.println(" "+ud.toString());
		SimpleDateFormat sfd= new SimpleDateFormat("dd/MMM/yyyy");
          System.out.println(" "+sfd.format(ud));
		}
		   
	   ps.close();
	   con.close();
        }
}
