package bloodcollection;

public class CollectionBean {
	String mobile,bgroup,dat;

	
	public CollectionBean(String mobile, String bgroup, String date) {
		super();
		this.mobile = mobile;
		this.bgroup = bgroup;
		this.dat = date;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBgroup() {
		return bgroup;
	}

	public void setBgroup(String bgroup) {
		this.bgroup = bgroup;
	}

	public String getDate() {
		return dat;
	}

	public void setDate(String date) {
		this.dat = date;
	}
}
