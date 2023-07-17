package br.com.clinictime.repository;


import br.com.clinictime.model.Secretaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SecretariaRepository extends JpaRepository<Secretaria, Long> {

    public boolean existsByEmail(String email);
    public Secretaria findByEmail(String email);


    @Query("SELECT s FROM Secretaria s WHERE UPPER(s.name) LIKE CONCAT('%', UPPER(:name), '%')")
    List<Secretaria> findByName(String name);
}
