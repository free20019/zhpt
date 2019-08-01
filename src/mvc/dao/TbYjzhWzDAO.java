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


@Repository("tbYjzhWzDAO")
public class TbYjzhWzDAO extends   BaseDAOImpl<TbYjzhWz> implements IBaseDAO<TbYjzhWz> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhWzDAO.class);

	public TbYjzhWz findById(java.lang.String id) {
		log.debug("getting TbYjzhWz instance with id: " + id);
		try {
			TbYjzhWz instance = (TbYjzhWz) getSession().get(
					"mvc.dao.TbYjzhWz", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhWz instance) {
		log.debug("finding TbYjzhWz instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhWz")
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
		log.debug("finding TbYjzhWz instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhWz as model where model."
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
		log.debug("finding all TbYjzhWz instances");
		try {
			String queryString = "from TbYjzhWz";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhWz merge(TbYjzhWz detachedInstance) {
//		log.debug("merging TbYjzhWz instance");
//		try {
//			TbYjzhWz result = (TbYjzhWz) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhWz instance) {
		log.debug("attaching dirty TbYjzhWz instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhWz instance) {
		log.debug("attaching clean TbYjzhWz instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhWz>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhWz where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhWz> list = (List<TbYjzhWz>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhWz where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

