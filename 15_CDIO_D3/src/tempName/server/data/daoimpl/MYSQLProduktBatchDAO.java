package tempName.server.data.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import tempName.server.data.daointerface.DALException;
import tempName.server.data.daointerface.ProduktbatchDAO;
import tempName.server.data.database.Connector;
import tempName.shared.dto.ProduktbatchDTO;

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
	public ProduktbatchDTO getProduktbatch(int pbId) throws DALException {
		ResultSet rs = connector.doQuery("SELECT * FROM recept WHERE produktbatchId = " + pbId);
		try{
			if (!rs.first()) throw new DALException("Produktbatch " + pbId + " findes ikke");
			ProduktbatchDTO pbDTO = new ProduktbatchDTO();
			pbDTO.setPbId(rs.getInt("pbId"));
			pbDTO.setPbId(rs.getInt("receptId"));
			pbDTO.setPbId(rs.getInt("status"));
			pbDTO.setPbId(rs.getInt("oprId"));
			pbDTO.setPbId(rs.getInt("rbId"));
			pbDTO.setTara(rs.getDouble("tara"));
			pbDTO.setNetto(rs.getDouble("netto"));
			ProduktbatchDTO result = pbDTO;
			return result;
		} catch(SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<tempName.shared.dto.ProduktbatchDTO> getProduktbatchList(int pbId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createRaavareBatch(tempName.shared.dto.ProduktbatchDTO pb) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRaavareBatch(tempName.shared.dto.ProduktbatchDTO pb) throws DALException {
		// TODO Auto-generated method stub
		
	}

}

