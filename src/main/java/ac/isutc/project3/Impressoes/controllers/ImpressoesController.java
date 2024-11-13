package ac.isutc.project3.Impressoes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ac.isutc.project3.Impressoes.models.Impressao;
import ac.isutc.project3.Impressoes.models.Pessoa;
import ac.isutc.project3.Impressoes.models.Status;
import ac.isutc.project3.Impressoes.services.BalcaoService;

@RestController
@RequestMapping("/api")
public class ImpressoesController {
	
	@Autowired
	private BalcaoService balcaoService;
	
	@GetMapping("/prints")
	public ResponseEntity<List<Impressao>> getAll() {
		return ResponseEntity.ok(balcaoService.getImpressoes());
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Pessoa>> getPessoas(){
		return ResponseEntity.ok(balcaoService.getPessoas());
	}
	
	@GetMapping("/prints/{printId}")
	public ResponseEntity<Impressao> get(@PathVariable int printId) {
		return ResponseEntity.ok(balcaoService.get(printId));
	}
	
	@PostMapping("/users/{userId}")
	public ResponseEntity<Pessoa> addCredit(@PathVariable int userId, @RequestParam float value) {
		return ResponseEntity.ok(balcaoService.adicionarCredito(userId, value));
	}
	
	@PostMapping("/prints/{printId}")
	public ResponseEntity<Impressao> updateState(@RequestParam Status status, @PathVariable int printId) {
		return ResponseEntity.ok(balcaoService.actualizarEstadoDaImpressao(printId, status));
	}
	
	
}
