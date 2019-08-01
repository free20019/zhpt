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


@Repository("tbYjzhInfoLogDAO")
public class TbYjzhInfoLogDAO extends   BaseDAOImpl<TbYjzhInfoLog> implements IBaseDAO<TbYjzhInfoLog> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhInfoLogDAO.class);

	public TbYjzhInfoLog findById(java.lang.String id) {
		log.debug("getting TbYjzhInfoLog instance with id: " + id);
		try {
			TbYjzhInfoLog instance = (TbYjzhInfoLog) getSession().get(
					"mvc.dao.TbYjzhInfoLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhInfoLog instance) {
		log.debug("finding TbYjzhInfoLog instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhInfoLog")
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
		log.debug("finding TbYjzhInfoLog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhInfoLog as model where model."
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
		log.debug("finding all TbYjzhInfoLog instances");
		try {
			String queryString = "from TbYjzhInfoLog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhInfoLog merge(TbYjzhInfoLog detachedInstance) {
//		log.debug("merging TbYjzhInfoLog instance");
//		try {
//			TbYjzhInfoLog result = (TbYjzhInfoLog) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhInfoLog instance) {
		log.debug("attaching dirty TbYjzhInfoLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhInfoLog instance) {
		log.debug("attaching clean TbYjzhInfoLog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhInfoLog>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhInfoLog where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhInfoLog> list = (List<TbYjzhInfoLog>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhInfoLog where 1=1 " + condition ;
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

