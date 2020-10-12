package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import model.Nota;
import model.NotaQuesito;
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
	
	public void listaNotaQuesito(int contadorQuesito, String nomeQuesitos[]) throws ClassNotFoundException, SQLException {
		int i = 0;
		NotaDao nDao = new NotaDao();
		List<NotaQuesito> listaNotaQuesito = nDao.listaNotaQuesito(contadorQuesito);
		String notaQuesito="";		
		for (NotaQuesito nq : listaNotaQuesito){ 
		    notaQuesito += nq.getNomeEscola() + ": " +  
		    		       "Nota: " + nq.getNota() + " || ";
		    		       if(i == 4) {
		    		    	   notaQuesito += "\n\n";
		    		    	   i = 0;
		    		       }
		   i++;
		}
		JOptionPane.showMessageDialog(null, notaQuesito, "Notas quesito " + nomeQuesitos[contadorQuesito-1] + " ", -1);
	}
}


