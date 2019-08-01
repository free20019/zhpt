package mvc.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * DAO基本接口,定义了Hibernate常规数据库操作
 */
public interface IBaseDAO<T extends Serializable> {
	/**
	 * 获取sessionFactory
	 * @return
	 */
	public SessionFactory getSessionFactory0();

	/**
	 * 按ID查找
	 * 
	 * @param id
	 * @return
	 */
	public abstract T findById(Serializable id);


	public abstract void update(T entity);
	
	public void merge(T entity);

	public abstract void save(T entity) throws Exception;

	public abstract void saveOrUpdate(T entity);

	public abstract void saveOrUpdateAll(Collection<T> entities);
 
	public abstract void delete(T entity) throws Exception;

	/**
	 * 按主键删除

	 * 
	 * @param id
	 * @throws Exception
	 */
	public abstract void deleteByKey(Serializable id) throws Exception;

 
	/**
	 * 按实体集合删除

	 * 
	 * @param entities
	 */
	public abstract void deleteAll(Collection<T> entities);

	public abstract T load(Serializable id);

	public abstract List<T> loadAll();
	public T get(Serializable id) ;
	/**
	 * 根据主键获取实体并加锁。如果没有相应的实体，返回 null。

	 * 
	 */
	public abstract T getWithLock(Serializable id, LockMode lock);

	/**
	 * 根据主键获取实体并加锁。如果没有相应的实体，抛出异常

	 * @param id
	 * @param lock
	 * @return
	 */
	public abstract T loadWithLock(Serializable id, LockMode lock);

	public abstract void updateWithLock(T entity, LockMode lock);

	public abstract void deleteWithLock(T entity, LockMode lock);

	public abstract void deleteByKeyWithLock(Serializable id, LockMode lock);

	/**
	 * 加锁指定的实体

	 * 
	 * @param entity
	 * @param lock
	 */
	public abstract void lock(T entity, LockMode lock);

	/**
	 * 强制初始化指定的实体
	 * 
	 * @param proxy
	 */
	public abstract void initialize(Object proxy);

	/**
	 * 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
	 */
	public abstract void flush();
	public Session getCurrentSession();
	
	public Map<String,List<T>> getAllPage(Map paramMap , int page,int pageSize);
	public long getCount(Map paramMap);
}