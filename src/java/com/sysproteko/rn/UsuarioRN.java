package com.sysproteko.rn;

import com.sysproteko.bd.UsuarioBD;
import com.sysproteko.bean.Usuario;
import com.sysproteko.crud.CrudGenericoRN;
import java.util.List;

/**
 *
 * @author Allison
 */
public class UsuarioRN extends CrudGenericoRN<Usuario>{
    
    private static UsuarioBD usuarioBD;

    public UsuarioRN() {
        usuarioBD = new UsuarioBD();
    }

    @Override
    public Usuario consultar(Usuario bean) {
        return usuarioBD.consultar(bean);
    }

    @Override
    public boolean excluir(Usuario bean) {
        return usuarioBD.excluir(bean);
    }

    @Override
    public Usuario salvar(Usuario bean) {
        return usuarioBD.salvar(bean);
    }

    @Override
    public Usuario alterar(Usuario bean) {
        return usuarioBD.alterar(bean);
    }

    @Override
    public List<Usuario> pesquisar(Usuario bean) {
        return usuarioBD.pesquisar(bean);
    }

    @Override
    public List<Usuario> pesquisar(String valor) {
        return usuarioBD.pesquisar(valor);
    }
    
}
