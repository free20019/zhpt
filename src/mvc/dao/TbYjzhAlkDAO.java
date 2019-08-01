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


@Repository("tbYjzhAlkDAO")
public class TbYjzhAlkDAO extends   BaseDAOImpl<TbYjzhAlk> implements IBaseDAO<TbYjzhAlk> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhAlkDAO.class);

	public TbYjzhAlk findById(java.lang.String id) {
		log.debug("getting TbYjzhAlk instance with id: " + id);
		try {
			TbYjzhAlk instance = (TbYjzhAlk) getSession().get(
					"mvc.dao.TbYjzhAlk", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhAlk instance) {
		log.debug("finding TbYjzhAlk instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhAlk")
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
		log.debug("finding TbYjzhAlk instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhAlk as model where model."
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
		log.debug("finding all TbYjzhAlk instances");
		try {
			String queryString = "from TbYjzhAlk";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhAlk merge(TbYjzhAlk detachedInstance) {
//		log.debug("merging TbYjzhAlk instance");
//		try {
//			TbYjzhAlk result = (TbYjzhAlk) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhAlk instance) {
		log.debug("attaching dirty TbYjzhAlk instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhAlk instance) {
		log.debug("attaching clean TbYjzhAlk instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhAlk>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TB_YJZH_Alk where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhAlk> list = (List<TbYjzhAlk>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TB_YJZH_Alk where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
	
	public List<String> getAllNames() {
		return (List<String>) findBySQL("select id,name from TB_YJZH_Alk");
	}

	public String getContent(String id) {
		List<String> list =   (List<String>) findBySQL("select content from TB_YJZH_Alk where id ='"  + id +"'");
		return list.get(0);
	}
}

