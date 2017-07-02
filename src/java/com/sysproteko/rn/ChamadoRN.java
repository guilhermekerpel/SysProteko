package com.sysproteko.rn;

import com.sysproteko.bd.ChamadoBD;
import com.sysproteko.bean.Chamado;
import com.sysproteko.crud.CrudGenericoRN;
import java.util.List;

/**
 *
 * @author Allison
 */
public class ChamadoRN extends CrudGenericoRN<Chamado> {

    private static ChamadoBD chamadoBD;

    public ChamadoRN() {
        chamadoBD = new ChamadoBD();
    }

    @Override
    public Chamado consultar(Chamado bean) {
        return chamadoBD.consultar(bean);
    }

    @Override
    public boolean excluir(Chamado bean) {
        return chamadoBD.excluir(bean);
    }

    @Override
    public Chamado salvar(Chamado bean) {
        return chamadoBD.salvar(bean);
    }

    @Override
    public Chamado alterar(Chamado bean) {
        return chamadoBD.alterar(bean);
    }

    @Override
    public List<Chamado> pesquisar(Chamado bean) {
        return chamadoBD.pesquisar(bean);
    }

    @Override
    public List<Chamado> pesquisar(String valor) {
        return chamadoBD.pesquisar(valor);
    }
    


}
