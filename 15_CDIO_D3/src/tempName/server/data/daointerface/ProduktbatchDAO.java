package tempName.server.data.daointerface;

import java.util.List;

import tempName.server.data.daointerface.DALException;
import tempName.shared.dto.ProduktBatchDTO;
import tempName.shared.dto.ProduktBatchKompDTO;

public interface ProduktbatchDAO {

	// Methods for acces to the produktbatch table 
	ProduktBatchDTO getProduktBatch(int pbId) throws DALException;
	List<ProduktBatchDTO> getProduktBatchList() throws DALException;
	void createProduktBatch(ProduktBatchDTO pb) throws DALException;
	void updateProduktBatch(ProduktBatchDTO pb) throws DALException;

	// Methods for acces to the produktbatchkomponent table
	ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException;
	List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException;
	List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException;
	void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomp) throws DALException;
	void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomp) throws DALException;	
}
