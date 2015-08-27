package hr.sdautovic.jhoneypot.client;

public class SimpleExample {

	public static void main(String[] args) {
		
		HoneypotResult hr1 = HoneypotClient.checkWebBL("xyaocdgixdkj", "85.114.35.2");
		System.out.println(hr1.toString());
	}
}
