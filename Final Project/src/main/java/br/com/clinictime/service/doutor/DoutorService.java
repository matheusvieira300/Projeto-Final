package br.com.clinictime.service.doutor;

import br.com.clinictime.model.Doutor;

public interface DoutorService {

    public Doutor createUser(Doutor user);

    public boolean checkEmail(String email);

}
