package ac.isutc.project3.Impressoes.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ac.isutc.project3.Impressoes.configs.FileStorageProperties;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/files")
public class FileStorageController {
	private final Path fileStorageLocation;
	
	public FileStorageController(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			Path targetLocation = fileStorageLocation.resolve(filename);
			file.transferTo(targetLocation);
			System.out.println(targetLocation);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/files/download/").path(filename).toUriString();
			
			return ResponseEntity.ok("Upload completed: Download link: "+ fileDownloadUri);
		} catch (IOException e) {
			return ResponseEntity.badRequest().build();
		} catch (IllegalStateException e) {
			return ResponseEntity.badRequest().build();			
		}
	}
	
	@GetMapping("/download/{filename:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpServletRequest request) {
		Path filePath = fileStorageLocation.resolve(filename).normalize();
		
		try {
			Resource resource = new UrlResource(filePath.toUri());
			String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			
			if (contentType == null) {
				contentType = "applicaton/octet-stream";
			}
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\""+ resource.getFilename()+"\"").body(resource);
			
		} catch (MalformedURLException e) {
			return ResponseEntity.badRequest().build();
		} catch (IOException ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<String>> listFiles() throws IOException {
		List<String> filenames = Files.list(fileStorageLocation)
				.map(Path::getFileName)
				.map(Path::toString)
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(filenames);
	}
	
	
}
