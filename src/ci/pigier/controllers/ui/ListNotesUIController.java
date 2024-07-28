package ci.pigier.controllers.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.util.Optional;
import java.util.ResourceBundle;

import ci.pigier.DatabaseConnection;
import ci.pigier.controllers.BaseController;
import ci.pigier.model.Note;
import ci.pigier.ui.FXMLPage;

import javafx.collections.ListChangeListener;

import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class ListNotesUIController extends BaseController implements Initializable {

    @FXML
    private TableColumn<Note,String> descriptionTc;

    @FXML
    private Label notesCount;

    @FXML
    private TableView<Note> notesListTable;
    
    @FXML
    private TextField searchNotes;
    
    @FXML
    private TableColumn<Note, String> titleTc;
    
    @FXML
	private Note selectedNote;
    
    
    public void setSelectedNote(Note note) {
        this.selectedNote = note;
        if (note != null) {
            titleTc.setText(note.getTitle());
            descriptionTc.setText(note.getDescription());
        }
    }
    
    @FXML
    void doDelete(ActionEvent event) {
        Note selectedNote = notesListTable.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            // Créer une boîte de dialogue de confirmation avec des boutons personnalisés
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirmation");
            confirmAlert.setHeaderText("Confirmer la suppression");
            confirmAlert.setContentText("Êtes-vous sûr de vouloir supprimer cette note?");
            
            // Créer les boutons Oui et Non
            ButtonType yesButton = new ButtonType("Oui");
            ButtonType noButton = new ButtonType("Non");
            
            // Ajouter les boutons personnalisés à la boîte de dialogue
            confirmAlert.getButtonTypes().setAll(yesButton, noButton);
            
            // Attendre la réponse de l'utilisateur
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == yesButton) {
                // L'utilisateur a confirmé la suppression
                String deleteSQL = "DELETE FROM Notes WHERE idNote = ?";
                try (Connection connection = DatabaseConnection.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                    preparedStatement.setInt(1, selectedNote.getId());
                    preparedStatement.executeUpdate();
                    
                    // Recharge les notes après suppression
                    LoadNotes();
                    
                    // Afficher un message de succès
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Succès");
                    successAlert.setHeaderText("Note Supprimée");
                    successAlert.setContentText("La note a été supprimée avec succès.");
                    successAlert.showAndWait();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Afficher un message d'erreur si aucune note n'est sélectionnée
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de sélection");
            alert.setHeaderText("Aucune note sélectionnée");
            alert.setContentText("Veuillez sélectionner une note à supprimer.");
            alert.showAndWait();
        }
    }



    

    @FXML
    void doEdit(ActionEvent event) throws IOException {
    editNote=notesListTable.getSelectionModel().getSelectedItem();
            navigate(event, FXMLPage.ADD.getPage());
        
    }


    @FXML
    void newNote(ActionEvent event) throws IOException {
    	editNote = null;
    	navigate(event, FXMLPage.ADD.getPage());
    }
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	FilteredList<Note> filteredData = new FilteredList<>(data, n -> true);
        notesListTable.setItems(filteredData);
        titleTc.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionTc.setCellValueFactory(new PropertyValueFactory<>("description"));
        searchNotes.setOnKeyReleased(e -> {
            filteredData.setPredicate(n -> {
                if (searchNotes.getText() == null || searchNotes.getText().isEmpty())
                    return true;
                return n.getTitle().contains(searchNotes.getText()) || n.getDescription().contains(searchNotes.getText());
            });
        });
        notesListTable.getItems().addListener((ListChangeListener<Note>) change -> updateNotesCount());
        updateNotesCount();
        LoadNotes();
    }

    private void updateNotesCount() {
        int count = notesListTable.getItems().size();
        notesCount.setText(count + " Notes");
    }
    
   
   
}