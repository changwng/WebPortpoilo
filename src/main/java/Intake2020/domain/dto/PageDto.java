package Intake2020.domain.dto;

import lombok.Data;

@Data
public class PageDto {
	
	
	private int page; //현재 페이지
	private int block; //버튼 갯수(페이지블럭_한페이지를 볼때 버튼갯수)
	private int total; //총 페이지
	
	private int start;
	private int end;
	

	/*public PageDto(Page<T> resultPage) {
		page = resultPage.getNumber()+1;
		block = 3;
		total = resultPage.getTotalPages();
		
		long pageBlockNo = page/block; //pageBlockNo 페이지블럭번호 
		if(page%block != 0) {
			pageBlockNo++;
		}
		
		end = pageBlockNo*block;
		start = end-block+1;
		
		if(end>total) {
			end=total;
		}
	}*/

	
	//public PageDto(int number, int totalPages) 
	public PageDto(int page, int total) {
		//this.page = getPage();
		
		this.page=page;
		block = 3; 
		this.total=total;
		
		int pageBlockNo = page/block; //pageBlockNo 페이지블럭번호 
		if(page%block != 0) {
			pageBlockNo++;
		}
		
		end = pageBlockNo*block;
		start = end-block+1;
		
		if(end>total) {
			end=total;
		}
	}
	
	

}
