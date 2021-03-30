package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;	
	public Model() {
		corsoDao= new CorsoDAO();
		studenteDao= new StudenteDAO();
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
	
	public List<Studente> getTuttiGliStudenti(){
		return studenteDao.getTuttiGliStudenti();
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return corsoDao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiIscrittoStudente(Studente studente){
		List<Corso> corsi= new ArrayList<>();
		for(String s: studenteDao.getCorsiIscrittoStudente(studente)) {
			corsi.add(corsoDao.getCorso(s));
		}
		return corsi;
	}
	
	public Studente getStudente(int matricola) {
		Studente studente=null;
		for(Studente s: studenteDao.getTuttiGliStudenti()) {
			if(s.getMatricola()==matricola)
				studente=s;
		}
		return studente;
	}
}
