package com.example.rest;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import controller.dao.RolServices;
import models.Rol;

@Path("roles") 
public class RolApi {

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRoles() {
        HashMap<String, Object> map = new HashMap<>();
        RolServices rolServices = new RolServices();
        map.put("msg", "Ok");
        map.put("data", rolServices.listAll().toArray());

        if (rolServices.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap<String, Object> map) {
        RolServices rolServices = new RolServices();
        HashMap<String, Object> res = new HashMap<>();

        try {
            // Validamos que los datos esenciales estén presentes
            if (!map.containsKey("nombre") || !map.containsKey("descripcion") || !map.containsKey("tipo")) {
                res.put("msg", "Error");
                res.put("data", "Faltan campos requeridos: nombre, descripcion, tipo.");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
            }

            // Creamos un nuevo objeto Rol
            Rol rol = new Rol();
            rol.setNombre(map.get("nombre").toString());
            rol.setDescripcion(map.get("descripcion").toString());
            rol.setTipo(map.get("tipo").toString());

            // Guardamos el rol
            rolServices.save();

            res.put("msg", "OK");
            res.put("data", "Rol registrado correctamente");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRol(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        RolServices rolServices = new RolServices();

        try {
            Rol rol = rolServices.get(id);
            if (rol == null) {
                map.put("msg", "Error");
                map.put("data", "Rol no encontrado");
                return Response.status(Status.NOT_FOUND).entity(map).build();
            }

            map.put("msg", "Ok");
            map.put("data", rol);
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", "Error al obtener el rol: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        RolServices rolServices = new RolServices();

        try {
            // Validar que los datos esenciales estén presentes
            if (!map.containsKey("idRol") || !map.containsKey("nombre") || !map.containsKey("descripcion") || !map.containsKey("tipo")) {
                res.put("msg", "Error");
                res.put("data", "Faltan campos requeridos: idRol, nombre, descripcion, tipo.");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
            }

            // Obtener el rol por ID y actualizar sus datos
            int rolId = Integer.parseInt(map.get("idRol").toString());
            Rol rol = rolServices.get(rolId);
            if (rol == null) {
                res.put("msg", "Error");
                res.put("data", "Rol no encontrado");
                return Response.status(Status.NOT_FOUND).entity(res).build();
            }

            rol.setNombre(map.get("nombre").toString());
            rol.setDescripcion(map.get("descripcion").toString());
            rol.setTipo(map.get("tipo").toString());

            // Actualizar el rol
            rolServices.update();

            res.put("msg", "OK");
            res.put("data", "Rol actualizado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", "Error al actualizar el rol: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
