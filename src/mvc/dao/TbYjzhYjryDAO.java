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


@Repository("tbYjzhYjryDAO")
public class TbYjzhYjryDAO extends   BaseDAOImpl<TbYjzhYjry> implements IBaseDAO<TbYjzhYjry> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhYjryDAO.class);

	public TbYjzhYjry findById(java.lang.String id) {
		log.debug("getting TbYjzhYjry instance with id: " + id);
		try {
			TbYjzhYjry instance = (TbYjzhYjry) getSession().get(
					"mvc.dao.TbYjzhYjry", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhYjry instance) {
		log.debug("finding TbYjzhYjry instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhYjry")
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
		log.debug("finding TbYjzhYjry instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhYjry as model where model."
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
		log.debug("finding all TbYjzhYjry instances");
		try {
			String queryString = "from TbYjzhYjry";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhYjry merge(TbYjzhYjry detachedInstance) {
//		log.debug("merging TbYjzhYjry instance");
//		try {
//			TbYjzhYjry result = (TbYjzhYjry) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhYjry instance) {
		log.debug("attaching dirty TbYjzhYjry instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhYjry instance) {
		log.debug("attaching clean TbYjzhYjry instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhYjry>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhYjry where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhYjry> list = (List<TbYjzhYjry>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhYjry where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

