package com.techmark.techmarkwebsite.repositories;

import com.techmark.techmarkwebsite.models.Embeddables.OrderDetailId;
import com.techmark.techmarkwebsite.models.Order;
import com.techmark.techmarkwebsite.models.OrderDetail;
import com.techmark.techmarkwebsite.repositories.base.OrderDetailRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDetailSqlRepository implements OrderDetailRepository {
	private SessionFactory factory;
	
	@Autowired
	public OrderDetailSqlRepository(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void create(OrderDetail orderDetail) {
		try(Session session = factory.openSession()) {
			session.beginTransaction();
			session.save(orderDetail);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public OrderDetail getById(OrderDetailId orderDetailId) {
		OrderDetail orderDetail = null;
		try(Session session = factory.openSession()) {
			session.beginTransaction();
			orderDetail = session.get(OrderDetail.class, orderDetailId);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return orderDetail;
	}
	
	@Override
	public List<OrderDetail> getAll() {
		List<OrderDetail> orderDetailList = new ArrayList<>();
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			orderDetailList = session.createQuery("from OrderDetail").list();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return orderDetailList;
	}
	
	@Override
	public void update(OrderDetailId orderDetailId, OrderDetail updateOrderDetail) {
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			OrderDetail orderDetail = session.get(OrderDetail.class, orderDetailId);
			orderDetail.setProductPrice(updateOrderDetail.getProductPrice());
			orderDetail.setQuantity(updateOrderDetail.getQuantity());
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public void delete(OrderDetailId orderDetailId) {
		OrderDetail orderDetail = getById(orderDetailId);
		try (Session session = factory.openSession()){
			session.beginTransaction();
			session.delete(orderDetail);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public List<OrderDetail> getAllByOrderId(int orderId) {
		List<OrderDetail> orderDetailList = new ArrayList<>();
		try(Session session = factory.openSession()){
			session.beginTransaction();
			orderDetailList = session.get(Order.class,orderId).getOrderDetailList();
			session.getTransaction().commit();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return orderDetailList;
	}
}
