package co.com.eww.trabajowebwar.exception;

import co.com.eej.trabajoejbjar.Exception.BadRequestEx;
import co.com.eej.trabajoejbjar.Exception.ConflictEx;
import co.com.eej.trabajoejbjar.Exception.NotFoundEx;
import javax.ejb.TransactionRolledbackLocalException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception ex) {
        ex.printStackTrace();
        
        ExceptionWrapper wrraper;

        if (ex instanceof NotSupportedException) {
            
            wrraper = new ExceptionWrapper("415", "UNSUPPORTED_MEDIA_TYP", "El formato multimedia de los datos solicitados no está soportado por el servidor", "/estudiantes");
            return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity(wrraper).build();
            
        } else if (ex instanceof NumberFormatException) {
            wrraper = new ExceptionWrapper("400", "BAD_REQUEST", "L url solo recibe numeros", "/estudiantes");
            return Response.status(Response.Status.BAD_REQUEST).entity(wrraper).build();
            
        } else if (ex instanceof NotAllowedException) {
            wrraper = new ExceptionWrapper("405", "METHOD_NOT_ALLOWED", "Error en el metedo ", "/estudiantes");
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(wrraper).build();
            
        } else if (ex instanceof NotFoundException) {
            wrraper = new ExceptionWrapper("404", "NOT_FOUND", "URI no encontrada", "/estudiantes");
            return Response.status(Response.Status.NOT_FOUND).entity(wrraper).build();
            
        } else if (ex instanceof WebApplicationException) {
            wrraper = new ExceptionWrapper("400", "BAD_REQUEST", "EL Json no cuenta con el formato adecuado", "/estudiantes");
            return Response.status(Response.Status.BAD_REQUEST).entity(wrraper).build();
            
        } else if (ex instanceof TransactionRolledbackLocalException) { ////////
            wrraper = new ExceptionWrapper("400", "BAD_REQUEST", ex.getMessage(), "/estudiantes");
            return Response.status(Response.Status.BAD_REQUEST).entity(wrraper).build();
            
        } else if (ex instanceof ConflictEx) {
            wrraper = new ExceptionWrapper("409", "CONFLICT", ex.getMessage(), "/estudiantes");
            return Response.status(Response.Status.CONFLICT).entity(wrraper).build();
            
        } else if (ex instanceof NotFoundEx) {
            wrraper = new ExceptionWrapper("404", "NOT_FOUND", ex.getMessage(), "/estudiantes");
            return Response.status(Response.Status.NOT_FOUND).entity(wrraper).build();
            
        } else if (ex instanceof BadRequestEx) {
            wrraper = new ExceptionWrapper("400", "BAD_REQUEST", ex.getMessage(), "/estudiantes");
            return Response.status(Response.Status.BAD_REQUEST).entity(wrraper).build();
            
        } else  {
            wrraper = new ExceptionWrapper("500", "INTERNAL_SERVER_ERROR", "Error en el servidor", "/estudiantes");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(wrraper).build();
        }
    }
}
