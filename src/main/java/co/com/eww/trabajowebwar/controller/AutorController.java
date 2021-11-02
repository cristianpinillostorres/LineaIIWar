package co.com.eww.trabajowebwar.controller;

import co.com.eej.trabajoejbjar.Entity.Autor;
import co.com.eej.trabajoejbjar.Service.IAutorService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/autores")
public class AutorController {
    
    @EJB
    private IAutorService service;
    
    @GET
    @Path("/obtener")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtener() {
          List<Autor> listAutores = service.listar();
          return Response.status(Response.Status.OK).entity(listAutores).build();
    }    
    
}