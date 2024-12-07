package controller.dao;

import models.Producto;

import controller.dao.implement.AdapterDao;
import controller.tda.list.LinkedList;

public class ProductoDao extends AdapterDao<Producto> {
    private Producto producto = new Producto();
    private LinkedList<Producto> listAll;

    public ProductoDao() {
        super(Producto.class);
    }

    public Producto getProducto() { // Obtiene la producto
        if (producto == null) {
            producto = new Producto(); // En caso de que la producto sea nula, crea una nueva instancia de Producto
        }
        return this.producto; // Devuelve la producto
    }

    public void setProducto(Producto producto) { // Establece la producto con un objeto Producto
        this.producto = producto; // Asigna el objeto Producto a la variable producto
    }

    public LinkedList getlistAll() { // Obtiene la lista de objetos
        if (listAll == null) { // Si la lista es nula
            this.listAll = listAll(); // Invoca el método listAll() para obtener la lista de objetos
        }
        return listAll; // Devuelve la lista de objetos de la variable listAll
    }

    public Boolean save() throws Exception { // Guarda la variable producto en la lista de objetos
        Integer id = getlistAll().getSize() + 1; // Obtiene el tamaño de la lista y le suma 1 para asignar un nuevo id
        producto.setId(id); // Asigna el id a producto
        this.persist(this.producto); // Guarda la producto en la lista de objetos LinkedList y en el archivo JSON
        this.listAll = listAll(); // Actualiza la lista de objetos
        return true; // Retorna verdadero si se guardó correctamente
    }

    public Boolean update() throws Exception { // Actualiza el nodo Producto en la lista de objetos
        this.merge(getProducto(), getProducto().getId() - 1); // Envia la producto a actualizar con su index
        this.listAll = listAll(); // Actualiza la lista de objetos
        return true;
    }

    public Boolean delete(int index) throws Exception { // Elimina un objeto Producto por su índice
        this.supreme(index);
        this.listAll = listAll(); // Actualiza la lista de objetos
        return true; // Retorna verdadero si se eliminó correctamente
    }

}
