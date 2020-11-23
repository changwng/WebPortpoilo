/*
 * NAME    : 메뉴 slide JQuery /메뉴 toggle JQuery
 * AUTHOR  : 정세은
 */

$(function(){
	//처음 로딩시 #drop-menu는 보이지 않도록
	$("#drop-menu").hide();
	
	var check = true;
	//#gnb가 클릭시 #drop-menu가 toggle 되도록
	/*$("#gnb").click(function(){
		$("#drop-menu").toggle();
		
	});*/
	
	$("#gnb").click(function(){
		if(check){
			$("#drop-menu").slideDown(500);
			check=false;
			
			$(this).css("background-color","#000000");
			$(".bar").css("border-color","#fff");
			$("#btn-wrap").hide();
			
			
			
		}else{
			$("#drop-menu").slideUp(500);
			check=true;
			$(this).css("background-color","#fff");
			$(".bar").css("border-color","#000");
			$("#btn-wrap").show();
		}
		
	});
	

});

