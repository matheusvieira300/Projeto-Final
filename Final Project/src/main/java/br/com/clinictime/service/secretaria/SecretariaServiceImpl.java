package br.com.clinictime.service.secretaria;


import br.com.clinictime.model.Secretaria;
import br.com.clinictime.repository.SecretariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SecretariaServiceImpl implements SecretariaService{

        @Autowired
        private SecretariaRepository secretariaRepository;

        @Autowired
        private BCryptPasswordEncoder passwordEncode;

        @Override
        public Secretaria createUser(Secretaria secretaria) {

            secretaria.setPassword(passwordEncode.encode(secretaria.getPassword()));
            secretaria.setRole("ROLE_USER");


            return secretariaRepository.save(secretaria);
        }

        @Override
        public boolean checkEmail(String email, Secretaria secretaria) {
            // Implementação para verificar se o e-mail já existe para outra secretária
            // excluindo a secretária sendo editada
            Secretaria existingSecretaria = secretariaRepository.findByEmail(email);
            return existingSecretaria != null && !existingSecretaria.equals(secretaria);
        }

    @Override
    public Secretaria getSecretariaById(Long id) {
        // Implementação para obter uma secretária pelo ID no banco de dados
        return secretariaRepository.findById(id).orElse(null);
    }
}


