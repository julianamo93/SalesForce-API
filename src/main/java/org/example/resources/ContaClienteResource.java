package org.example.resources;

import org.example.entities.ContaCliente;
import org.example.entities.dtos.SearchContaClienteDto;
import org.example.repositories.ContaClienteRepository;
import org.example.services.ContaClienteService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("contaCliente")
public class ContaClienteResource {

    private final ContaClienteService contaClienteService;
    private final ContaClienteRepository contaClienteRepository;

    public ContaClienteResource() {
        this.contaClienteService = new ContaClienteService();
        this.contaClienteRepository = new ContaClienteRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        // Parâmetros padrão para paginação e ordenação
        int limit = 10; // valor ajustador conforme necessário - configuramos para 10
        int offset = 0;
        String orderBy = "id"; // Campo para ordenação padrão
        String direction = "asc"; // Direção padrão para ordenação

        SearchContaClienteDto result = contaClienteService.getAll(
                null, null, null, null, null, null, null, null, null, null, null, null, null, orderBy, direction, limit, offset);

        return Response.ok(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCliente(ContaCliente cliente) {
        ContaCliente novoCliente = contaClienteService.addCliente(cliente);
        return Response.status(Response.Status.CREATED).entity(novoCliente).build();
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
