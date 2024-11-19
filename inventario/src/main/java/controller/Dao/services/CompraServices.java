package controller.Dao.services;


import controller.Dao.CompraDao;
import controller.tda.list.LinkedList;
import controller.tda.models.Compra;

public class CompraServices {

    private CompraDao obj;

    public CompraServices(){
        obj = new CompraDao();
    }

    public Boolean save() throws Exception{
        return obj.save();
    }


    public LinkedList listAll() {
        return obj.getListAll();
    }

    public Compra getCompra(){
        return obj.getCompra();
    }

    public void setCompra(Compra compra){
        obj.setCompra(compra);
    }
}
