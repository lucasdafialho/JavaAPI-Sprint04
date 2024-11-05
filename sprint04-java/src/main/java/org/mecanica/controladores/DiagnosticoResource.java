package org.mecanica.controladores;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mecanica.entidades.Diagnostico;
import org.mecanica.services.DiagnosticoService;


import java.util.List;
import java.util.Optional;

@Path("/diagnostico")
public class DiagnosticoResource {

    private final DiagnosticoService diagnosticoServico = new DiagnosticoService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Diagnostico> getDiagnosticos() {
        return diagnosticoServico.Listar();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiagnosticoById(@PathParam("id") int id) {
        Optional<Diagnostico> diagnostico = diagnosticoServico.BuscarPorId(id);
        return diagnostico.map(value -> Response.ok(value).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).entity("Diagnóstico não encontrado").build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDiagnostico(Diagnostico diagnostico) {
        diagnosticoServico.Cadastrar(diagnostico);
        return Response.status(Response.Status.CREATED).entity(diagnostico).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDiagnostico(@PathParam("id") int id, Diagnostico diagnostico) {
        Optional<Diagnostico> _diagnostico = diagnosticoServico.BuscarPorId(id);
        if (_diagnostico.isPresent()) {
            diagnosticoServico.Atualizar(diagnostico, id);
            return Response.ok(diagnostico).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteDiagnostico(@PathParam("id") int id) {
        Optional<Diagnostico> diagnostico = diagnosticoServico.BuscarPorId(id);
        if (diagnostico.isPresent()) {
            diagnosticoServico.Deletar(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
