package cn.bbs.dao;

import java.util.List;

import cn.bbs.bean.PostBean;
import cn.bbs.bean.ReplyBean;

/**
 * 
 * 2019-11-06 14:56
 * 这是处理回复的接口
 * @author wmx
 *
 */
public interface ReplyDao {
	public int addReply(ReplyBean reply);
	public int updateReply(ReplyBean reply);
	public int deleteReply(ReplyBean reply);
	public ReplyBean selectReplyById(ReplyBean reply);
	public List<ReplyBean> selectReplyByPost(PostBean post);
}
