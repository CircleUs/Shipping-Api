package us.circle.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.circle.base.BaseRequest;
import us.circle.base.CircleException;
import us.circle.model.Shipment;
import us.circle.model.TrackInfo;

public class LabelRequest extends BaseRequest {
	
	public static Shipment create(String shipKey, boolean isTest) throws CircleException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("shipKey", shipKey);
		params.put("isTest", Boolean.valueOf(isTest).toString());
		return call("LabelServer/create", "GET", params, Shipment.class);
	}
	
	public static Boolean cancel(String shipKey) throws CircleException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("shipKey", shipKey);
		return Boolean.valueOf(call("LabelServer/cancel", "GET", params));
	}
	
	public static Shipment downloadLabel(String shipKey, Boolean isCrop) throws CircleException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("shipKey", shipKey);
		if(isCrop != null){
			params.put("isCrop", isCrop.toString());
		}
		return call("LabelServer/downloadLabel", "GET", params, Shipment.class);
	}
	
	public static Shipment tracking(String shipKey) throws CircleException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("shipKey", shipKey);
		return call("LabelServer/tracking", "GET", params, Shipment.class);
	}
	
	public static List<TrackInfo> trackingNumber(String trackingNumber) throws CircleException{
		return callList(String.format("LabelServer/trackingNumber/%s", trackingNumber), "GET", null, TrackInfo.class);
	}
}
