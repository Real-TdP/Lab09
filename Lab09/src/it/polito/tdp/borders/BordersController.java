/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class BordersController {

	Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;
	
	@FXML
    private ComboBox<Country> state;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	private void doCalcolaConfini(ActionEvent event) {
		txtResult.clear();
		int anno;
		try {
			anno = Integer.parseInt(txtAnno.getText());
		} catch (NumberFormatException e) {
			txtResult.appendText("ERRORE: Inserire un anno valido!\n");
			return;
		}
		txtResult.appendText(model.calcolaConfini(anno));
		return;
	}
	
	 @FXML
	 private void doTrovaVicini(ActionEvent event) {
		 txtResult.clear();
		 int anno;
			try {
				anno = Integer.parseInt(txtAnno.getText());
			} catch (NumberFormatException e) {
				txtResult.appendText("ERRORE: Inserire un numero valido!\n");
				return;
			}
		 txtResult.appendText(model.trovaCompari(state.getValue(),anno));
		 

	 }


	@FXML // This method is called by the FXMLLoader when initialization is complete
	private void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
		assert state != null : "fx:id=\"state\" was not injected: check your FXML file 'Borders.fxml'.";
	}
	
	public void setModel(Model model){
		this.model=model;
		state.getItems().addAll(model.getCountry());
		
	}
}
