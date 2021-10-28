package co.com.eww.trabajowebwar.controller;

import co.com.eej.trabajoejbjar.Entity.Artista;
import co.com.eej.trabajoejbjar.Service.IArtistaService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/artistas")
public class ArtistaController {
    
    @EJB
    private IArtistaService service;
    
    //Servicio para registrar Artistas
    @POST
    @Path("/guardar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertarEstudiante(@Valid Artista artista) {
        service.guardarArtista(artista);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarEstudiantes(){
        List <Artista> listaArtista = service.listarArtistas();
        return Response.status(Response.Status.OK).entity(listaArtista).build();    
    }
    
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerEstudiante(@PathParam("id") Integer cedula ){
        Artista artista = service.obtenerArtista(cedula);
        return Response.status(Response.Status.OK).entity(artista).build();
    }
    
    
}
