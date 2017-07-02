/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysproteko.bd;

import com.sysproteko.bean.Chamado;
import com.sysproteko.crud.CrudGenericoBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author allis
 */
public class ChamadoBD extends CrudGenericoBD<Chamado> {

    @Override
    public List<Chamado> pesquisar(Chamado chamado) {
        return pesquisar(chamado.getStatus());
    }

    @Override
    public List<Chamado> pesquisar(String valor) {
         EntityManager em = createEntityManager();        
        try {
            Query query = em.createNamedQuery("Chamado.findAll");
            //query.setParameter("chamado", "%" + valor + "%");
            return query.getResultList();
        } finally {
            em.close();        
        }
    }

   
}
