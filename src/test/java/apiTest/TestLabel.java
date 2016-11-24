package apiTest;

import java.io.File;
import java.io.FileOutputStream;

import sun.misc.BASE64Decoder;
import us.circle.Ghon.Ghon;
import us.circle.base.Circle;
import us.circle.base.CircleException;
import us.circle.model.CirclePackage;
import us.circle.model.Shipment;
import us.circle.request.LabelRequest;

@SuppressWarnings("restriction")
public class TestLabel {
	
	private static final Ghon GHON = new Ghon();
	
	private static final BASE64Decoder DECODER = new BASE64Decoder();
	
	public static void main(String[] args) {
		GHON.setShowNull(false);
		createLabel();
	}
	
	private static void writeFile(File file, byte[] bytes) throws Exception{
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(bytes);
		outputStream.flush();
		outputStream.close();
	}
	
	public static void createLabel(){
		try {
			Shipment shipment = LabelRequest.create("ship_6D1IA0G65HFF0I974B", true);
			System.out.println(GHON.objectToGhonEle(shipment).toJSON(false));
			for(CirclePackage circlePackage : shipment.getPackages()){
				if(circlePackage.getLabels() != null){
					for(int i = 0;i < circlePackage.getLabels().size();i++){
						String label = circlePackage.getLabels().get(i);
						File file = new File(String.format("/Users/maimaijiao/Documents/Temp/%s_%s.%s", circlePackage.getTrackingNumber(), i+1, circlePackage.getFileType())); 
						file.createNewFile();
						writeFile(file, DECODER.decodeBuffer(label));
					}
				}
			}
			if(shipment.getCustomBase() != null && shipment.getCustomBase().getInvoices() != null){
				for(int i = 0;i < shipment.getCustomBase().getInvoices().size(); i++){
					String invoice = shipment.getCustomBase().getInvoices().get(i);
					File file = new File(String.format("/Users/maimaijiao/Documents/Temp/%s_Invoice_%s.%s", shipment.getShipmentNo(), i+1, shipment.getCustomBase().getFileType())); 
					file.createNewFile();
					writeFile(file, DECODER.decodeBuffer(invoice));
				}
			}
		} catch (CircleException e) {
			System.out.println(GHON.objectToGhonEle(e.getError()).toJSON(false));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void cancelLabel(){
		try {
			System.out.println(LabelRequest.cancel("ship_B4H72B309EAF83E4"));
		} catch (CircleException e) {
			System.out.println(GHON.objectToGhonEle(e.getError()).toJSON(false));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void downloadLabel(){
		try {
			Shipment shipment = LabelRequest.downloadLabel("ship_1EHGI1305G2AB8C7");
			System.out.println(GHON.objectToGhonEle(shipment).toJSON(false));
			for(CirclePackage circlePackage : shipment.getPackages()){
				if(circlePackage.getLabels() != null){
					for(int i = 0;i < circlePackage.getLabels().size();i++){
						String label = circlePackage.getLabels().get(i);
						File file = new File(String.format("/Users/maimaijiao/Documents/Temp/%s_%s.%s", circlePackage.getTrackingNumber(), i+1, circlePackage.getFileType())); 
						file.createNewFile();
						writeFile(file, DECODER.decodeBuffer(label));
					}
				}
			}
			if(shipment.getCustomBase() != null && shipment.getCustomBase().getInvoices() != null){
				for(int i = 0;i < shipment.getCustomBase().getInvoices().size(); i++){
					String invoice = shipment.getCustomBase().getInvoices().get(i);
					File file = new File(String.format("/Users/maimaijiao/Documents/Temp/%s_Invoice_%s.%s", shipment.getShipmentNo(), i+1, shipment.getCustomBase().getFileType())); 
					file.createNewFile();
					writeFile(file, DECODER.decodeBuffer(invoice));
				}
			}
		} catch (CircleException e) {
			System.out.println(GHON.objectToGhonEle(e.getError()).toJSON(false));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
