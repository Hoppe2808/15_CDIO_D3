package tempName.server.data.daointerface;

import java.util.List;

import tempName.server.data.daointerface.DALException;
import tempName.shared.dto.ProduktBatchKomponentDTO;
import tempName.shared.dto.ProduktBatchDTO;

public interface ProduktbatchDAO {

	ProduktBatchDTO getProduktBatch(int pbId) throws DALException;
	List<ProduktBatchDTO> getProduktBatchList() throws DALException;
	void createProduktBatch(ProduktBatchDTO pb) throws DALException;
	void updateProduktBatch(ProduktBatchDTO pb) throws DALException;

	//Methods for acces to the produktbatchkomponent table
	ProduktBatchKomponentDTO getProduktBatchKomp(int pbId, int rbId) throws DALException;
	List<ProduktBatchKomponentDTO> getProduktBatchKompList(int pbId) throws DALException;
	List<ProduktBatchKomponentDTO> getProduktBatchKompList() throws DALException;
	void createProduktBatchKomp(ProduktBatchKomponentDTO produktbatchkomponent) throws DALException;
	void updateProduktBatchKomp(ProduktBatchKomponentDTO produktbatchkomponent) throws DALException;	
}
