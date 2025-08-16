package GestorProductos.api.web;

import GestorProductos.api.dto.ProductoRequest;
import GestorProductos.api.dto.ProductoResponse;
import GestorProductos.api.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    // Listar (filtro opcional: ?nombre=abc)
    @GetMapping
    public List<ProductoResponse> listar(@RequestParam(required = false) String nombre) {
        return service.listar(nombre);
    }

    // Obtener por id
    @GetMapping("/{id}")
    public ProductoResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    // Crear
    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest req) {
        ProductoResponse creado = service.crear(req);
        return ResponseEntity.created(URI.create("/api/productos/" + creado.id())).body(creado);
    }

    // Actualizar
    @PutMapping("/{id}")
    public ProductoResponse actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest req) {
        return service.actualizar(id, req);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Utilidad: producto con mayor stock
    @GetMapping("/mayor-stock")
    public ProductoResponse mayorStock() {
        return service.mayorStock();
    }
}
