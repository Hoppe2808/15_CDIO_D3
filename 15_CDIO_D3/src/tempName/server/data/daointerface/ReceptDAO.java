package tempName.server.data.daointerface;

import java.util.List;

import tempName.shared.dto.ReceptDTO;

public interface ReceptDAO {
	ReceptDTO getRecept(int receptId) throws DALException;
	List<ReceptDTO> getReceptList() throws DALException;
	void createRecept(ReceptDTO rec) throws DALException;
	void updateRecept(ReceptDTO rec) throws DALException;
	
}
