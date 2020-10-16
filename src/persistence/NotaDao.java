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
		String sql = "{CALL sp_inserenota2(?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, nota.getIdEscola());
		cs.setInt(2, nota.getIdQuesito());		
		cs.setString(3, nota.getNota());
		cs.registerOutParameter(4,Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(4);
		cs.close();
		
		return saida;
	}
	
	public List<NotaQuesito> listaNotaQuesito(int contadorQuesito) throws SQLException {
		List<NotaQuesito> listaNotaQuesito = new ArrayList<NotaQuesito>();
		String sql = "select e.nome,nota1,nota2,nota3,nota4,nota5,maior,menor,total_quesito from nota2 \r\n"
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
			nq.setNota1(rs.getString("nota1"));
			nq.setNota2(rs.getString("nota2"));
			nq.setNota3(rs.getString("nota3"));
			nq.setNota4(rs.getString("nota4"));
			nq.setNota5(rs.getString("nota5"));
			nq.setMaior(rs.getString("maior"));
			nq.setMenor(rs.getString("menor"));
			nq.setTotal_quesito(rs.getString("total_quesito"));
			listaNotaQuesito.add(nq);
		}		
		
		rs.close();
		ps.close();
		return listaNotaQuesito;
	}
}
