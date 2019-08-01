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


@Repository("tbYjzhPermissionDAO")
public class TbYjzhPermissionDAO extends   BaseDAOImpl<TbYjzhPermission> implements IBaseDAO<TbYjzhPermission> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhPermissionDAO.class);

	public TbYjzhPermission findById(java.lang.String id) {
		log.debug("getting TbYjzhPermission instance with id: " + id);
		try {
			TbYjzhPermission instance = (TbYjzhPermission) getSession().get(
					"mvc.dao.TbYjzhPermission", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhPermission instance) {
		log.debug("finding TbYjzhPermission instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhPermission")
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
		log.debug("finding TbYjzhPermission instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhPermission as model where model."
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
		log.debug("finding all TbYjzhPermission instances");
		try {
			String queryString = "from TbYjzhPermission";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhPermission merge(TbYjzhPermission detachedInstance) {
//		log.debug("merging TbYjzhPermission instance");
//		try {
//			TbYjzhPermission result = (TbYjzhPermission) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhPermission instance) {
		log.debug("attaching dirty TbYjzhPermission instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhPermission instance) {
		log.debug("attaching clean TbYjzhPermission instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhPermission>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhPermission where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhPermission> list = (List<TbYjzhPermission>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhPermission where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

