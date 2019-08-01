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


@Repository("tbYjzhSjwzDAO")
public class TbYjzhSjwzDAO extends   BaseDAOImpl<TbYjzhSjwz> implements IBaseDAO<TbYjzhSjwz> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhSjwzDAO.class);

	public TbYjzhSjwz findById(java.lang.String id) {
		log.debug("getting TbYjzhSjwz instance with id: " + id);
		try {
			TbYjzhSjwz instance = (TbYjzhSjwz) getSession().get(
					"mvc.dao.TbYjzhSjwz", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhSjwz instance) {
		log.debug("finding TbYjzhSjwz instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhSjwz")
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
		log.debug("finding TbYjzhSjwz instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhSjwz as model where model."
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
		log.debug("finding all TbYjzhSjwz instances");
		try {
			String queryString = "from TbYjzhSjwz";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhSjwz merge(TbYjzhSjwz detachedInstance) {
//		log.debug("merging TbYjzhSjwz instance");
//		try {
//			TbYjzhSjwz result = (TbYjzhSjwz) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhSjwz instance) {
		log.debug("attaching dirty TbYjzhSjwz instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhSjwz instance) {
		log.debug("attaching clean TbYjzhSjwz instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhSjwz>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhSjwz where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhSjwz> list = (List<TbYjzhSjwz>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhSjwz where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

