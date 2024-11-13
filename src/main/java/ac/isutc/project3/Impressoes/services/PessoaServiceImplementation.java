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
		String msg = "";
        Pessoa employee1 = loginRepo.findByEmail(loginDTO.getEmail());
        if (employee1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = employee1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Pessoa> employee = loginRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("password Not Match", false);
            }
        }else {
            return new LoginResponse("Email not exits", false);
        }	
       }

}
