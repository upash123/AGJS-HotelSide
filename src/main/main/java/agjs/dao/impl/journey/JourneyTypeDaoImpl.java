package agjs.dao.impl.journey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.journey.JourneyTypePo;
import agjs.dao.journey.JourneyTypeDao;

@Repository
public class JourneyTypeDaoImpl implements JourneyTypeDao {

	@PersistenceContext
	private Session session;

	@Override
	public Serializable insert(JourneyTypePo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(JourneyTypePo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(JourneyTypePo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JourneyTypePo select(Integer id) {

		if (id != null) {
			return session.get(JourneyTypePo.class, id);
		} else {
			return null;
		}
	}

	@Override
	public List<JourneyTypePo> select() {

		List<JourneyTypePo> journeyTypePoList = new ArrayList<JourneyTypePo>();
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<JourneyTypePo> criteriaQuery = criteriaBuilder.createQuery(JourneyTypePo.class);

			Root<JourneyTypePo> root = criteriaQuery.from(JourneyTypePo.class);
			criteriaQuery.select(root);

			Query<JourneyTypePo> query = session.createQuery(criteriaQuery);
			journeyTypePoList = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return journeyTypePoList;
	}

	@Override
	public int selectIdByName(String typeName) {

		String hql = "from JourneyTypePo where typeName = :name";
		System.out.println("ty=" + typeName);
		JourneyTypePo po = new JourneyTypePo();
		po = session.createQuery(hql, JourneyTypePo.class).setParameter("name", typeName).uniqueResult();

//		JourneyTypePo po = session.createQuery(hql, JourneyTypePo.class)
//				.setParameter("name", typeName).uniqueResult();

		System.out.println("results=" + po);

		return po.getTypeId();
	}

	@Override
	public List<JourneyTypePo> select(Integer[] idList) {
		// TODO Auto-generated method stub
		return null;
	}

}
