/**
 * 
 */
package com.xnk.service.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = 7472569185643418133L;
	
	private int pageNo = 1; //当前页码
	
	private int pageSize = 20; //页面大小
	
	private long count;// 总记录数
	
	private String orderBy = ""; //排序条件
	
	private String condition = "";//条件
	
	private List<T> list = new ArrayList<>();
	
	private int first;// 首页索引
	
	private int last;// 尾页索引
	
	private int prev;// 上一页索引
	
	private int next;// 下一页索引

	private boolean firstPage;//是否是第一页
	
	private boolean lastPage;//是否是最后一页
	
	private String pageUrl;
	
	private boolean paging = true;//分页标志
	
	private String groupBy = ""; //分组条件
	
	public Page() {
		
	}
	public Page(int pageNo, int pageSize) {
		this(pageNo, pageSize, 0);
	}
	
	public Page(int pageNo, int pageSize, String orderBy){
		this.setPageNo(pageNo);
		this.setPageSize(pageSize);
		this.setOrderBy(orderBy);
	}

	public Page(int pageNo, int pageSize, long count) {
		this(pageNo, pageSize, count, new ArrayList<T>());
	}
	
	public Page(int pageNo, int pageSize, long count, List<T> list) {
		this.setCount(count);
		this.setPageNo(pageNo);
		this.pageSize = pageSize;
		this.list = list;
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize <= 0 ? 20 : pageSize;
	}


	public long getCount() {
		return count;
	}


	public void setCount(long count) {
		this.count = count;
		initialize();
	}

	@JsonIgnore
	public String getOrderBy() {
		return orderBy;
	}


	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	@JsonIgnore
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}



	public List<T> getList() {
        if(list == null){
            list = new ArrayList<T>();
        }
		return list;
	}


	public void setList(List<T> list) {
		this.list = list;
	}
	
	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPage() {
        long totalPage = 0;
        if (0 == getCount() % pageSize) {
            if (0 != getCount()) {
                totalPage = getCount() / pageSize;
            }
        } else {
            totalPage = getCount() / pageSize + 1;
        }
        return new Long(totalPage).intValue();
	}
	
	public int getStart() {
		return (this.getPageNo() - 1) * this.getPageSize();
	}

	//@JsonIgnore
	public int getFirst() {
		return first;
	}

	//@JsonIgnore
	public int getLast() {
		return last;
	}
	
	/**
	 * 是否为第一页
	 * @return
	 */
	//@JsonIgnore
	public boolean isFirstPage() {
		return firstPage;
	}

	/**
	 * 是否为最后一页
	 * @return
	 */
	//@JsonIgnore
	public boolean isLastPage() {
		return lastPage;
	}
	
	/**
	 * 上一页索引值
	 * @return
	 */
	//@JsonIgnore
	public int getPrev() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}

	/**
	 * 下一页索引值
	 * @return
	 */
	//@JsonIgnore
	public int getNext() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}
	
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public void initialize(){
				
		//第一页
		this.first = 1;
		
		this.last = (int)(count / (this.pageSize < 1 ? 20 : this.pageSize) + first - 1);
		if (this.count % this.pageSize != 0 || this.last == 0) {
			this.last++;
		}
		if (this.last < this.first) {
			this.last = this.first;
		}
		
		if (this.pageNo <= 1) {
			this.pageNo = this.first;
			this.firstPage=true;
		}
		if (this.pageNo >= this.last) {
			this.pageNo = this.last;
			this.lastPage=true;
		}

		if (this.pageNo < this.last - 1) {
			this.next = this.pageNo + 1;
		} else {
			this.next = this.last;
		}

		if (this.pageNo > 1) {
			this.prev = this.pageNo - 1;
		} else {
			this.prev = this.first;
		}
		
		// 如果当前页小于首页
		if (this.pageNo < this.first) {
			this.pageNo = this.first;
		}

		// 如果当前页大于尾页
		if (this.pageNo > this.last) {
			this.pageNo = this.last;
		}
	}
	public boolean isPaging() {
		return paging;
	}
	public void setPaging(boolean paging) {
		this.paging = paging;
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	
}
