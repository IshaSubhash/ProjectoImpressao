package ac.isutc.project3.Impressoes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ac.isutc.project3.Impressoes.models.Pessoa;
import jakarta.persistence.EntityManager;


@Repository
public class PessoaDAOImplementation implements PessoaDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Pessoa> get() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Pessoa> query = currentSession.createQuery("from Pessoa", Pessoa.class);
		List<Pessoa> pessoas = query.getResultList();
		return pessoas;
	}

	@Override
	public Pessoa get(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Pessoa pessoa = currentSession.get(Pessoa.class, id);
		return pessoa;
	}

	@Override
	public void save(Pessoa pessoa) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(pessoa);
		
	}

	@Override
	public void delete(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Pessoa pessoa = currentSession.get(Pessoa.class, id);
		currentSession.remove(pessoa);
	}

	@Override
	public void update(Pessoa pessoa) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.merge(pessoa);
	}

}
