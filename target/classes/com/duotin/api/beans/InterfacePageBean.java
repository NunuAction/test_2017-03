package com.duotin.api.beans;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author jared
 * 
 * @Description:接口返回分页对象
 * 
 * @date Nov 27, 2014 4:48:22 PM
 * 
 */
@Deprecated
public class InterfacePageBean extends InterfaceBean {

	private PageBean pageBean;

	public InterfacePageBean() {
		pageBean = new PageBean(new Page());
		setData(pageBean);
	}

	public InterfacePageBean(int currentPage, int pageSize, int totalPage) {
		pageBean = new PageBean(new Page(currentPage, pageSize, totalPage));
		setData(pageBean);
	}

	@JsonIgnore
	public PageBean getPageBean() {
		return pageBean;
	}

	/**
	 * 
	 * @author jared
	 * 
	 * @Description:分页对象
	 * 
	 * @date Nov 27, 2014 4:56:03 PM
	 * 
	 */
    @Deprecated
	public class PageBean {

		private Page page;

		private Object dataList;

		public PageBean(Page page) {
			super();
			this.page = page;
		}

		public PageBean dataList(Object value) {
			setDataList(value);
			return this;
		}

		public Page getPage() {
			return page;
		}

		public void setPage(Page page) {
			this.page = page;
		}

		@JsonProperty("data_list")
		public Object getDataList() {
			return dataList;
		}

		public void setDataList(Object dataList) {
			this.dataList = dataList;
		}
	}

    @Deprecated
	public class Page {

		private int currentPage;

		private int pageSize;

		private int totalPage;

		public Page(int currentPage, int pageSize, int totalPage) {
			this.currentPage = currentPage;
			this.pageSize = pageSize;
			this.totalPage = totalPage;
		}

		public Page() {
		}

		@JsonProperty("current_page")
		public int getCurrentPage() {
			return currentPage;
		}

		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

		@JsonProperty("page_size")
		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		@JsonProperty("total_page")
		public int getTotalPage() {
			return totalPage;
		}

		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}

		@JsonProperty("has_next")
		public boolean isHasNext() {
			return currentPage < totalPage;
		}
	}
}
