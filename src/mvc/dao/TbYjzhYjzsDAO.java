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


@Repository("tbYjzhYjzsDAO")
public class TbYjzhYjzsDAO extends   BaseDAOImpl<TbYjzhYjzs> implements IBaseDAO<TbYjzhYjzs> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhYjzsDAO.class);

	public TbYjzhYjzs findById(java.lang.String id) {
		log.debug("getting TbYjzhYjzs instance with id: " + id);
		try {
			TbYjzhYjzs instance = (TbYjzhYjzs) getSession().get(
					"mvc.dao.TbYjzhYjzs", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhYjzs instance) {
		log.debug("finding TbYjzhYjzs instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhYjzs")
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
		log.debug("finding TbYjzhYjzs instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhYjzs as model where model."
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
		log.debug("finding all TbYjzhYjzs instances");
		try {
			String queryString = "from TbYjzhYjzs";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhYjzs merge(TbYjzhYjzs detachedInstance) {
//		log.debug("merging TbYjzhYjzs instance");
//		try {
//			TbYjzhYjzs result = (TbYjzhYjzs) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhYjzs instance) {
		log.debug("attaching dirty TbYjzhYjzs instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhYjzs instance) {
		log.debug("attaching clean TbYjzhYjzs instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhYjzs>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhYjzs where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhYjzs> list = (List<TbYjzhYjzs>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhYjzs where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

