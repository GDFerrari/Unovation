package br.com.gabriel.cadastro.classes;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Exame {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	
	private String Especialista;
	
	@Enumerated(EnumType.STRING)
	private TipoVisita Visita;
	private BigDecimal ValorConsulta;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar DataExame;
	private String Descricao;
	
	@ManyToOne
	private Paciente paciente ;
	
	// Getter e Setter
	public Calendar getDataExame() {
		return DataExame;
	}
	public void setDataExame(Calendar dataExame) {
		DataExame = dataExame;
	}
	public BigDecimal getValorConsulta() {
		return ValorConsulta;
	}
	public void setValorConsulta(BigDecimal valorConsulta) {
		ValorConsulta = valorConsulta;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getEspecialista() {
		return Especialista;
	}
	public void setEspecialista(String especialista) {
		Especialista = especialista;
	}
	public TipoVisita getVisita() {
		return Visita;
	}
	public void setVisita(TipoVisita visita) {
		Visita = visita;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
}
