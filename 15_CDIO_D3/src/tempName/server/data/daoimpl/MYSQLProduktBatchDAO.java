package tempName.server.data.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import tempName.server.data.daointerface.DALException;
import tempName.server.data.daointerface.ProduktbatchDAO;
import tempName.server.data.database.Connector;
import tempName.shared.dto.ProduktBatchDTO;

public class MYSQLProduktBatchDAO implements ProduktbatchDAO{
	private Connector connector;
	 	
	public MYSQLProduktBatchDAO() {
		try {
			connector = new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ProduktBatchDTO getProduktbatch(int pbId) throws DALException {
		ResultSet rs = connector.doQuery("SELECT * FROM produktbatch WHERE pb_id = " + pbId);
		try{
			if (!rs.first()) throw new DALException("Produktbatch " + pbId + " findes ikke");
			ProduktBatchDTO pbDTO = new ProduktBatchDTO();
			pbDTO.setPbId(rs.getInt("pb_id"));
			pbDTO.setPbId(rs.getInt("status"));
			pbDTO.setPbId(rs.getInt("recept_id"));
			ProduktBatchDTO result = pbDTO;
			return result;
		} catch(SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<ProduktBatchDTO> getProduktbatchList(int pbId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createRaavareBatch(ProduktBatchDTO pb) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRaavareBatch(ProduktBatchDTO pb) throws DALException {
		// TODO Auto-generated method stub
		
	}

}

