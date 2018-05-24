package com.his.vo;

import java.util.List;

public class Page<T>{
		private int pageSize=5;//每页显示的条数
		private int pageNo=1;//当前页
		private int totalPage=0;//总页数
		private int totalCount=0;//总条数
		private int offset=0;//偏移量
		
		private List<T> pageItem;//当前展示数据列表


		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getPageNo() {
			return pageNo;
		}
		//setPageNo 封装页码时直接求offset 偏移量
		public void setPageNo(int pageNo) {
			/*if (pageNo<=1) {
				this.pageNo=1;
			}else if(pageNo>=getTotalPage()){
				this.pageNo=getTotalPage();
			}else{
				this.pageNo=pageNo;
			}*/
		}

		public int getTotalPage(int totalCount,int pageSize) {
			if(totalCount%pageSize!=0){
				this.totalPage=totalCount/pageSize+1;
			}else{
				this.totalPage=totalCount/pageSize;
			}
			return totalPage;
		}

		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}

		public int getTotalCount() {
			return totalCount;
		}
		//当总记录赋值时，直接求总页数，即pageSize不需要在调用set方法
		public void setTotalCount(int totalCount) {
			if(totalCount%pageSize!=0){
				this.totalPage=totalCount/this.pageSize+1;
			}else{
				this.totalPage=totalCount/this.pageSize;
			}
		}

		public List<T> getPageItem() {
			return pageItem;
		}

		public void setPageItem(List<T> pageItem) {
			this.pageItem = pageItem;
		}

		public int getOffset(int pageNo) {
			this.offset=(pageNo-1)*this.pageSize;
			return offset;
		}

		public void setOffset(int offset) {
			this.offset = offset;
		}
		
}
