package br.com.zup.mercadolivre.repository;

import br.com.zup.mercadolivre.modelo.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
