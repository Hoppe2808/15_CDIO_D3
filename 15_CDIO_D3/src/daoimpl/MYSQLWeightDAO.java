package daoimpl;

import java.util.List;

import daointerface.DALException;
import daointerface.WeightDAO;
import database.Connector;
import dto.WeightDTO;

int w_id

public class MYSQLWeightDAO implements WeightDAO {

	@Override
	public void addWeight(WeightDTO w) throws DALException {
		Connector.doUpdate(
				"INSERT INTO weight(weight, o_id) VALUES "
				+"(" +
				)
		
	}

	@Override
	public List<WeightDTO> getWeightList() throws DALException {
		return null;
		
	}

}
