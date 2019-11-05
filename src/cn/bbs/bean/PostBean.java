package cn.bbs.bean;
/**
 * 
 * 2019-11-05 09:45
 * 这是帖子的bean
 * @author wmx
 *
 */

import java.util.Date;

public class PostBean {
	public int postid;//帖子id
	public int userid;//发帖人id
	public int sectionid;//所属板块id
	public String content;//内容
	public String title;//标题
	public Date postdate;//发帖时间
	public int posttype;//帖子类型  0为普通主题，1为投票主题，2为公告
	public String postip;//发布者IP
	public int readnum;//阅读次数
	public int replynum;//回复总数
	public int hot;//精华帖
	public int hotid;//精华帖推荐人
	public String edituser;//编辑者用户名
	public Date edittime;//编辑时间
	public String hotreason;//热帖推荐原因
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
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
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
	public Date getEdittime() {
		return edittime;
	}
	public void setEdittime(Date edittime) {
		this.edittime = edittime;
	}
	public String getHotreason() {
		return hotreason;
	}
	public void setHotreason(String hotreason) {
		this.hotreason = hotreason;
	}
	public PostBean(int postid, int userid, int sectionid, String content, String title, Date postdate, int posttype,
			String postip, int readnum, int replynum, int hot, int hotid, String edituser, Date edittime,
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
