package ac.isutc.project3.Impressoes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.isutc.project3.Impressoes.dao.ImpressaoDAO;
import ac.isutc.project3.Impressoes.dao.PessoaDAO;
import ac.isutc.project3.Impressoes.models.Impressao;
import ac.isutc.project3.Impressoes.models.Pessoa;
import ac.isutc.project3.Impressoes.models.Status;
import jakarta.transaction.Transactional;

@Service
public class BalcaoServiceImplementation implements BalcaoService {
	
	@Autowired
	private PessoaDAO pessoaDAO;
		
	@Autowired
	private ImpressaoDAO impressaoDAO;
	
	
	@Override
	@Transactional
	public List<Pessoa> getPessoas() {
		return pessoaDAO.get();
	}

	@Override
	@Transactional
	public List<Impressao> getImpressoes() {
		return impressaoDAO.get();
	}

	@Override
	@Transactional
	public Impressao actualizarEstadoDaImpressao(int id, Status estado) {
		Impressao impressao = impressaoDAO.get(id);
		
		if (impressao.getStatus() == Status.DONE) {
			return impressao;
		}
		
		impressao.setStatus(estado);
		impressaoDAO.update(impressao);
		
		if (estado == Status.DONE) {
			impressao.complete();
		}
		
		return impressao;
	}

	@Override
	@Transactional
	public Pessoa adicionarCredito(int id, float credito) {
		Pessoa pessoa = pessoaDAO.get(id);
		pessoa.addCredito(credito);
		pessoaDAO.update(pessoa);
		return pessoa;
	}

	@Override
	public Impressao get(int id) {
		return impressaoDAO.get(id);
	}
	
	
}
