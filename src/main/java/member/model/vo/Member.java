package member.model.vo;

import java.io.Serializable;
import java.sql.Date;
// 1. 반드시 직렬화(Serializable) 할것 : 네트워크에서 왔다 갔다 해야하니깐
// 2. 모든 필드는 private : 반드시 캡슐화 할것
// 3. 기본생성자, 매개변수있는 생성자 만들기, 생성자의 갯수는 필요한만큼 만든다
// 4. 모든 필드에 대한 getter, setter 만들기
// 5. toString() overriding 하기
// 선택 : equals(), clone() 등 오버라이딩

public class Member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4952932019676617041L;

	private String userId;
	private String userPwd;
	private String userName;
	private String gender;
	private int age;
	private String phone;
	private String email;
	private java.sql.Date enrollDate;
	private java.sql.Date lastModified;
	private String signType;
	private String admin;
	private String loginOk;

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", gender=" + gender
				+ ", age=" + age + ", phone=" + phone + ", email=" + email + ", enrollDate=" + enrollDate
				+ ", lastModified=" + lastModified + ", signType=" + signType + ", admin=" + admin + ", loginOk="
				+ loginOk + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.sql.Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(java.sql.Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public java.sql.Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(java.sql.Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getLoginOk() {
		return loginOk;
	}

	public void setLoginOk(String loginOk) {
		this.loginOk = loginOk;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Member(String userId, String userPwd, String userName, String gender, int age, String phone, String email,
			Date enrollDate, Date lastModified, String signType, String admin, String loginOk) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.enrollDate = enrollDate;
		this.lastModified = lastModified;
		this.signType = signType;
		this.admin = admin;
		this.loginOk = loginOk;
	}

	public Member() {
		super();
	}

}