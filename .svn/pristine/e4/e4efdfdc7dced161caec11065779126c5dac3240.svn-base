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


@Repository("tbYjzhRoleDAO")
public class TbYjzhRoleDAO extends   BaseDAOImpl<TbYjzhRole> implements IBaseDAO<TbYjzhRole> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhRoleDAO.class);

	public TbYjzhRole findById(java.lang.String id) {
		log.debug("getting TbYjzhRole instance with id: " + id);
		try {
			TbYjzhRole instance = (TbYjzhRole) getSession().get(
					"mvc.dao.TbYjzhRole", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhRole instance) {
		log.debug("finding TbYjzhRole instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhRole")
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
		log.debug("finding TbYjzhRole instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhRole as model where model."
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
		log.debug("finding all TbYjzhRole instances");
		try {
			String queryString = "from TbYjzhRole";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhRole merge(TbYjzhRole detachedInstance) {
//		log.debug("merging TbYjzhRole instance");
//		try {
//			TbYjzhRole result = (TbYjzhRole) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhRole instance) {
		log.debug("attaching dirty TbYjzhRole instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhRole instance) {
		log.debug("attaching clean TbYjzhRole instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhRole>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhRole where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhRole> list = (List<TbYjzhRole>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhRole where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

