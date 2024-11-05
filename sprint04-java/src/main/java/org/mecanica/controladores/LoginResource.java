package org.mecanica.controladores;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mecanica.entidades.Login;
import org.mecanica.services.LoginService;


import java.util.List;
import java.util.Optional;

@Path("/login")
public class LoginResource {

    private final LoginService loginServico = new LoginService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Login> getLogins() {
        return loginServico.Listar();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoginById(@PathParam("id") int id) {
        Optional<Login> login = loginServico.BuscarPorId(id);
        if (login.isPresent()) {
            return Response.ok(login.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Login não encontrado").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLogin(Login login) {
        loginServico.Cadastrar(login);
        return Response.status(Response.Status.CREATED)
                .entity(loginServico)
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogin(@PathParam("id") int id, Login login) {
        Optional<Login> _login = loginServico.BuscarPorId(id);
        if (_login.isPresent()) {
            loginServico.Atualizar(login, id);
            return Response.ok(login).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteLogin(@PathParam("id") int id) {
        Optional<Login> login = loginServico.BuscarPorId(id);
        if (login.isPresent()) {
            loginServico.Deletar(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
