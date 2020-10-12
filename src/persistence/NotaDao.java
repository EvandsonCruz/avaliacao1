package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import model.Nota;

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
}
