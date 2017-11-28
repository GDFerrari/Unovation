package br.com.gabriel.cadastro.Testes;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.gabriel.cadastro.classes.Exame;
import br.com.gabriel.cadastro.classes.Paciente;
import br.com.gabriel.cadastro.classes.TipoVisita;

public class TesteRelacionamento {
	public static void main(String[] args) {
		
		Paciente paciente = new Paciente();
		paciente.setNome("Gabriel");
		paciente.setEndereco("Rua Justino Paixao,123");
		paciente.setIdade("20");
		paciente.setTelefone("11912345678");
	
		
		Exame exame = new Exame();
		exame.setEspecialista("Oftalmologista");
		exame.setDataExame(Calendar.getInstance());
		exame.setDescricao("Checkup Anual");
		exame.setVisita(TipoVisita.PrimeiraVisita);
		exame.setValorConsulta(new BigDecimal("150.00"));
		
		exame.setPaciente(paciente);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("paciente");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(paciente);
		em.persist(exame);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
