/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysproteko.rest;

import com.google.gson.Gson;
import com.sysproteko.bean.Chamado;
import com.sysproteko.bean.Tecnico;
import com.sysproteko.bean.Unidade;
import com.sysproteko.crud.CrudGenericoRest;
import com.sysproteko.crud.ErroRest;
import com.sysproteko.crud.RNException;
import com.sysproteko.rn.UnidadeRN;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author Guilherme
 */
@Path("/unidades")
public class UnidadeREST extends CrudGenericoRest<Unidade> {
    
 private final UnidadeRN rn;

    public UnidadeREST() {
        this.rn = new UnidadeRN();
    }

    @Override
    public Response consultarPK(String pk) {
        try {
            Unidade u = rn.consultar(new Unidade(Integer.parseInt(pk)));

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
            List<Unidade> ret = rn.pesquisar(q);

            for (Unidade t : ret) {
                for(Chamado c : t.getChamadoList()) {
                    c.setUnidade(null);
                    c.setTecnico(null);
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
            rn.excluir(new Unidade(Integer.parseInt(pk)));
            return Response.ok().build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

 @Override
    public Response salvar(String obj) {
        try {
            Unidade u = rn.salvar(new Gson().fromJson(obj, Unidade.class));
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(u.getId())).build();
            return Response.created(uri).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

   

    @Override
    protected Response gerarResponseParaCollection(List<Unidade> obj) {
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(toJSON(new ErroRest("Nenhum registro disponível; lista vazia")))
                .build();
        }

        return Response.ok(new Gson().toJson(obj)).build();
    }

    @Override
    public Response alterar(String obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}
