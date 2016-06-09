package test;
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

		test4();				


	}
	private static void test1() {	
		MYSQLRaavareBatchDAO dao = new MYSQLRaavareBatchDAO();		
		try { 		
			System.out.println("Test 1: Raavarebatch med ID 1 vises her -->  "+dao.getRaavareBatch(1).toString()); 		
		}		
		catch (DALException e) { 		
			System.out.println(e.getMessage()); 		
		}
	}	
	private static void test2() {
		MYSQLRaavareBatchDAO dao = new MYSQLRaavareBatchDAO();	
		try { 		
			System.out.println("Test 2: Alle raavarebatches vises -->  "+ dao.getRaavareBatchList()); 		
		}		
		catch (DALException e) {		
			System.out.println(e.getMessage()); 		
		} 
	}
	private static void test3() { 
		MYSQLRaavareBatchDAO dao = new MYSQLRaavareBatchDAO();
		RaavareBatchDTO rbDTO = new RaavareBatchDTO();	
		rbDTO.setRaavareId(7);
		rbDTO.setMaengde(7.7);
		try { 		
			dao.createRaavareBatch(rbDTO); 		
		}catch (DALException e) {		
			System.out.println(e.getMessage());		
		}				
		try { 		
			System.out.println("Test 3: Oprettelse af nyt raavarebatch vises her -->  "+dao.getRaavareBatch(26).toString()); 	
		}		
		catch (DALException e) {		
			System.out.println(e.getMessage());		
		}
	}
	private static void test4() {
		MYSQLRaavareBatchDAO dao = new MYSQLRaavareBatchDAO();

		RaavareBatchDTO rbDTO = new RaavareBatchDTO();
		rbDTO.setRbId(1);
		rbDTO.setRaavareId(1);
		rbDTO.setMaengde(1111d);	
		try { 		
			dao.updateRaavareBatch(rbDTO);		
		}		
		catch (DALException e) { 		
			System.out.println(e.getMessage()); 		
		}		

		try { 		
			System.out.println("Test 4: Opdateret raavarebatch -->  "+ dao.getRaavareBatch(1).toString());		
		}		
		catch (DALException e) {		
			System.out.println(e.getMessage()); 		
		}
	}
}

