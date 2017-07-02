/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysproteko.bd;

import com.sysproteko.bean.Usuario;
import com.sysproteko.crud.CrudGenericoBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author allis
 */
public class UsuarioBD extends CrudGenericoBD<Usuario> {

    @Override
    public List<Usuario> pesquisar(String valor) {
        EntityManager em = createEntityManager();        
        try {
            Query query = em.createNamedQuery("Usuario.findAll");
            //query.setParameter("usuario", Integer.parseInt(valor));
            return query.getResultList();
        } finally {
            em.close();        
        }
    }

    @Override
    public List<Usuario> pesquisar(Usuario bean) {
         return pesquisar(bean.getNome());
    }

   
}
