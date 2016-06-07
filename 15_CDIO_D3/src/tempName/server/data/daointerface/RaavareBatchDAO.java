package tempName.server.data.daointerface;

import java.util.List;

import tempName.shared.dto.RaavareBatchDTO;

public interface RaavareBatchDAO {
	
	RaavareBatchDTO getRaavareBatch(int rbId) throws DALException;
	List<RaavareBatchDTO> getRaavareBatchList( int rbId) throws DALException;
	void createRaavareBatch(RaavareBatchDTO rb) throws DALException;
	void updateRaavareBatch(RaavareBatchDTO rb) throws DALException;

}
