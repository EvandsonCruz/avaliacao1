package controller;

import java.sql.SQLException;
import java.util.List;

import model.Quesito;
import persistence.QuesitoDao;

public class ControllerQuesito {
	public String[] listarNomeQuesitos() throws SQLException, ClassNotFoundException {	
		int i = 0;
		QuesitoDao qDao = new QuesitoDao();
		List<Quesito> listaQuesito = qDao.listarNomeQuesitos();
		String nomeQuesitos[] = new String[9];		
		for (Quesito q : listaQuesito){ 
		    nomeQuesitos[i] = q.getNome();
		    i++;
		}			
		
		return nomeQuesitos;
	}
	
	public int[] listarIdQuesito() throws SQLException, ClassNotFoundException {	
		int i = 0;
		QuesitoDao qDao = new QuesitoDao();
		List<Quesito> listaQuesito = qDao.listarIdQuesito();
		int idQuesito[] = new int[9];		
		for (Quesito q : listaQuesito){ 
		    idQuesito[i] = q.getId();
		    i++;
		}			
		return idQuesito;
	}
}
