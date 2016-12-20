package us.circle.model;

import java.util.List;

public class TrackInfo {
	private String trackingNumber;
	
	private String carrier;
	
	private String carrierService;
	
	private List<TrackDetail> trackDetails;

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getCarrierService() {
		return carrierService;
	}

	public void setCarrierService(String carrierService) {
		this.carrierService = carrierService;
	}

	public List<TrackDetail> getTrackDetails() {
		return trackDetails;
	}

	public void setTrackDetails(List<TrackDetail> trackDetails) {
		this.trackDetails = trackDetails;
	}
}
