package GestorProductos.api.repository;

import GestorProductos.api.domain.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    List<ProductoEntity> findByNombreContainingIgnoreCase(String nombre);
}
