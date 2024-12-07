package controller.dao;


import controller.dao.implement.AdapterDao;
import controller.tda.list.LinkedList;
import controller.tda.list.ListEmptyException;
import models.Persona;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;
import controller.dao.RolDao;
import controller.dao.TokenUtil;
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
        if (!isUnique(getPersona().getCorreo(), getPersona().getDni())) {
            throw new Exception("Correo o DNI ya existen.");
        }
        Integer id = getListAll().getSize() + 1;
        getPersona().setIdPersona(id);
        String encryclave = getPersona().getClave();
        getPersona().setClave(encryclave(encryclave));
        String token = TokenUtil.generateToken(getPersona().getIdPersona(), getPersona().getCorreo());
        getPersona().setToken(token);
        persist(getPersona());
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getPersona(), getPersona().getIdPersona() - 1);
        this.listAll = listAll();
        return true;
    }

    //login
    
    public Boolean iniciosesion(String correo, String clave)  throws ListEmptyException {
        LinkedList<Persona> list = listAll();
        Persona[] personas = list.toArray();
    
        for (Persona persona : personas) {
            if (persona.getCorreo().equals(correo)) {
                if (BCrypt.checkpw(clave, persona.getClave())) {
                    // Generar el token
                    String token = TokenUtil.generateToken(persona.getIdPersona(), persona.getCorreo());
                    persona.setToken(token);
    
                    // Encuentra el índice de la persona y actualízala en la lista
                    for (int i = 0; i < list.getSize(); i++) {
                        if (list.get(i).getIdPersona().equals(persona.getIdPersona())) {
                            list.update(persona, i);  // Actualiza la persona en el índice correcto
                            break;
                        }
                    }

                    setPersona(persona);
                    return true;  // Login exitoso
                }
            }
        }
    
        return false;  // Login fallido
    }

    public static String encryclave(String clave) {
        return BCrypt.hashpw(clave, BCrypt.gensalt());
    }
    
    public Boolean isUnique(String correo, String dni) throws ListEmptyException {
        LinkedList<Persona> list = listAll();
        Persona[] personas = list.toArray();

        for (Persona persona : personas) {
            if (persona.getCorreo().equals(correo) || persona.getDni().equals(dni)) {
                return false;  // Correo o DNI no son únicos
            }
        }

        return true;  // Correo y DNI son únicos
    }



   
    
}
