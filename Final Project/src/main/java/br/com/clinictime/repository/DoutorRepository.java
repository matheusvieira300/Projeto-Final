package br.com.clinictime.repository;

import br.com.clinictime.model.Doutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoutorRepository extends JpaRepository<Doutor, Long> {

    public boolean existsByEmail(String email);
    public Doutor findByEmail(String email);

}
