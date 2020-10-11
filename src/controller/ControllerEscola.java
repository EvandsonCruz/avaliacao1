package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import model.Escola;
import persistence.EscolaDao;

public class ControllerEscola {
	public void listarEscola() throws SQLException, ClassNotFoundException {
		int i = 1;
		EscolaDao eDao = new EscolaDao();
		List<Escola> listaEscola = eDao.listarEscola();
		String escolas="";		
		for (Escola e : listaEscola){ 
		    escolas += 	   i + "� Colocado -    "	+ "Id: " + e.getId() + ", " + 
		    		       "Nome: " + e.getNome() + ", " + 
		    		       "Total: " + e.getTotal() + "\n" +
		                   "--------------------------------------------------------------------------------------------\n";	
		    i++;
		}
		JOptionPane.showMessageDialog(null, escolas, "                           Classifica��o Escolas de Samba 2013", -1);			
		//txtNota.setText("");
	}
	
	public String[] listarNomeEscola() throws SQLException, ClassNotFoundException {	
		int i = 0;
		EscolaDao eDao = new EscolaDao();
		List<Escola> listaEscola = eDao.listarNomeEscola();
		String nomeEscolas[] = new String[14];		
		for (Escola e : listaEscola){ 
		    nomeEscolas[i] = e.getNome();
		    i++;
		}			
		
		return nomeEscolas;
		//txtNota.setText("");
	}
}
