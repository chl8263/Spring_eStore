package kr.ac.hansung.cse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity	//product 만 entity 를 줘서 이것만 계속 다시만든다.
@Table(name="product") //없으면 class이름과 동일한 이름
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -567117648902464025L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //id 는 수동적으로 주는것이 아니라 자동으로 줘야 유닡크 하기때문에 넣는다 , 시퀀스 테이블을 만들지 않는다!
	@Column(name = "product_id")
	private int id;
	@NotEmpty(message = "The product name nust not be null")
	private String name;
	private String category;
	
	@Min(value=0, message = "The product price must not be less than zero")
	private int price;
	@NotEmpty(message = "The product manufacturer nust not be null")
	private String manufacturer;
	@Min(value=0, message = "The product unitInStock must not be less than zero")
	private int unitInStock;
	private String description;
	
	@Transient
	private MultipartFile productImage;
	
	private String imageFilename;
}
