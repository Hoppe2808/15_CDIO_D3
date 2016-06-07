package tempName.server.data.daointerface;

import java.util.List;

import tempName.shared.dto.ProduktBatchDTO;

public interface ProduktbatchDAO {

	ProduktBatchDTO getProduktbatch(int pbId) throws DALException;
	List<ProduktBatchDTO> getProduktbatchList( int pbId) throws DALException;
	void createRaavareBatch(ProduktBatchDTO pb) throws DALException;
	void updateRaavareBatch(ProduktBatchDTO pb) throws DALException;
}
