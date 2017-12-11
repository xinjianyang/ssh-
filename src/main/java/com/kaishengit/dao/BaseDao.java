package com.kaishengit.dao;

import com.kaishengit.pojo.Product;
import com.kaishengit.util.RequestQuery;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author NativeBoy
 */
public abstract class BaseDao<T, PK extends Serializable> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    public BaseDao(){
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void save(T t) {
        getSession().saveOrUpdate(t);
    }

    public T findById(PK id){
       return (T) getSession().get(clazz ,id);
    }

    public void deleteById(PK id){
        getSession().delete(findById(id));
    }

    public List<T> findAll(){
        Criteria criteria = getSession().createCriteria(clazz);
        return criteria.list();
    }

    public List<T> page(Integer start, Integer size){
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.setFirstResult(start);
        criteria.setMaxResults(size);
        return criteria.list();
    }

    public List<Product> findByRequestQueryList(List<RequestQuery> requestQueryList){
        Criteria criteria = getSession().createCriteria(clazz);
        for(RequestQuery requestQuery : requestQueryList){
            String parameterName = requestQuery.getParameterName();
            Object value = requestQuery.getValue();
            String type = requestQuery.getType();
            criteria.add(createCriterion(parameterName,value,type));

        }
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult(0);
        criteria.setMaxResults(1000);
        return criteria.list();
    }

    public Criterion createCriterion(String parameterName, Object value, String type){
        if("eq".equalsIgnoreCase(type)){
            return Restrictions.eq(parameterName,value);
        }else if("like".equalsIgnoreCase(type)){
            return Restrictions.like(parameterName, (String) value, MatchMode.ANYWHERE);
        }else if("gt".equalsIgnoreCase(type)){
            return Restrictions.gt(parameterName,value);
        }else if("lt".equalsIgnoreCase(type)){
            return Restrictions.lt(parameterName,value);
        }else if("ge".equalsIgnoreCase(type)){
            return Restrictions.ge(parameterName,value);
        }else if("le".equalsIgnoreCase(type)){
            return Restrictions.le(parameterName,value);
        }
        return null;
    }

}
