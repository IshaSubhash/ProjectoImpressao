package ac.isutc.project3.Impressoes.models;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.File;
import java.sql.Date;

@Data
@Entity
public class Impressao {
	private Date created;
	private Date complete;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private File file;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	

}
