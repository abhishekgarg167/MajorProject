package bloodissued;

public class IssuedBean {
	String nname,mobile,hospital,bgroup,reason,date;

	public IssuedBean(String nname, String mobile, String hospital, String bgroup, String reason, String date) {
		super();
		this.nname = nname;
		this.mobile = mobile;
		this.hospital = hospital;
		this.bgroup = bgroup;
		this.reason = reason;
		this.date = date;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getBgroup() {
		return bgroup;
	}

	public void setBgroup(String bgroup) {
		this.bgroup = bgroup;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
