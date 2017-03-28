package us.circle.model;



public class SimpleRateDTO {
	private Address toAddress;
	
	private Address fromAddress;
	
	private String serverLevel;
	
	private String server;
	
	private String packageType;
	
	private String confirmation;
	
	private Float weight; 

	public Address getToAddress() {
		return toAddress;
	}

	public void setToAddress(Address toAddress) {
		this.toAddress = toAddress;
	}

	public Address getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(Address fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getServerLevel() {
		return serverLevel;
	}

	public void setServerLevel(String serverLevel) {
		this.serverLevel = serverLevel;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}
}
