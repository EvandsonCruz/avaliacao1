package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Quesito;

public class QuesitoDao {
	private Connection c;
	public QuesitoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public List<Quesito> listarNomeQuesitos() throws SQLException {
		List<Quesito> listaNomeQuesitos = new ArrayList<Quesito>();
		String sql = "select nome from quesito";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Quesito que = new Quesito();
			que.setNome(rs.getString("nome"));
			listaNomeQuesitos.add(que);
		}		
		
		rs.close();
		ps.close();
		return listaNomeQuesitos;
	}
	
	public List<Quesito> listarIdQuesito() throws SQLException {
		List<Quesito> listaIdQuesito = new ArrayList<Quesito>();
		String sql = "select id from quesito";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Quesito que = new Quesito();
			que.setId(rs.getInt("id"));
			listaIdQuesito.add(que);
		}		
		
		rs.close();
		ps.close();
		return listaIdQuesito;
	}
}
