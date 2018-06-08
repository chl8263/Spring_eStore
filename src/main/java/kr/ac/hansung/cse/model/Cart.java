package kr.ac.hansung.cse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity	//테이블 생성하기 위한 어노테이션
public class Cart implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1426760065960296166L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy="cart" , cascade=CascadeType.ALL,fetch=FetchType.EAGER)	//one to Many 는 default 가 lazy 이다  , Many to one 은 default 가 eager 이다.
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	
	private double grandTotal;

}
