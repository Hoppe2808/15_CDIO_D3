package tempName.server.data.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import tempName.server.data.daointerface.DALException;
import tempName.server.data.daointerface.OperatoerDAO;
import tempName.server.data.database.Connector;
import tempName.shared.dto.OperatoerDTO;

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
		ResultSet rs = connector.doQuery("SELECT * FROM operatoer WHERE opr_id = " + oprId);
		try{
			if (!rs.first()) throw new DALException("Brugeren " +oprId+ "findes ikke");
			OperatoerDTO opDTO = new OperatoerDTO();
			opDTO.setOprId(rs.getInt("opr_id"));
			opDTO.setOprNavn(rs.getString("opr_navn"));
			opDTO.setIni(rs.getString("ini"));
			opDTO.setCpr(rs.getString("cpr"));
			opDTO.setPassword(rs.getString("password"));
			opDTO.setAdminStatus(rs.getInt("admin"));
			OperatoerDTO result = opDTO;
			return result;
		} catch(SQLException e) {
			throw new DALException(e.getMessage());
		}
	}


	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();
		ResultSet rs = connector.doQuery("SELECT * FROM operatoer");
		try{
			while (rs.next()){
				OperatoerDTO opDTO = new OperatoerDTO();
				opDTO.setOprId(rs.getInt("opr_id"));				
				opDTO.setOprNavn(rs.getString("opr_navn"));
				opDTO.setIni(rs.getString("ini"));
				opDTO.setCpr(rs.getString("cpr"));
				opDTO.setPassword(rs.getString("password"));
				opDTO.setAdminStatus(rs.getInt("admin"));
				list.add(opDTO);
			}
		} catch(SQLException e){
			throw new DALException(e.getMessage());
		}
		return list;
	}

	public void createOperatoer(OperatoerDTO opr) throws DALException {
		connector.doUpdate(
				"INSERT INTO operatoer(opr_navn, ini, cpr, password, admin) VALUES "
						+"('" + opr.getOprNavn() + "', '" + opr.getIni() + "', '" + opr.getCpr() + 
						"', '" + opr.getPassword() + "', " + opr.getAdminStatus() + ")"
				);

	}

	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		connector.doUpdate(
				"UPDATE operatoer SET opr_navn = '" + opr.getOprNavn() + "', ini = '" + opr.getIni() + "', cpr = '" 
						+ opr.getCpr() + "', password = '" + opr.getPassword() + "', admin = " + opr.getAdminStatus() + " WHERE opr_id = "
						+ 	opr.getOprId()			
				);

	}
}