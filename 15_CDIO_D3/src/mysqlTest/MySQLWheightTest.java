package mysqlTest;

import java.sql.SQLException;

import tempName.server.data.daoimpl.MYSQLWeightDAO;
import tempName.server.data.database.Connector;

public class MySQLWheightTest {

	public static void main(String[] args) {
		try { 
			new Connector(); 
		} catch (InstantiationException e) {
			e.printStackTrace(); 
		}catch (IllegalAccessException e) {
			e.printStackTrace(); 
		}catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		}catch (SQLException e) {
			e.printStackTrace(); 
		}
		
		MYSQLWeightDAO w = new MYSQLWeightDAO();
		
		System.out.println("Liste over vægtmålinger: ");
		
	}
}
