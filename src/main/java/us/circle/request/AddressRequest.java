package us.circle.request;

import java.util.HashMap;
import java.util.Map;

import us.circle.base.BaseRequest;
import us.circle.base.CircleException;
import us.circle.model.Address;

public class AddressRequest extends BaseRequest{
	
	public static Address create(Address address) throws CircleException{
		return call("AddressServer/create", "POST", address, Address.class);
	}
	
	public static Address createAndVerify(Address address) throws CircleException{
		return call("AddressServer/createAndVerify", "POST", address, Address.class);
	}
	
	public static Address retrieve(String adrKey) throws CircleException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("adrKey", adrKey);
		return call("AddressServer/retrieve", "GET", params, Address.class);
	}
	
	public static Address retrieveAndVerify(String adrKey) throws CircleException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("adrKey", adrKey);
		return call("AddressServer/retrieveAndVerify", "GET", params, Address.class);
	}
	
	public static Address verify(Address address) throws CircleException{
		return call("AddressServer/verify", "POST", address, Address.class);
	}
}
