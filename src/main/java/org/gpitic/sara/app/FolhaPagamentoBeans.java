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
import org.gpitic.sara.infra.Folhapagamento;

/**
 *
 * @author paulo
 */
@ManagedBean
@RequestScoped
public class FolhaPagamentoBeans {
    Folhapagamento pagamento = new Folhapagamento();
    List<Folhapagamento> pagamentos = new ArrayList<Folhapagamento>();
    /**
     * Creates a new instance of FolhaPagamentoBeans
     */
    public FolhaPagamentoBeans() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlspu");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select p from Folhapagamento p", Folhapagamento.class);
        this.pagamentos = q.getResultList();
        
        
    }
    public void CalcularFolhaPagamento() {
        pagamento = new Folhapagamento(pagamento.getId(), pagamento.getSalarioBruto());
        pagamento.calcularInss();
        pagamento.calcularIrrf();
        pagamento.calcularSalarioLiquido();
        pagamento.setId(pagamento.getId());
        pagamento.setInss(pagamento.getInss());
        pagamento.setIrrf(pagamento.getIrrf());
        pagamento.setSalarioLiquido(pagamento.getSalarioLiquido());
        getPagamento();
        setPagamento(pagamento);
    }
       
    public String cadastrar(){
        CalcularFolhaPagamento();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlspu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        
        etx.begin();
        em.merge(pagamento);
        etx.commit();
        em.close();
        emf.close();
        return "folhaPagamentoSucesso";
        
    }
    
    public List<Folhapagamento> getPagamentos() {
        List<Folhapagamento> pagamentos = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlspu");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT f FROM Folhapagamento f", Folhapagamento.class);
        pagamentos = q.getResultList();
        return pagamentos;
    }
    
    public void excluir(Folhapagamento pagamento){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlspu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        pagamento = em.merge(pagamento);
        em.remove(pagamento);
        etx.commit();
        em.close();
        emf.close();
    }
    
    public String voltar(){
        return "folhaPagamento";
    }
    
    public Folhapagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Folhapagamento pagamento) {
        this.pagamento = pagamento;
    }

}
