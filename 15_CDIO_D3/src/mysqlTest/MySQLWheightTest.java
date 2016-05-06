package mysqlTest;

import java.sql.SQLException;

import tempName.server.data.daoimpl.MYSQLWeightDAO;
import tempName.server.data.daointerface.DALException;
import tempName.server.data.database.Connector;
import tempName.server.data.dto.WeightDTO;

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
		try{
			System.out.println(w.getWeightList());
		}catch(DALException e){
			System.out.println(e.getMessage());
		}
		
		System.out.println("Indsættelse af ny vægtmåling med o_id = 200: ");
		WeightDTO wDTO = new WeightDTO();
		// 200, 15, 200
		try{
			w.addWeight(wDTO);
			System.out.println(w.getWeightList());
		}catch(DALException e){
			System.out.println(e.getMessage());
		}
	}
}
