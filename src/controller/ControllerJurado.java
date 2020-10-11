package controller;

import java.sql.SQLException;
import java.util.List;

import model.Jurado;
import persistence.JuradoDao;

public class ControllerJurado {
	public String[] listarNomeJurados() throws SQLException, ClassNotFoundException {	
		int i = 0;
		JuradoDao jDao = new JuradoDao();
		List<Jurado> listaJurado = jDao.listarNomeJurados();
		String nomeJurados[] = new String[5];		
		for (Jurado j : listaJurado){ 
		    nomeJurados[i] = j.getNome();
		    i++;
		}			
		
		return nomeJurados;
		//txtNota.setText("");
	}
}
