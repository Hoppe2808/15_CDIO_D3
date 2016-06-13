package tempName.server.data.daointerface;

import java.util.List;

import tempName.server.data.daointerface.DALException;
import tempName.shared.dto.ProduktBatchDTO;
import tempName.shared.dto.ProduktBatchKomponentDTO;

public interface ProduktbatchDAO {

	// Methods for acces to the produktbatch table 
	ProduktBatchDTO getProduktBatch(int pbId) throws DALException;
	List<ProduktBatchDTO> getProduktBatchList() throws DALException;
	void createProduktBatch(ProduktBatchDTO pb) throws DALException;
	void updateProduktBatch(ProduktBatchDTO pb) throws DALException;
	void deleteProduktBatch(int pbId) throws DALException;

	// Methods for acces to the produktbatchkomponent table
	ProduktBatchKomponentDTO getProduktBatchKomponent(int pbId, int rbId) throws DALException;
	List<ProduktBatchKomponentDTO> getProduktBatchKomponentList(int pbId) throws DALException;
	List<ProduktBatchKomponentDTO> getProduktBatchKomponentList() throws DALException;
	void updateProduktBatchKomponent(ProduktBatchKomponentDTO produktbatchkomponent) throws DALException;
	void deleteProduktBatchKomponent(int pbId, int rbId) throws DALException;
}
