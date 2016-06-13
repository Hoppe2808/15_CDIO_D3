package tempName.server.data.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tempName.server.data.daointerface.DALException;
import tempName.server.data.daointerface.RaavareBatchDAO;
import tempName.server.data.database.Connector;
import tempName.shared.dto.ProduktBatchDTO;
import tempName.shared.dto.RaavareBatchDTO;

public class MYSQLRaavareBatchDAO implements RaavareBatchDAO{
	private Connector connector;

	public MYSQLRaavareBatchDAO(){
		try {
			connector = new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException {
		ResultSet rs = connector.doQuery("SELECT * FROM raavarebatch WHERE rb_Id = " + rbId);
		try{
			if (!rs.first()) throw new DALException("RaavareBatch " +rbId+ "findes ikke");
			RaavareBatchDTO rbDTO = new RaavareBatchDTO();
			rbDTO.setRbId(rbId);
			rbDTO.setRaavareId(rs.getInt("raavare_Id"));
			rbDTO.setMaengde(rs.getDouble("maengde"));
			return rbDTO;
		} catch(SQLException e) {
			throw new DALException(e);
		}
	}
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		ResultSet rs = connector.doQuery("SELECT * FROM raavarebatch");
		try{
			while (rs.next()){
				RaavareBatchDTO rbDTO = new RaavareBatchDTO();
				rbDTO.setRbId(rs.getInt("rb_Id"));
				rbDTO.setRaavareId(rs.getInt("raavare_Id"));
				rbDTO.setMaengde(rs.getDouble("maengde"));
				list.add(rbDTO);
			}
		} catch(SQLException e){
			throw new DALException(e);
		}
		return list;
	}
	public void createRaavareBatch(RaavareBatchDTO rb) throws DALException {
		String query = "INSERT INTO raavarebatch(rb_id, raavare_Id, maengde) VALUES(" + rb.getRbId() + ", " + rb.getRaavareId() + ", " + rb.getMaengde()+")";
		connector.doUpdate(query);
	}
	public void updateRaavareBatch(RaavareBatchDTO rb) throws DALException {
		String query = "UPDATE raavarebatch SET maengde = '" + rb.getMaengde() + 
				"', raavare_Id = '" + rb.getRaavareId() + "' WHERE rb_id = '"+ rb.getRbId()+"'";
		System.out.println("-------"+query);
		connector.doUpdate(query);
	}
	@Override
	public void deleteRaavareBatch(int rbId) throws DALException {
		connector.doUpdate("DELETE FROM raavarebatch WHERE rb_Id = " + rbId);
		
	}
}