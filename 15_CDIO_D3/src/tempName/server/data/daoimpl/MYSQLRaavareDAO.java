package tempName.server.data.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tempName.server.data.daointerface.DALException;
import tempName.server.data.daointerface.RaavareDAO;
import tempName.server.data.database.Connector;
import tempName.shared.dto.RaavareDTO;
import tempName.shared.dto.ReceptKomponentDTO;

public class MYSQLRaavareDAO implements RaavareDAO{
	private Connector connector;
	
	public MYSQLRaavareDAO(){
		try {
			connector = new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addRaavare(RaavareDTO w) throws DALException {
		connector.doUpdate(
				"INSERT INTO raavare(raavare_navn, leverandoer) VALUES "
						+"('" + w.getrName() + "', '" + w.getDeliverer() + "')"
				);
	}

	@Override
	public List<RaavareDTO> getRaavareList() throws DALException {
		List<RaavareDTO> list = new ArrayList<RaavareDTO>();
		ResultSet rs = connector.doQuery("SELECT*FROM raavare");
		try{
			while (rs.next()){
				RaavareDTO raavareDTO = new RaavareDTO();
				raavareDTO.setrID(rs.getInt("raavare_id"));
				raavareDTO.setrName(rs.getString("raavare_navn"));
				raavareDTO.setDeliverer(rs.getString("leverandoer"));
				list.add(raavareDTO);
			}
		} catch(SQLException e){
			throw new DALException(e.getMessage());
		}
		return list;
	}
	
	@Override
	public void updateRaavare(int id, String name, String deliverer) throws DALException {
		connector.doUpdate(
				"UPDATE raavare SET raavare_navn = '" + name + "', leverandoer = '" + deliverer
				+ "' WHERE raavare_id = " + id
		);
	}
	@Override
	public void deleteRaavare(int id) throws DALException{
		connector.doUpdate(
				"DELETE FROM raavare WHERE raavare_id = " + id
		);
	}

}
