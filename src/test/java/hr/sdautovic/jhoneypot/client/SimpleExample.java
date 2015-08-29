package hr.sdautovic.jhoneypot.client;

public class SimpleExample {

	public static void main(String[] args) {
		
		HoneypotResult hr1 = HoneypotClient.checkWebBL("xyaocdgixdkj", "85.114.53.70");
//		HoneypotResult hr1 = HoneypotClient.checkWebBL("xyaocdgixdkj", "127.0.0.1");
		System.out.println(hr1.toString());
	}
}
