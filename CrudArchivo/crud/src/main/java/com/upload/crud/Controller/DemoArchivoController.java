package com.upload.crud.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.upload.crud.Entity.DemoArchivo;
import com.upload.crud.Entity.DemoArchivoResponse;
import com.upload.crud.Repository.DemoArchivoRepository;
import com.upload.crud.Service.DemoArchivoService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class DemoArchivoController {
    @Autowired
    DemoArchivoService demoArchivoService;

    @PostMapping("/create")
    public DemoArchivo save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("cantidad") Integer cantidad,
            @RequestParam("valor") Double valor) {
        try {
            validarExtension(file.getOriginalFilename());
            return demoArchivoService
                    .save(new DemoArchivo(nombre, descripcion, file.getOriginalFilename(), cantidad, valor,
                            file.getBytes()));
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DemoArchivoResponse>> getListFiles() {
        List<DemoArchivoResponse> files = demoArchivoService.findAll().map(producto -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/download/")
                    .path(producto.getId())
                    .toUriString();
            return new DemoArchivoResponse(producto.getId(), producto.getNombre(), producto.getDescripcion(),
                    producto.getFileName(),
                    producto.getCantidad(), producto.getValor(), fileDownloadUri);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable("id") String id) {
        DemoArchivo demoArchivo = demoArchivoService.findById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + demoArchivo.getFileName() + "\"")
                .body(demoArchivo.getData());
    }

    private void validarExtension(String fileName){
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        switch (extension) {
            case "jpg":
            case "jpeg":
            case "gif":
            case "png":
                break;
            default:
            throw new RuntimeException("Extensión no permitida");
                
        }
    }
}
