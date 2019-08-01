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


@Repository("tbYjzhSjczDAO")
public class TbYjzhSjczDAO extends   BaseDAOImpl<TbYjzhSjcz> implements IBaseDAO<TbYjzhSjcz> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhSjczDAO.class);

	public TbYjzhSjcz findById(java.lang.String id) {
		log.debug("getting TbYjzhSjcz instance with id: " + id);
		try {
			TbYjzhSjcz instance = (TbYjzhSjcz) getSession().get(
					"mvc.dao.TbYjzhSjcz", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhSjcz instance) {
		log.debug("finding TbYjzhSjcz instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhSjcz")
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
		log.debug("finding TbYjzhSjcz instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhSjcz as model where model."
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
		log.debug("finding all TbYjzhSjcz instances");
		try {
			String queryString = "from TbYjzhSjcz";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhSjcz merge(TbYjzhSjcz detachedInstance) {
//		log.debug("merging TbYjzhSjcz instance");
//		try {
//			TbYjzhSjcz result = (TbYjzhSjcz) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhSjcz instance) {
		log.debug("attaching dirty TbYjzhSjcz instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhSjcz instance) {
		log.debug("attaching clean TbYjzhSjcz instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhSjcz>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhSjcz where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhSjcz> list = (List<TbYjzhSjcz>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhSjcz where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

