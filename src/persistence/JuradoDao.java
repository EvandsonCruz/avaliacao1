package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Jurado;

public class JuradoDao {	
	private Connection c;
	public JuradoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public List<Jurado> listarNomeJurados() throws SQLException {
		List<Jurado> listaNomeJurados = new ArrayList<Jurado>();
		String sql = "select nome from jurado";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Jurado jur = new Jurado();
			jur.setNome(rs.getString("nome"));
			listaNomeJurados.add(jur);
		}		
		
		rs.close();
		ps.close();
		return listaNomeJurados;
	}
	
	public List<Jurado> listarIdJurado() throws SQLException {
		List<Jurado> listaIdJurados = new ArrayList<Jurado>();
		String sql = "select id from jurado";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Jurado jur = new Jurado();
			jur.setId(rs.getInt("id"));
			listaIdJurados.add(jur);
		}		
		
		rs.close();
		ps.close();
		return listaIdJurados;
	}
}
