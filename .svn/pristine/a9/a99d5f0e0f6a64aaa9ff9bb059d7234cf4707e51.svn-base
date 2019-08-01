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


@Repository("tbYjzhPgDAO")
public class TbYjzhPgDAO extends   BaseDAOImpl<TbYjzhPg> implements IBaseDAO<TbYjzhPg> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhPgDAO.class);

	public TbYjzhPg findById(java.lang.String id) {
		log.debug("getting TbYjzhPg instance with id: " + id);
		try {
			TbYjzhPg instance = (TbYjzhPg) getSession().get(
					"mvc.dao.TbYjzhPg", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhPg instance) {
		log.debug("finding TbYjzhPg instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhPg")
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
		log.debug("finding TbYjzhPg instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhPg as model where model."
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
		log.debug("finding all TbYjzhPg instances");
		try {
			String queryString = "from TbYjzhPg";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhPg merge(TbYjzhPg detachedInstance) {
//		log.debug("merging TbYjzhPg instance");
//		try {
//			TbYjzhPg result = (TbYjzhPg) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhPg instance) {
		log.debug("attaching dirty TbYjzhPg instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhPg instance) {
		log.debug("attaching clean TbYjzhPg instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhPg>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhPg where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhPg> list = (List<TbYjzhPg>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhPg where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

