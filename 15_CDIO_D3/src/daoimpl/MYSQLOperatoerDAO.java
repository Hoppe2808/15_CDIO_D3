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
			return new OperatoerDTO(rs.getInt("o_id"), rs.getString("name"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getBoolean("admin"));
		} catch(SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer");
		try{
			while (rs.next()){
				list.add(new OperatoerDTO(rs.getInt("o_id"), rs.getString("name"), rs.getString("ini"), 
						rs.getString("cpr"), rs.getString("password"), rs.getBoolean("admin")));
			}
		} catch(SQLException e){
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createOperatoer(OperatoerDTO opr) throws DALException {
		Connector.doUpdate(
				"INSERT INTO operatoer(o_id, name, ini, cpr, password, admin) VALUES "
						+"(" + opr.getOprId() + ", '" + opr.getOprNavn() + "', '" + opr.getIni() + "', '" + opr.getCpr() + 
						"', '" + opr.getPassword() + "', " + opr.getAdminStatus() + ")"
				);

	}

	@Override
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		Connector.doUpdate(
				"UPDATE operatoer SET name = '" + opr.getOprNavn() + "', ini = '" + opr.getIni() + "', '" 
				+ opr.getCpr() + "', '" + opr.getPassword() + "', " + opr.getAdminStatus() + " WHERE o_id = "
				+ 	opr.getOprId()			
				);

	}
}