package cn.bbs.bean;

public class Account {
     @Override
	public String toString() {
		return "Account [username=" + username + ", account=" + account + ", rolename=" + rolename + ", password="
				+ password + ", sign=" + sign + ", introduce=" + introduce + ", qq=" + qq + ", blog=" + blog
				+ ", birplace=" + birplace + ", birthday=" + birthday + ", phone=" + phone + ", sex=" + sex + ", img="
				+ img + ", problemhint=" + problemhint + ", answerhint=" + answerhint + "]";
	}
	private String username;
	 private String account; 
	 private String rolename;
	 private String password;
	 private String sign;
	 private String introduce;
	 private String qq;
	 private String blog;
	 private String birplace;
	 private String birthday;
	 private String phone;
	 private String sex;
	 private String img;
	 private String problemhint;
	 private String answerhint;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getBlog() {
		return blog;
	}
	public void setBlog(String blog) {
		this.blog = blog;
	}
	public String getBirplace() {
		return birplace;
	}
	public void setBirplace(String birplace) {
		this.birplace = birplace;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getProblemhint() {
		return problemhint;
	}
	public void setProblemhint(String problemhint) {
		this.problemhint = problemhint;
	}
	public String getAnswerhint() {
		return answerhint;
	}
	public void setAnswerhint(String answerhint) {
		this.answerhint = answerhint;
	}
	 
}
