package uz.imirsaburov.manage.shop.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.imirsaburov.manage.shop.dto.file.CustomResourceWrapper;
import uz.imirsaburov.manage.shop.service.FileService;

@RestController
@RequestMapping("v1/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(fileService.upload(file));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {

        CustomResourceWrapper resource = fileService.get(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(resource.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getOriginalName() + "\"")
                .body(resource.getResource());
    }

    @GetMapping("info/{id}")
    public ResponseEntity<?> getForInfo(@PathVariable Long id) {
        return ResponseEntity.ok(fileService.getForInfo(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(fileService.delete(id));
    }
}
