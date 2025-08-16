package GestorProductos.api.service;

import GestorProductos.api.domain.ProductoEntity;
import GestorProductos.api.dto.ProductoRequest;
import GestorProductos.api.dto.ProductoResponse;
import GestorProductos.api.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<ProductoResponse> listar(String filtroNombre) {
        List<ProductoEntity> productos = (filtroNombre == null || filtroNombre.isBlank())
                ? repo.findAll()
                : repo.findByNombreContainingIgnoreCase(filtroNombre);
        return productos.stream().map(ProductoResponse::from).toList();
    }

    public ProductoResponse obtener(Long id) {
        ProductoEntity p = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        return ProductoResponse.from(p);
    }

    public ProductoResponse crear(ProductoRequest req) {
        validarNegocio(req.precio(), req.cantidad());
        ProductoEntity p = new ProductoEntity(req.nombre(), req.precio(), req.cantidad());
        return ProductoResponse.from(repo.save(p));
    }

    public ProductoResponse actualizar(Long id, ProductoRequest req) {
        validarNegocio(req.precio(), req.cantidad());
        ProductoEntity actual = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        actual.setNombre(req.nombre());
        actual.setPrecio(req.precio());
        actual.setCantidad(req.cantidad());
        return ProductoResponse.from(repo.save(actual));
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Producto no encontrado");
        repo.deleteById(id);
    }

    public ProductoResponse mayorStock() {
        ProductoEntity p = repo.findAll().stream()
                .max(Comparator.comparing(ProductoEntity::getCantidad))
                .orElseThrow(() -> new NotFoundException("No hay productos"));
        return ProductoResponse.from(p);
    }

    private void validarNegocio(BigDecimal precio, Integer cantidad) {
        if (precio != null && precio.signum() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (cantidad != null && cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
    }
}
