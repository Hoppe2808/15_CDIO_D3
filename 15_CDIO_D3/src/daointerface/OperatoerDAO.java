package daointerface;

import java.util.List;

import daointerface.DALException;
import dto.OperatoerDTO;

public interface OperatoerDAO {
		OperatoerDTO getOperatoer(int oprId) throws DALException;
		List<OperatoerDTO> getOperatoerList() throws DALException;
		void createOperatoer(OperatoerDTO opr) throws DALException;
		void updateOperatoer(OperatoerDTO opr) throws DALException;
	}
}
