package daointerface;

import java.util.List;
import daointerface.DALException;
import dto.WeightDTO;

public interface WeightDAO {
		
	void addWeight (WeightDTO w) throws DALException;
	List<WeightDTO> getWeightList() throws DALException;
}
