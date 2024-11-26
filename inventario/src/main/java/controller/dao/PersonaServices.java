package controller.dao;

import controller.tda.list.LinkedList;
import models.Persona;

public class PersonaServices {
    private PersonaDao obj;
    public PersonaServices() {
        obj = new PersonaDao();
    }
    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public LinkedList<Persona> listAll() {
        return obj.getListAll();
    }

    public Persona getPersona()  {
        return obj.getPersona();
    }
    
    public void setPersona(Persona persona) {
        obj.setPersona(persona);
    }


    public Persona get(Integer id) throws Exception {
        return obj.get(id);
    }
}