package agjs.dao.impl.order;


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
import agjs.bean.order.SalesOrderStatusPo;
import agjs.dao.order.SalesOrderStatusDao;

@Repository
public class SalesOrderStatusDaoImpl implements SalesOrderStatusDao{
	@PersistenceContext
	private Session session;
	
	@Override
	public List<SalesOrderStatusPo> select() {
		List<SalesOrderStatusPo> salesOrderStatusPoList = new ArrayList<SalesOrderStatusPo>();
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<SalesOrderStatusPo> criteriaQuery = criteriaBuilder.createQuery(SalesOrderStatusPo.class);
			
			Root<SalesOrderStatusPo> root = criteriaQuery.from(SalesOrderStatusPo.class);
			criteriaQuery.select(root);
//			criteriaQuery.orderBy(criteriaBuilder.desc(root.get("salesOrderStatusId")));

			Query<SalesOrderStatusPo> query = session.createQuery(criteriaQuery);
			salesOrderStatusPoList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salesOrderStatusPoList;
	}
	
	
	@Override
	public String selectNameById(Integer id) {
		String hql = "from SalesOrderStatusPo where salesOrderStatusId = :id";
		System.out.println("status id = " + id);
		SalesOrderStatusPo po = new SalesOrderStatusPo();
		po = session.createQuery(hql, SalesOrderStatusPo.class).setParameter("id", id).uniqueResult();

		System.out.println("results=" + po);

		return po.getSalesOrderStatus();
	}
	
	@Override
	public Integer selectIdByName(String statusName) {
		String hql = "from SalesOrderStatusPo where salesOrderStatus = :name";
		System.out.println("status name = " + statusName);
		SalesOrderStatusPo po = new SalesOrderStatusPo();
		po = session.createQuery(hql, SalesOrderStatusPo.class).setParameter("name", statusName).uniqueResult();

		System.out.println("results=" + po);

		return po.getSalesOrderStatusId();
	}

	
	
	
	@Override
	public SalesOrderStatusPo insert(SalesOrderStatusPo salesOrderStatus) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SalesOrderStatusPo update(SalesOrderStatusPo salesOrderStatus) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SalesOrderStatusPo delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
