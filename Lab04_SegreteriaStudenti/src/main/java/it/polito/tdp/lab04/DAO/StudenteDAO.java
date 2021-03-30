package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	/*
	 * Ottengo tutti gli studenti salvati nel DB
	 */
	public List<Studente> getTuttiGliStudenti(){
		final String sql = "SELECT * FROM studente";
		List<Studente> studenti= new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Studente studente= new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS"));
				studenti.add(studente);
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public List<String> getCorsiIscrittoStudente(Studente studente){
		final String sql="SELECT DISTINCT i.codins "
				+ "FROM iscrizione i, studente s "
				+ "WHERE s.matricola=i.matricola AND s.matricola=? ";
		List<String> codiciCorsi= new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				codiciCorsi.add(rs.getString("codins"));								
			}
			conn.close();
			return codiciCorsi;
			
		} catch(SQLException e) {
			throw new RuntimeException("Errore Db", e);			
		}
	}

}
