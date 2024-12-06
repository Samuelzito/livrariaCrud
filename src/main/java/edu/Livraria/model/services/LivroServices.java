package edu.Livraria.model.services;

import edu.Livraria.model.entity.Livro;
import jakarta.persistence.EntityManager;

import java.util.List;

public class LivroServices {

    private final EntityManager em;

    public LivroServices(EntityManager em) {
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
        return em.createQuery("FROM Livro", Livro.class).getResultList();
    }
}
