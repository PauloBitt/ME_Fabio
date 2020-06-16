/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpitic.sara.app;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.gpitic.sara.infra.Projeto;

/**
 *
 * @author franc
 */
@ManagedBean
@RequestScoped
public class ProjetoBeans {
    Projeto projeto = new Projeto();
    List<Projeto> projetos = new ArrayList<Projeto>();
    
    /**
     * Creates a new instance of ProjetoBeans
     */
    public ProjetoBeans() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("spu");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select p from Projeto p", Projeto.class);
        this.projetos = q.getResultList();
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    public String cadastrar(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("spu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.persist(projeto);
        etx.commit();
        em.close();
        emf.close();
            
        return "projeto";
        
        
    }

    public List<Projeto> getProjetos() {
        List<Projeto> projetos = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("spu");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select p from Projeto p", Projeto.class);
        this.projetos = q.getResultList();
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }
    
  
}
