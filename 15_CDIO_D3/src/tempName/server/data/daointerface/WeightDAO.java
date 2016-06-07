package tempName.server.data.daointerface;

import java.util.List;

import tempName.shared.dto.WeightDTO;

public interface WeightDAO {

	void addWeight (WeightDTO w) throws DALException;
	List<WeightDTO> getWeightList() throws DALException;
}
