package br.com.zup.mercadolivre.repository;

import br.com.zup.mercadolivre.modelo.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
