package br.com.prova.seg.bim.prova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prova.seg.bim.prova.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {}
