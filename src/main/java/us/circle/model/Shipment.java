package us.circle.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Shipment {
	
	private String key;

    private String status;

    private Address toAddress;
    
    private Address fromAddress;

    private String server;

    private String serverLevel;

    private String packageType;

    private String dropOffType;

    private String confirmation;

    private String reference;

    private Account account;

    private String shipmentNo;

    private String scanShipId;

    private Date shipDate;
    
    private List<CirclePackage> packages;
    
    private CustomBase customBase;
    
    private List<CustomDetail> customDetails;
    
    private Rate rate;


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server == null ? null : server.trim();
    }

    public String getServerLevel() {
        return serverLevel;
    }

    public void setServerLevel(String serverLevel) {
        this.serverLevel = serverLevel == null ? null : serverLevel.trim();
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType == null ? null : packageType.trim();
    }

    public String getDropOffType() {
        return dropOffType;
    }

    public void setDropOffType(String dropOffType) {
        this.dropOffType = dropOffType == null ? null : dropOffType.trim();
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation == null ? null : confirmation.trim();
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference == null ? null : reference.trim();
    }

    public String getShipmentNo() {
        return shipmentNo;
    }

    public void setShipmentNo(String shipmentNo) {
        this.shipmentNo = shipmentNo == null ? null : shipmentNo.trim();
    }

    public String getScanShipId() {
        return scanShipId;
    }

    public void setScanShipId(String scanShipId) {
        this.scanShipId = scanShipId == null ? null : scanShipId.trim();
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<CirclePackage> getPackages() {
		if(packages == null){
			packages = new ArrayList<CirclePackage>();
		}
		return packages;
	}

	public void setPackages(List<CirclePackage> packages) {
		this.packages = packages;
	}

	public CustomBase getCustomBase() {
		return customBase;
	}

	public void setCustomBase(CustomBase customBase) {
		this.customBase = customBase;
	}

	public List<CustomDetail> getCustomDetails() {
		if(customDetails == null){
			customDetails = new ArrayList<CustomDetail>();
		}
		return customDetails;
	}

	public void setCustomDetails(List<CustomDetail> customDetails) {
		this.customDetails = customDetails;
	}

	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}
}