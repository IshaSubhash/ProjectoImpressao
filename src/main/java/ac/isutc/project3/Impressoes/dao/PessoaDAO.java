package ac.isutc.project3.Impressoes.dao;

import java.util.List;

import ac.isutc.project3.Impressoes.models.Pessoa;

public interface PessoaDAO {
	List<Pessoa> get();
	Pessoa get(int id);
	void save(Pessoa pessoa);
	void delete(int id);
	void update(Pessoa pessoa);
}
