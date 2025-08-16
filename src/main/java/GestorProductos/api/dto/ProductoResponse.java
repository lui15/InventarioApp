package GestorProductos.api.dto;

import GestorProductos.api.domain.ProductoEntity;

import java.math.BigDecimal;

public record ProductoResponse(
        Long id,
        String nombre,
        BigDecimal precio,
        Integer cantidad
) {
    public static ProductoResponse from(ProductoEntity p) {
        return new ProductoResponse(p.getId(), p.getNombre(), p.getPrecio(), p.getCantidad());
    }
}
