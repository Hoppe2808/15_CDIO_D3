package tempName.server.data.daointerface;

import java.util.List;

import tempName.shared.dto.ProduktBatchDTO;

public interface ProduktbatchDAO {

	ProduktBatchDTO getProduktBatch(int pbId) throws DALException;
	List<ProduktBatchDTO> getProduktBatchList() throws DALException;
	void createProduktBatch(ProduktBatchDTO pb) throws DALException;
	void updateProduktBatch(ProduktBatchDTO pb) throws DALException;
}
