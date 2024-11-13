package ac.isutc.project3.Impressoes.dao;

import java.util.List;

import ac.isutc.project3.Impressoes.models.Impressao;

public interface ImpressaoDAO {
	List<Impressao> get();
	Impressao get(int id);
	void save (Impressao impressao);
	void delete (int id);
	void update (Impressao impressao);
}
