package tempName.server.data.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tempName.server.data.daointerface.DALException;
import tempName.server.data.daointerface.WeightDAO;
import tempName.server.data.database.Connector;
import tempName.server.data.dto.WeightDTO;

public class MYSQLWeightDAO implements WeightDAO {

	@Override
	public void addWeight(WeightDTO w) throws DALException {
		Connector.doUpdate(
				"INSERT INTO weight(weight, o_id) VALUES "
				+"(" + w.getMS() + ", " + w.getopID() + ")"
				);
	}

	@Override
	public List<WeightDTO> getWeightList() throws DALException {
		List<WeightDTO> list = new ArrayList<WeightDTO>();
		ResultSet rs = Connector.doQuery("SELECT*FROM weight");
		try{
			while (rs.next()){
				WeightDTO weightDTO = new WeightDTO();
				weightDTO.setMS(rs.getDouble("weight"));
				weightDTO.setopID(rs.getInt("o_id"));
				weightDTO.setWID(rs.getInt("w_id"));
				list.add(weightDTO);
			}
		} catch(SQLException e){
			throw new DALException(e);
		}
		return list;
		
	}

}
