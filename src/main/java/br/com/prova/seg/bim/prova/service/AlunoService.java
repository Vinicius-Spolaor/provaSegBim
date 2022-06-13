package br.com.prova.seg.bim.prova.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prova.seg.bim.prova.model.Aluno;
import br.com.prova.seg.bim.prova.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	AlunoRepository alunoRepository;

	public List<Aluno> listaAlunos() {
		return alunoRepository.findAll();
	}
	
	public Aluno adicionaAluno(Aluno aluno) {
		return alunoRepository.save(aluno);
	}

	public Aluno encontraAlunoById(Long idAluno) {
		Optional<Aluno> aluno = alunoRepository.findById(idAluno);
		
		if (aluno.isPresent()) {
			return aluno.get();			
		}
		return null;
	}

	public Aluno atualizaAluno(Long idAluno, Aluno aluno) {
		Aluno alunoAtual = alunoRepository.getReferenceById(idAluno);

		alunoAtual = aluno;

		return alunoAtual;
	}

	public void removeAluno(Long idAluno) {
		alunoRepository.deleteById(idAluno);
	}
}
