/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysproteko.rn;

import com.sysproteko.bd.UnidadeBD;
import com.sysproteko.bean.Unidade;
import com.sysproteko.crud.CrudGenericoRN;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public class UnidadeRN extends CrudGenericoRN<Unidade>{
    
    private static UnidadeBD unidadeBD ;
    
    public UnidadeRN(){
        unidadeBD = new UnidadeBD();
    }

    @Override
    public Unidade consultar(Unidade bean) {
        return unidadeBD.consultar(bean);
    }

    @Override
    public boolean excluir(Unidade bean) {
        return unidadeBD.excluir(bean);
    }

    @Override
    public Unidade salvar(Unidade bean) {
        return unidadeBD.salvar(bean);
    }

    @Override
    public Unidade alterar(Unidade bean) {
        return unidadeBD.alterar(bean);
    }

    @Override
    public List<Unidade> pesquisar(Unidade bean) {
        return unidadeBD.pesquisar(bean);
    }

    @Override
    public List<Unidade> pesquisar(String valor) {
        return unidadeBD.pesquisar(valor);
    }
    
}
