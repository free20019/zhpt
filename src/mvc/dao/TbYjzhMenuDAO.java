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


@Repository("tbYjzhMenuDAO")
public class TbYjzhMenuDAO extends   BaseDAOImpl<TbYjzhMenu> implements IBaseDAO<TbYjzhMenu> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhMenuDAO.class);

	public TbYjzhMenu findById(java.lang.String id) {
		log.debug("getting TbYjzhMenu instance with id: " + id);
		try {
			TbYjzhMenu instance = (TbYjzhMenu) getSession().get(
					"mvc.dao.TbYjzhMenu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhMenu instance) {
		log.debug("finding TbYjzhMenu instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhMenu")
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
		log.debug("finding TbYjzhMenu instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhMenu as model where model."
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
		log.debug("finding all TbYjzhMenu instances");
		try {
			String queryString = "from TbYjzhMenu";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhMenu merge(TbYjzhMenu detachedInstance) {
//		log.debug("merging TbYjzhMenu instance");
//		try {
//			TbYjzhMenu result = (TbYjzhMenu) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhMenu instance) {
		log.debug("attaching dirty TbYjzhMenu instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhMenu instance) {
		log.debug("attaching clean TbYjzhMenu instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhMenu>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhMenu where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhMenu> list = (List<TbYjzhMenu>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhMenu where 1=1 " + condition ;
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

