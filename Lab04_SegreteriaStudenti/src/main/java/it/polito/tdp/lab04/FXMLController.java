/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

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

    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {

    }

    @FXML
    void doGreenButton(ActionEvent event) {
    	String matricola= this.txtMatricola.getText();
    	for(Studente s: model.getTuttiGliStudenti()) {
    		if(s.getMatricola()==(Integer.parseInt(matricola))) {
    			this.txtCognome.setText(s.getCognome());
    			this.txtNome.setText(s.getNome());
    		}
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }
    /**
     * permette di cancellare il contenuto di tutti i campi
     * @param event
     */
    @FXML
    void doReset(ActionEvent event) {

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
