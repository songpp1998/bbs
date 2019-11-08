package cn.bbs.dao;
/** 

* @author ——Hydra

* @version ——2019年11月7日 下午3:57:50 

* 这是一个分页的工具类

*/
public class PageDao {

	//第几页，从0开始
	private int page;
	//每一页的数量
	private int num;
	//数据总数
	private int total;
	
	
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public PageDao(int page, int num) {
		super();
		this.page = page;
		this.num = num;
	}
	
	/**
	 * 判断是否有上上一页
	 * @return
	 */
	public boolean isHasPrevious() {
		if(page==0)
			return false;
		return true;
		
	}
	/**
	 * 判断是否有下一页
	 * @return
	 */
	public boolean isHasNext() {
		if(page*num==getLast())
			return false;
		return true;
	}
	
	/**
	 * 计算得到尾页
	 * @return
	 */
	public int getLast() {
		int last;
		// 假设总数是50，是能够被5整除的，那么最后一页的开始就是45
		if(total % num == 0)
			last = total - num;
		 	// 假设总数是51，不能够被5整除的，那么最后一页的开始就是50
		else
			last = total - total % num;
		return last;
	}
	
	/**
	 * 获得总页数
	 * @return
	 */
	public int getTotalPage() {
		int totalPage;
		if(total % num == 0) {
			totalPage = total / num;
		}else {
			totalPage = total / num + 1;
		}
		
		if(totalPage == 0)
			totalPage = 1;
		return totalPage;
	}
}
