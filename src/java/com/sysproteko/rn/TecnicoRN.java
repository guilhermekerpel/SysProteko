/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysproteko.rn;

import com.sysproteko.bd.TecnicoBD;
import com.sysproteko.bean.Tecnico;
import com.sysproteko.crud.CrudGenericoRN;
import java.util.List;

/**
 *
 * @author Allison
 */
public class TecnicoRN extends CrudGenericoRN<Tecnico> {

    private TecnicoBD tecnicoBD;

    public TecnicoRN() {
        tecnicoBD = new TecnicoBD();
    }

    @Override
    public Tecnico consultar(Tecnico bean) {
        return tecnicoBD.consultar(bean);
    }

    @Override
    public boolean excluir(Tecnico bean) {
        return tecnicoBD.excluir(bean);
    }

    @Override
    public Tecnico salvar(Tecnico bean) {
        return tecnicoBD.salvar(bean);
    }

    @Override
    public Tecnico alterar(Tecnico bean) {
        return tecnicoBD.alterar(bean);
    }

    @Override
    public List<Tecnico> pesquisar(Tecnico bean) {
        return tecnicoBD.pesquisar(bean);
    }

    @Override
    public List<Tecnico> pesquisar(String valor) {
        return tecnicoBD.pesquisar(valor);
    }

}
