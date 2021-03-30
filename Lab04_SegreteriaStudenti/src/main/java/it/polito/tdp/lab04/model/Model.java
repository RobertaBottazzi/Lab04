package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model {
	private CorsoDAO corsoDao;
	
	public Model() {
		corsoDao= new CorsoDAO();
	}
	/**
	 * restituisce tutti i corsi in una lista di corsi
	 * @return
	 */
	public List<Corso> getTuttiICorsi(){
		return corsoDao.getTuttiICorsi();
	}
	
	/**
	 * restituisce una lista contenente SOLO i nomi dei corsi
	 * @return
	 */
	public List<String> getTuttiICorsiPerNome(){
		List<String> corsiPerNome= new ArrayList<>();
		for(Corso c: corsoDao.getTuttiICorsi()) {
			corsiPerNome.add(c.getNome());			
		}
		return corsiPerNome;
	}
}
