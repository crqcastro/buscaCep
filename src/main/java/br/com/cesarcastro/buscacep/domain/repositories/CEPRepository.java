package br.com.cesarcastro.buscacep.domain.repositories;

import br.com.cesarcastro.buscacep.domain.model.entities.CEPEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CEPRepository extends JpaRepository<CEPEntity, Long> {
    CEPEntity findByCep(String cep);
}
