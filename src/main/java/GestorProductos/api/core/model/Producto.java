package GestorProductos.api.core.model;

public class Producto {
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre; this.precio = precio; this.cantidad = cantidad;
    }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public void setNombre(String n) { this.nombre = n; }
    public void setPrecio(double p) { this.precio = p; }
    public void setCantidad(int c) { this.cantidad = c; }
    public void mostrarInfo() {
        System.out.println("Producto: "+nombre+", Precio: "+precio+", Cantidad: "+cantidad);
    }
}