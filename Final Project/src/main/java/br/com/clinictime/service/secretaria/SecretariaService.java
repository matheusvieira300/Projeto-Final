package br.com.clinictime.service.secretaria;

import br.com.clinictime.model.Secretaria;

public interface SecretariaService {

    public Secretaria createUser(Secretaria secretaria);

    public boolean checkEmail(String email, Secretaria secretaria);

    Secretaria getSecretariaById(Long id);

}
