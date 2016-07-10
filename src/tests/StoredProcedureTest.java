package tests;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import database.DataBase;

public class StoredProcedureTest {
	public static void main(String[] args){
		try {
			CallableStatement proc = DataBase.getConnection().prepareCall("{ call Nothing(?,?)}");			    
				proc.setString(1, "1234");					
				proc.setString(2, "ZAMBIJA");						      
				proc.execute();
				System.out.println("EXECUTED");
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				System.out.println("EXC");
				e.printStackTrace();
				
			}
		
        Connection con = DataBase.getConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            StringBuilder builder = new StringBuilder("");
            builder.append("CREATE PROCEDURE drzavaSelect");
            builder.append("SELECT DR_SIFRA,DR_NAZIV FROM DRZAVA");
            stmt.execute(builder.toString());
            System.out.println("CREATED");

        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
	}
}
