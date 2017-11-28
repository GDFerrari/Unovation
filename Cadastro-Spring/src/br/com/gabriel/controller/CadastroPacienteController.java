package br.com.gabriel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CadastroPacienteController {
	
	@RequestMapping(value = "/cadastro")
	public String cadastro() {
		System.out.println("Incio do cadastro Paciente");
		return "CadPaciente";
	}
}
