package controller.Dao;
import controller.Dao.implement.AdapterDao;
import controller.tda.list.LinkedList;
import controller.tda.models.Compra;


public class CompraDao extends AdapterDao<Compra> {
    private Compra codigo = new Compra();
    private LinkedList<Compra> listAll;

    public CompraDao() {
        super(Compra.class);
    }

    public Compra getCompra() {
        if (codigo == null) {
            codigo = new Compra();
        }
        return this.compra;
    }

    public void setCodigo(Compra compra) {
        this.compra = compra;
    }

    public LinkedList<Compra> getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    // Solo persiste el código, sin validación aquí
    public Boolean save() throws Exception {
        this.persist(this.compra);  // Persistir el objeto
        this.listAll = listAll();   // Recargar la lista de códigos
        return true;
    }
}
