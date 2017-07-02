package com.sysproteko.rest;

import com.google.gson.Gson;
import com.sysproteko.bean.Chamado;
import com.sysproteko.bean.Tecnico;
import com.sysproteko.bean.Unidade;
import com.sysproteko.crud.CrudGenericoRest;
import com.sysproteko.crud.ErroRest;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import com.sysproteko.crud.RNException;
import com.sysproteko.rn.ChamadoRN;
import java.net.URI;
import javax.ws.rs.core.GenericEntity;

/**
 *
 * @author Allison
 */
@Path("/chamados")
public class ChamadoREST extends CrudGenericoRest<Chamado> {

    private final ChamadoRN rn;

    public ChamadoREST() {
        this.rn = new ChamadoRN();
    }

    @Override
    public Response consultarPK(String pk) {
        try {
            Chamado c = new Chamado(Integer.parseInt(pk));
            rn.consultar(c);

            if (c == null) {
                return exceptionParaResponse(new RNException(RNException.Tipo.REGISTRO_NAO_ENCONTRADO));
            }

            return Response.ok(new Gson().toJson(c)).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response pesquisar(String q) {
        try {
            List<Chamado> ret = rn.pesquisar(q);
            
            for (Chamado c : ret) {
                c.setUnidade(null);
                c.setTecnico(null);
            }

            return gerarResponseParaCollection(ret);
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response excluirPK(String pk) {
        try {
            rn.excluir(new Chamado(Integer.parseInt(pk)));
            return Response.ok().build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response salvar(String obj) {
        try {
            System.out.println(obj);
            Chamado c = rn.salvar(new Gson().fromJson(obj, Chamado.class));
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(c.getNChamado())).build();
            return Response.created(uri).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }
    
    @Override
    public Response alterar(String obj) {
          try {
            Chamado c = rn.salvar(new Gson().fromJson(obj, Chamado.class));
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(c.getNChamado())).build();
            return Response.created(uri).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    protected Response gerarResponseParaCollection(List<Chamado> obj) {
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(toJSON(new ErroRest("Nenhum registro disponível; lista vazia")))
                    .build();
        }

        return Response.ok(new Gson().toJson(obj)).build();
    }

}
