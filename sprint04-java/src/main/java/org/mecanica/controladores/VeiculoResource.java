package org.mecanica.controladores;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mecanica.entidades.Veiculo;
import org.mecanica.services.VeiculoService;


import java.util.List;
import java.util.Optional;

@Path("/veiculo")
public class VeiculoResource {

    private final VeiculoService veiculoService = new VeiculoService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> getCarros() {
        return veiculoService.Listar();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarroById(@PathParam("id") int id) {
        Optional<Veiculo> carro = veiculoService.BuscarPorId(id);
        return carro.map(value -> Response.ok(value).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).entity("Carro não encontrado").build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCarro(Veiculo veiculo) {
        veiculoService.Cadastrar(veiculo);
        return Response.status(Response.Status.CREATED).entity(veiculo).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCarro(@PathParam("id") int id, Veiculo veiculo) {
        Optional<Veiculo> _carro = veiculoService.BuscarPorId(id);
        if (_carro.isPresent()) {
            veiculoService.Atualizar(veiculo, id);
            return Response.ok(veiculo).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCarro(@PathParam("id") int id) {
        Optional<Veiculo> carro = veiculoService.BuscarPorId(id);
        if (carro.isPresent()) {
            veiculoService.Deletar(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
