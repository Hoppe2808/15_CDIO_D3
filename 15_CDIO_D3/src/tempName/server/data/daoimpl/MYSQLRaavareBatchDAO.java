package tempName.server.data.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tempName.server.data.daointerface.DALException;
import tempName.server.data.database.Connector;
import tempName.server.data.dto.OperatoerDTO;
import tempName.server.data.dto.RaavareBatchDTO;

public class MYSQLRaavareBatchDAO {
	private Connector connector;

	public MYSQLRaavareBatchDAO(){
		try {
			connector = new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException {
		ResultSet rs = connector.doQuery("SELECT * FROM raavareBatch WHERE rb_Id = " + rbId);
		try{
			if (!rs.first()) throw new DALException("RaavareBatch " +rbId+ "findes ikke");
			RaavareBatchDTO rbDTO = new RaavareBatchDTO (rbId, raavareId, maengde); // Brug for hjælp her 
			rbDTO.setRbId(rs.getInt("rb_Id"));
			rbDTO.setRaavareId(rs.getInt("raavare_Id"));
			rbDTO.setMaengde(rs.getDouble("maengde"));
			RaavareBatchDTO result = rbDTO;
			return result;
		} catch(SQLException e) {
			throw new DALException(e);
		}
	}
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		ResultSet rs = connector.doQuery("SELECT * FROM raavareBatch");
		try{
			while (rs.next()){
				RaavareBatchDTO rbDTO = new RaavareBatchDTO();// Brug for hjælp her
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
		connector.doUpdate(
				"INSERT INTO raavareBatch(rb_Id, raavare_Id, maengde) VALUES "
						+"(" + rb.getRbId() + ", '" + rb.getRaavareId() + "', '" + rb.getMaengde());
	}

	public void updateRaavareBatch(RaavareBatchDTO rb) throws DALException {
		connector.doUpdate(
				"UPDATE raavareBatch SET maengde = '" + rb.getRbId() + ", '" + rb.getRaavareId() + "', '" + rb.getMaengde()	);

	}
}
