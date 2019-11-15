package cn.bbs.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 2019-11-06 09:56
 * 这是私信短消息的bean
 * @author wmx
 *
 */
public class ShortMessageBean {
	private int messageid;//编号
	private String message;//消息内容
	private int senderid;//发信人
	private int receiverid;//收信人
	private String title;//标题
	private String createtime;//发送时间
	private int status;//消息状态 0是草稿 1是已发送 2是未读 3是已读
	/*********************************/
	private String sendername;//发信人名
	private String receivername;//收信人名
	
	public String getSendername() {
		return sendername;
	}
	public void setSendername(String sendername) {
		this.sendername = sendername;
	}
	public String getReceivername() {
		return receivername;
	}
	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}
	public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getSenderid() {
		return senderid;
	}
	public void setSenderid(int senderid) {
		this.senderid = senderid;
	}
	public int getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(int receiverid) {
		this.receiverid = receiverid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.createtime = df.format(createtime);
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ShortMessageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShortMessageBean(int messageid, String message, int senderid, int receiverid, String title,
			String createtime, int status) {
		super();
		this.messageid = messageid;
		this.message = message;
		this.senderid = senderid;
		this.receiverid = receiverid;
		this.title = title;
		this.createtime = createtime;
		this.status = status;
	}
	@Override
	public String toString() {
		return "ShortMessageBean [messageid=" + messageid + ", message=" + message + ", senderid=" + senderid
				+ ", receiverid=" + receiverid + ", title=" + title + ", createtime=" + createtime + ", status="
				+ status + "]";
	}
}
