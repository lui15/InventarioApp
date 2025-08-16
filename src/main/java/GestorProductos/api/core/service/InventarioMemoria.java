package GestorProductos.api.core.service;


import GestorProductos.api.core.model.Producto;

import java.util.*;

public class InventarioMemoria {
    private final List<Producto> productos = new ArrayList<>();

    public void agregar(Producto p){
        if (p.getPrecio()<0 || p.getCantidad()<0) throw new IllegalArgumentException("Precio/cantidad inválidos");
        productos.add(p);
    }
    public List<Producto> listar(){ return Collections.unmodifiableList(productos); }
    public List<Producto> buscarPorNombre(String nombre){
        String q = nombre.toLowerCase();
        List<Producto> res = new ArrayList<>();
        for (Producto p: productos) if (p.getNombre().toLowerCase().contains(q)) res.add(p);
        return res;
    }
    public Producto mayorStock(){
        return productos.stream().max(Comparator.comparingInt(Producto::getCantidad))
                .orElseThrow(() -> new NoSuchElementException("Inventario vacío"));
    }
}