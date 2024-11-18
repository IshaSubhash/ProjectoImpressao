package ac.isutc.project3.Impressoes.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import ac.isutc.project3.Impressoes.dao.ImpressaoDAO;
import ac.isutc.project3.Impressoes.dao.LoginRepo;
import ac.isutc.project3.Impressoes.dao.PessoaDAO;
import ac.isutc.project3.Impressoes.models.Impressao;
import ac.isutc.project3.Impressoes.models.LoginDTO;
import ac.isutc.project3.Impressoes.models.LoginResponse;
import ac.isutc.project3.Impressoes.models.Pessoa;
import ac.isutc.project3.Impressoes.models.Status;
import jakarta.transaction.Transactional;

@Service
public class PessoaServiceImplementation implements PessoaService {
	
	@Autowired
	private PessoaDAO pessoaDAO;
	
	@Autowired
	private ImpressaoDAO impressaoDAO;
	
	@Autowired
	private LoginRepo loginRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	@Transactional
	public Pessoa get(int id) {
		return pessoaDAO.get(id);
	}

	@Override
	@Transactional
	public void save(Pessoa pessoa) {
		pessoa.setPassword(passwordEncoder.encode(pessoa.getPassword()));
		pessoaDAO.save(pessoa);
	}

	@Override
	@Transactional
	public Impressao adicionarImpressao(float credits, String downloadUri, Pessoa pessoa) {
		if (pessoa.cobrar(credits)) {
			Impressao impressao = new Impressao();
			impressao.setCredits(credits);
			impressao.setDownloadlink(downloadUri);
			impressao.setStatus(Status.CREATED);
			impressaoDAO.save(impressao);
			pessoa.addImpressao(impressao);
			pessoaDAO.save(pessoa);
			return impressao;
		}
		return null;
	}

	@Override
	public LoginResponse loginEmployee(LoginDTO loginDTO) {
        Pessoa pessoa = loginRepo.findByEmail(loginDTO.getEmail());
        if (pessoa != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = pessoa.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Pessoa> pessoaCheck = loginRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (pessoaCheck.isPresent()) {
                    return new LoginResponse("Login Success", true, pessoa.getId());
                } else {
                    return new LoginResponse("Login Failed", false, -1);
                }
            } else {
                return new LoginResponse("password Not Match", false, -1);
            }
        }else {
            return new LoginResponse("Email not exits", false, -1);
        }	
       }

}
