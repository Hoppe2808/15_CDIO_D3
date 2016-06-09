package test;
import tempName.server.data.daoimpl.MYSQLProduktBatchDAO;
import tempName.server.data.daointerface.DALException;
import tempName.server.data.database.Connector;
import tempName.shared.dto.ProduktBatchDTO;
import tempName.shared.dto.ProduktBatchKomponentDTO;		


public class MYSQLProduktBatchTest {	

	public static void main(String[] args) {		
		try { 		
			new Connector(); 		
		} catch (Exception e) {		
			e.printStackTrace(); 		
		}

		ProduktBatchTest1(); // Get
		ProduktBatchTest2(); // List
		ProduktBatchTest3(); // Create
		ProduktBatchTest4(); // Update				
		ProduktBatchTest5(); // Delete
		
		ProduktBatchKomponentTest1(); // Get
		ProduktBatchKomponentTest2(); // Liste over komponenter med speciffikt pbId
		ProduktBatchKomponentTest3(); // List over alle komponenter   
		ProduktBatchKomponentTest4(); // Create
		ProduktBatchKomponentTest5(); // Update				
//		ProduktBatchKomponentTest6(); // Delete


	}
	private static void ProduktBatchTest1() {	
		MYSQLProduktBatchDAO dao = new MYSQLProduktBatchDAO();		
		try { 		
			System.out.println("Test 1: Produktbatch med ID 1 vises her -->  "+dao.getProduktBatch(1).toString()); 		
		}		
		catch (DALException e) { 		
			System.out.println(e.getMessage()); 		
		}
	}	
	private static void ProduktBatchTest2() {
		MYSQLProduktBatchDAO dao = new MYSQLProduktBatchDAO();	
		try { 		
			System.out.println("Test 2: Alle produktbatches vises -->  "+ dao.getProduktBatchList()); 		
		}		
		catch (DALException e) {		
			System.out.println(e.getMessage()); 		
		} 
	}
	private static void ProduktBatchTest3() { 
		MYSQLProduktBatchDAO dao = new MYSQLProduktBatchDAO();
		ProduktBatchDTO pbDTO = new ProduktBatchDTO();	
		pbDTO.setReceptId(1);
		pbDTO.setStatus(2); 
		try { 		
			dao.createProduktBatch(pbDTO); 		
		}
		catch (DALException e) {		
			System.out.println(e.getMessage());		
		}
		try { 		
			System.out.println("Test 3: Oprettelse af nyt produktbatch vises her -->  "+dao.getProduktBatch(3).toString()); 	
		}		
		catch (DALException e) {		
			System.out.println(e.getMessage());		
		}
	}
	private static void ProduktBatchTest4() {
		MYSQLProduktBatchDAO dao = new MYSQLProduktBatchDAO();

		ProduktBatchDTO pbDTO = new ProduktBatchDTO();
		pbDTO.setPbId(1);
	    pbDTO.setStatus(1);
		pbDTO.setReceptId(1);	
		try { 		
			dao.updateProduktBatch(pbDTO);		
		}		
		catch (DALException e) { 		
			System.out.println(e.getMessage()); 		
		}		

		try { 		
			System.out.println("Test 4: Opdateret produktbatch -->  "+ dao.getProduktBatch(1).toString());		
		}		
		catch (DALException e) {		
			System.out.println(e.getMessage()); 		
		}
	}
	private static void ProduktBatchTest5() {

	}

	public static void ProduktBatchKomponentTest1() {	
		MYSQLProduktBatchDAO dao = new MYSQLProduktBatchDAO();
		try { 		
			System.out.println( "\n"+ "Test 1: ProduktbatchKomponenter med speciffik kombination af pbId og rbId hentes her -->  "+dao.getProduktBatchKomponent(4,4).toString()); 		
		}		
		catch (DALException e) { 		
			System.out.println(e.getMessage()); 		
		}
	}	
	public static void ProduktBatchKomponentTest2() {
		MYSQLProduktBatchDAO dao = new MYSQLProduktBatchDAO();	
		try { 		
			System.out.println("Test 2: Alle produktbatchKomponenter med et speciffikt pbId vises -->  "+ dao.getProduktBatchKomponent(4,3).toString()); 		
		}		
		catch (DALException e) {		
			System.out.println(e.getMessage()); 		
		} 
	}
	public static void ProduktBatchKomponentTest3() { 
		MYSQLProduktBatchDAO dao = new MYSQLProduktBatchDAO();	
		try { 		
			System.out.println("Test 3: Alle produktbatchKomponenter vises -->  "+ dao.getProduktBatchKomponentList()); 		
		}		
		catch (DALException e) {		
			System.out.println(e.getMessage()); 		
		} 
	}
	public static void ProduktBatchKomponentTest4() {
		MYSQLProduktBatchDAO dao = new MYSQLProduktBatchDAO();
		ProduktBatchKomponentDTO pbkDTO = new ProduktBatchKomponentDTO();	
		pbkDTO.setTara(3.3);
		pbkDTO.setNetto(3.3);
		pbkDTO.setOprId(3);
		pbkDTO.setPbId(4);
		pbkDTO.setRbId(4);
		try { 		
			dao.createProduktBatchKomponent(pbkDTO); 		
		}
		catch (DALException e) {		
			System.out.println(e.getMessage());		
		}
		try { 		
			System.out.println("Test 4: Oprettelse af nyt produktbatchKomponent vises her -->  "+dao.getProduktBatchKomponent(4,4).toString()); 	
		}		
		catch (DALException e) {		
			System.out.println(e.getMessage());		
		}
	}
	public static void ProduktBatchKomponentTest5() {
		MYSQLProduktBatchDAO dao = new MYSQLProduktBatchDAO();

		ProduktBatchKomponentDTO pbkDTO = new ProduktBatchKomponentDTO();
		pbkDTO.setTara(0.33);
		pbkDTO.setNetto(33.33);
//		pbkDTO.setOprId(3);
		pbkDTO.setPbId(4);
		pbkDTO.setRbId(4);
		try { 		
			dao.updateProduktBatchKomponent(pbkDTO);		
		}		
		catch (DALException e) { 		
			System.out.println(e.getMessage()); 		
		}		

		try { 		
			System.out.println("Test 5: Opdateret produktbatchKomponent -->  "+ dao.getProduktBatchKomponent(4,4).toString());		
		}		
		catch (DALException e) {		
			System.out.println(e.getMessage()); 		
		}
	}
	//private static void ProduktBatchKomponentTest6(){
		
	//}

}