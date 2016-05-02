package mysqlTest;

import java.sql.SQLException;

import tempName.server.data.daoimpl.MYSQLOperatoerDAO;
import tempName.server.data.daointerface.DALException;
import tempName.server.data.database.Connector;
import tempName.server.data.dto.OperatoerDTO;

public class MySQLTest {

	public static void main(String[] args) {
		try { 
			new Connector(); 
		} 
		catch (InstantiationException e) {
			e.printStackTrace(); 
		}
		catch (IllegalAccessException e) {
			e.printStackTrace(); 
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		}
		catch (SQLException e) {
			e.printStackTrace(); 
		}
		
		System.out.println("Operatoer nummer 1:");
		MYSQLOperatoerDAO opr = new MYSQLOperatoerDAO();
		try { 
			System.out.println(opr.getOperatoer(1).toString()); 
		}
		catch (DALException e) { 
			System.out.println(e.getMessage()); 
		}

		System.out.println("Indsaettelse af ny operatoer med opr_id =  200");
		OperatoerDTO oprDTO = new OperatoerDTO(200,"Don Juan","DJ","0000000000","password", false);
		try { 
			opr.createOperatoer(oprDTO); 
		}catch (DALException e) {
			System.out.println(e.getMessage());
		}	

		System.out.println("Operatoer nummer 200:");
		try { 
			System.out.println(opr.getOperatoer(200)); 
		}
		catch (DALException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Opdatering af initialer for operatoer nummer 200");
		oprDTO.setIni("DJu");
		try { 
			opr.updateOperatoer(oprDTO);
		}
		catch (DALException e) { 
			System.out.println(e.getMessage()); 
		}

		System.out.println("Operatoer nummer 200:");
		try { 
			System.out.println(opr.getOperatoer(200));
		}
		catch (DALException e) {
			System.out.println(e.getMessage()); 
		}

		System.out.println("Alle operatoerer:");
		try { 
			System.out.println(opr.getOperatoerList()); 
		}
		catch (DALException e) {
			System.out.println(e.getMessage()); 
		}

		System.out.println("Operatoer nummer 5:");
		try {
			System.out.println(opr.getOperatoer(5));
		}
		catch (DALException e) {
			System.out.println(e.getMessage());
		}		

	}
}
