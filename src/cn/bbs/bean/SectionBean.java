package cn.bbs.bean;
/***
 * 2019-11-5 09:56
 * 这是分板块和分区的bean
 * @author wmx
 *
 */
public class SectionBean {
	private int sectionid;//这是板块id
	private int districtid;//这是所属的分区id
	private String name;//这是分区名
	private int roleid;//这是版主id 
	@Override
	public String toString() {
		return "SectionBean [section=" + sectionid + ", districtid=" + districtid + ", name=" + name + ", roleid="
				+ roleid + "]";
	}
	public int getSectionId() {
		return sectionid;
	}
	public void setSectionId(int section) {
		this.sectionid = section;
	}
	public int getDistrictid() {
		return districtid;
	}
	public void setDistrictid(int districtid) {
		this.districtid = districtid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public SectionBean(int section, int districtid, String name, int roleid) {
		super();
		this.sectionid = section;
		this.districtid = districtid;
		this.name = name;
		this.roleid = roleid;
	}
	public SectionBean() {
		super();
	}
}
