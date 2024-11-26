package controller.dao;

import controller.tda.list.LinkedList;
import models.Rol;

public class RolServices {
    private RolDao obj;
    public RolServices() {
        obj = new RolDao();
    }
    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public LinkedList<Rol> listAll() {
        return obj.getListAll();
    }

    public Rol getRol()  {
        return obj.getRol();
    }
    
    public void setPersona(Rol rol) {
        obj.setRol(rol);
    }


    public Rol get(Integer id) throws Exception {
        return obj.get(id);
    }
}