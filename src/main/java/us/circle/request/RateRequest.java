package us.circle.request;

import java.util.HashMap;
import java.util.Map;

import us.circle.base.BaseRequest;
import us.circle.base.CircleException;
import us.circle.model.Rate;
import us.circle.model.SimpleRateDTO;

public class RateRequest extends BaseRequest{

	
	public static Rate rate(String shipKey) throws CircleException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("shipKey", shipKey);
		return call("RateServer/rate", "POST", params, Rate.class);
	}
	
	public static Rate rate(SimpleRateDTO simpleRate)throws CircleException{
		return call("RateServer/simpleRate", "POST", simpleRate, Rate.class);
	}
}
