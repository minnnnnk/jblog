<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${bMap.ID}/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${bMap.ID}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${bMap.ID}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		<img alt="" src="" >
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>		
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
$(document).ready(function(){
	fetchList();
})


$("#btnAddCate").on("click",function(){
	var cateName = $("[name='name']").val();		
	var description = $("[name='desc']").val();
	var id = "${bMap.ID}";
	
	var cateVo = {
			id : id
			,cateName : cateName
			,description: description
	}
	console.log(cateVo);
	
	$.ajax({
		url : "${pageContext.request.contextPath}/{id}/admin/category/add",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(cateVo),
		dataType : "json",
		success : function(cVo){
			
			
			render(cVo,"up");
			
		    $("[name='name']").val("");	
		    $("[name='desc']").val("");
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

})
	
$("#cateList").on("click",".btnCateDel",function(){
	console.log("삭제사진클릭");
	
	
	
	var $this = $(this);
	
	var cateNo = $this.attr("id");
	var pCount = $this.attr("pCount");
	
	if(pCount > 0){
		alert("삭제할수없습니다");
		
		return;
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/{id}/admin/category/Delete",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(cateNo),

		dataType : "json",
		success : function(count){
			/*성공시 처리해야될 코드 작성*/
			
			if(count = 1){
				$("#c"+cateNo).remove();
			}
			
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
	
}) 


function fetchList(){
	var id = '${authUser.id}';
	
	$.ajax({
		url : "${pageContext.request.contextPath}/{id}/admin/category/getList",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(id),

		dataType : "json",
		success : function(cList){
			/*성공시 처리해야될 코드 작성*/
			console.log(cList);

			for(var i=0; i<cList.length; i++){
				render(cList[i],"down")	//vo --> 화면에 그린다.
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
}

//리스트 그리기
function render(cVo, opt){
	console.log("render()");
	
	var str = '';
	str += '<tr id = "c'+cVo.cateNo+'">';
	str += '	<td>'+cVo.cateNo+'</td>';
	str += '	<td>'+cVo.cateName +'</td>';
	str += '	<td>'+cVo.pCount+'</td>';
	str += '	<td>'+cVo.description+'</td>';
	str += '    <td class="text-center">';
	str += '    	<img class="btnCateDel" id="'+cVo.cateNo+'" src="${pageContext.request.contextPath}/assets/images/delete.jpg" pCount = "'+cVo.pCount+'">';
	str += '	</td>';
	str += '</tr>';
	
	if(opt == "down"){
        $("#cateList").append(str);   
     }else if(opt == "up"){
        $("#cateList").prepend(str);
     }else{
        console.log("opt오류");
     }
}
</script>



</html>