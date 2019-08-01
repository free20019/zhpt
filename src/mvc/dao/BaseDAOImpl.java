package mvc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;


@Repository
public class BaseDAOImpl<T extends Serializable> extends HibernateDaoSupport
		implements IBaseDAO<T> {
	@Autowired
	public void setSessionFactory0(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private Class<T> entityClass;

	/**
	 * 当子类实例化时，执行此方法.根据实例类自动获取实体类类型
	 */
	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		this.entityClass = null;
		Class<?> c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.entityClass = (Class<T>) p[0];
		}
	}

	public SessionFactory getSessionFactory0(){
		return super.getSessionFactory();
	}
	/**
	 * 批量更新
	 *
	 */
	public int bulkUpdate(String hql) {
		return getHibernateTemplate().bulkUpdate(hql);
	}

	/**
	 * 批量更新带参数
	 *
	 * @param queryString
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int bulkUpdate(final String hql, final Map<String,Object> paramMap) {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				String[] params=query.getNamedParameters();
				if(null != paramMap){
					for(Map.Entry<String,Object> entry:paramMap.entrySet()){
						String key=entry.getKey();
						Object value=entry.getValue();
						//如果有这个参数
						if(ArrayUtils.contains(params, entry.getKey())){
							if(value instanceof Collection<?>){
								query.setParameterList(key,(Collection<?>)value);
							}else if(value instanceof Object[]){
								query.setParameterList(key,(Object[])value);
							}else{
								query.setParameter(key, value);
							}
						}
					}
				}
				int result = query.executeUpdate();
				session.close();
				return result;
			}

		});
	}

	/**
	 * 根据ID查询
	 *
	 * @param id
	 * @return
	 */
	public T findById(Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 获取行数
	 *
	 * @param criteria
	 * @return
	 */
	public Integer getRowCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<?> list = this.findByCriteria(criteria, 0, 1);
		return (Integer) list.get(0);
	}

	/**
	 * 获取行数
	 */
	public Integer getRowCount(String sql) {
		List<?> list = this.findBySQL(sql);
		return list.size();

	}

	/**
	 *
	 * @param criteria
	 * @param propertyName
	 * @param StatName
	 * @return
	 */
	public Object getStatValue(DetachedCriteria criteria, String propertyName,
			String StatName) {
		if (StatName.toLowerCase().equals("max"))
			criteria.setProjection(Projections.max(propertyName));
		else if (StatName.toLowerCase().equals("min"))
			criteria.setProjection(Projections.min(propertyName));
		else if (StatName.toLowerCase().equals("avg"))
			criteria.setProjection(Projections.avg(propertyName));
		else if (StatName.toLowerCase().equals("sum"))
			criteria.setProjection(Projections.sum(propertyName));
		else
			return null;
		List<?> list = this.findByCriteria(criteria, 0, 1);
		return list.get(0);
	}

	/**
	 * 根据HQL查询，返回List
	 *
	 * @param queryString
	 * @return
	 */
	public List<?> findByHQL(String queryString) {
		return getHibernateTemplate().find(queryString);
	}

	/**
	 * 根据HQL查询，返回List
	 *
	 * @param queryString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<?> findByHQL(final String hql,final Map<String,Object> paramMap) {
		List<?> list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						String[] params=query.getNamedParameters();
						if(null != paramMap){
							for(Map.Entry<String,Object> entry:paramMap.entrySet()){
								String key=entry.getKey();
								Object value=entry.getValue();
								//如果有这个参数
								if(ArrayUtils.contains(params, entry.getKey())){
									if(value instanceof Collection<?>){
										query.setParameterList(key,(Collection<?>)value);
									}else if(value instanceof Object[]){
										query.setParameterList(key,(Object[])value);
									}else{
										query.setParameter(key, value);
									}
								}
							}
						}
						List<?> list=query.list();
						session.close();
						return list;
					}
				});
		return list;
	}

	/**
	 * 根据HQL查询分页，返回List
	 *
	 * @param queryString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<?> findByHQL(final String hql,final Map<String,Object> paramMap,final int currPage,
			final int pagesize) {
		List<?> list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						String[] params=query.getNamedParameters();
						if(null != paramMap){
							for(Map.Entry<String,Object> entry:paramMap.entrySet()){
								String key=entry.getKey();
								Object value=entry.getValue();
								//如果有这个参数
								if(ArrayUtils.contains(params, entry.getKey())){
									if(value instanceof Collection<?>){
										query.setParameterList(key,(Collection<?>)value);
									}else if(value instanceof Object[]){
										query.setParameterList(key,(Object[])value);
									}else{
										query.setParameter(key, value);
									}
								}
							}
						}
						if (currPage > 0) {

							query.setFirstResult(
									currPage > 1 ? (currPage - 1) * pagesize
											: 0).setMaxResults(pagesize).list();
						}
						List<?> list=query.list();
						session.close();
						return list;
					}
				});
		return list;
	}
	/**
	 * 根据HQL查询分页，返回List
	 *
	 * @param queryString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<?> findByHQL(final String hql, final int currPage,
			final int pageSize) {
		List<?> list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						if (currPage > 0) {
							query.setFirstResult(
									currPage > 1 ? (currPage - 1) * pageSize
											: 0).setMaxResults(pageSize);
						}
						List<?> list = query.list();
						session.close();
						return list;
					}
				});
		return list;
	}


	@SuppressWarnings("unchecked")
	public List<?> findBySQL(final String SQL) {
		List<?> list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(SQL);
						List<?> list=query.list();
						session.close();
						return list;
					}
				});
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<?> findBySQL(final String SQL, final int currPage,
			final int pagesize) {
		List<?> list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(SQL);
						if (currPage > 0) {
							query.setFirstResult(
									currPage > 1 ? (currPage - 1) * pagesize
											: 0).setMaxResults(pagesize).list();
						}
						List<?> list=query.list();
						session.close();
						return list;
					}
				});
		return list;

	}

	/**
	 * DetachedCriteria
	 *
	 * @param
	 * @return
	 */
	public List<?> findByCriteria(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public List<?> findByCriteria(DetachedCriteria criteria, int firstResult,
			int maxResults) {
		return getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}

	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(this.entityClass);
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}
	public void merge(T entity) {
		getHibernateTemplate().merge(entity);
	}

	public void save(T entity) throws Exception {
		getHibernateTemplate().save(entity);
	}

	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<T> entities) {
		getHibernateTemplate().saveOrUpdate(entities);
	}

	public void delete(T entity) throws Exception {
		getHibernateTemplate().delete(entity);
	}

	public void deleteByKey(Serializable id) throws Exception {
		this.delete(this.load(id));
	}

	public void deleteAll(Collection<T> entities) {
		getHibernateTemplate().deleteAll(entities);
	}

	public void deleteAll() {
		getHibernateTemplate().bulkUpdate("delete from " + entityClass);
	}

	public void deleteAll(String entityName) {
		getHibernateTemplate().bulkUpdate("delete from " + entityName);
	}

	public T load(Serializable id) {
		return (T) getHibernateTemplate().load(entityClass, id);
	}

	public List<T> loadAll() {
		return (List<T>) getHibernateTemplate().loadAll(entityClass);
	}

	public T getWithLock(Serializable id, LockMode lock) {
		T t = (T) getHibernateTemplate().get(entityClass, id, lock);
		if (t != null) {
			this.flush(); // 立即刷新，否则锁不会生效。

		}
		return t;
	}

	public T get(Serializable id) {
		T t = (T) getHibernateTemplate().get(entityClass, id);
		if (t != null) {
			this.flush(); // 立即刷新，否则锁不会生效。

		}
		return t;
	}

	public T loadWithLock(Serializable id, LockMode lock) {
		T t = (T) getHibernateTemplate().load(entityClass, id, lock);
		if (t != null) {
			this.flush(); // 立即刷新，否则锁不会生效。

		}
		return t;
	}

	public void updateWithLock(T entity, LockMode lock) {
		getHibernateTemplate().update(entity, lock);
		this.flush(); // 立即刷新，否则锁不会生效。

	}

	public void deleteWithLock(T entity, LockMode lock) {
		getHibernateTemplate().delete(entity, lock);
		this.flush(); // 立即刷新，否则锁不会生效。

	}

	public void deleteByKeyWithLock(Serializable id, LockMode lock) {
		this.deleteWithLock(this.load(id), lock);

	}

	public void lock(T entity, LockMode lock) {
		getHibernateTemplate().lock(entity, lock);
	}

	public void initialize(Object proxy) {
		getHibernateTemplate().initialize(proxy);
	}

	public void flush() {
		getHibernateTemplate().flush();
	}
	public Object getUniqueResult(final String hql) {
		Object obj = getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				Object obj = query.uniqueResult();
				session.close();
				return obj;
			}
	});
		return obj;
	}
	public Object getUniqueResult(final String hql, final Map<String,Object> paramMap) {
		Object obj = getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				String[] params=query.getNamedParameters();
				if(null != paramMap){
					for(Map.Entry<String,Object> entry:paramMap.entrySet()){
						String key=entry.getKey();
						Object value=entry.getValue();
						//如果有这个参数
						if(ArrayUtils.contains(params, entry.getKey())){
							if(value instanceof Collection<?>){
								query.setParameterList(key,(Collection<?>)value);
							}else if(value instanceof Object[]){
								query.setParameterList(key,(Object[])value);
							}else{
								query.setParameter(key, value);
							}
						}
					}
				}
				Object obj = query.uniqueResult();
				session.close();
				return obj;
			}
	});
		return obj;
	}
	public long getCountByUniqueResult(final String hql, final Map<String,Object> paramMap){
		long count = 0;
		Object object = getUniqueResult(hql,paramMap);
		if(null != object && StringUtils.isNumeric(String.valueOf(object))){
			count = Long.parseLong(String.valueOf(object));
		}
		return count;
	}
	public Session getCurrentSession(){
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory()
			.getCurrentSession();
		} catch (Exception e) {
			System.out.println("getCurrentSession() fail......");
		}
		if(null == session){
			session = getHibernateTemplate().getSessionFactory().openSession();
		}
		return session;
	}
	
	public Map<String,List<T>> getAllPage(Map paramMap, int page,int pageSize) {
		String hql ="from " +entityClass.getName();
		Map map = new HashMap();
		List<T> list =  (List<T>) findByHQL(hql,paramMap, page, pageSize);
		hql = "select count(*) from " + entityClass.getName() ;
		long count = Long.valueOf(getUniqueResult(hql).toString());
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
	
	public long getCount(Map paramMap) {
		String hql = "select count(*) from " + entityClass.getName();
		long count = Long.valueOf(getUniqueResult(hql).toString());
		return count;
	}
}
