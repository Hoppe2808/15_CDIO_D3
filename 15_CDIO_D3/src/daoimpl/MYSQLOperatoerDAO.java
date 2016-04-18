package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import database.Connector;

import daointerface.DALException;
import daointerface.OperatoerDAO;
import dto.OperatoerDTO;

public class MYSQLOperatoerDAO implements OperatoerDAO {

	@Override
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer WHERE o_id = " + oprId);
		
		try{
			if (!rs.first()) throw new DALException("Brugeren " +oprId+ "findes ikke");
			return new OperatoerDTO(rs.getInt("o_id"))
		}
		return null;
	}

	@Override
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createOperatoer(OperatoerDTO opr) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		// TODO Auto-generated method stub
		
	}
	
		
		
}
	
