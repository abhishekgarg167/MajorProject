package donorsinfo;

public class DonorBean {
	String mobile,name,gender,address,city,bgroup,disease;
	int age;
	
	public DonorBean(String mobile, String name, String gender, String address, String city, String bgroup,
			String disease, int age) {
		super();
		this.mobile = mobile;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.city = city;
		this.bgroup = bgroup;
		this.disease = disease;
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBgroup() {
		return bgroup;
	}
	public void setBgroup(String bgroup) {
		this.bgroup = bgroup;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
