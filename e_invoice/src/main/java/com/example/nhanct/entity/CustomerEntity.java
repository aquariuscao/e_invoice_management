package com.example.nhanct.entity;

import com.example.nhanct.annotation.Email;
import com.example.nhanct.annotation.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customer")
public class CustomerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "mst")
//	@NotBlank(message = "Please Input This Field!")
	private String mst;
	
	@Column(name="customer_name")
//	@NotBlank(message = "Please Input This Field!")
	private String customerName;
	
	@Column(name="phone")
//	@NotBlank(message = "Please Input This Field!")
	private String phone;
	
	@Column(name="email")
	private String email;
	
	@Column(name="address")
//	@NotBlank(message = "Please Input This Field!")
//	@Length(min = 15 , message = "This field length must be more than 15 characters!")
	private String address;
	
//	@Column(name="user_name")
//	@NotBlank(message = "Please Input This Field!")
//	private String userName;
//
//	@JsonIgnore
//	@Column(name="password")
//	@NotBlank(message = "Please Input This Field!")
//	private String password;
//
//	@Column(name = "reset_token")
//	private String resetToken;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<InvoiceEntity> invoiceEntityList;

//	@OneToMany(mappedBy = "khach_hang")
//	private List<ReviewEntity> review;

}
