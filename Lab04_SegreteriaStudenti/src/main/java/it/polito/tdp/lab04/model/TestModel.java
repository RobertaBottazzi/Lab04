package it.polito.tdp.lab04.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		/*
		 * 	Write here your test model
		 */
		Corso corso=model.getCorso("Gestione dei progetti");
		//System.out.println(corso.toString());
		List<Studente> studenti=model.getStudentiIscrittiAlCorso(corso);
		Studente studente= model.getStudente(161245);
		
		
		for(Studente s: studenti) {
			if(s.getMatricola().equals(studente.getMatricola()))
				System.out.println("TROVATO");
		}
		
		/*for(Studente s: studenti) {
			int m=s.getMatricola();
			int n=studente.getMatricola();
			if(m==n)
				System.out.println("TROVATO");
			//System.out.println(m);
			//System.out.println(n);
			
		}*/
			
		
	}

}
