package tempName.server.data.daointerface;

import java.util.List;

import tempName.server.data.dto.RaavareBatchDTO;

public interface RaavareBatchDAO {
	
	RaavareBatchDAO getRaavareBatch(int rbId) throws DALException;
	List<RaavareBatchDTO> getRaavareBatchList( int rbId) throws DALException;
	void createRaavareBatch(RaavareBatchDTO rbId) throws DALException;
	void updateRaavareBatch(RaavareBatchDTO rbId) throws DALException;

}
