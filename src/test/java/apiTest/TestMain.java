package apiTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import us.circle.Ghon.Ghon;
import us.circle.base.Circle;
import us.circle.base.CircleException;
import us.circle.model.Account;
import us.circle.model.Address;
import us.circle.model.CirclePackage;
import us.circle.model.CustomBase;
import us.circle.model.CustomDetail;
import us.circle.model.Shipment;
import us.circle.model.type.Confirmation;
import us.circle.model.type.DropOffType;
import us.circle.model.type.EEI;
import us.circle.model.type.PackageType;
import us.circle.model.type.PaymentType;
import us.circle.model.type.PayorType;
import us.circle.model.type.Server;
import us.circle.model.type.ServerLevel;
import us.circle.model.type.ShipmentPurpose;
import us.circle.model.type.SizeUnit;
import us.circle.model.type.WeightUnit;
import us.circle.request.AddressRequest;
import us.circle.request.ShipmentRequest;

public class TestMain {
	
	public static Ghon ghon = new Ghon();
	
	static{
//		Circle.basePath = "http://127.0.0.1:9000";
		Circle.apiKey = "API_a7da5fd80ea45a7ac084648a4d0dfe23";
//		Circle.apiKey = "TestApiKey";
	}
	

	
	public static void main(String[] args) {
		createShipment();
	}
	
	public static void retrieveAndVerify(){
		String json = null;
		try {
			json = ghon.objectToGhonEle(AddressRequest.retrieveAndVerify("ec39a41039fd85e62cf7bf66cd03df9f")).toJSON(false);
		} catch (CircleException e) {
			json = ghon.objectToGhonEle(e.getError()).toJSON(false);
		}
		System.out.println(json);
	}
	
	public static void createShipment(){
		String json = null;
		try {
			Shipment shipment = new Shipment();
			Address toAddress = AddressRequest.retrieve("2934b4dbbb0c6c06ecd604e288358cdd");//26995a36fe2b91b996f6d0571f443ee8
			shipment.setToAddress(toAddress);
			Address fromAddress = AddressRequest.retrieve("032a498677a724c0e65c55f19fe35aaf");
			shipment.setFromAddress(fromAddress);
			shipment.setShipDate(new Date());
			Account account = new Account();
			account.setPayorType(PayorType.circleus.name());
			account.setPaymentType(PaymentType.SENDER.name());
//			account.setAccountNumber("130173756");
//			account.setMeterNumber("103576278");
//			account.setAccessKey("20xO40CUZMyZ2mPd");
//			account.setPassword("MtCM6LHPm5vePO0MFZt9gQvQH");
			shipment.setAccount(account);
			CirclePackage circlePackage = new CirclePackage();
			circlePackage.setWeight(10.0f);
			circlePackage.setWeightUnit(WeightUnit.LB.name());
			circlePackage.setSizeUnit("IN");
			circlePackage.setHeight(20F);
			circlePackage.setWidth(20F);
			circlePackage.setLength(30F);
			shipment.getPackages().add(circlePackage);
//			shipment.getPackages().add(circlePackage);
//			CirclePackage circlePackage2 = new CirclePackage();
//			circlePackage2.setWeight(3.0f);
//			circlePackage2.setWeightUnit(WeightUnit.LB.name());
//			shipment.getPackages().add(circlePackage2);
//			shipment.getPackages().add(circlePackage2);
			shipment.setServer(Server.FedEx.name());
			shipment.setServerLevel(ServerLevel.FedExInternationalEconomy.name());
			shipment.setConfirmation(Confirmation.FedExAdultsignaturerequired.name());
			shipment.setDropOffType(DropOffType.DropBox.name());
			shipment.setPackageType(PackageType.FedExPackage.name());
			shipment.setReference("test");
			CustomBase customBase = new CustomBase();
			customBase.setEei(EEI.NOEEI30_37_a.name());
			customBase.setPaymentType(PaymentType.SENDER.name());
			customBase.setValueUnit("USD");
			customBase.setWeightUnit(WeightUnit.LB.name());
			customBase.setShipmentPurpose(ShipmentPurpose.Gift.name());
			shipment.setCustomBase(customBase);
			List<CustomDetail> customDetails = new ArrayList<CustomDetail>();
			shipment.setCustomDetails(customDetails);
			CustomDetail customDetail = new CustomDetail();
			customDetails.add(customDetail);
			customDetail.setIsTotal("0");
			customDetail.setManufactureCountry("US");
			customDetail.setName("Test");
			customDetail.setQuantity(1);
			customDetail.setValue(BigDecimal.ONE);
			customDetail.setWeight(1.0f);
			json = ghon.objectToGhonEle(ShipmentRequest.create(shipment)).toJSON(false);
		} catch (CircleException e) {
			json = ghon.objectToGhonEle(e.getError()).toJSON(false);
		}
		System.out.println(json);
	}
	
	public static void retrieveShipment(){
		String json = null;
		try {
			json = ghon.objectToGhonEle(ShipmentRequest.retrieve("ship_1E7F91D0B2J91G2A")).toJSON(false);
		} catch (CircleException e) {
			json = ghon.objectToGhonEle(e.getError()).toJSON(false);
		}
		System.out.println(json);
	}
	
