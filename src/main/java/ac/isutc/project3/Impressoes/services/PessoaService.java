package ac.isutc.project3.Impressoes.services;


import ac.isutc.project3.Impressoes.models.Impressao;
import ac.isutc.project3.Impressoes.models.Pessoa;

public interface PessoaService {
	Pessoa get(int id);
	void save(Pessoa pessoa);
	Impressao adicionarImpressao(float credit, String downloadUri, Pessoa pessoa);
}
