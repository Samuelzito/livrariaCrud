package edu.Livraria.controller;

import edu.Livraria.utils.PathFXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    public void abrirTelaLivros() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(PathFXML.LIVRO_VIEW));
            Stage stage = new Stage();
            stage.setTitle("Gerenciamento de Livros");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
