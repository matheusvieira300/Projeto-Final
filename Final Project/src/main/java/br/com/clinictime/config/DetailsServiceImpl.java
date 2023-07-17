package br.com.clinictime.config;

import br.com.clinictime.model.Doutor;
import br.com.clinictime.model.Secretaria;
import br.com.clinictime.repository.DoutorRepository;
import br.com.clinictime.repository.SecretariaRepository;
import br.com.clinictime.security.ContaDoutor;
import br.com.clinictime.security.ContaSecretaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DoutorRepository doutorRepository;

    @Autowired
    private SecretariaRepository secretariaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Doutor doutor = doutorRepository.findByEmail(email);
        Secretaria secretaria = secretariaRepository.findByEmail(email);

        if (doutor != null) {
            return new ContaDoutor(doutor);
        } else if (secretaria != null) {
            return new ContaSecretaria(secretaria);
        }

        throw new UsernameNotFoundException("E-mail não encontrado.");
    }
}
