package ac.isutc.project3.Impressoes.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import ac.isutc.project3.Impressoes.models.Pessoa;

@EnableJpaRepositories
@Repository
public interface LoginRepo extends JpaRepository<Pessoa, Integer>{
	 Optional<Pessoa> findOneByEmailAndPassword(String email, String password);
	    Pessoa findByEmail(String email);
	

}
