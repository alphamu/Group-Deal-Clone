package com.groupdealclone.app.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class ShoppingCart {

	private Long		id;
	private Account		account;
	private List<Deal>	deals;

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToMany
	public List<Deal> getDeals() {
		return deals;
	}

	public void setDeals(List<Deal> deals) {
		this.deals = deals;
	}

	@Transient
	public BigDecimal getTotal() {
		BigDecimal total = new BigDecimal(0);
		for (Deal deal : deals) {
			total = total.add(deal.getPrice());
		}
		return total;
	}

}
