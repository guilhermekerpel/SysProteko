/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysproteko.bd;

import com.sysproteko.bean.Tecnico;
import com.sysproteko.crud.CrudGenericoBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author allis
 */
public class TecnicoBD extends CrudGenericoBD<Tecnico> {

    @Override
    public List<Tecnico> pesquisar(String valor) {

        EntityManager em = createEntityManager();
        try {
            Query query = em.createNamedQuery("Tecnico.findAll");
            //query.setParameter("nome", "%" + valor + "%");
            return query.getResultList();
        } finally {
            em.close();
        }

    }

    @Override
    public List<Tecnico> pesquisar(Tecnico bean) {
        return pesquisar(bean.getNome());
    }

}
