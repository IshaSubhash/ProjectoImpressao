package ac.isutc.project3.Impressoes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ac.isutc.project3.Impressoes.models.Impressao;
import ac.isutc.project3.Impressoes.models.Pessoa;
import jakarta.persistence.EntityManager;

@Repository
public class ImpressaoDAOImplementation implements ImpressaoDAO {
	
	@Autowired
	private EntityManager entityManger;

	@Override
	public Impressao get(int id) {
		Session currentSession = entityManger.unwrap(Session.class);
		Impressao impressao = currentSession.get(Impressao.class, id);
		return impressao;
	}

	@Override
	public void save(Impressao impressao) {
		Session currentSession = entityManger.unwrap(Session.class);
		currentSession.persist(impressao);
	}

	@Override
	public void delete(int id) {
		Session currentSession = entityManger.unwrap(Session.class);
		Impressao impressao = currentSession.get(Impressao.class, id);
		currentSession.persist(impressao);

	}

	@Override
	public void update(Impressao impressao) {
		Session currentSession = entityManger.unwrap(Session.class);
		currentSession.merge(impressao);
	}

	@Override
	public List<Impressao> get() {
		Session currentSession = entityManger.unwrap(Session.class);
		Query<Impressao> query = currentSession.createQuery("from Impressao", Impressao.class);
		List<Impressao> impressoes = query.getResultList();
		return impressoes;
	}

}
