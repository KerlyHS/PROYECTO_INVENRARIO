package com.example.rest;

import java.util.HashMap;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;


import controller.dao.PersonaServices;


@Path("/persona")
public class PersonaApi {

    

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getpresona(){
        HashMap<String, Object> map = new HashMap<>();
        PersonaServices ps = new PersonaServices();
        map.put("personas", "Lista de personas");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()){
            map.put("message", "No hay personas registradas");
        }
        return Response.ok(map).build();

    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response savePersona(HashMap<String , Object> map){
        HashMap<String, Object> res = new HashMap<>();
        PersonaServices ps = new PersonaServices();

        if (map.get("nombre") == null || map.get("apellido") == null || map.get("telefono") == null || map.get("correo") == null || map.get("dni") == null || map.get("clave") == null) {
            res.put("message", "Faltan datos");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }
        try {
            ps.getPersona().setNombre(map.get("nombre").toString());
            ps.getPersona().setApellido(map.get("apellido").toString());
            ps.getPersona().setTelefono(map.get("telefono").toString());
            ps.getPersona().setCorreo(map.get("correo").toString());
            ps.getPersona().setDni(map.get("dni").toString());
            ps.getPersona().setClave(map.get("clave").toString());
            
            ps.save();
            res.put("message", "Persona registrada correctamente");
            res.put("data", "Guardado");
            res.put("token", ps.getPersona().getToken()); 
            return Response.ok(res).build();
           } catch (Exception e) {
            if (e.getMessage().contains("Correo o DNI ya existen")){
                res.put("message", "Correo o DNI ya existen");
                return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
            }           
            res.put("message", "Error al registrar persona");
            res.put("data", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
     }

    @Path("/list/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersona(@PathParam("id") Integer id){
        HashMap<String, Object> map = new HashMap<>();
        PersonaServices ps = new PersonaServices();
        try {
            map.put("persona", ps.get(id));
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("message", "Error al obtener persona");
            map.put("data", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePersona(HashMap<String, Object> map){
        HashMap<String, Object> res = new HashMap<>();
        PersonaServices ps = new PersonaServices();
        try {
            ps.getPersona().setIdPersona(Integer.parseInt(map.get("id").toString()));
            ps.getPersona().setNombre(map.get("nombre").toString());
            ps.getPersona().setApellido(map.get("apellido").toString());
            ps.getPersona().setTelefono(map.get("telefono").toString());
            ps.getPersona().setCorreo(map.get("correo").toString());
            ps.getPersona().setDni(map.get("dni").toString());
            ps.getPersona().setClave(map.get("clave").toString());
            ps.update();
            res.put("message", "Persona actualizada correctamente");
            res.put("data", "Actualizado");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("message", "Error al actualizar persona");
            res.put("data", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/iniciosesion")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response iniciar(HashMap<String, Object> map){
        HashMap<String, Object> res = new HashMap<>();
        PersonaServices ps = new PersonaServices();

        try {
            String correo = map.get("correo").toString();
            String clave = map.get("clave").toString();
            NewCookie cookie = new NewCookie("token", ps.getPersona().getToken(), "/", null, NewCookie.DEFAULT_VERSION, null, 3600, false);        
            if (ps.iniciosesion(correo, clave)) {
                res.put("msg", "Login exitoso");	
                res.put("token", ps.getPersona().getToken());
                return Response.ok(res).cookie(cookie).build();
            } else {
                res.put("msg", "Correo o clave incorrectos");
                return Response.status(Response.Status.UNAUTHORIZED).entity(res).build();
            }
        } catch (Exception e) {
            res.put("msg", "Error al iniciar sesión");
            res.put("data", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

}