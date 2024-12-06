package edu.Livraria.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class MenuController {

    @FXML
    private Button btnGerenciarLivros;

    @FXML
    private Button btnSair;

    @FXML
    public void initialize() {
        btnGerenciarLivros.setOnAction(event -> abrirTelaGerenciamentoLivros());
        btnSair.setOnAction(event -> sair());
    }

    private void abrirTelaGerenciamentoLivros() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LivroView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Gerenciamento de Livros");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
}
