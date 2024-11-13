package ac.isutc.project3.Impressoes.models;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.io.File;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

@Table(name="impressoes")
@Entity
public class Impressao {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String downloadlink;
	private float credits;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	private Date complete;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public void complete() {
		this.complete = new Date();
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public float getCredits() {
		return this.credits;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getComplete() {
		return complete;
	}

	public void setComplete(Date complete) {
		this.complete = complete;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDownloadlink() {
		return downloadlink;
	}

	public void setDownloadlink(String downloadlink) {
		this.downloadlink = downloadlink;
	}

	public Status getStatus() {
		return status;
	}

	public void setCredits(float credits) {
		this.credits = credits;
	}
	
	
}
