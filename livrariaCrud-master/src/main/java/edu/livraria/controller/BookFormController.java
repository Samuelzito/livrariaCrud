package edu.livraria.controller;

import edu.livraria.model.entity.Livro;
import edu.livraria.model.services.LivroServices;
import edu.livraria.utils.HibernateUtil;
import jakarta.persistence.EntityManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookFormController {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtAnoPublicacao;

    @FXML
    private TextField txtGenero;

    private final EntityManager em = HibernateUtil.getEntityManager();
    private final LivroServices livroServices = new LivroServices(em);

    private Livro livroAtual; // Livro que está sendo editado ou criado


    public void setLivro(Livro livro) {
        this.livroAtual = livro;

        if (livro != null) {
            // Preenche os campos com os dados do livro existente
            txtTitulo.setText(livro.getTitulo());
            txtAutor.setText(livro.getAutor());
            txtAnoPublicacao.setText(String.valueOf(livro.getAnoPublicacao()));
            txtGenero.setText(livro.getGenero());
        } else {
            // Limpa os campos para criar um novo livro
            txtTitulo.clear();
            txtAutor.clear();
            txtAnoPublicacao.clear();
            txtGenero.clear();
        }
    }


    @FXML
    public void btnSalvarOnAction() {
        validarDadosFormulario();

        if (livroAtual == null) {
            livroAtual = new Livro();
        }

        livroAtual.setTitulo(txtTitulo.getText());
        livroAtual.setAutor(txtAutor.getText());
        livroAtual.setAnoPublicacao(Integer.parseInt(txtAnoPublicacao.getText()));
        livroAtual.setGenero(txtGenero.getText());

        if (livroAtual.getId() == null) {
            livroServices.salvar(livroAtual);
            System.out.println("Livro salvo com sucesso!");
        } else {
            livroServices.atualizar(livroAtual);
            System.out.println("Livro atualizado com sucesso!");
        }

    }


    @FXML
    public void btnCancelarOnAction() {
        fecharFormulario();
    }


    private void fecharFormulario() {
        Stage stage = (Stage) txtTitulo.getScene().getWindow();
        stage.close();
    }


    private void validarDadosFormulario() {
        if (txtTitulo.getText().isBlank()) {
            throw new IllegalArgumentException("O título do livro é obrigatório.");
        }
        if (txtAutor.getText().isBlank()) {
            throw new IllegalArgumentException("O autor do livro é obrigatório.");
        }
        try {
            Integer.parseInt(txtAnoPublicacao.getText());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O ano de publicação deve ser um número válido.");
        }
        if (txtGenero.getText().isBlank()) {
            throw new IllegalArgumentException("O gênero do livro é obrigatório.");
        }
    }
}
