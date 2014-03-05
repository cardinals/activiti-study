/** 
 * @Project:emms
 * @Package:com.szewec.emms.common.dao 
 * @FileName:BaseDao.java 
 * @Author:caiwu
 * @Date:2013-7-22 下午2:40:22 
 * @Version V1.0
 *  
 */
package com.emmsframework.common.dao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:BaseDao
 * 
 * @Author:caiwu
 * @Date:2013-7-22 下午2:40:22
 * @Since:V 1.0
 */
public interface BaseDao
{

	public <T> void save(T t);

	public <T> void saveOrUpdate(T t);

	public <T> void delete(T t);

	public <T> void delete(Class<T> entityClass, Integer id);

	public <T> void update(T t);

	public long queryCount(String hql);

	public <T> T get(Class<T> entityClass, Integer id);

	public <T> List<T> findAll(String hql, Class<T> entityClass);

	public <T> List<T> findAll(String hql, Class<T> entityClass, Object param);

	public <T> List<T> findAll(String hql, Class<T> entityClass, Object[] params);

	public <T> List<T> findAll(String hql, Class<T> entityClass,
			Map<String, Object> paramMap);

	public <T> List<T> findAll(String hql, Class<T> entityClass,
			Map<String, Object> paramMap, int firstResult, int maxResult);

	public <T> List<T> findByPage(final String hql, final Class<T> entityClass,
			final int firstResult, final int maxResult);

	public <T> List<T> findByPage(final String hql, final Class<T> entityClass,
			final Object param, final int firstResult, final int maxResult);

	public <T> List<T> findByPage(final String hql, final Class<T> entityClass,
			final Object[] params, final int firstResult, final int maxResult);

	public <T> void batchSave(List<T> list);

	public <T> void batchUpdate(List<T> list);

	public void execute(String sql, Object[] param);

	public void execute(String sql);

	public List querySQLQuery(String sql, Object[] param);

	
}
