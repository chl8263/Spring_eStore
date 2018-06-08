package kr.ac.hansung.cse.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.CartItem;

@Repository
@Transactional
public class CartItemDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addCartItem(CartItem cartItem) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartItem);
		session.flush();
	}	
	
	public void removeCartItem (CartItem cartItem) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(cartItem);
		session.flush();
	}
	
	public void removeAllCartItems(Cart cart) {
		List<CartItem> cartItems = cart.getCartItems();	//주의깊게 보자	, cart 의 FetchType 을 eager 로 줘서 이걸 읽으면 자동으로 가져온다 lazy로 주면 null 이 넘어 올것이다.
		
		for(CartItem item : cartItems) {
			removeCartItem(item);
		}
	}
	@SuppressWarnings("unchecked")
	public CartItem getCartItemByProductId(int cartId, int productId) {
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<CartItem> query = session.createQuery("from CartItem where cart.id=? and product.id=?");
		query.setParameter(0, cartId);
		query.setParameter(1, productId);
		
		return (CartItem) query.getSingleResult();
	}
}
