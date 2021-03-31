/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboBox;

    @FXML
    private TextField txtMatricola;
    
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button greenBtn;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	int matricola = 0;
    	Studente studente = null;
    	List<Corso> corsi = null;
    	try{
    		matricola=Integer.parseInt(this.txtMatricola.getText());
    	} catch (NumberFormatException e) {
    		this.txtRisultato.setText("Scrivere una matricola valida");
    		return;
    	}
    	studente=this.model.getStudente(matricola);
		corsi= new ArrayList<>();			
    	if(studente!=null)
    		corsi.addAll(this.model.getCorsiIscrittoStudente(studente));    		
    	else {
    		this.txtRisultato.setText("Studente non presente nel database");
    		return;
    	}
    	
    	if((!this.ComboBox.getValue().isBlank() || this.ComboBox.getValue()!=null) && !this.txtMatricola.getText().isEmpty()){
    		if(cerca(this.txtMatricola.getText(),this.ComboBox.getValue())) {
    				this.txtRisultato.setText("Studente iscritto al corso");
    				return;    				
    		}
    	}
    	
    	for(Corso c: corsi) {
    		this.txtRisultato.appendText(c.toString());
    	}
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	List<Studente> studentiIscrittiAlCorso= new ArrayList<>();
    	Corso corso= null;
    	if(this.ComboBox.getValue().equals("")) {
    		this.txtRisultato.setText("Seleziona un corso");
    		return;
    	}	
    	for(Corso c: this.model.getTuttiICorsi()) {
    		if(this.ComboBox.getValue().equals(c.getNome()))
    			corso=c;    			
    	}
    	if(corso!=null)
    		studentiIscrittiAlCorso.addAll(this.model.getStudentiIscrittiAlCorso(corso));
    	else
    		this.txtRisultato.setText("Selezionare un corso");
    	
    	if((!this.ComboBox.getValue().isBlank() || this.ComboBox.getValue()!=null) && !this.txtMatricola.getText().isEmpty()){
    		if(cerca(this.txtMatricola.getText(),this.ComboBox.getValue())) {
    				this.txtRisultato.setText("Studente iscritto al corso");
    				return;    				
    		}    			       			    			    		
    	}
    	
    	for(Studente s: studentiIscrittiAlCorso) {    			
    		this.txtRisultato.appendText(s.toString());
    	}    	
    }

    @FXML
    void doGreenButton(ActionEvent event) {
    	try {
    		String matricola= this.txtMatricola.getText();
        	for(Studente s: model.getTuttiGliStudenti()) {
        		if(s.getMatricola()==(Integer.parseInt(matricola))) {
        			this.txtCognome.setText(s.getCognome());
        			this.txtNome.setText(s.getNome());
        		}
        	}
        	if(this.txtCognome.getText().isBlank() && this.txtNome.getText().isBlank())
        		this.txtRisultato.setText("Studente non presente nel database");
    	} catch (NumberFormatException e) {
    		this.txtRisultato.setText("Scrivere una matricola valida");
    		return;    		
    	}
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	Studente studente = null;
    	try{
    		studente=this.model.getStudente(Integer.parseInt(this.txtMatricola.getText()));    		
    	} catch (NumberFormatException e) {
    		this.txtRisultato.setText("Scrivere una matricola valida");
    		return;
    	}
    	Corso corso=this.model.getCorso(this.ComboBox.getValue());
    	if(studente==null) {
    		this.txtRisultato.setText("Studente non presente nel database");
    	}
    	if(corso==null) {
    		this.txtRisultato.setText("Seleziona un corso");
    		return;
    	}
    	if((!this.ComboBox.getValue().isBlank() || this.ComboBox.getValue()!=null) && !this.txtMatricola.getText().isEmpty()){
    		if(cerca(this.txtMatricola.getText(),this.ComboBox.getValue())) {
    				this.txtRisultato.setText("Studente iscritto al corso");
    				return;    				
    		}    			       			    			    		
    	}
    	
    	if(this.model.inscriviStudenteACorso(studente, corso))
    		this.txtRisultato.setText("Studente iscritto correttamente");
    	
    }
    /**
     * permette di cancellare il contenuto di tutti i campi
     * @param event
     */
    @FXML
    void doReset(ActionEvent event) {
    	this.txtRisultato.clear();
    	this.txtMatricola.clear();
    	this.txtCognome.clear();
    	this.txtNome.clear();
    	this.ComboBox.setValue("Corsi");
    }
    /**
     * Cerca se uno studente è già iscritto ad un corso
     */
    public boolean cerca(String txtMatricola, String boxValue) {
    	Studente studente=this.model.getStudente(Integer.parseInt(txtMatricola));
    	Corso corso=this.model.getCorso(boxValue);
    	if(studente!=null && corso!=null) {
    		List<Studente> studenti=this.model.getStudentiIscrittiAlCorso(corso); 
    		if(studenti.contains(studente))
    			return true;
    	}    	
    	return false;
    }
    public void setModel(Model model) {
    	this.model= new Model();
    	this.ComboBox.setValue("Corsi");
    	this.ComboBox.getItems().addAll(this.model.getTuttiICorsiPerNome());
    	this.ComboBox.getItems().add(""); //campo vuoto se non si vuole selezionare un corso in particolare
    }
    @FXML
    void initialize() {
        assert ComboBox != null : "fx:id=\"ComboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert greenBtn != null : "fx:id=\"greenBtn\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
