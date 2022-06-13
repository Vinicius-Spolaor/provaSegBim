package br.com.prova.seg.bim.prova.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.prova.seg.bim.prova.model.Aluno;
import br.com.prova.seg.bim.prova.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
	
	@Autowired
	AlunoService alunosService;
	
	@GetMapping
	public List<Aluno> listaAlunos() {
		List<Aluno> alunos = alunosService.listaAlunos();
		return alunos;
	}
	
	@GetMapping("/{id}")
	public Aluno getAlunoById(@PathVariable Long id) {
		Aluno aluno = alunosService.encontraAlunoById(id);
		return aluno;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno, UriComponentsBuilder uriBuilder) {
		Aluno alunoAdicionado = alunosService.adicionaAluno(aluno);
		
		URI uri = uriBuilder.path("/alunos").buildAndExpand(alunoAdicionado.getId()).toUri();
		return ResponseEntity.created(uri).body(alunoAdicionado);
	}
	
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
		Aluno alunoExistente = alunosService.encontraAlunoById(id);
		if (alunoExistente != null) {
			Aluno alunoAtualizado = alunosService.atualizaAluno(id, aluno);
			return ResponseEntity.ok(alunoAtualizado);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removerAluno(@PathVariable Long id) {
		Aluno alunoExistente = alunosService.encontraAlunoById(id);
		if (alunoExistente != null) {
			alunosService.removeAluno(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
