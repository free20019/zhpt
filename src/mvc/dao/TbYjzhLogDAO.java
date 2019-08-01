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


@Repository("tbYjzhLogDAO")
public class TbYjzhLogDAO extends   BaseDAOImpl<TbYjzhLog> implements IBaseDAO<TbYjzhLog> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhLogDAO.class);

	public TbYjzhLog findById(java.lang.String id) {
		log.debug("getting TbYjzhLog instance with id: " + id);
		try {
			TbYjzhLog instance = (TbYjzhLog) getSession().get(
					"mvc.dao.TbYjzhLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhLog instance) {
		log.debug("finding TbYjzhLog instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhLog")
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
		log.debug("finding TbYjzhLog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhLog as model where model."
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
		log.debug("finding all TbYjzhLog instances");
		try {
			String queryString = "from TbYjzhLog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhLog merge(TbYjzhLog detachedInstance) {
//		log.debug("merging TbYjzhLog instance");
//		try {
//			TbYjzhLog result = (TbYjzhLog) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhLog instance) {
		log.debug("attaching dirty TbYjzhLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhLog instance) {
		log.debug("attaching clean TbYjzhLog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhLog>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhLog where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhLog> list = (List<TbYjzhLog>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhLog where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

