package br.com.gabriel.cadastro.Testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.gabriel.cadastro.classes.Paciente;

public class PacienteTeste {

	public static void main(String[] args) {
		
		Paciente paciente = new Paciente();
		
		paciente.setNome("Matias");
		paciente.setEndereco("Rua Justino Paixao,123");
		paciente.setIdade("20");
		paciente.setTelefone("11912345678");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("paciente");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(paciente);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}

}
