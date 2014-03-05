/** 
 * @Project:emms
 * @Package:com.szewec.emms.common.dao.impl 
 * @FileName:BaseDaoImpl.java 
 * @Author:caiwu
 * @Date:2013-7-22 下午2:40:46 
 * @Version V1.0
 *  
 */
package com.emmsframework.common.dao.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.emmsframework.common.dao.BaseDao;

/**
 * @ClassName:BaseDaoImpl
 * 
 * @Author:caiwu
 * @Date:2013-7-22 下午2:40:46
 * @Since:V 1.0
 */
public class BaseDaoImpl implements BaseDao
{

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}

	@Override
	public <T> void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}

	@Override
	public <T> void save(T t) {
		getSession().save(t);
	}

	@Override
	public <T> void delete(T t) {
		getSession().delete(t);

	}

	@Override
	public <T> void delete(Class<T> entityClass, Integer id) {
		getSession().delete(get(entityClass, id));
	}

	@Override
	public <T> void update(T t) {
		getSession().update(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> entityClass, Integer id) {

		return (T) getSession().get(entityClass, id);
	}

	@Override
	public long queryCount(String hql) {
		Query query = getSession().createQuery(hql);
		long count = ((Long) query.iterate().next()).longValue();
		return count;
	}

	@Override
	public <T> List<T> findAll(String hql, Class<T> entityClass) {

		return findAll(hql, entityClass, new Object[] {});
	}

	@Override
	public <T> List<T> findAll(String hql, Class<T> entityClass, Object param) {
		return findAll(hql, entityClass, new Object[] { param });
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(String hql, Class<T> entityClass,
			Map<String, Object> paramMap) {

		try {
			Query query = getSession().createQuery(hql);
			setQueryParameter(query, paramMap);
			return query.list();
		}
		catch (Exception e) {
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(String hql, Class<T> entityClass,
			Map<String, Object> paramMap, int firstResult, int maxResult) {

		try {
			Query query = getSession().createQuery(hql);
			setQueryParameter(query, paramMap);
			if (maxResult > 0 && firstResult > -1) {
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResult);
			}
			return query.list();
		}
		catch (Exception e) {
		}
		return null;

	}

	@Override
	public <T> List<T> findAll(String hql, Class<T> entityClass, Object[] params) {
		try {
			Query query = getSession().createQuery(hql);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			return query.list();
		}
		catch (Exception e) {
		}
		return null;

	}

	@Override
	public <T> List<T> findByPage(final String hql, Class<T> entityClass,
			final int firstResult, final int maxResult) {
		return findByPage(hql, entityClass, new Object[] {}, firstResult,
				maxResult);
	}

	@Override
	public <T> List<T> findByPage(final String hql, Class<T> entityClass,
			final Object param, final int firstResult, final int maxResult) {
		return findByPage(hql, entityClass, new Object[] { param },
				firstResult, maxResult);
	}

	@Override
	public <T> List<T> findByPage(final String hql, Class<T> entityClass,
			final Object[] params, final int firstResult, final int maxResult) {
		// where ?>? and d=?

		Query query = getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
			// setQueryParam(query, i, params[i]);
		}
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.szwec.demo.dao.BaseDao#batchSave(java.util.List)
	 */
	@Override
	public <T> void batchSave(List<T> list) {

		// long start = System.currentTimeMillis();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();
		int length = list.size();
		try {
			for (int i = 0; i < length; i++) {
				session.save(list.get(i));
				if (i % 300 == 0) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
			tx.rollback();
		}
		finally {
			session.close();
		}
		// System.out.println("cost:" + (System.currentTimeMillis() - start));
	}

	@Override
	public <T> void batchUpdate(List<T> list) {

		long start = System.currentTimeMillis();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();
		int length = list.size();
		try {
			for (int i = 0; i < length; i++) {
				session.update(list.get(i));
				if (i % 300 == 0) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
			tx.rollback();
		}
		finally {
			session.close();
		}
		System.out.println("cost:" + (System.currentTimeMillis() - start));
	}

	@Override
	public void execute(String sql, Object[] param) {
		SQLQuery query = getSession().createSQLQuery(sql);
		int length = param.length;
		for (int index = 0; index < length; index++) {
			setQueryParameter(query, index, param[index]);
		}

		query.executeUpdate();

	}

	@Override
	public void execute(String sql) {
		execute(sql, new Object[] {});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.szwec.demo.dao.BaseDao#querySQLQuery(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public List querySQLQuery(String sql, Object[] param) {
		SQLQuery query = getSession().createSQLQuery(sql);
		int length = param.length;
		for (int index = 0; index < length; index++) {
			setQueryParameter(query, index, param[index]);
		}

		return query.list();

	}

	private void setQueryParameter(SQLQuery query, int index, Object obj) {
		if (obj == null) {
			return;
		}
		if (obj instanceof String) {
			query.setString(index, obj.toString());
		}
		else if (obj instanceof Integer) {
			query.setInteger(index, Integer.parseInt(obj.toString()));
		}
		else if (obj instanceof Double) {
			query.setDouble(index, Double.parseDouble(obj.toString()));
		}
		else if (obj instanceof Float) {
			query.setFloat(index, Float.parseFloat(obj.toString()));
		}
		else {
			query.setString(index, obj.toString());
		}

	}

	private void setQueryParameter(Query query, Map<String, Object> paramMap) {
		if (paramMap != null) {
			Iterator<String> keys = paramMap.keySet().iterator();
			while (keys.hasNext()) {

				String key = keys.next();
				Object value = paramMap.get(key);

				if (value instanceof Collection)
					query.setParameterList(key, (Collection<?>) value);
				else if (value instanceof Object[])
					query.setParameterList(key, (Object[]) value);
				else
					query.setParameter(key, value);
			}
		}
	}

}
