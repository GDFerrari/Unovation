package br.com.gabriel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.gabriel.ConnectionFactory.ConnectionFactory;
import br.com.gabriel.classes.Paciente;

public class PacienteDAO {

		private Connection connection;

		public PacienteDAO() {
			try {
				this.connection = new ConnectionFactory().getConnection();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		public void adiciona(Paciente paciente) {
			String sql = "insert into paciente (Nome, Idade, Endereco, Telefone) values (?,?,?,?)";
			PreparedStatement stmt;
			try {
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, paciente.getNome());
				stmt.setInt(2, paciente.getIdade());
				stmt.setString(3, paciente.getEndereco());
				stmt.setString(4, paciente.getTelefone());
				stmt.execute();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
		}

		public void remove(Paciente paciente) {

			if (paciente.getId() == null) {
				throw new IllegalStateException("Id do Paciente nao deve ser nulo.");
			}

			String sql = "delete from paciente where id = ?";
			PreparedStatement stmt;
			try {
				stmt = connection.prepareStatement(sql);
				stmt.setLong(1, paciente.getId());
				stmt.execute();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		public void altera(Paciente paciente) {
			String sql = "update paciente set Nome = ?, Endereco = ?, Idade = ?, Telefone = ?, valor = ? where id = ?";
			PreparedStatement stmt;
				try {
					stmt = connection.prepareStatement(sql);
					stmt.setString(1, paciente.getNome());
					stmt.setString(2, paciente.getEndereco());
					stmt.setInt(3, paciente.getIdade());
					stmt.setString(4, paciente.getTelefone());
					stmt.execute();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
		}
	
		
		public List <Paciente> lista() {
			try {
				PreparedStatement ps = this.connection
						.prepareStatement("select nome, idade, endereco, telefone from Paciente limit ?,? ");
	
				ResultSet rs = ps.executeQuery();
	
				List<Paciente> lista = new ArrayList<Paciente>();
	
				while (rs.next()) {
					Paciente paciente = new Paciente();
					paciente.setNome(rs.getString("Nome"));
					paciente.setIdade(rs.getInt("Idade"));
					paciente.setEndereco(rs.getString("Endereco"));
					paciente.setTelefone(rs.getString("Telefone"));
					lista.add(paciente);
				}
	
				ps.close();
				return lista;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

			
		public Paciente buscaPorId(Long id) {

			
			if (id == null) {
				throw new IllegalStateException("Id do paciente nao deve ser nulo.");
			}

			try {
				PreparedStatement stmt = this.connection
						.prepareStatement("select * from paciente where id = ?");
				stmt.setLong(1, id);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					return populaPaciente(rs);
				}

				rs.close();
				stmt.close();
				
				return null;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}


		private Paciente populaPaciente(ResultSet rs) throws SQLException {
			Paciente paciente = new Paciente();

			paciente.setId(rs.getInt("id"));
			paciente.setNome(rs.getString("Nome"));
			paciente.setEndereco(rs.getString("Endereco"));
			paciente.setIdade(rs.getInt("Idade"));
			
			
			return paciente;
		}
}
