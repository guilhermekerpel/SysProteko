package com.sysproteko.rest;

import com.google.gson.Gson;
import com.sysproteko.bean.Chamado;
import com.sysproteko.bean.Tecnico;
import com.sysproteko.crud.CrudGenericoRest;
import com.sysproteko.crud.ErroRest;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import com.sysproteko.crud.RNException;
import com.sysproteko.rn.TecnicoRN;
import java.io.Console;
import java.net.URI;
import javax.ws.rs.core.GenericEntity;

/**
 *
 * @author Allison
 */
@Path("/tecnicos")
public class TecnicoREST extends CrudGenericoRest<Tecnico> {

    private final TecnicoRN rn;

    public TecnicoREST() {
        this.rn = new TecnicoRN();
    }

    @Override
    public Response consultarPK(String pk) {
        try {
            Tecnico t = rn.consultar(new Tecnico(Integer.parseInt(pk)));

            if (t == null) {
                return exceptionParaResponse(new RNException(RNException.Tipo.REGISTRO_NAO_ENCONTRADO));
            }

            return Response.ok(new Gson().toJson(t)).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response pesquisar(String q) {
        try {
            List<Tecnico> ret = rn.pesquisar(q);
            
            for (Tecnico t : ret) {
                for (Chamado c : t.getChamadoList()) {
                    c.setTecnico(null);
                    c.setUnidade(null);
                    t.setChamadoList(null);
                    
                }
            }

            return gerarResponseParaCollection(ret);
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response excluirPK(String pk) {
        try {
            rn.excluir(new Tecnico(Integer.parseInt(pk)));
            return Response.ok().build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response salvar(String obj) {
        try {
            Tecnico t = rn.salvar(new Gson().fromJson(obj, Tecnico.class));
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(t.getId())).build();
            return Response.created(uri).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response alterar(String obj) {
        try {
            Tecnico t = rn.alterar(new Gson().fromJson(obj, Tecnico.class));
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(t.getId())).build();
            return Response.created(uri).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    protected Response gerarResponseParaCollection(List<Tecnico> obj) {
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(toJSON(new ErroRest("Nenhum registro disponível; lista vazia")))
                    .build();
        }

        return Response.ok(new Gson().toJson(obj)).build();
    }
}
