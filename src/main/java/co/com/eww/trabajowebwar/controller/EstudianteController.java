package co.com.eww.trabajowebwar.controller;

import co.com.eej.trabajoejbjar.Entity.Estudiante;
import co.com.eej.trabajoejbjar.Exception.BadRequestEx;
import co.com.eej.trabajoejbjar.Exception.ConflictEx;
import co.com.eej.trabajoejbjar.Exception.NoContentEx;
import co.com.eej.trabajoejbjar.Exception.NotFoundEx;
import co.com.eej.trabajoejbjar.Service.IEstudianteService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/estudiantes")

public class EstudianteController {
    
    @EJB
    private IEstudianteService service;
    
    @GET
    @Path("/obtener")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtener() throws NoContentEx {
          List<Estudiante> listaEstudiante = service.listar();
          if(listaEstudiante.isEmpty() == true){
              return Response.status(Response.Status.NO_CONTENT).build();
          }
          return Response.status(Response.Status.OK).entity(listaEstudiante).build();
    }       
    
    @GET
    @Path("/obtenerPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws NotFoundEx{
          Estudiante estudiante = service.listarPorId(id);
          return Response.status(Response.Status.OK).entity(estudiante).build();
    }     
    
    @POST
    @Path("/guardar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) 
    public Response guardar(Estudiante estudiante) throws ConflictEx, BadRequestEx {
        this.service.guardar(estudiante);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    @Path("/modificar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) 
    public Response editar(@Valid Estudiante estudiante) throws NotFoundEx, ConflictEx, BadRequestEx{
        this.service.editar(estudiante);
        return Response.status(Response.Status.OK).build();
    }   
    
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws NotFoundEx {
          service.eliminar(id);
          return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @DELETE
    @Path("/eliminar2/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar2(@PathParam("id") Integer id) throws NotFoundEx {
          service.eliminar2(id);
          return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    

}

