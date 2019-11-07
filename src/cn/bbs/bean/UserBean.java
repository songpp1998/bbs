package cn.bbs.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class UserBean {

	private int userId;//用户id
	private int roleid;//角色id
	private String username;//用户名
	private int account;//登录账户，可用于登录
	private String password;
	private String signature;//个性签名
	private String introdure;//自我介绍
	private int qq;
	private String blog;//个人网站
	private String birplace;//籍贯
	private Date birthday;//生日
	private short sex;//性别 
	private String img;//图片url，头像
	private int phone;//手机号
	private Timestamp registertime;//注册时间，精确到s
	private Timestamp logintime;//登录时间
	private Timestamp lastlogintime;//上次登录时间，精确到s
	private String loginIp;//登录ip
	private String lastloginIp;//上次登录ip
	
	
	
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
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
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
	public String getIntrodure() {
		return introdure;
	}
	public void setIntrodure(String introdure) {
		this.introdure = introdure;
	}
	public int getQq() {
		return qq;
	}
	public void setQq(int qq) {
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public short getSex() {
		return sex;
	}
	public void setSex(short sex) {
		this.sex = sex;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public Timestamp getRegistertime() {
		return registertime;
	}
	public void setRegistertime(Timestamp registertime) {
		this.registertime = registertime;
	}
	public Timestamp getLogintime() {
		return logintime;
	}
	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
	}
	public Timestamp getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(Timestamp lastlogintime) {
		this.lastlogintime = lastlogintime;
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
