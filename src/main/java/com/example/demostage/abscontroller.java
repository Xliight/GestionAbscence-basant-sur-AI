package com.example.demostage;

import com.example.dao.absdoaImp;
import com.example.model.abs;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class abscontroller  {

    private boolean uplo;



    @FXML
    private TableColumn<com.example.model.abs, String> date_arriv;



    @FXML
    private TableColumn<com.example.model.abs, Long> immatr;

    @FXML
    private TableColumn<com.example.model.abs, String> name;

    @FXML
    private TextField searchinput;

    @FXML
    private TableView<com.example.model.abs> table;
    @FXML
    private Label lblmsg;


    private String globalimmatricule;


    @FXML
    void upload(ActionEvent event) throws IOException {
        scene.changeScene(event,"menu.fxml" ,  "ajouter eployee" , 960 , 640 );

    }
    ObservableList<abs> l;

    com.example.dao.absdao absdao=new absdoaImp();

    public void refreshtablecondidat2(String immatricul) throws InterruptedException {
        name.setCellValueFactory(new PropertyValueFactory<abs,String>("name"));
        immatr.setCellValueFactory(new PropertyValueFactory<abs,Long>("immatr"));
        date_arriv.setCellValueFactory(new PropertyValueFactory<abs,String>("date_arriv"));
        l=absdao.showlistabs(immatricul);
        if (l.isEmpty()){
            lblmsg.setText("No employee associate to this immatricule");
        }else {
            System.out.println(l);
            table.setItems(l);
        }

    }
    @FXML
    public void btnsearch() throws InterruptedException {
        lblmsg.setText("");
        String immatricul=searchinput.getText();
        refreshtablecondidat2(immatricul);
        globalimmatricule=immatricul;
        System.out.println(globalimmatricule);
    }
    @FXML
    void backtorh(ActionEvent event) throws IOException {
        scene.changeScene(event,"menu.fxml" ,  "ajouter eployee" , 900  , 600 );
    }

    @FXML
    void Download(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Table Data");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showSaveDialog(table.getScene().getWindow());

        if (selectedFile != null) {
            saveTableDataToCSV(selectedFile);
        }
    }
    private String formatDate(String dateTimeStr) {
        try {
            // Parse the input date and time string in "dd-MMMM-yyyy" and "HH:mm" format to LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm"));

            // Format the LocalDateTime to "HH:mm dd-MMMM-yyyy" format
            return dateTime.format(DateTimeFormatter.ofPattern("HH:mm dd-MMMM-yyyy"));
        } catch (DateTimeParseException e) {
            // In case of any parsing error, return the original string as is
            return dateTimeStr;
        }
    }
    private void saveTableDataToCSV(File file) {
        boolean fileExists = file.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Write data
            if (!fileExists) {
                // If the file doesn't exist, write the header
                writer.write("Nom Prénom,immatricul,Date prevue d'entrée\n");
            }

            for (abs person : l) {
                String formattedDate = formatDate(person.getDate_arriv()); // Format the date
                String line = person.getName() + "," + person.getImmatr() + "," + formattedDate + "\n";
                writer.write(line);
            }

            System.out.println("Table data saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving table data: " + e.getMessage());
        }
    }

}
