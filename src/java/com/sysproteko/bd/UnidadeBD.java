package com.sysproteko.bd;

import com.sysproteko.bean.Unidade;
import com.sysproteko.crud.CrudGenericoBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Guilherme
 */
public class UnidadeBD extends CrudGenericoBD<Unidade> {

    @Override
    public List<Unidade> pesquisar(Unidade bean) {
        return pesquisar(bean);
    
    }
    @Override
    public List<Unidade> pesquisar(String valor) {
        EntityManager em = createEntityManager();        
        try {
            Query query = em.createNamedQuery("Unidade.findAll");
            //query.setParameter("nome", "%" + valor + "%");
            return query.getResultList();
        } finally {
            em.close();        
        }
    }
    
}
