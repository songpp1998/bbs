package cn.bbs.bean;
/**
 * 
 * 2019-11-05 09:45
 * 这是帖子的bean
 * @author wmx
 *
 */

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostBean {
	private int postid;//帖子id
	private int userid;//发帖人id
	private int sectionid;//所属板块id
	private String content;//内容
	private String title;//标题
	private String postdate;//发帖时间
	private int posttype;//帖子类型  0为普通主题，1为投票主题，2为公告
	private String postip;//发布者IP
	private int readnum;//阅读次数
	private int replynum;//回复总数
	private int hot;//精华帖
	private int hotid;//精华帖推荐人
	private String edituser;//编辑者用户名
	private String edittime;//编辑时间
	private String hotreason;//热帖推荐原因
	/****************************/
	private String username;//作者名
	private String sectionname;//板块名
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSectionname() {
		return sectionname;
	}
	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getSectionid() {
		return sectionid;
	}
	public void setSectionid(int sectionid) {
		this.sectionid = sectionid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPostdate() {
		return postdate;
	}
	public void setPostdate(Timestamp postdate) {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.postdate = df.format(postdate);
	}
	public int getPosttype() {
		return posttype;
	}
	public void setPosttype(int posttype) {
		this.posttype = posttype;
	}
	public String getPostip() {
		return postip;
	}
	public void setPostip(String postip) {
		this.postip = postip;
	}
	public int getReadnum() {
		return readnum;
	}
	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}
	public int getReplynum() {
		return replynum;
	}
	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	public int getHotid() {
		return hotid;
	}
	public void setHotid(int hotid) {
		this.hotid = hotid;
	}
	public String getEdituser() {
		return edituser;
	}
	public void setEdituser(String edituser) {
		this.edituser = edituser;
	}
	public String getEdittime() {
		return edittime;
	}
	public void setEdittime(Timestamp edittime) {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.edittime = df.format(edittime);
	}
	public String getHotreason() {
		return hotreason;
	}
	public void setHotreason(String hotreason) {
		this.hotreason = hotreason;
	}
	public PostBean(int postid, int userid, int sectionid, String content, String title, String postdate, int posttype,
			String postip, int readnum, int replynum, int hot, int hotid, String edituser, String edittime,
			String hotreason) {
		super();
		this.postid = postid;
		this.userid = userid;
		this.sectionid = sectionid;
		this.content = content;
		this.title = title;
		this.postdate = postdate;
		this.posttype = posttype;
		this.postip = postip;
		this.readnum = readnum;
		this.replynum = replynum;
		this.hot = hot;
		this.hotid = hotid;
		this.edituser = edituser;
		this.edittime = edittime;
		this.hotreason = hotreason;
	}
	public PostBean() {
		super();
	}
	@Override
	public String toString() {
		return "PostBean [postid=" + postid + ", userid=" + userid + ", sectionid=" + sectionid + ", content=" + content
				+ ", title=" + title + ", postdate=" + postdate + ", posttype=" + posttype + ", postip=" + postip
				+ ", readnum=" + readnum + ", replynum=" + replynum + ", hot=" + hot + ", hotid=" + hotid
				+ ", edituser=" + edituser + ", edittime=" + edittime + ", hotreason=" + hotreason + "]";
	}
	
}
