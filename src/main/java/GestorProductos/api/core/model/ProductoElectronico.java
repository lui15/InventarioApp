package GestorProductos.api.core.model;

import GestorProductos.api.core.model.Producto;

public class ProductoElectronico extends Producto {
    private String marca; private String modelo;
    public ProductoElectronico(String nombre,double precio,int cantidad,String marca,String modelo){
        super(nombre,precio,cantidad); this.marca=marca; this.modelo=modelo;
    }
    @Override public void mostrarInfo(){
        System.out.println("Producto Electrónico: "+getNombre()+", Marca: "+marca+", Modelo: "+modelo);
    }
}