package us.circle.request;

import java.util.HashMap;
import java.util.Map;

import us.circle.base.BaseRequest;
import us.circle.base.CircleException;
import us.circle.model.Account;
import us.circle.model.CirclePackage;
import us.circle.model.CustomBase;
import us.circle.model.CustomDetail;
import us.circle.model.Shipment;

public class ShipmentRequest extends BaseRequest{
	
	public static Shipment create(Shipment shipment) throws CircleException{
		return call("ShipmentServer/create", "POST", shipment, Shipment.class);
	}
	
	public static Shipment retrieve(String shipKey) throws CircleException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("shipKey", shipKey);
		return call("ShipmentServer/retrieve", "GET", params, Shipment.class);
	}
	
	public static Shipment modifyShipment(Shipment shipment) throws CircleException{
		return call("ShipmentServer/modifyShipment", "PUT", shipment, Shipment.class);
	}
	
	public static Shipment modifyAccount(Account account, String shipKey) throws CircleException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", account);
		params.put("shipKey", shipKey);
		return call("ShipmentServer/modifyAccount", "PUT", params, Shipment.class);
	}
	
	public static Shipment modifyCustomBase(CustomBase customBase, String shipKey) throws CircleException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customBase", customBase);
		params.put("shipKey", shipKey);
		return call("ShipmentServer/modifyCustomBase", "PUT", params, Shipment.class);
	}
	
	public static Shipment modifyCustomDetail(CustomDetail customDetail, Integer index, String shipKey) throws CircleException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customDetail", customDetail);
		params.put("index", index);
		params.put("shipKey", shipKey);
		return call("ShipmentServer/modifyCustomDetail", "PUT", params, Shipment.class);
	}

	public static Shipment modifyPackage(CirclePackage circlePackage, Integer index, String shipKey) throws CircleException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("package", circlePackage);
		params.put("index", index);
		params.put("shipKey", shipKey);
		return call("ShipmentServer/modifyPackage", "PUT", params, Shipment.class);
	}
	
	public static Shipment deletePackage(String shipKey, Integer index) throws CircleException{
		if(index == null){
			return null;
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("shipKey", shipKey);
		params.put("index", index.toString());
		return call("ShipmentServer/deletePackage", "DELETE", params, Shipment.class);
	}
	
	public static Shipment deleteCustomDetail(String shipKey, Integer index) throws CircleException{
		if(index == null){
			return null;
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("shipKey", shipKey);
		params.put("index", index.toString());
		return call("ShipmentServer/deleteCustomDetail", "DELETE", params, Shipment.class);
	}
	
	public static Shipment addPackage(CirclePackage circlePackage, String shipKey) throws CircleException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("package", circlePackage);
		params.put("shipKey", shipKey);
		return call("ShipmentServer/addPackage", "POST", params, Shipment.class);
	}
	
	public static Shipment addCustomDetail(CustomDetail customDetail, String shipKey) throws CircleException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customDetail", customDetail);
		params.put("shipKey", shipKey);
		return call("ShipmentServer/addCustomDetail", "POST", params, Shipment.class);
	}
}
