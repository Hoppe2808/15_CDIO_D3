package tempName.server.data.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tempName.server.data.daointerface.DALException;
import tempName.server.data.daointerface.ProduktbatchDAO;
import tempName.server.data.database.Connector;
import tempName.shared.dto.ProduktBatchDTO;
import tempName.shared.dto.ProduktBatchKomponentDTO;

public class MYSQLProduktBatchDAO implements ProduktbatchDAO{
	private Connector connector;

	public MYSQLProduktBatchDAO() {
		try {
			connector = new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
		ResultSet rs = connector.doQuery("SELECT * FROM produktbatch WHERE pb_id = " + pbId);
		try{
			if (!rs.first()) throw new DALException("Produktbatch " + pbId + " findes ikke");
			ProduktBatchDTO pbDTO = new ProduktBatchDTO();
			pbDTO.setPbId(rs.getInt("pb_id"));
			pbDTO.setStatus(rs.getInt("status"));
			pbDTO.setReceptId(rs.getInt("recept_id"));
			ProduktBatchDTO result = pbDTO;
			return result;
		} catch(SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
		List<ProduktBatchDTO> list = new ArrayList<ProduktBatchDTO>();
		ResultSet rs = connector.doQuery("SELECT * FROM produktbatch");
		try{
			while (rs.next()){
				ProduktBatchDTO pbDTO = new ProduktBatchDTO();
				pbDTO.setPbId(rs.getInt("pb_id"));
				pbDTO.setStatus(rs.getInt("status"));
				pbDTO.setReceptId(rs.getInt("recept_id"));
				list.add(pbDTO);
			}
		} catch(SQLException e){
			throw new DALException(e.getMessage());
		}
		return list;
	}

	@Override
	public void createProduktBatch(ProduktBatchDTO pb) throws DALException {
		String query = "INSERT INTO produktbatch(status, recept_id) VALUES (" + pb.getStatus() + ", " + pb.getReceptId() + ")";	
		connector.doUpdate(query);
	}

	@Override
	public void updateProduktBatch(ProduktBatchDTO pb) throws DALException {
		String query = "UPDATE produktbatch SET status = '" + pb.getStatus() + 
				"', recept_id = '" + pb.getReceptId() + "' WHERE pb_id = '" + pb.getPbId()+"'";
		//			System.out.println("-------"+query);
		connector.doUpdate(query);
	}
	@Override
	public void deleteProduktBatch(int pbId) throws DALException {
		String query =("DELETE FROM produktbatch WHERE pb_id = " + pbId );
		connector.doUpdate(query);

	}

	// Produktbatch 			^^^^^^^^^^^^^^^^
	///////////////////////////////////////////////////////////////////////////////
	// Produktbatchkomponent 	vvvvvvvvvvvvvvvv

	@Override
	public ProduktBatchKomponentDTO getProduktBatchKomponent(int pbId, int rbId) throws DALException {
		ResultSet rs = connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = '" + pbId + "' AND rb_id = '" + rbId+ "'");	
		
		try {
			if (!rs.first()) throw new DALException("Produktbatchkomponent med produkt ID" + pbId + " og raavare ID "+rbId+ " findes ikke");
			ProduktBatchKomponentDTO pbkDTO = new ProduktBatchKomponentDTO();
			pbkDTO.setPbId(pbId);
			pbkDTO.setRbId(rbId);
			pbkDTO.setTara(rs.getDouble("tara"));
			pbkDTO.setNetto(rs.getDouble("netto"));
			pbkDTO.setOprId(rs.getInt("opr_id"));
			return pbkDTO;
		} catch (SQLException e) {
			throw new DALException(e); 
		}
	}
	@Override
	public List<ProduktBatchKomponentDTO> getProduktBatchKomponentList(int pbId) throws DALException {
		List<ProduktBatchKomponentDTO> list = new ArrayList<ProduktBatchKomponentDTO>(pbId);
		ResultSet rs = connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = "+ pbId);
		try{
			while (rs.next()) {
				ProduktBatchKomponentDTO pbkDTO = new ProduktBatchKomponentDTO();
				pbkDTO.setPbId(rs.getInt("pb_id"));
				pbkDTO.setRbId(rs.getInt("rb_id"));
				pbkDTO.setTara(rs.getDouble("tara"));
				pbkDTO.setNetto(rs.getDouble("netto"));
				pbkDTO.setOprId(rs.getInt("opr_id"));
				list.add(pbkDTO);
			}
			return list;
		}
		catch (SQLException e) { throw new DALException(e); }
	}
	@Override
	public List<ProduktBatchKomponentDTO> getProduktBatchKomponentList() throws DALException {
		List<ProduktBatchKomponentDTO> list = new ArrayList<ProduktBatchKomponentDTO>();
		ResultSet rs = connector.doQuery("SELECT * FROM produktbatchkomponent" );
		try{
			while (rs.next()) {
				ProduktBatchKomponentDTO pbkDTO = new ProduktBatchKomponentDTO();
				pbkDTO.setPbId(rs.getInt("pb_id"));
				pbkDTO.setRbId(rs.getInt("rb_id"));
				pbkDTO.setTara(rs.getDouble("tara"));
				pbkDTO.setNetto(rs.getDouble("netto"));
				pbkDTO.setOprId(rs.getInt("opr_id"));
				list.add(pbkDTO);
			}
			return list;
		}
		catch (SQLException e) { throw new DALException(e); }
	}
	@Override
	public void updateProduktBatchKomponent(ProduktBatchKomponentDTO pbk) throws DALException {
		String query = "UPDATE produktbatchkomponent SET  netto = '" + pbk.getNetto() + "', tara = '" + pbk.getTara() + "' "
				+ "WHERE pb_id = '" + pbk.getPbId()+"' AND rb_id = '" + pbk.getRbId()+ "'";
//				System.out.println("-------"+query);
		connector.doUpdate(query);
	}
	@Override
	public void deleteProduktBatchKomponent(int pbId, int rbId) throws DALException {
		String query =("DELETE FROM produktbatchkomponent WHERE pb_id = '" + pbId + "' AND rb_id = '" + rbId+ "'");
		connector.doUpdate(query);

	}
}