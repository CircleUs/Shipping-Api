package test;

import us.circle.Ghon.Ghon;
import us.circle.base.Circle;
import us.circle.base.CircleException;
import us.circle.model.Address;
import us.circle.request.AddressRequest;

public class TestAddress {
	
	public static Ghon ghon = new Ghon();
	
	public static void main(String[] args) {
		Circle.basePath = "https://api.circle.us";
		Circle.apiKey = "API_a7da5fd80ea45a7ac084648a4d0dfe23";
		Address add = new Address();
		add.setCountry("US");
		add.setStreet1("201 Ruthar Drive, Suite 7");
		add.setZipcode("19711");
		add.setCity("Newark");
		add.setFullName("Circle.Us");
		add.setCompany("Circle US");
		add.setPhoneNumber("302-283-9881");
		add.setState("DE");
		// ec39a41039fd85e62cf7bf66cd03df9f
		try {
			System.out.println(ghon.objectToGhonEle(AddressRequest.create(add)).toJSON(false));
//			System.out.println(ghon.objectToGhonEle(AddressRequest.retrieveAndVerify("ec39a41039fd85e62cf7bf66cd03df9f")).toJSON(false));
		} catch (CircleException e) {
			System.out.println(ghon.objectToGhonEle(e.getError()).toJSON(false));
		}
	}
}
