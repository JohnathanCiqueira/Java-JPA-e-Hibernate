package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteFuncoesJPQL {
	
	public static void main(String[] args) {
		

		EntityManager em = new JPAUtil().geEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		String jpql = "select sum(m.valor) from Movimentacao m where m.conta = :pConta" + 
		" and m.tipo = :pTipo" +
		" order by m.valor desc";
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta); 
		query.setParameter("pTipo", TipoMovimentacao.SAIDA); 

		
		BigDecimal soma = (BigDecimal)query.getSingleResult();
		
		System.out.println("A soma �: " + soma);
		
		em.getTransaction().commit();
		em.close();
		
	}

}
