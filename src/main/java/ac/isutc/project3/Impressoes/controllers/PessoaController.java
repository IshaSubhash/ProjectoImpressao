package ac.isutc.project3.Impressoes.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ac.isutc.project3.Impressoes.configs.FileStorageProperties;
import ac.isutc.project3.Impressoes.models.Impressao;
import ac.isutc.project3.Impressoes.models.LoginDTO;
import ac.isutc.project3.Impressoes.models.LoginResponse;
import ac.isutc.project3.Impressoes.models.Pessoa;
import ac.isutc.project3.Impressoes.services.PessoaService;

@RestController
@RequestMapping("/api/users")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	private final Path fileStorageLocation;
	
	public PessoaController(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Pessoa> get(@PathVariable int userId) {
		return ResponseEntity.ok(pessoaService.get(userId));
	}
	
	@PostMapping("")
	public ResponseEntity<Pessoa> post(@RequestBody Pessoa pessoa){
		pessoaService.save(pessoa);
		return ResponseEntity.ok(pessoa);
	}
	
	@PostMapping("/{userId}/add-print")
	public ResponseEntity<Impressao> addImpressao(@RequestParam("credits") float credits, @PathVariable int userId, @RequestParam("file") MultipartFile file){
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			Path targetLocation = fileStorageLocation.resolve(filename);
			file.transferTo(targetLocation);
			System.out.println(targetLocation);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/files/download/").path(filename).toUriString();
			Pessoa pessoa = pessoaService.get(userId);
			var impressao = pessoaService.adicionarImpressao(credits, fileDownloadUri, pessoa);
			return ResponseEntity.ok(impressao);
		} catch (IOException e) {
			return ResponseEntity.badRequest().build();
		} catch (IllegalStateException e) {
			return ResponseEntity.badRequest().build();			
		}
		
		
	}
	
	@PostMapping("/login")
    public ResponseEntity<LoginResponse> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = pessoaService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
}
