package GestorProductos.api.boot;

import GestorProductos.api.core.model.Producto;
import GestorProductos.api.core.service.InventarioMemoria;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleDemoRunner implements CommandLineRunner {
    @Override public void run(String... args){
        InventarioMemoria inv = new InventarioMemoria();
        inv.agregar(new Producto("Teclado",40,10));
        inv.agregar(new Producto("Mouse",25,15));
        System.out.println("POO Demo — total productos: "+inv.listar().size());
        System.out.println("Mayor stock: "+inv.mayorStock().getNombre());
    }
}