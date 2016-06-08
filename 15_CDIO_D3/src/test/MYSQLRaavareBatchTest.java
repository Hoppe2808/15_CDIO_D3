package test;
import java.sql.SQLException;		

import tempName.server.data.daoimpl.MYSQLRaavareBatchDAO;
import tempName.server.data.daointerface.DALException;		
import tempName.server.data.database.Connector;
import tempName.shared.dto.RaavareBatchDTO;		


public class MYSQLRaavareBatchTest {	

	public static void main(String[] args) {		
		try { 		
			new Connector(); 		
		} catch (Exception e) {		
			e.printStackTrace(); 		
		}

		test1();		

		test2();		

		test3();		

		MYSQLRaavareBatchDAO dao = new MYSQLRaavareBatchDAO();
		System.out.println("Alle raavarebatches vises:");		
		try { 		
			System.out.println(dao.getRaavareBatchList()); 		
		}		
		catch (DALException e) {		
			System.out.println(e.getMessage()); 		
		}		
				

	}

	private static void test3() {
		System.out.println("Opdatering af maengde for raavarebatch ");
		MYSQLRaavareBatchDAO dao = new MYSQLRaavareBatchDAO();
		
		RaavareBatchDTO rbDTO = new RaavareBatchDTO();	
		rbDTO.setRaavareId(7);
		rbDTO.setMaengde(200d);	
		try { 		
			dao.updateRaavareBatch(rbDTO);		
		}		
		catch (DALException e) { 		
			System.out.println(e.getMessage()); 		
		}		

		System.out.println("Opdateret raavarebatch ");	
		try { 		
			System.out.println(dao.getRaavareBatch(8));		
		}		
		catch (DALException e) {		
			System.out.println(e.getMessage()); 		
		}
	}

	private static void test2() {
		System.out.println("Oprettelse af ny raavarebatch "); // Virker ikke
		MYSQLRaavareBatchDAO dao = new MYSQLRaavareBatchDAO();
		RaavareBatchDTO rbDTO = new RaavareBatchDTO();	
		rbDTO.setRaavareId(7);
		rbDTO.setMaengde(3.3);
		try { 		
			dao.createRaavareBatch(rbDTO); 		
		}catch (DALException e) {		
			System.out.println(e.getMessage());		
		}			
		System.out.println(" Det senest tilføjet raavarebatch ");	
		try { 		
			System.out.println(dao.getRaavareBatch(8)); 		
		}		
		catch (DALException e) {		
			System.out.println(e.getMessage());		
		}
	}

	private static void test1() {
		System.out.println("RaavareBatch nummer 1 ");		
		MYSQLRaavareBatchDAO dao = new MYSQLRaavareBatchDAO();		
		try { 		
			System.out.println(dao.getRaavareBatch(1).toString()); 		
		}		
		catch (DALException e) { 		
			System.out.println(e.getMessage()); 		
		}
	}		
}

