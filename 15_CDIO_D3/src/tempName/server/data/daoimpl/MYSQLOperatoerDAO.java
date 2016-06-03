package tempName.server.data.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import tempName.server.data.daointerface.DALException;
import tempName.server.data.daointerface.OperatoerDAO;
import tempName.server.data.database.Connector;
import tempName.server.data.dto.OperatoerDTO;

public class MYSQLOperatoerDAO implements OperatoerDAO {
	private Connector connector;
	
	public MYSQLOperatoerDAO(){
		try {
			connector = new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		ResultSet rs = connector.doQuery("SELECT * FROM operatoer WHERE o_id = " + oprId);
		try{
			if (!rs.first()) throw new DALException("Brugeren " +oprId+ "findes ikke");
			OperatoerDTO opDTO = new OperatoerDTO();
			opDTO.setOprId(rs.getInt("o_id"));
			opDTO.setOprNavn(rs.getString("name"));
			opDTO.setIni(rs.getString("ini"));
			opDTO.setCpr(rs.getString("cpr"));
			opDTO.setPassword(rs.getString("password"));
			opDTO.setAdminStatus(rs.getBoolean("admin"));
			OperatoerDTO result = opDTO;
			return result;
		} catch(SQLException e) {
			throw new DALException(e);
		}
	}

	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();
		ResultSet rs = connector.doQuery("SELECT * FROM operatoer");
		try{
			while (rs.next()){
				OperatoerDTO opDTO = new OperatoerDTO();
				opDTO.setOprId(rs.getInt("o_id"));
				opDTO.setOprNavn(rs.getString("name"));
				opDTO.setIni(rs.getString("ini"));
				opDTO.setCpr(rs.getString("cpr"));
				opDTO.setPassword(rs.getString("password"));
				opDTO.setAdminStatus(rs.getBoolean("admin"));
				list.add(opDTO);
			}
		} catch(SQLException e){
			throw new DALException(e);
		}
		return list;
	}

	public void createOperatoer(OperatoerDTO opr) throws DALException {
		connector.doUpdate(
				"INSERT INTO operatoer(o_id, name, ini, cpr, password, admin) VALUES "
						+"(" + opr.getOprId() + ", '" + opr.getOprNavn() + "', '" + opr.getIni() + "', '" + opr.getCpr() + 
						"', '" + opr.getPassword() + "', " + opr.getAdminStatus() + ")"
				);

	}

	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		connector.doUpdate(
				"UPDATE operatoer SET name = '" + opr.getOprNavn() + "', ini = '" + opr.getIni() + "', cpr = '" 
						+ opr.getCpr() + "', password = '" + opr.getPassword() + "', admin = " + opr.getAdminStatus() + " WHERE o_id = "
						+ 	opr.getOprId()			
				);

	}
}