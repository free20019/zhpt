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


@Repository("tbYjzhYjyaDAO")
public class TbYjzhYjyaDAO extends   BaseDAOImpl<TbYjzhYjya> implements IBaseDAO<TbYjzhYjya> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhYjyaDAO.class);

	public TbYjzhYjya findById(java.lang.String id) {
		log.debug("getting TbYjzhYjya instance with id: " + id);
		try {
			TbYjzhYjya instance = (TbYjzhYjya) getSession().get(
					"mvc.dao.TbYjzhYjya", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhYjya instance) {
		log.debug("finding TbYjzhYjya instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhYjya")
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
		log.debug("finding TbYjzhYjya instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhYjya as model where model."
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
		log.debug("finding all TbYjzhYjya instances");
		try {
			String queryString = "from TbYjzhYjya";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhYjya merge(TbYjzhYjya detachedInstance) {
//		log.debug("merging TbYjzhYjya instance");
//		try {
//			TbYjzhYjya result = (TbYjzhYjya) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhYjya instance) {
		log.debug("attaching dirty TbYjzhYjya instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhYjya instance) {
		log.debug("attaching clean TbYjzhYjya instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhYjya>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhYjya where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhYjya> list = (List<TbYjzhYjya>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhYjya where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
	
	public List<String> getAllNames() {
		return (List<String>) findBySQL("select id,name from TB_YJZH_YJYA");
	}

	public String getContent(String id) {
		List<String> list =   (List<String>) findBySQL("select content from TB_YJZH_YJYA where id ='"  + id +"'");
		return list.get(0);
	}
}

