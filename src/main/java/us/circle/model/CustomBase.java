package us.circle.model;

import java.util.List;

public class CustomBase {

	private String shipmentPurpose;

	private String valueUnit;
	
	private String weightUnit;

	private String eei;

	private String aes;
	
    private String paymentType;
    
    private String fileType;
	
	private List<String> invoices;

	public String getShipmentPurpose() {
		return shipmentPurpose;
	}

	public void setShipmentPurpose(String shipmentPurpose) {
		this.shipmentPurpose = shipmentPurpose;
	}

	public String getValueUnit() {
		return valueUnit;
	}

	public void setValueUnit(String valueUnit) {
		this.valueUnit = valueUnit;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	public java.lang.String getEei() {
		return eei;
	}

	public void setEei(String eei) {
		this.eei = eei;
	}

	public String getAes() {
		return aes;
	}

	public void setAes(String aes) {
		this.aes = aes;
	}
	
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public List<String> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<String> invoices) {
		this.invoices = invoices;
	}

}

