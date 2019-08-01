package mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository("tbYjzhSjDAO")
public class TbYjzhSjDAO extends   BaseDAOImpl<TbYjzhSj> implements IBaseDAO<TbYjzhSj> {
	private static final Logger log = LoggerFactory
			.getLogger(TbYjzhSjDAO.class);

	public TbYjzhSj findById(java.lang.String id) {
		log.debug("getting TbYjzhSj instance with id: " + id);
		try {
			TbYjzhSj instance = (TbYjzhSj) getSession().get(
					"mvc.dao.TbYjzhSj", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbYjzhSj instance) {
		log.debug("finding TbYjzhSj instance by example");
		try {
			List results = getSession().createCriteria("mvc.dao.TbYjzhSj")
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
		log.debug("finding TbYjzhSj instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbYjzhSj as model where model."
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
		log.debug("finding all TbYjzhSj instances");
		try {
			String queryString = "from TbYjzhSj";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

//	public TbYjzhSj merge(TbYjzhSj detachedInstance) {
//		log.debug("merging TbYjzhSj instance");
//		try {
//			TbYjzhSj result = (TbYjzhSj) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}

	public void attachDirty(TbYjzhSj instance) {
		log.debug("attaching dirty TbYjzhSj instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbYjzhSj instance) {
		log.debug("attaching clean TbYjzhSj instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public Map<String,List<TbYjzhSj>> getAllPage(Map paramMap, int page, int pageSize) {
		String hql = "from TbYjzhSj where 1=1   ";
		Map map = new HashMap();
		StringBuilder condition = new StringBuilder();
		String address = String.valueOf(paramMap.get("address"));
		if(!StringUtils.isEmpty(address) && !address.toLowerCase().equals("null")){
			condition.append(" and address like '%" +address+"%'");
		}
		List<TbYjzhSj> list = (List<TbYjzhSj>) findByHQL(hql + condition +" order by sj desc ",paramMap , page, pageSize);
		hql = "select count(*) from TbYjzhSj where 1=1 " + condition ;
		System.out.println("##### :"+ hql);
		long count = getCountByUniqueResult(hql,paramMap);
		map.put("datas", list);
		map.put("count", count);
		return map;
	}
}

