package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daointerface.DALException;
import daointerface.WeightDAO;
import database.Connector;
import dto.WeightDTO;

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
		ResultSet rs = Connector.doQuery("SELECT*FROM operatoer");
		try{
			while (rs.next()){
				list.add(new WeightDTO(rs.getInt("w_id"), rs.getDouble("weight"), rs.getInt("o_id")));
			}
		} catch(SQLException e){
			throw new DALException(e);
		}
		return list;
		
	}

}
