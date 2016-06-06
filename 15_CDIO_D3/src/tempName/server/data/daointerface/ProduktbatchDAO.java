package tempName.server.data.daointerface;

import java.util.List;

import tempName.server.data.dto.ProduktbatchDTO;

public interface ProduktbatchDAO {

	ProduktbatchDAO getProduktbatch(int pbId) throws DALException;
	List<ProduktbatchDTO> getProduktbatchList( int pbId) throws DALException;
	void createRaavareBatch(ProduktbatchDTO pbId) throws DALException;
	void updateRaavareBatch(ProduktbatchDTO pbId) throws DALException;
}
