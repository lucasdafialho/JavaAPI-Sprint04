package org.mecanica.controladores;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mecanica.entidades.Usuario;
import org.mecanica.services.UsuarioService;

import java.util.List;
import java.util.Optional;

@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private final UsuarioService usuarioService = new UsuarioService();

    @GET
    public List<Usuario> listarUsuarios() {
        return usuarioService.listar();
    }

    @GET
    @Path("{id}")
    public Response buscarUsuarioPorId(@PathParam("id") int id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        return usuario.map(value -> Response.ok(value).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                        .entity("Usuário não encontrado").build());
    }

    @POST
    @Path("/registro")
    public Response registrarUsuario(Usuario usuario) {
        System.out.println("Tentativa de registro de usuário: " + usuario);
        try {
            usuarioService.registrar(usuario);
            return Response.status(Response.Status.CREATED).entity("Usuário registrado com sucesso.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro no registro: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response atualizarUsuario(@PathParam("id") int id, Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioService.buscarPorId(id);
        if (usuarioExistente.isPresent()) {
            usuarioService.atualizar(usuario, id);
            return Response.ok(usuario).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletarUsuario(@PathParam("id") int id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        if (usuario.isPresent()) {
            usuarioService.deletar(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/login")
    public Response autenticarUsuario(@QueryParam("email") String email, @QueryParam("senha") String senha) {
        boolean autenticado = usuarioService.autenticar(email, senha);
        if (autenticado) {
            return Response.ok("Login bem-sucedido.").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciais inválidas.").build();
        }
    }
}
