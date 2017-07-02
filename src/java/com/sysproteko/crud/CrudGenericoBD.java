package com.sysproteko.crud;

import com.sysproteko.infra.Propriedades;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;

/**
 * Classe de persistência genérica para os beans utilizados no projeto.
 *
 * @author lossurdo
 * @param <T>
 */
public abstract class CrudGenericoBD<T> implements CrudGenerico<T> {

    
    protected EntityManager createEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                Propriedades.getInstance().get("persistenceUnitName")
        );
        System.out.println(Propriedades.getInstance().get("persistenceUnitName"));
        EntityManager em = emf.createEntityManager();
        return em;
    }
    
    @Override
    public T salvar(T bean) {
        EntityManager em = createEntityManager();
        try {
            em.getTransaction().begin();        
            em.persist(bean);
            em.flush();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return bean;
    }

    @Override
    public T alterar(T bean) {
        EntityManager em = createEntityManager();
        try {
            em.getTransaction().begin();        
            em.merge(bean);
            em.getTransaction().commit();        
        } finally {
            em.close();
        }
        return bean;
    }

    @Override
    public boolean excluir(T bean) {
        EntityManager em = createEntityManager();
        try {
            T obj = (T) em.getReference(bean.getClass(), descobrirValorPK(bean));
            em.getTransaction().begin();        
            em.remove(obj);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    public T consultar(T bean) {
        EntityManager em = createEntityManager();
        try {
            Object valorPK = descobrirValorPK(bean);
            if (valorPK == null) {
                return null;
            }
            return (T) em.find(bean.getClass(), valorPK);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            em.close();
        }
    }

    /**
     * Utilizado para executar qualquer NamedQuery
     * criada nos beans.
     * 
     * @param namedQuery
     * @return 
     */
    public List<T> namedQuery(String namedQuery) {
        EntityManager em = createEntityManager(); 
        try {
            return em.createNamedQuery(namedQuery).getResultList();
        } finally {
            em.close();        
        }            
    }
    
    /**
     * Percorre os atributos de uma classe a procura daquele anotado com @Id; Ao
     * encontrar, retorna o valor setado para este atributo.
     *
     * @param bean
     * @return Conteúdo da PK
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private Object descobrirValorPK(T bean) throws IllegalArgumentException, IllegalAccessException {
        Class<? extends Object> clazz = bean.getClass();
        for (Field f : clazz.getDeclaredFields()) {
            if (f.isAnnotationPresent(Id.class)) {
                f.setAccessible(true);
                Object valorPK = f.get(bean);
                return valorPK;
            }
        }
        return null;
    }

}
