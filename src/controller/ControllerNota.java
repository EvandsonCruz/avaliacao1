package controller;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import model.Nota;
import model.NotaQuesito;
import persistence.NotaDao;

public class ControllerNota{
	
	public String insereNota(Nota nota) throws SQLException, ClassNotFoundException {		
		NotaDao nDao = new NotaDao();
		String saida = nDao.procNota(nota);
		return saida;		
	}
	
	public void listaNotaQuesito(int contadorQuesito, String nomeQuesitos[]) throws ClassNotFoundException, SQLException {
		
		NotaDao nDao = new NotaDao();
		List<NotaQuesito> listaNotaQuesito = nDao.listaNotaQuesito(contadorQuesito);

		String col[] = { "ESCOLA", "NOTA1", "NOTA2", "NOTA3", "NOTA4", "NOTA5", "MAIOR", "MENOR", "TOTAL_QUESITO"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for (NotaQuesito nq : listaNotaQuesito) {
			Object[] notas = { nq.getNomeEscola(),nq.getNota1(),nq.getNota2(),nq.getNota3(),nq.getNota4(),nq.getNota5()
					,nq.getMaior(),nq.getMenor(),nq.getTotal_quesito()};
			tableModel.addRow(notas);			
		}
		JTable table = new JTable(tableModel);
		UIManager.put("OptionPane.minimumSize",new Dimension(700,500)); 
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(8).setPreferredWidth(150);
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "Notas quesito " + nomeQuesitos[contadorQuesito-1], JOptionPane.PLAIN_MESSAGE);
	}
}


