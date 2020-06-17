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
import javax.persistence.TypedQuery;
import org.gpitic.sara.infra.Funcionario;

/**
 *
 * @author paulo
 */
@ManagedBean
@RequestScoped
public class FuncionarioBean {
    private Funcionario funcionario = new Funcionario();
    List<Funcionario> funcionarios = new ArrayList<Funcionario>();
    
    /**
     * Creates a new instance of FuncionarioBean
     */
    
    public void selecionar(Funcionario funcionario){
        this.funcionario = funcionario;
    
    }
    public FuncionarioBean() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlspu");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select p from Funcionario p", Funcionario.class);
        this.funcionarios = q.getResultList();
        
    }
    
    public String consultarPorId(int id){
        String retorno="";
        String consulta = "SELECT f FROM Funcionario f WHERE f.id = :id";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlspu");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Funcionario> query = em.createQuery(consulta, Funcionario.class);
        query.setParameter("id", funcionario.getId());
        for(Funcionario f: query.getResultList()){
            if(f.getId().equals(funcionario.getId()))
                retorno = "consultarResultId";
        }
        return retorno;
    }
    
    public String consultarPorNome(String nome) {
        String retorno ="";
        String consulta = "SELECT f FROM Funcionario f WHERE f.nome = :nome";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlspu");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Funcionario> query = em.createQuery(consulta, Funcionario.class);
        query.setParameter("nome", funcionario.getNome());
        for(Funcionario f: query.getResultList()){
            if(f.getNome().equals(funcionario.getNome()))
                retorno = "consultaResultNome";
        }
        return retorno;
   
    }
    
    public List<Funcionario> getFuncionarioID(){
        List<Funcionario> funcionarios = null;
        String consulta = "SELECT f FROM Funcionario f WHERE f.id = :id";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlspu");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Funcionario> query = em.createQuery(consulta, Funcionario.class);
        query.setParameter("id", funcionario.getId());
        for(Funcionario f: query.getResultList()){
            if(f.getId().equals(funcionario.getId())){
                funcionarios = query.getResultList();
            }
        }
        return funcionarios;
    }
    
    public List<Funcionario> getFuncionarioNome(){
        List<Funcionario> funcionarios = null;
        String consulta = "SELECT f FROM Funcionario f WHERE f.nome = :nome";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlspu");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Funcionario> query = em.createQuery(consulta, Funcionario.class);
        query.setParameter("nome", funcionario.getNome());
        for(Funcionario f: query.getResultList()){
            if(f.getNome().equals(funcionario.getNome())){
                funcionarios = query.getResultList();
            }
        }
        return funcionarios;
    }
    
    
    public String cadastrar(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlspu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        
        etx.begin();
        em.persist(funcionario);
        etx.commit();
        em.close();
        emf.close();
        funcionario = funcionario;
        
        return "consultarFuncionarios";
        
    }
    public String salvar(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlspu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        
        etx.begin();
        em.merge(funcionario);
        etx.commit();
        em.close();
        emf.close();
        
        return "consultarFuncionarios";
        
    }
    
    public void excluir(Funcionario funcionario){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlspu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        funcionario = em.merge(funcionario);
        em.remove(funcionario);
        etx.commit();
        em.close();
        emf.close();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getFuncionarios() {
        List<Funcionario> funcionarios = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlspu");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT f FROM Funcionario f", Funcionario.class);
        funcionarios = q.getResultList();
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    public String voltar(){
        return "cadastro";
    }
    
    
    
}
