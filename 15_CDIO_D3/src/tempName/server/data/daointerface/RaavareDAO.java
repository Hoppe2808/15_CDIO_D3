package tempName.server.data.daointerface;

import java.util.List;

import tempName.shared.dto.RaavareDTO;

public interface RaavareDAO {
	void addRaavare (RaavareDTO w) throws DALException;
	List<RaavareDTO> getRaavareList() throws DALException;
}
