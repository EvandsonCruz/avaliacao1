package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Escola;


public class EscolaDao {
private Connection c;
	
	public EscolaDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public List<Escola> listarEscola() throws SQLException {
		List<Escola> listaEscolas = new ArrayList<Escola>();
		String sql = "select id,nome,total_pontos from escola order by total_pontos desc";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Escola esc = new Escola();
			esc.setId(rs.getInt("id"));
			esc.setNome(rs.getString("nome"));
			esc.setTotal(rs.getString("total_pontos"));
			listaEscolas.add(esc);
		}		
		
		rs.close();
		ps.close();
		return listaEscolas;
	}
	
	public List<Escola> listarNomeEscola() throws SQLException {
		List<Escola> listaNomeEscolas = new ArrayList<Escola>();
		String sql = "select nome from escola";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Escola esc = new Escola();
			esc.setNome(rs.getString("nome"));
			listaNomeEscolas.add(esc);
		}		
		
		rs.close();
		ps.close();
		return listaNomeEscolas;
	}
	
	public List<Escola> listarIdEscola() throws SQLException {
		List<Escola> listaIdEscolas = new ArrayList<Escola>();
		String sql = "select id from escola";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Escola esc = new Escola();
			esc.setId(rs.getInt("id"));
			listaIdEscolas.add(esc);
		}		
		
		rs.close();
		ps.close();
		return listaIdEscolas;
	}

}
