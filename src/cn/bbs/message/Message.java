package cn.bbs.message;
/**
 * 
 * 2019-11-07 11:33
 * 这是api回复json的格式类
 * 所有回复都按照此格式
 * @author wmx
 *
 */
public class Message {
	public boolean success;//是否成功
	public int status;//状态码
	public String message;//信息
	public Object data;//这是具体的数据
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Message(boolean success, int status, String message, Object data) {
		super();
		this.success = success;
		this.status = status;
		this.message = message;
		this.data = data;
	}
	@Override
	public String toString() {
		return "Message [success=" + success + ", status=" + status + ", message=" + message + ", data=" + data + "]";
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
