package tempName.server.data.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tempName.server.data.daointerface.DALException;
import tempName.server.data.daointerface.ReceptDAO;
import tempName.server.data.database.Connector;
import tempName.shared.dto.ReceptDTO;


public class MYSQLReceptDAO implements ReceptDAO{
	private Connector connector;
	
	public MYSQLReceptDAO() {
		try {
			connector = new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		ResultSet rs = connector.doQuery("SELECT * FROM recept WHERE recept_id = " + receptId);
		try{
			if (!rs.first()) throw new DALException("Recepten " + receptId + " findes ikke");
			ReceptDTO recDTO = new ReceptDTO();
			recDTO.setReceptId(rs.getInt("recept_id"));
			recDTO.setReceptName(rs.getString("recept_navn"));
			ReceptDTO result = recDTO;
			return result;
		} catch(SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		ResultSet rs = connector.doQuery("SELECT * FROM recept");
		try{
			while (rs.next()){
				ReceptDTO recDTO = new ReceptDTO();
				recDTO.setReceptId(rs.getInt("recept_id"));
				recDTO.setReceptName(rs.getString("recept_navn"));
				list.add(recDTO);
			}
		} catch(SQLException e){
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createRecept(ReceptDTO rec) throws DALException {
		connector.doUpdate(
				"INSERT INTO recept(recept_id, recept_navn) VALUES "
						+"(" + rec.getReceptId() + ", '" + rec.getReceptName() + ")"
				);		
	}

	@Override
	public void updateRecept(ReceptDTO rec) throws DALException {
		connector.doUpdate(
				"UPDATE recept SET recept_id = '" + rec.getReceptId() + "', recept_navn = '" + rec.getReceptName()
				 + " WHERE recept_id = " + rec.getReceptId()			
				);		
	}
}
