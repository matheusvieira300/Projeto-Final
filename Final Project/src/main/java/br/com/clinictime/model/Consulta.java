package br.com.clinictime.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String receita;

    @ManyToOne
    @JoinColumn(name = "doutor_id")
    private Doutor doutor;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}

