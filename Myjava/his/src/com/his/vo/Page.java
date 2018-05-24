package com.his.vo;

import java.util.List;

public class Page<T>{
		private int pageSize=5;//ÿҳ��ʾ������
		private int pageNo=1;//��ǰҳ
		private int totalPage=0;//��ҳ��
		private int totalCount=0;//������
		private int offset=0;//ƫ����
		
		private List<T> pageItem;//��ǰչʾ�����б�


		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getPageNo() {
			return pageNo;
		}
		//setPageNo ��װҳ��ʱֱ����offset ƫ����
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
		//���ܼ�¼��ֵʱ��ֱ������ҳ������pageSize����Ҫ�ڵ���set����
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
