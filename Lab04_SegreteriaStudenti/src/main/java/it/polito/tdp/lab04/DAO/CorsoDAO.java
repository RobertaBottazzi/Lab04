package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new ArrayList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				Corso corso= new Corso(codins,numeroCrediti,nome,periodoDidattico);
				corsi.add(corso);
				//DONE
				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 *Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codice) {
		Corso corso = null;
		for(Corso c: this.getTuttiICorsi()) {
			if(c.getCodins().equals(codice))
				corso=c;
		}
		return corso;
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		final String sql="SELECT DISTINCT s.matricola, s.cognome,s.nome,s.CDS "
				+ "FROM iscrizione i, studente s "
				+ "WHERE s.matricola=i.matricola AND i.codins=? ";
		List<Studente> studenti= new ArrayList<>();
		try {
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				Studente studente= new Studente(rs.getInt("matricola"), rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS"));
				studenti.add(studente);
			}
			conn.close();
			return studenti;
			
		} catch (SQLException e) {
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
