package edu.Livraria.controller;

import edu.Livraria.model.entity.Livro;
import edu.Livraria.model.services.LivroServices;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class LivroController {

    @FXML
    private TableView<Livro> tabelaLivros;
    @FXML
    private TableColumn<Livro, String> colunaTitulo;
    @FXML
    private TableColumn<Livro, String> colunaAutor;
    @FXML
    private TableColumn<Livro, Integer> colunaAno;
    @FXML
    private TableColumn<Livro, String> colunaGenero;

    @FXML
    private TextField campoTitulo, campoAutor, campoAno, campoGenero;
    @FXML
    private Button btnCadastrar, btnAtualizar, btnExcluir, btnListar;

    private EntityManager em;
    private LivroServices livroServices;

    @FXML
    public void initialize() {
        em = Persistence.createEntityManagerFactory("bibliotecaPU").createEntityManager();
        livroServices = new LivroServices(em);

        colunaTitulo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitulo()));
        colunaAutor.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAutor()));
        colunaAno.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getAnoPublicacao()).asObject());
        colunaGenero.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getGenero()));

        listarLivros();
    }

    @FXML
    private void cadastrarLivro() {
        Livro livro = new Livro();
        livro.setTitulo(campoTitulo.getText());
        livro.setAutor(campoAutor.getText());
        livro.setAnoPublicacao(Integer.parseInt(campoAno.getText()));
        livro.setGenero(campoGenero.getText());
        livroServices.salvar(livro);
        listarLivros();
        limparCampos();
    }

    @FXML
    private void atualizarLivro() {
        Livro livroSelecionado = tabelaLivros.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            livroSelecionado.setTitulo(campoTitulo.getText());
            livroSelecionado.setAutor(campoAutor.getText());
            livroSelecionado.setAnoPublicacao(Integer.parseInt(campoAno.getText()));
            livroSelecionado.setGenero(campoGenero.getText());
            livroServices.atualizar(livroSelecionado);
            listarLivros();
            limparCampos();
        }
    }

    @FXML
    private void excluirLivro() {
        Livro livroSelecionado = tabelaLivros.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            livroServices.excluir(livroSelecionado.getId());
            listarLivros();
            limparCampos();
        }
    }

    @FXML
    private void listarLivros() {
        List<Livro> livros = livroServices.listarTodos();
        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        tabelaLivros.setItems(data);
    }

    private void limparCampos() {
        campoTitulo.clear();
        campoAutor.clear();
        campoAno.clear();
        campoGenero.clear();
    }
}