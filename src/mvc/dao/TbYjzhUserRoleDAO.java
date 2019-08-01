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


@Repository("tbYjzhUserRoleDAO")
public class TbYjzhUserRoleDAO extends   BaseDAOImpl<TbYjzhUserRole> implements IBaseDAO<TbYjzhUserRole> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhUserRoleDAO.class);

	public TbYjzhUserRole findById(java.lang.String id) {
		log.debug("getting TbYjzhUserRole instance with id: " + id);
		try {
			TbYjzhUserRole instance = (TbYjzhUserRole) getSession().get(
					"mvc.dao.TbYjzhUserRole", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhUserRole instance) {
		log.debug("finding TbYjzhUserRole instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhUserRole")
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
		log.debug("finding TbYjzhUserRole instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhUserRole as model where model."
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
		log.debug("finding all TbYjzhUserRole instances");
		try {
			String queryString = "from TbYjzhUserRole";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhUserRole merge(TbYjzhUserRole detachedInstance) {
//		log.debug("merging TbYjzhUserRole instance");
//		try {
//			TbYjzhUserRole result = (TbYjzhUserRole) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhUserRole instance) {
		log.debug("attaching dirty TbYjzhUserRole instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhUserRole instance) {
		log.debug("attaching clean TbYjzhUserRole instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhUserRole>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhUserRole where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhUserRole> list = (List<TbYjzhUserRole>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhUserRole where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

