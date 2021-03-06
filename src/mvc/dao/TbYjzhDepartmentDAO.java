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


@Repository("tbYjzhDepartmentDAO")
public class TbYjzhDepartmentDAO extends   BaseDAOImpl<TbYjzhDepartment> implements IBaseDAO<TbYjzhDepartment> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhDepartmentDAO.class);

	public TbYjzhDepartment findById(java.lang.String id) {
		log.debug("getting TbYjzhDepartment instance with id: " + id);
		try {
			TbYjzhDepartment instance = (TbYjzhDepartment) getSession().get(
					"mvc.dao.TbYjzhDepartment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhDepartment instance) {
		log.debug("finding TbYjzhDepartment instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhDepartment")
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
		log.debug("finding TbYjzhDepartment instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhDepartment as model where model."
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
		log.debug("finding all TbYjzhDepartment instances");
		try {
			String queryString = "from TbYjzhDepartment";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhDepartment merge(TbYjzhDepartment detachedInstance) {
//		log.debug("merging TbYjzhDepartment instance");
//		try {
//			TbYjzhDepartment result = (TbYjzhDepartment) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhDepartment instance) {
		log.debug("attaching dirty TbYjzhDepartment instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhDepartment instance) {
		log.debug("attaching clean TbYjzhDepartment instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhDepartment>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhDepartment where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhDepartment> list = (List<TbYjzhDepartment>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhDepartment where 1=1 " + condition ;
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

