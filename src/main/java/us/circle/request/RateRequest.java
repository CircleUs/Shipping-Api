package us.circle.request;

import java.util.HashMap;
import java.util.Map;

import us.circle.base.BaseRequest;
import us.circle.base.CircleException;
import us.circle.model.Rate;

public class RateRequest extends BaseRequest{

	
	public static Rate rate(String shipKey) throws CircleException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("shipKey", shipKey);
		return call("RateServer/rate", "POST", params, Rate.class);
	}
}
