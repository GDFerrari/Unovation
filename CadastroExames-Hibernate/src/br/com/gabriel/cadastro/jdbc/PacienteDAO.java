package br.com.gabriel.cadastro.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.gabriel.cadastro.classes.Paciente;


public class PacienteDAO {

	private Connection connection;

	public PacienteDAO(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(Paciente paciente) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("insert into Paciente (nome, idade, endereco, telefone) values (?,?,?,?)");
			ps.setString(1, paciente.getNome());
			ps.setString(2, paciente.getIdade());
			ps.setString(3, paciente.getEndereco());
			ps.setString(4, paciente.getTelefone());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Paciente paciente) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("update Paciente set nome=?, idade=?, endereco=?, telefone=? where id=?");
			ps.setString(1, paciente.getNome());
			ps.setString(2, paciente.getIdade());
			ps.setString(3, paciente.getEndereco());
			ps.setString(4, paciente.getTelefone());
			ps.setInt(5, paciente.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Paciente paciente) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("delete from Paciente where id=?");
			ps.setInt(1, paciente.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Paciente procura(Integer id) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select nome, idade, endereco, telefone from Paciente where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				return null;
			}
			Paciente paciente = new Paciente();
			paciente.setNome(rs.getString("Nome"));
			paciente.setIdade(rs.getString("Idade"));
			paciente.setEndereco(rs.getString("Endereco"));
			paciente.setTelefone(rs.getString("Telefone"));
			ps.close();
			return paciente;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Paciente> listaPaginada(int primeiro, int quantidade) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select nome, idade, endereco, telefone from Paciente limit ?,? ");
			ps.setInt(1, primeiro);
			ps.setInt(2, quantidade);

			ResultSet rs = ps.executeQuery();

			List<Paciente> lista = new ArrayList<Paciente>();

			while (rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setNome(rs.getString("Nome"));
				paciente.setIdade(rs.getString("Idade"));
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

	public List<Paciente> lista() {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select nome, idade, endereco, telefone from Paciente");
			ResultSet rs = ps.executeQuery();

			List<Paciente> lista = new ArrayList<Paciente>();

			while (rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setNome(rs.getString("Nome"));
				paciente.setIdade(rs.getString("Idade"));
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

	public List<Paciente> procuraPeloNome(String nome) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select nome, idade, endereco, telefone from Paciente where nome like ?");
			ps.setString(1, "%" + nome + "%");
			ResultSet rs = ps.executeQuery();

			List<Paciente> lista = new ArrayList<Paciente>();

			while (rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setNome(rs.getString("Nome"));
				paciente.setIdade(rs.getString("Idade"));
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

}