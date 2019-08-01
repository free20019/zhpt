package mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository("tbYjzhSjfkDAO")
public class TbYjzhSjfkDAO extends   BaseDAOImpl<TbYjzhSjfk> implements IBaseDAO<TbYjzhSjfk> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhSjfkDAO.class);

	public TbYjzhSjfk findById(java.lang.String id) {
		log.debug("getting TbYjzhSjfk instance with id: " + id);
		try {
			TbYjzhSjfk instance = (TbYjzhSjfk) getSession().get(
					"mvc.dao.TbYjzhSjfk", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhSjfk instance) {
		log.debug("finding TbYjzhSjfk instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhSjfk")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbYjzhSjfk instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhSjfk as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TbYjzhSjfk instances");
		try {
			String queryString = "from TbYjzhSjfk";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhSjfk merge(TbYjzhSjfk detachedInstance) {
//		log.debug("merging TbYjzhSjfk instance");
//		try {
//			TbYjzhSjfk result = (TbYjzhSjfk) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhSjfk instance) {
		log.debug("attaching dirty TbYjzhSjfk instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhSjfk instance) {
		log.debug("attaching clean TbYjzhSjfk instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhSjfk>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhSjfk where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhSjfk> list = (List<TbYjzhSjfk>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhSjfk where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

