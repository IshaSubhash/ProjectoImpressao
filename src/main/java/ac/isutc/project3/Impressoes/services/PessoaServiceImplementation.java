package ac.isutc.project3.Impressoes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.isutc.project3.Impressoes.dao.ImpressaoDAO;
import ac.isutc.project3.Impressoes.dao.PessoaDAO;
import ac.isutc.project3.Impressoes.models.Impressao;
import ac.isutc.project3.Impressoes.models.Pessoa;
import ac.isutc.project3.Impressoes.models.Status;
import jakarta.transaction.Transactional;

@Service
public class PessoaServiceImplementation implements PessoaService {
	
	@Autowired
	private PessoaDAO pessoaDAO;
	
	@Autowired
	private ImpressaoDAO impressaoDAO;
	

	@Override
	@Transactional
	public Pessoa get(int id) {
		return pessoaDAO.get(id);
	}

	@Override
	@Transactional
	public void save(Pessoa pessoa) {
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

}
