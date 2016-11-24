package us.circle.model;

import java.math.BigDecimal;
import java.util.List;



public class CirclePackage{

    private Float weight;

    private Float length;

    private Float height;

    private Float width;

    private String weightUnit;

    private String sizeUnit;

    private BigDecimal insurancedeclvalue;

    private BigDecimal insurance3rdvalue;

    private String trackingNumber;

    private String tarckingStatus;
    
    private List<String> labels;
    
    private String fileType;
    
    private List<TrackDetail> trackDetails;

	public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit == null ? null : weightUnit.trim();
    }

    public String getSizeUnit() {
        return sizeUnit;
    }

    public void setSizeUnit(String sizeUnit) {
        this.sizeUnit = sizeUnit == null ? null : sizeUnit.trim();
    }

    public BigDecimal getInsurancedeclvalue() {
        return insurancedeclvalue;
    }

    public void setInsurancedeclvalue(BigDecimal insurancedeclvalue) {
        this.insurancedeclvalue = insurancedeclvalue;
    }

    public BigDecimal getInsurance3rdvalue() {
		return insurance3rdvalue;
	}

	public void setInsurance3rdvalue(BigDecimal insurance3rdvalue) {
		this.insurance3rdvalue = insurance3rdvalue;
	}

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber == null ? null : trackingNumber.trim();
    }

    public String getTarckingStatus() {
        return tarckingStatus;
    }

    public void setTarckingStatus(String tarckingStatus) {
        this.tarckingStatus = tarckingStatus == null ? null : tarckingStatus.trim();
    }

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<TrackDetail> getTrackDetails() {
		return trackDetails;
	}

	public void setTrackDetails(List<TrackDetail> trackDetails) {
		this.trackDetails = trackDetails;
	}

}