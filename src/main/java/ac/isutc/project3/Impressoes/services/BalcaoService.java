package ac.isutc.project3.Impressoes.services;

import java.util.List;

import ac.isutc.project3.Impressoes.models.Impressao;
import ac.isutc.project3.Impressoes.models.Pessoa;
import ac.isutc.project3.Impressoes.models.Status;

public interface BalcaoService {
	List<Pessoa> getPessoas();
	List<Impressao> getImpressoes();
	Impressao actualizarEstadoDaImpressao(int id, Status estado);
	Pessoa adicionarCredito(int id, float credito);
	Impressao get(int id);
}
