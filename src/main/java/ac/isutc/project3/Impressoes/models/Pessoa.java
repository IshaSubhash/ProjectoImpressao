package ac.isutc.project3.Impressoes.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "pessoas")
@Entity
public class Pessoa {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String password;
	
	@Column
	private String email;
	
	@Column
	private double credits;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
	private List<Impressao> impressoes;
	
	public void addImpressao(Impressao impressao) {
		impressoes.add(impressao);
	}
	
	public void addCredito(float credito) {
		this.credits += credito;
	}
	
	public boolean cobrar(float credits) {
		if (this.credits > credits) {
			this.credits -= credits;
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}

	public List<Impressao> getImpressoes() {
		return impressoes;
	}

	public void setImpressoes(List<Impressao> impressoes) {
		this.impressoes = impressoes;
	}
	
	
}
