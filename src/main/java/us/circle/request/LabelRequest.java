package us.circle.request;

import java.util.HashMap;
import java.util.Map;

import us.circle.base.BaseRequest;
import us.circle.base.CircleException;
import us.circle.model.Shipment;

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
	
	public static Shipment downloadLabel(String shipKey) throws CircleException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("shipKey", shipKey);
		return call("LabelServer/downloadLabel", "GET", params, Shipment.class);
	}
}
