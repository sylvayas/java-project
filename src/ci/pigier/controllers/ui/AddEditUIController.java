package ci.pigier.controllers.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import ci.pigier.DatabaseConnection;
import ci.pigier.controllers.BaseController;
import ci.pigier.model.Note;
import ci.pigier.ui.FXMLPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class AddEditUIController extends BaseController implements Initializable {

    @FXML
    private TextArea descriptionTxtArea;

    @FXML
    private Button saveBtn;
    @FXML
    private Button cleartextBtn;

    @FXML
    private TextField titleTxtFld;
    
   @FXML
   private Note selectedNote;
    
    @FXML
    void doBack(ActionEvent event) throws IOException {
    	navigate(event, FXMLPage.LIST.getPage());
    }

    @FXML
    void doClear(ActionEvent event) {
    	if(!titleTxtFld.getText().isEmpty() || descriptionTxtArea.getText().isEmpty() ){
    			titleTxtFld.clear();
    			descriptionTxtArea.clear();
    		
    	}
    }
    
   
 
    @FXML
    void doSave(ActionEvent event) throws IOException {
        try (Connection cnx = DatabaseConnection.getConnection()) {
            if (editNote != null) {
                // Mise à jour de la note existante
                String ModifQuery = "UPDATE Notes SET titre = ?, description = ? WHERE idNote = ?";
                try (PreparedStatement pstmt = cnx.prepareStatement(ModifQuery)) {
                    pstmt.setString(1, titleTxtFld.getText());
                    pstmt.setString(2, descriptionTxtArea.getText());
                    pstmt.setInt(3, editNote.getId());
                    pstmt.executeUpdate();
                    // Afficher un message de succès
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Succès");
                    successAlert.setHeaderText("Note Supprimée");
                    successAlert.setContentText("Modification effectuée avec succès.");
                    successAlert.showAndWait();
                }
            } else {
                // Ajout d'une nouvelle note
                String ADDQuery = "INSERT INTO Notes (titre, description) VALUES (?, ?)";
                try (PreparedStatement pstmt = cnx.prepareStatement(ADDQuery)) {
                    pstmt.setString(1, titleTxtFld.getText());
                    pstmt.setString(2, descriptionTxtArea.getText());
                    pstmt.executeUpdate();
                }
            }
           
            navigate(event, FXMLPage.LIST.getPage());
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Could not save data!");
            alert.setContentText("An error occurred while saving the note.");
            alert.showAndWait();
        }
        
    }

    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    if (Objects.nonNull(editNote)) {
	        titleTxtFld.setText(editNote.getTitle());
	        descriptionTxtArea.setText(editNote.getDescription());
	        saveBtn.setText(bundle.getString("button.update"));
	    }
	}
	
	
    

}
