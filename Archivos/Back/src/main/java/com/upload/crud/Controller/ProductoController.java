package com.upload.crud.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.upload.crud.DTO.ResponseMessage;
import com.upload.crud.Entity.Producto;
import com.upload.crud.Service.ProductoService;
import com.upload.crud.DTO.ResponseProducto;

@RestController
@CrossOrigin("*")
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("cantidad") Integer cantidad,
            @RequestParam("valor") Double valor) {

        try {
            validarExtension(file.getOriginalFilename());
            productoService.save(file, nombre, descripcion, file.getOriginalFilename(),
                    cantidad, valor);
            return new ResponseEntity<>(new ResponseMessage("Producto creado de forma satisfactoriamente"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseMessage> updateFile(
            @PathVariable("id") String id,
            @RequestParam("file") MultipartFile file,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("cantidad") Integer cantidad,
            @RequestParam("valor") Double valor) {
        try {
            System.out.println(file.getOriginalFilename()+"--");
            validarExtension(file.getOriginalFilename());
            productoService.update(id, file, nombre, descripcion, file.getOriginalFilename(), cantidad, valor);
            return new ResponseEntity<>(new ResponseMessage("Producto actualizado de forma satisfactoriamente"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseProducto>> getListFiles() {
        List<ResponseProducto> files = productoService.findAll().map(producto -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/producto/download/")
                    .path(producto.getId())
                    .toUriString();
            return new ResponseProducto(producto.getId(), producto.getNombre(), producto.getDescripcion(),
                    fileDownloadUri, producto.getCantidad(), producto.getValor());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {

        Producto producto = productoService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + producto.getFileName() + "\"")
                .body(producto.getData());
    }

    @GetMapping("/list/{id}")
    public Producto listFile(@PathVariable String id) {
        return productoService.getFile(id);        
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseMessage deleteFile(@PathVariable String id) {
        return productoService.delete(id);
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