	public static void modifyShipment(){
		String json = null;
		try {
			Shipment shipment = new Shipment();
			shipment.setKey("ship_147G9BDA4GJ7DDGH");
//			shipment.setReference("test");
			Address toAddress = new Address();
			toAddress.setKey("26995a36fe2b91b996f6d0571f443ee8");
			shipment.setToAddress(toAddress);
//			shipment.setToAddress(AddressRequest.retrieve("26995a36fe2b91b996f6d0571f443ee8"));
//			shipment.setFromAddress(AddressRequest.retrieve("c4a4ba7b82de1ba5f5d41ac349e826ac"));
//			shipment.setShipDate(new Date());
			shipment.setServer(Server.UPS.name());
			shipment.setServerLevel(ServerLevel.UPSGround.name());
			shipment.setConfirmation(Confirmation.UPSNoConfirmation.name());
			shipment.setPackageType(PackageType.UPSPackage.name());
			
			json = ghon.objectToGhonEle(ShipmentRequest.modifyShipment(shipment)).toJSON(false);
		} catch (CircleException e) {
			json = ghon.objectToGhonEle(e.getError()).toJSON(false);
		}
		System.out.println(json);
	}
	
	public static void modifyAccount(){
		String json = null;
		try {
			Account account = new Account();
			account.setPayorType(PayorType.user.name());
			account.setPaymentType(PaymentType.SENDER.name());
			json = ghon.objectToGhonEle(ShipmentRequest.modifyAccount(account, "ship_1E7F91D0B2J91G2A")).toJSON(false);
		} catch (CircleException e) {
			json = ghon.objectToGhonEle(e.getError()).toJSON(false);
		}
		System.out.println(json);
	}
	
	public static void modifyCustomBase(){
		String json = null;
		try {
			CustomBase customBase = new CustomBase();
			customBase.setWeightUnit(WeightUnit.OZ.name());
			customBase.setEei(EEI.NOEEI30_36.name());
			json = ghon.objectToGhonEle(ShipmentRequest.modifyCustomBase(customBase, "ship_147G9BDA4GJ7DDGH")).toJSON(false);
		} catch (CircleException e) {
			json = ghon.objectToGhonEle(e.getError()).toJSON(false);
		}
		System.out.println(json);
	}
	
	public static void modifyPackage(){
		String json = null;
		try {
			CirclePackage circlePackage = new CirclePackage();
			circlePackage.setWeightUnit(WeightUnit.OZ.name());
			json = ghon.objectToGhonEle(ShipmentRequest.modifyPackage(circlePackage, 1, "ship_147G9BDA4GJ7DDGH")).toJSON(false);
		} catch (CircleException e) {
			json = ghon.objectToGhonEle(e.getError()).toJSON(false);
		}
		System.out.println(json);
	}
	
	public static void modifyCustomDetail(){
		String json = null;
		try {
			CustomDetail customDetail = new CustomDetail();
			customDetail.setName("Test Item");
			customDetail.setValue(BigDecimal.TEN);
			json = ghon.objectToGhonEle(ShipmentRequest.modifyCustomDetail(customDetail, 2, "ship_147G9BDA4GJ7DDGH")).toJSON(false);
		} catch (CircleException e) {
			json = ghon.objectToGhonEle(e.getError()).toJSON(false);
		}
		System.out.println(json);
	}
	
	public static void deletePackage(){
		String json = null;
		try {
			json = ghon.objectToGhonEle(ShipmentRequest.deletePackage("ship_1E7F91D0B2J91G2A", 1)).toJSON(false);
		} catch (CircleException e) {
			json = ghon.objectToGhonEle(e.getError()).toJSON(false);
		}
		System.out.println(json);
	}
	
	public static void addPackage(){
		String json = null;
		try {
			CirclePackage circlePackage = new CirclePackage();
			circlePackage.setHeight(18f);
			circlePackage.setLength(26f);
			circlePackage.setWidth(31f);
			circlePackage.setSizeUnit(SizeUnit.IN.name());
			circlePackage.setWeight(5f);
			circlePackage.setWeightUnit(WeightUnit.LB.name());
			json = ghon.objectToGhonEle(ShipmentRequest.addPackage(circlePackage, "ship_1E7F91D0B2J91G2A")).toJSON(false);
		} catch (CircleException e) {
			json = ghon.objectToGhonEle(e.getError()).toJSON(false);
		}
		System.out.println(json);
	}
	
	public static void addCustomDetail(){
		String json = null;
		try {
			CustomDetail customDetail = new CustomDetail();
			customDetail.setName("Test Name");
			customDetail.setIsTotal("0");
			customDetail.setQuantity(3);
			customDetail.setValue(BigDecimal.TEN);
			customDetail.setWeight(10f);
			customDetail.setManufactureCountry("US");
			json = ghon.objectToGhonEle(ShipmentRequest.addCustomDetail(customDetail, "ship_1E7F91D0B2J91G2A")).toJSON(false);
		} catch (CircleException e) {
			json = ghon.objectToGhonEle(e.getError()).toJSON(false);
		}
		System.out.println(json);
	}
}
