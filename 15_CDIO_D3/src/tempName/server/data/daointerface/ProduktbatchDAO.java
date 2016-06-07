package tempName.server.data.daointerface;

import java.util.List;

import tempName.shared.dto.ProduktbatchDTO;

public interface ProduktbatchDAO {

	ProduktbatchDTO getProduktbatch(int pbId) throws DALException;
	List<ProduktbatchDTO> getProduktbatchList( int pbId) throws DALException;
	void createRaavareBatch(ProduktbatchDTO pb) throws DALException;
	void updateRaavareBatch(ProduktbatchDTO pb) throws DALException;
}
