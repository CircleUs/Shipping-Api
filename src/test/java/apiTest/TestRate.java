package apiTest;

import us.circle.Ghon.Ghon;
import us.circle.base.CircleException;
import us.circle.model.Rate;
import us.circle.request.RateRequest;

public class TestRate {
	
	private static final Ghon GHON = new Ghon();
	
	public static void main(String[] args) {
		try {
			Rate rate = RateRequest.rate("ship_B47HD1D0H5CF5498");
			System.out.println(GHON.objectToGhonEle(rate).toJSON(false));
		} catch (CircleException e) {
			System.out.println(GHON.objectToGhonEle(e.getError()).toJSON(false));
		}
	}
}
