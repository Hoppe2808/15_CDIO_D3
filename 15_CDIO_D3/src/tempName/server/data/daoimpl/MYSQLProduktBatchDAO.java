package tempName.server.data.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tempName.shared.dto.ProduktBatchKomponentDTO;
import tempName.server.data.daointerface.DALException;
import tempName.server.data.daointerface.ProduktbatchDAO;
import tempName.server.data.database.Connector;
import tempName.shared.dto.ProduktBatchDTO;

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
			throw new DALException(e);
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
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createProduktBatch(ProduktBatchDTO pb) throws DALException {
		connector.doUpdate(
				"INSERT INTO produktbatch(pb_id, status, recept_id) VALUES "
						+"(" + pb.getPbId() + ", '" + pb.getStatus() + ", '" + pb.getReceptId() + ")"
				);			
	}

	@Override
	public void updateProduktBatch(ProduktBatchDTO pb) throws DALException {
		connector.doUpdate(
				"UPDATE produktbatch SET pb_id = '" + pb.getPbId() + "', status = '" + pb.getStatus() + 
				"', recept_id = '" + pb.getReceptId() + " WHERE recept_id = " + pb.getPbId()			
				);
	}
	@Override
	public ProduktBatchKomponentDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
		ResultSet rs = connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId + " AND rb_id = " + rbId);
		try {
			if (!rs.first()) throw new DALException("Produktbatchkomponent " + pbId + " findes ikke");
			return new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id"));
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException{
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId);
		try{
			while (rs.next()){
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKomponentList() throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent");
		try{
			while (rs.next()) {
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProduktBatchKomp(ProduktBatchKomponentDTO produktbatchkomponent) throws DALException {
		Connector.doUpdate(
				"INSERT INTO produktbatchkomponent(pb_id, rb_id, tara, netto, opr_id) VALUES " +
						"(" + produktbatchkomponent.getPbId() + ", " + produktbatchkomponent.getRbId() + ", " + produktbatchkomponent.getNetto() + ", " + 
						produktbatchkomponent.getTara() + ", " + produktbatchkomponent.getOprId() + ")"
				);
	}

	@Override
	public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		Connector.doUpdate(
				"UPDATE produktbatchkomponent SET  netto = " + produktbatchkomponent.getNetto() + ", tara = " + produktbatchkomponent.getTara() + " WHERE pb_id = " +
						produktbatchkomponent.getPbId()
				);
	}



}