package org.example.resources;

import org.example.entities.ContaCliente;
import org.example.repositories.ContaClienteRepository;
import org.example.services.ContaClienteService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("contaCliente")
public class ContaClienteResource {

    private final ContaClienteRepository contaClienteRepository;
    private final ContaClienteService contaClienteService;

    public ContaClienteResource() {
        this.contaClienteRepository = new ContaClienteRepository();
        this.contaClienteService = new ContaClienteService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(
            @QueryParam("orderBy") String orderBy,
            @QueryParam("direction") String direction,
            @QueryParam("limit") int limit,
            @QueryParam("offset") int offset,
            @QueryParam("nome") String nome,
            @QueryParam("sobrenome") String sobrenome,
            @QueryParam("cargo") String cargo,
            @QueryParam("nomeEmpresa") String nomeEmpresa,
            @QueryParam("telefone") String telefone,
            @QueryParam("email") String email,
            @QueryParam("cep") String cep,
            @QueryParam("rua") String rua,
            @QueryParam("bairro") String bairro,
            @QueryParam("cidade") String cidade,
            @QueryParam("estado") String estado,
            @QueryParam("senha") String senha
    ) {
        return Response.ok(contaClienteService.getAll(nome, sobrenome, cargo, nomeEmpresa, telefone, email, cep,
                rua, bairro, cidade, estado, senha, orderBy, direction, limit, offset)).build();
    }

    @GET
    @Path("registroAcesso/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByRegistroAcesso(@PathParam("id") int idRegistroAcesso) {
        return Response.ok(contaClienteRepository.getAllByRegistroAcesso(idRegistroAcesso)).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Optional<ContaCliente> contaCliente = contaClienteRepository.get(id);
        return contaCliente.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ContaCliente contaCliente) {
        try {
            contaClienteService.create(contaCliente);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, ContaCliente contaCliente) {
        try {
            contaClienteService.update(id, contaCliente);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        try {
            contaClienteService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
