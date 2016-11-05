package us.circle.model;

import java.math.BigDecimal;

public class CustomDetail{

	private String name;

	private String harMonizationCode;

	private Integer quantity;

	private BigDecimal value;

	private Float weight;

	private String manufactureCountry;

	private String isTotal;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHarMonizationCode() {
		return harMonizationCode;
	}

	public void setHarMonizationCode(String harMonizationCode) {
		this.harMonizationCode = harMonizationCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getManufactureCountry() {
		return manufactureCountry;
	}

	public void setManufactureCountry(String manufactureCountry) {
		this.manufactureCountry = manufactureCountry;
	}

	public String getIsTotal() {
		return isTotal;
	}

	public void setIsTotal(String isTotal) {
		this.isTotal = isTotal;
	}
}

