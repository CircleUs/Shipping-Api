package apiTest;

import java.util.HashMap;
import java.util.Map;

import us.circle.Ghon.Ghon;
import us.circle.base.Circle;
import us.circle.base.CircleException;
import us.circle.ghonHttp.GhonHttp;
import us.circle.ghonHttp.GhonHttpException;
import us.circle.model.Address;
import us.circle.request.AddressRequest;

public class TestAddress {
	
	public static Ghon ghon = new Ghon();
	
	public static GhonHttp ghonHttp = new GhonHttp();
	
	public static void main(String[] args) {
		Address add = new Address();
		add.setCountry("US");
		add.setStreet1("18596 SILHOUETTE LN");
		add.setZipcode("96007-9193");
		add.setCity("ANDERSON");
		add.setFullName("RES");
		add.setCompany("RES");
		add.setPhoneNumber("8678289762");
		add.setState("CA");
//		try {
//			System.out.println(ghon.objectToGhonEle(AddressRequest.retrieveAndVerify("5a4e2ebc65f9d8b2353ac39ad75d7347")).toJSON(false));
//			Map<String, String> headers = new HashMap<String, String>();
//			headers.put("Authorization", "");
//			headers.put("Content-Type", "application/json");
//			ghon.setShowNull(false);
//			String json = ghon.objectToGhonEle(add).toJSON(false);
//			System.out.println(json);
//			
//			byte[] bytes = ghonHttp.call(String.format("%s/AddressServer/createAndVerify", Circle.basePath), 
//					"POST", json.getBytes(), headers, 3000, 10000, false);
//			System.out.println(new String(bytes));
//		} catch (GhonHttpException e) {
//			e.printStackTrace();
//			System.out.println(new String(e.getBytes()));
//		}
	}
}
