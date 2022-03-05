// 글 등록 로그인 확인함
   function regi(section,page){
    	$.ajax({
    		url:"/library/board/regiB4Check",
    		method:'get',
    		cache:false,
    		dataType:'json',
    		success: function(res){
 				if(res.msg=="match"){
    				location.href="/library/board/"+section+"/regi/"+page;
				}else if(res.msg=="로그인하지 않으셨습니다."){
    				if(!confirm("로그인 하시겠어요?")) return;
					location.href="/library";
				}
				return false; 
			},
    		err: function (xhr, status, err){
    			alert(err);
    		}
    	});
    	return false;
    }
    
    function detail(section,page,id){
    	location.href="/library/board/"+section+"/detail/"+page+"/"+id;
    	return false;
    }
    function edit(section,page,id){
    	$.ajax({
    		url:"/library/board/writerCheck/"+id,
    		method:'get',
    		cache:false,
    		dataType:'json',
    		success: function(res){
				if(res.msg=="로그인하지 않으셨습니다."){
					alert(res.msg);
					if(!confirm("로그인 하시겠어요?")) return;
					location.href="/library";
				}else if(res.msg=="master"){
		    		location.href="/library/board/"+section+"/edit/"+page+"/"+id;
				}else{
				alert("운영진만 사용할수 있는 기능입니다.");
				}
				return false;
			},
    		err: function (xhr, status, err){
    			alert(err);
    		}
    	});
    	return false;
    }
    
    	function del(section,pg,id){
        	$.ajax({
        		url:"/library/board/writerCheck/"+id,
        		method:'get',
        		cache:false,
        		dataType:'json',
        		success: function(res){
    				if(res.msg=="로그인하지 않으셨습니다."){
    					alert(res.msg);
    					location.href="/library";
    				}else if(res.msg=="master"){
        	if(!confirm("정말로 글을 삭제하시겠어요?")) return;
						$.ajax({
			        		url:"/library/board/delete/"+id,
			        		method:'get',
			        		cache:false,
			        		dataType:'json',
			        		success: function(res){
			    				if(res.ok){
									location.href="/library/board/"+section+"/list/page/"+pg;
			    				}else{
			    					alert("삭제 실패");
			    				}
			    				return false;
			    			},
			        		err: function (xhr, status, err){
			        			alert(err);
			        		}
			        	});
			        	return false;
    				}else{
    					alert("운영진만 사용할수 있는 기능입니다.");
    				}
    				return false;
    			},
        		err: function (xhr, status, err){
        			alert(err);
        		}
        	});
        	return false;
    }
    	
    function search(section){
    	var section = section;
		var cmd = $('#cmd').val();
		var word = $('#word').val();
		if(word=='') {
			alert("검색어를 입력하세요");
			return false;
		}
		var obj = {};
		obj.section = section;
		obj.cmd = cmd;
		obj.word = word;
		$.ajax({
			url:'/library/board/search',
			method:'post',
			cache:false,
			data:obj,
			dataType:'json',
			success:function(res){
				if(res.ok){
				location.href="/library/board/search/"+section+"/1/"+cmd+"/"+word;
				return false;
				}
				alert("검색결과가 없습니다.");
			},
			err: function(xhr,status,err){
				alert("err:"+err);
			}
		});
 	   return false;
    }

function logout(){
	$.ajax({
		url:'/library/logout',
		method:'get',
		cache:false,
		dataType:'text',
		success:function(res){
			alert(res? "로그아웃되었습니다.":"로그아웃 실패");
			location.href="/library";
		},
		err: function(xhr,status,err){
			alert("err:"+err);
		}
	});
   return false;
}