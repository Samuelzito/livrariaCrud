package edu.Livraria.persistence;

import edu.Livraria.model.entity.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class LivroDAO {

    private EntityManager em;

    public LivroDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(Livro livro) {
        em.getTransaction().begin();
        em.persist(livro);
        em.getTransaction().commit();
    }

    public void atualizar(Livro livro) {
        em.getTransaction().begin();
        em.merge(livro);
        em.getTransaction().commit();
    }

    public void excluir(Long id) {
        em.getTransaction().begin();
        Livro livro = em.find(Livro.class, id);
        if (livro != null) {
            em.remove(livro);
        }
        em.getTransaction().commit();
    }

    public List<Livro> listarTodos() {
        TypedQuery<Livro> query = em.createQuery("SELECT l FROM Livro l", Livro.class);
        return query.getResultList();
    }
}
