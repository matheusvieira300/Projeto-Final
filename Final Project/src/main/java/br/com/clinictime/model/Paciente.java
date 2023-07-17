package br.com.clinictime.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Data //para criar os getters e setters e o to string
@AllArgsConstructor //para criar construtor com as propriedades da entidade
@NoArgsConstructor //para criar um construtor vazio
@Builder //para ajudar na criação de objetos cliente
@Entity
public class Paciente {

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cpf", length = 11)
    @NotNull
    private String cpf;

    private String name;
    private String telefone;
    private String email;
    private String nascimento;
    private String nomeDaMae;
    private String nomeDoPai;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
    private String cep;

 @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
 private List<Consulta> consultasAnteriores;



}
