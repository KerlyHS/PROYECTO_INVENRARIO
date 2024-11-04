package controller.dao;


import controller.tda.list.LinkedList;
import controller.dao.implement.AdapterDao;
import models.Persona;
public class PersonaDao extends AdapterDao<Persona> {
    private Persona persona;
    private LinkedList<Persona> listAll;
    public PersonaDao() {
        super(Persona.class);
    }

    public Persona getPersona() {
        if(persona == null) {
            persona = new Persona();
        }
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public LinkedList<Persona> getListAll() {
        if (this.listAll == null) {
            this.listAll = listAll();
            
        }
        return this.listAll();
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        getPersona().setIdPersona(id);
        persist(getPersona());
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getPersona(), getPersona().getIdPersona() - 1);
        this.listAll = listAll();
        return true;
    }


   
    
}
