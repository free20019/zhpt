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


@Repository("tbYjzhUserDAO")
public class TbYjzhUserDAO extends   BaseDAOImpl<TbYjzhUser> implements IBaseDAO<TbYjzhUser> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhUserDAO.class);

	public TbYjzhUser findById(java.lang.String id) {
		log.debug("getting TbYjzhUser instance with id: " + id);
		try {
			TbYjzhUser instance = (TbYjzhUser) getSession().get(
					"mvc.dao.TbYjzhUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhUser instance) {
		log.debug("finding TbYjzhUser instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhUser")
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
		log.debug("finding TbYjzhUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhUser as model where model."
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
		log.debug("finding all TbYjzhUser instances");
		try {
			String queryString = "from TbYjzhUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhUser merge(TbYjzhUser detachedInstance) {
//		log.debug("merging TbYjzhUser instance");
//		try {
//			TbYjzhUser result = (TbYjzhUser) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhUser instance) {
		log.debug("attaching dirty TbYjzhUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhUser instance) {
		log.debug("attaching clean TbYjzhUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhUser>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhUser where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		List<TbYjzhUser> list = (List<TbYjzhUser>) findByHQL(hql + condition +"  ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhUser where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}

	public boolean login() {
		// TODO Auto-generated method stub
		return false;
	}
}

