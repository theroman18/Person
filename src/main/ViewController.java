/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.PersonDAO;
import entities.Person;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import JpaController.exceptions.NonexistentEntityException;

import JpaController.PersonJpaController;
import javafx.event.EventHandler;

/**
 * FXML Controller class
 *
 * @author Roman-Desktop
 */
public class ViewController implements Initializable {
    
    PersonJpaController personJpaController;
    
    @FXML
    private TextField textFieldFirstName;
    @FXML
    private TextField textFieldLastName;
    @FXML
    private TextField textFieldAge;
    @FXML
    private TextField textFieldFilter;
    @FXML
    private ChoiceBox<String> choiceBoxEmployed;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnUpdate;
    @FXML
    private ListView<Person> personsList;
    private final ObservableList<Person> personObservableList = FXCollections.observableArrayList();
    private final PersonDAO personDAO = new PersonDAO();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        personsList.setItems(personObservableList);
        personObservableList.addAll(personDAO.getAllPersons()); 
        personsList.setCellFactory((ListView<Person> param) -> {
            ListCell<Person> listCell = new ListCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                    
                    if (item != null) {
                        Person person = (Person) item;
                        if (person.getAge() < 18){
                            setText(person.getFirstName() + "    !");
                            setStyle("-fx-text-fill: red");
                        }
                        else {
                            setText(person.getFirstName());  
                            setStyle("-fx-text-fill: black");
                        }
                    } else {
                        setText("");
                        setStyle("-fx-text-fill: black");
                    }
                }
            };
            return listCell;
        });
        
        FilteredList<Person> filteredList = new FilteredList<>(personObservableList);
        personsList.setItems(filteredList);
        textFieldFilter.textProperty().addListener((observable, oldValue, newValue) ->  {
            if (newValue.isEmpty()) {
                filteredList.setPredicate(null);
            } else {
                final String searchString = newValue.toUpperCase();
                filteredList.setPredicate(s -> s.getFirstName().toUpperCase().contains(searchString));
            }
        });
        
        // Add Person
        btnAdd.setOnAction((ActionEvent event) -> {
            if (!textFieldFirstName.getText().trim().isEmpty() && !textFieldLastName.getText().trim().isEmpty() && !textFieldAge.getText().trim().isEmpty()) {
                Person newPerson = new Person();
                newPerson.setFirstName(textFieldFirstName.getText());
                newPerson.setLastName(textFieldLastName.getText());
                newPerson.setAge(Integer.parseInt(textFieldAge.getText()));
                newPerson.setEmployed(processStringToBool(choiceBoxEmployed.getSelectionModel().getSelectedItem()));
                try {
                    personDAO.addPerson(newPerson);
                } catch (Exception ex) {
                    Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                textFieldFirstName.clear();
                textFieldLastName.clear();
                textFieldAge.clear();
                choiceBoxEmployed.setValue("No");
                personObservableList.add(newPerson);
            }
        });
        
        // Remove Person
        btnRemove.setOnAction((ActionEvent event) -> {
            Person selectedItem = personsList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                try {
                    personDAO.removePerson(selectedItem.getId());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                personObservableList.remove(selectedItem);
            }
        });
        
        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person personToEdit = personDAO.getPersonById(5);
                personToEdit.setAge(30);
                personToEdit.setEmployed(true);
                
                if (personToEdit != null) {
                    try {
                        personDAO.editPerson(personToEdit);
                    } catch (Exception ex) {
                        Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    textFieldFirstName.clear();
                    textFieldLastName.clear();
                    textFieldAge.clear();
                    choiceBoxEmployed.setValue("No");
                } 
            }
        });
        
        choiceBoxEmployed.getItems().addAll("No", "Yes");
        choiceBoxEmployed.setValue("No");
    }    
    private int getID() {
        List<Person> allPerson = personDAO.getAllPersons();
        int maxID = 0;
        
        if (!allPerson.isEmpty()) {
            for (Person person:allPerson) {
                if (person.getId() > maxID) {
                    maxID = person.getId();
                }
            }
        }
        
        return ++maxID;
    }
    
    private boolean processStringToBool(String item) {
        return item.equals("Yes");
    }
}

