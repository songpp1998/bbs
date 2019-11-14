package cn.bbs.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2019-11-06 09:50
 * 这是回复的bean
 * @author wmx
 *
 */
public class ReplyBean {
	private int replyid;//编号
	private int postid;//回复的帖子
	private int userid;//回复人
	private String content;//回复的内容
	private String replaytime;//回复时间
	private String username;//回复人名
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "ReplyBean [replyid=" + replyid + ", postid=" + postid + ", userid=" + userid + ", content=" + content
				+ ", replaytime=" + replaytime + "]";
	}
	public ReplyBean(int replyid, int postid, int userid, String content, String replaytime) {
		super();
		this.replyid = replyid;
		this.postid = postid;
		this.userid = userid;
		this.content = content;
		this.replaytime = replaytime;
	}
	public ReplyBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getReplyid() {
		return replyid;
	}
	public void setReplyid(int replyid) {
		this.replyid = replyid;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReplaytime() {
		return replaytime;
	}
	public void setReplaytime(Timestamp replaytime) {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.replaytime = df.format(replaytime);
	}
}
