/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpitic.sara.app;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.gpitic.sara.infra.Folhapagamento;

/**
 *
 * @author paulo
 */
@ManagedBean
@RequestScoped
public class FolhaPagamentoBeans {
    private Folhapagamento pagamento = new Folhapagamento();
    /**
     * Creates a new instance of FolhaPagamentoBeans
     */
    public FolhaPagamentoBeans() {
        
        
    }
    
    public String cadastrar(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlspu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        
        etx.begin();
        em.merge(pagamento);
        etx.commit();
        em.close();
        emf.close();
        pagamento = pagamento;
        return "FolhaPagamento";
        
    }
    
    public Double calcularInss(){
        double salarioBruto = pagamento.getSalarioBruto();
        double inss = 0.0;
        if (salarioBruto < 1751.82) {
            inss = salarioBruto * 0.08;
        } else if (salarioBruto > 1751.81 && salarioBruto < 2919.73) {
            inss = salarioBruto * 0.09;
        } else if (salarioBruto > 2919.72 && salarioBruto < 5839.46) {
            inss = salarioBruto * 0.11;
        } else if (salarioBruto > 5839.45) {
            inss = 817.66;
        }
        pagamento.setInss(inss);
        return inss;
    }
    
    public Double calcularIrrf(){
        double salarioBruto = pagamento.getSalarioBruto();
        double salarioSInss = (pagamento.getInss()-salarioBruto);
        double irrf = pagamento.getIrrf();
        
        if (salarioSInss < 1903.99) {
            irrf = 0;
        } else if (salarioSInss > 1903.98 && salarioSInss < 2826.66) {
            irrf = (salarioSInss * 0.075) - 142.80;
        } else if (salarioSInss > 2826.65 && salarioSInss < 3751.06) {
            irrf = (salarioSInss * 0.15) - 354.8;
        } else if (salarioSInss > 3751.05 && salarioSInss < 4664.69) {
            irrf = (salarioSInss * 0.225) - 636.13;
        } else if (salarioSInss > 4664.68) {
            irrf = (salarioSInss * 0.275) - 869.36;
        }
        pagamento.setIrrf(irrf);
        
        return irrf;
    
    }
    
    public Double calcularSalario(){     
        double salarioLiquido = 0.0;
        
        salarioLiquido = pagamento.getSalarioBruto() - (pagamento.getInss() + pagamento.getIrrf());
       
        pagamento.setSalarioLiquido(salarioLiquido);
        
        return salarioLiquido;
    }

    public Folhapagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Folhapagamento pagamento) {
        this.pagamento = pagamento;
    }
    
    
}
