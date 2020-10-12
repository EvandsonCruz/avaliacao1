package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Nota;
import model.NotaQuesito;

public class NotaDao {
	private Connection c;
	
	public NotaDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public String procNota(Nota nota) throws SQLException {
		String sql = "{CALL sp_inserenota(?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, nota.getIdEscola());
		cs.setInt(2, nota.getIdJurado());
		cs.setInt(3, nota.getIdQuesito());		
		cs.setString(4, nota.getNota());
		cs.registerOutParameter(5,Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(5);
		cs.close();
		
		return saida;
	}
	
	public List<NotaQuesito> listaNotaQuesito(int contadorQuesito) throws SQLException {
		List<NotaQuesito> listaNotaQuesito = new ArrayList<NotaQuesito>();
		String sql = "select e.nome,q.nome,nota from nota \r\n"
				+ "join escola e\r\n"
				+ "on e.id = id_escola\r\n"
				+ "join quesito q\r\n"
				+ "on q.id = id_quesito\r\n"
				+ "where id_quesito = " + contadorQuesito + " order by e.nome";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			NotaQuesito nq = new NotaQuesito();
			nq.setNomeEscola(rs.getString("nome"));
			nq.setNota(rs.getString("nota"));
			listaNotaQuesito.add(nq);
		}		
		
		rs.close();
		ps.close();
		return listaNotaQuesito;
	}
}
