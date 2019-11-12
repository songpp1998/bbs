package cn.bbs.dao;

import java.util.List;

import cn.bbs.bean.SectionBean;

/***
 * 
 * 2019-11-06 16:18
 * 这是处理板块的接口
 * @author wmx
 *
 */
public interface SectionDao {
	public int addSection(SectionBean section);
	public int updateSection(SectionBean section);
	public int deleteSection(SectionBean section);
	public List<SectionBean> selectSectionByDistrictid(SectionBean section);//按照分区查询板块  如果传入0则查询所有分区
	public SectionBean selectSctionById(int sectionid);//查询单个
}
