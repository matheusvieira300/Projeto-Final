package br.com.clinictime.repository;

import br.com.clinictime.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    //abaixo ta uma query que faz o findbyname pesquisar por partes do nome pra não ter que pesquisar o nome completo
    //pra aparecer o resultado exato

    @Query("SELECT p FROM Paciente p WHERE UPPER(p.name) LIKE CONCAT('%', UPPER(:name), '%')")
    List<Paciente> findByName(@Param("name") String name);
}
