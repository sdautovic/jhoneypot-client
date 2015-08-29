package hr.sdautovic.jhoneypot.client;

import org.xbill.DNS.ARecord;
import org.xbill.DNS.Record;

public class HoneypotResult {
	public enum Visitor {
		SEARCH_ENGINE, 
		SUSPICIOUS, 
		HARVESTER, 
		SUSPICIOUS_HARVESTER, 
		COMMENT_SPAMMER, 
		SUSPICIOUS_COMMENT_SPAMMER, 
		HARVESTER_COMMENT_SPAMMER, 
		SUSPECIOUS_HARVESTER_COMMENT_SPAMMER,
		ERROR
	}
	
	public boolean OK = false;
	private String[] result_tokens;
	private String query_ipv4;
	
	public HoneypotResult(Record[] dns_records, String query_ipv4) {
		this.query_ipv4 = query_ipv4;
		if (dns_records == null) return;
		else if (dns_records.length > 1) return;
		
		this.OK = true;
		result_tokens = ((ARecord) dns_records[0]).getAddress().getHostAddress().split("\\.", 4);
	}
	
	public int daysSinceLastActivity() {
		if (this.OK == false) return -1;
		return Integer.valueOf(this.result_tokens[1]);
	}
	
	public int threadScore() {
		if (this.OK == false) return -1;
		return Integer.valueOf(this.result_tokens[2]);
	}
	
	public Visitor visitorType() {
		if (this.OK == false) return Visitor.ERROR;
		return Visitor.values()[Integer.valueOf(this.result_tokens[3])];
	}
	
	public String toString() {
		String s = "ip=" + this.query_ipv4 + " days=" + this.daysSinceLastActivity() + " thread_score=" + this.threadScore() + " visitor=" + this.visitorType();
		return s;
	}
}
