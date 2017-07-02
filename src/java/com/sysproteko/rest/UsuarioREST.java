package com.sysproteko.rest;

import com.google.gson.Gson;
import com.sysproteko.bean.Usuario;
import com.sysproteko.crud.CrudGenericoRest;
import com.sysproteko.crud.ErroRest;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import com.sysproteko.crud.RNException;
import com.sysproteko.rn.UsuarioRN;
import java.net.URI;

/**
 *
 * @author Allison
 */
@Path("/usuarios")
public class UsuarioREST extends CrudGenericoRest<Usuario> {

    private final UsuarioRN rn;

    public UsuarioREST() {
        this.rn = new UsuarioRN();
    }

    @Override
    public Response consultarPK(String pk) {
        try {
            Usuario u = rn.consultar(new Usuario(Integer.parseInt(pk)));

            if (u == null) {
                return exceptionParaResponse(new RNException(RNException.Tipo.REGISTRO_NAO_ENCONTRADO));
            }

                return Response.ok(new Gson().toJson(u)).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response pesquisar(String q) {
        try {
            List<Usuario> ret = rn.pesquisar(q);
            
            return gerarResponseParaCollection(ret);
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response excluirPK(String pk) {
        try {
            rn.excluir(new Usuario(Integer.parseInt(pk)));
            return Response.ok().build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response salvar(String obj) {
        try {
            Usuario u = rn.salvar(new Gson().fromJson(obj, Usuario.class));
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(u.getId())).build();
            return Response.created(uri).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response alterar(String obj) {
        try {
            Usuario u = rn.alterar(new Gson().fromJson(obj, Usuario.class));
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(u.getId())).build();
            return Response.created(uri).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    protected Response gerarResponseParaCollection(List<Usuario> obj) {
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(toJSON(new ErroRest("Nenhum registro disponível; lista vazia")))
                    .build();
        }
        return Response.ok(new Gson().toJson(obj)).build();
    }

}