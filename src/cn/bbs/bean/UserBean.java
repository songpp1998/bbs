package cn.bbs.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class UserBean {

	private int userId;//用户id
	private int roleid;//角色id
	private String username;//用户名
	private String account;//登录账户，可用于登录
	private String password;
	private String signature;//个性签名
	private String introduce;//自我介绍
	private String qq;
	private String blog;//个人网站
	private String birplace;//籍贯
	private String birthday;//生日
	private int sex;//性别 
	private String img;//图片url，头像
	private String phone;//手机号
	private String registertime;//注册时间，精确到s
	private String logintime;//登录时间
	private String lastlogintime;//上次登录时间，精确到s
	private String loginIp;//登录ip
	private String lastloginIp;//上次登录ip
	
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegistertime() {
		return registertime;
	}
	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public String getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getLastloginIp() {
		return lastloginIp;
	}
	public void setLastloginIp(String lastloginIp) {
		this.lastloginIp = lastloginIp;
	}
		
	
}
