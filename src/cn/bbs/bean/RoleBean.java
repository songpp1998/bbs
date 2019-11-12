package cn.bbs.bean;
/** 
* @author wmx
* @version 创建时间：2019年11月12日 下午4:07:29 
* 这是角色的bean
*/
public class RoleBean {
	private int roleid;//角色id
	private int postion;//职位
	private String name;//角色名
	
	public int getRoleId() {
		return roleid;
	}
	public void setRoleId(int role) {
		this.roleid = role;
	}
	public int getPostion() {
		return postion;
	}
	public void setPostion(int postion) {
		this.postion = postion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "RoleBean [role=" + roleid + ", postion=" + postion + ", name=" + name + "]";
	}
	public RoleBean(int role, int postion, String name) {
		super();
		this.roleid = role;
		this.postion = postion;
		this.name = name;
	}
	public RoleBean() {
		super();
		// TODO Auto-generated constructor stub
	}
}
