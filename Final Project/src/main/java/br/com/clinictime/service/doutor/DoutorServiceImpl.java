package br.com.clinictime.service.doutor;

import br.com.clinictime.repository.DoutorRepository;
import br.com.clinictime.model.Doutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoutorServiceImpl implements DoutorService {

    @Autowired
    private DoutorRepository doutorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @Override
    public Doutor createUser(Doutor doutor) {

        doutor.setPassword(passwordEncode.encode(doutor.getPassword()));
        doutor.setRole("ROLE_ADMIN");


        return doutorRepository.save(doutor);
    }

    @Override
    public boolean checkEmail(String email) {

        return doutorRepository.existsByEmail(email);
    }

}
