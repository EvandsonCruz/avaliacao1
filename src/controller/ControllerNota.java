package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Nota;
import persistence.NotaDao;

public class ControllerNota implements ActionListener{
	
	private JTextField txtNota;
		
	public ControllerNota(JTextField txtNota) {
		this.txtNota = txtNota;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			insereNota();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void insereNota() throws SQLException, ClassNotFoundException {
		Nota nota = new Nota();
		nota.setNota(txtNota.getText());
		
		NotaDao cDao = new NotaDao();
		String saida = cDao.procNota(nota);
		JOptionPane.showMessageDialog(null, saida, "MENSAGEM", JOptionPane.INFORMATION_MESSAGE);
		
		//txtNota.setText("");
		txtNota.grabFocus();
	}

}
