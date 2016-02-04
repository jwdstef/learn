package org.zgf.learn.mybatis.entity.one2one;
/**
 * 公交卡
 */
public class PassengerCard {
	
	private Integer id ;
	
	private String cardNo;
	
	public PassengerCard() {
		super();
	}

	public PassengerCard(Integer id, String cardNo) {
		this.id = id;
		this.cardNo = cardNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Override
	public String toString() {
		return "PassengerCard [id=" + id + ", cardNo=" + cardNo + "]";
	}

}
