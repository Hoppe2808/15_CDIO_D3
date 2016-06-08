package tempName.server.data.daointerface;

import java.util.List;

import tempName.server.data.daointerface.DALException;
import tempName.shared.dto.ReceptKomponentDTO;
import tempName.shared.dto.ReceptDTO;

public interface ReceptDAO {
	ReceptDTO getRecept(int receptId) throws DALException;
	List<ReceptDTO> getReceptList() throws DALException;
	void createRecept(ReceptDTO rec) throws DALException;
	void updateRecept(ReceptDTO rec) throws DALException;
	
	//Methods for "receptkomponent" table access
		ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException;
		List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException;
		List<ReceptKompDTO> getReceptKompList() throws DALException;
		void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException;
		void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException;
	
}
