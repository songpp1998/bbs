package cn.bbs.dao;

import java.util.List;

import cn.bbs.bean.ShortMessageBean;
import cn.bbs.bean.UserBean;

/**
 * 这是处理私信消息的接口
 * 2019-11-06 10：18
 * @author wmx
 *
 */
public interface ShortMessageDao {
	public int addShortMessage(ShortMessageBean message);
	public int deleteShortMessage(ShortMessageBean message);
	public int updateShortMessage(ShortMessageBean message);
	public List<ShortMessageBean> selectShortMessageBySender(UserBean user,int status);//收信人 根据用户和状态查询
	public List<ShortMessageBean> selectShortMessageByReceiver(UserBean user,int status);//发信人 根据用户和状态查询
	public ShortMessageBean selectShortMessageById(ShortMessageBean message);//
}
