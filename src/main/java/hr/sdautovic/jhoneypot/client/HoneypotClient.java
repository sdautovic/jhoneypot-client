package hr.sdautovic.jhoneypot.client;

import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

public class HoneypotClient {
	private static String LIST_SPECIFIC_DOMAIN = "dnsbl.httpbl.org";
	
	public static HoneypotResult checkWebBL(String auth_key, String ipv4) {
		String reverse_octet_format = toReverseOctetFormat(ipv4);
		String query = auth_key + "." + reverse_octet_format + "." + LIST_SPECIFIC_DOMAIN;
		
		Record[] records = null;
		try {
			records = new Lookup(query, Type.A).run();
		} catch (TextParseException e) {
			return new HoneypotResult(records);
		}
		return new HoneypotResult(records);
	}
	
	private static String toReverseOctetFormat(String ipv4) {
		String[] tokens = ipv4.split("\\.", 4);
		
		if (tokens.length != 4) return "";
		return tokens[3] + "." + tokens[2] + "." + tokens[1] + "." + tokens[0];
	}
}
