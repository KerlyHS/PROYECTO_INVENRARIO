package controller.dao;

import controller.dao.PersonaDao;
import controller.tda.list.LinkedList;
import controller.tda.list.ListEmptyException;
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


    public Boolean iniciosesion(String correo, String clave) throws ListEmptyException{
        return obj.iniciosesion(correo, clave);
    }
}