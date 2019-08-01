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


@Repository("tbYjzhZbryDAO")
public class TbYjzhZbryDAO extends   BaseDAOImpl<TbYjzhZbry> implements IBaseDAO<TbYjzhZbry> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhZbryDAO.class);

	public TbYjzhZbry findById(java.lang.String id) {
		log.debug("getting TbYjzhZbry instance with id: " + id);
		try {
			TbYjzhZbry instance = (TbYjzhZbry) getSession().get(
					"mvc.dao.TbYjzhZbry", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhZbry instance) {
		log.debug("finding TbYjzhZbry instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhZbry")
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
		log.debug("finding TbYjzhZbry instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhZbry as model where model."
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
		log.debug("finding all TbYjzhZbry instances");
		try {
			String queryString = "from TbYjzhZbry";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhZbry merge(TbYjzhZbry detachedInstance) {
//		log.debug("merging TbYjzhZbry instance");
//		try {
//			TbYjzhZbry result = (TbYjzhZbry) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhZbry instance) {
		log.debug("attaching dirty TbYjzhZbry instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhZbry instance) {
		log.debug("attaching clean TbYjzhZbry instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhZbry>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhZbry where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhZbry> list = (List<TbYjzhZbry>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhZbry where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

