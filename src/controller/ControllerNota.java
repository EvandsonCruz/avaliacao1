package controller;

import java.sql.SQLException;

import model.Nota;
import persistence.NotaDao;

public class ControllerNota{
//implements ActionListener{
	
//	private JTextField txtNota;
//		
//	public ControllerNota(JTextField txtNota) {
//		this.txtNota = txtNota;
//	}



	//@Override
//	public void actionPerformed(ActionEvent e) {
//		try {
//			insereNota(null);
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}		
//	}
	
	public String insereNota(Nota nota) throws SQLException, ClassNotFoundException {
		
		NotaDao nDao = new NotaDao();
		String saida = nDao.procNota(nota);
		return saida;
		
	}

}
