package ci.pigier.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;
import ci.pigier.DatabaseConnection;
import ci.pigier.model.Note;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class BaseController {
	protected Alert alert;
	protected static Note editNote = null;
	
	protected static Locale locale = Locale.forLanguageTag("fr");
	protected static ResourceBundle bundle = ResourceBundle.getBundle("ci.pigier.language_properties.settings", locale);

	protected static ObservableList<Note> data = FXCollections.<Note>observableArrayList();
			 
	protected void navigate(Event event, URL fxmlDocName) throws IOException {
		// Chargement du nouveau document FXML de l'interface utilisateur
		Parent pageParent = FXMLLoader.load(fxmlDocName, bundle);
		// Création d'une nouvelle scène
		Scene scene = new Scene(pageParent);
		// Obtention de la scène actuelle
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		// Masquage de l'ancienne scène (facultatif)
		appStage.hide(); // facultatif
		// Définition de la nouvelle scène pour la scène
		appStage.setScene(scene);
		// Affichage de la scène
		appStage.show();
	}
	
	public ResourceBundle getBundle() {
		return bundle;
	}
	
	public void LoadNotes() {
		String NotesList="SELECT * FROM Notes";
		
		try{Connection connex =DatabaseConnection.getConnection();
			Statement stamt=connex.createStatement();
			ResultSet resul=stamt.executeQuery(NotesList);{
				data.clear();
				while (resul.next()) {
					int id=resul.getInt("idNote");
					String title = resul.getString("titre");
					String description = resul.getString("description");
					data.add(new Note (id,title,description));
					
				}
			}
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
