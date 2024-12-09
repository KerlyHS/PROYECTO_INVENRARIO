package controller.dao;

import models.Lote;

import controller.dao.implement.AdapterDao;
import controller.tda.list.LinkedList;

public class LoteDao extends AdapterDao<Lote> {
    private Lote lote = new Lote();
    private LinkedList<Lote> listAll;

    public LoteDao() {
        super(Lote.class);
    }

    public Lote getLote() { // Obtiene la lote
        if (lote == null) {
            lote = new Lote(); // En caso de que la lote sea nula, crea una nueva instancia de Lote
        }
        return this.lote; // Devuelve la lote
    }

    public void setLote(Lote lote) { // Establece la lote con un objeto Lote
        this.lote = lote; // Asigna el objeto Lote a la variable lote
    }

    public LinkedList<Lote> getlistAll() { // Obtiene la lista de objetos
        if (listAll == null) { // Si la lista es nula
            this.listAll = listAll(); // Invoca el método listAll() para obtener la lista de objetos
        }
        return listAll; // Devuelve la lista de objetos de la variable listAll
    }

    public Boolean save() throws Exception { // Guarda la variable lote en la lista de objetos
        Integer id = getlistAll().getSize() + 1; // Obtiene el tamaño de la lista y le suma 1 para asignar un nuevo id
        lote.setId(id); // Asigna el id a lote
        this.persist(this.lote); // Guarda la lote en la lista de objetos LinkedList y en el archivo JSON
        this.listAll = listAll(); // Actualiza la lista de objetos
        return true; // Retorna verdadero si se guardó correctamente
    }

    public Boolean update() throws Exception { // Actualiza el nodo Lote en la lista de objetos
        this.merge(getLote(), getLote().getId() - 1); // Envia la lote a actualizar con su index
        this.listAll = listAll(); // Actualiza la lista de objetos
        return true;
    }

    public Boolean delete(int index) throws Exception { // Elimina un objeto Lote por su índice
        this.supreme(index);
        this.listAll = listAll(); // Actualiza la lista de objetos
        return true; // Retorna verdadero si se eliminó correctamente
    }

}
