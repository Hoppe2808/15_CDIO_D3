package test;
import tempName.server.data.daoimpl.MYSQLProduktBatchDAO;
import tempName.server.data.daointerface.DALException;
import tempName.server.data.database.Connector;
import tempName.shared.dto.ProduktBatchDTO;		


public class MYSQLProduktBatchTest {	

	public static void main(String[] args) {		
		try { 		
			new Connector(); 		
		} catch (Exception e) {		
			e.printStackTrace(); 		
		}

//		test1();		
//
//		test2();
//
//		test3();
//
//		test4();				


	}
//	private static void test1() {	
//		MYSQLProduktBatchDAO dao = new MYSQLProduktBatchDAO();		
//		try { 		
//			System.out.println("Test 1: Produktbatch med ID 1 vises her -->  "+dao.getProduktBatch(1).toString()); 		
//		}		
//		catch (DALException e) { 		
//			System.out.println(e.getMessage()); 		
//		}
//	}	
//	private static void test2() {
//		MYSQLProduktBatchDAO dao = new MYSQLProduktBatchDAO();	
//		try { 		
//			System.out.println("Test 2: Alle produktatches vises -->  "+ dao.getProduktBatchList()); 		
//		}		
//		catch (DALException e) {		
//			System.out.println(e.getMessage()); 		
//		} 
//	}
//	private static void test3() { 
//		MYSQLProduktBatchDAO dao = new MYSQLProduktBatchDAO();
//		ProduktBatchDTO pbDTO = new ProduktBatchDTO();	
//		pbDTO.setReceptId(1);
//		pbDTO.setStatus(2); 
//		try { 		
//			dao.createProduktBatch(pbDTO); 		
//		}
//		catch (DALException e) {		
//			System.out.println(e.getMessage());		
//		}
//		try { 		
//			System.out.println("Test 3: Oprettelse af nyt produktbatch vises her -->  "+dao.getProduktBatch(3).toString()); 	
//		}		
//		catch (DALException e) {		
//			System.out.println(e.getMessage());		
//		}
//	}
//	private static void test4() {
//		MYSQLProduktBatchDAO dao = new MYSQLProduktBatchDAO();
//
//		ProduktBatchDTO pbDTO = new ProduktBatchDTO();
//		pbDTO.setPbId(1);
//	    pbDTO.setStatus(1);
//		pbDTO.setReceptId(1);	
//		try { 		
//			dao.updateProduktBatch(pbDTO);		
//		}		
//		catch (DALException e) { 		
//			System.out.println(e.getMessage()); 		
//		}		
//
//		try { 		
//			System.out.println("Test 4: Opdateret produktbatch -->  "+ dao.getProduktBatch(1).toString());		
//		}		
//		catch (DALException e) {		
//			System.out.println(e.getMessage()); 		
//		}
//	}
	
	
}

