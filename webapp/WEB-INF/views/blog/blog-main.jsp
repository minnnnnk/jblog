<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

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
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
				<c:choose>
					<c:when test="${bMap.LOGOFILE == null}">
					<!-- 기본이미지 -->
						<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
					</c:when>
					<c:otherwise>
					<!-- 사용자업로드 이미지 -->
						<img id="proImg" src="${pageContext.request.contextPath}/upload/${bMap.LOGOFILE}">
					</c:otherwise>
				</c:choose>	
					<div id="nick">${bMap.USERNAME}(${bMap.ID})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
					
					<c:forEach items="${cList}" var="cList">
						<li><a href="${pageContext.request.contextPath}/${bMap.ID}?cateNo=${cList.cateNo}">${cList.cateName}</a></li>
					</c:forEach>	
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				
					<c:choose>
						<c:when test="${pList == null}">
							<div id="postBox" class="clearfix">
									<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
									<div id="postDate" class="text-left"><strong></strong></div>
									<div id="postNick"></div>
							</div>
						</c:when>
						<c:when test="${request.param.postNo == 0 or request.param.postNo == null}">
							<div id="postBox" class="clearfix">
									<div id="postTitle" class="text-left"><strong>${pList[0].postTitle}</strong></div>
									<div id="postDate" class="text-left"><strong>${pList[0].regDate}</strong></div>
									<div id="postNick">${bMap.USERNAME}(${bMap.ID})님</div>
							</div>
							<div id="post" >
								${pList[0].postContent}
							</div>
						</c:when>
						<c:otherwise>
							<div id="postBox" class="clearfix">
									<div id="postTitle" class="text-left"><strong>${pVo.postTitle}</strong></div>
									<div id="postDate" class="text-left"><strong>${pVo.regDate}</strong></div>
									<div id="postNick">${bMap.USERNAME}(${bMap.ID})님</div>
							</div>
							<div id="post" >
								${pVo.postContent}
							</div>
						</c:otherwise>
					</c:choose>
					<!-- comment -->
					
					
					<div id="cmt">
						<form id="cmtAdd">
							<table border ="1">
								<colgroup>
									<col style="">
									<col style="width: 20%;">
								</colgroup>
								
								<tr>
									<td>${authUser.userName}</td>
									<td><input type="text" name="cmtContent" value=""></td>
									<td><button type="button" class="btn" id="btnCmdAdd">저장</button></td>
								</tr>
								
								
								<!-- list -->
								<tr>
									<td>이름</td>
									<td>컨텐츠</td>
									<td>날짜</td>
								</tr>
								
							</table>
							
							
							
						</form>
					</div>
					
					
					
					<!-- //post -->
					
					
					
					
					
				<!-- 글이 없는 경우 -->
				
					
						<!-- 
						<div id="post" > 
					</div> -->
						<div id="list">
							<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
							<table>
								<colgroup>
									<col style="">
									<col style="width: 20%;">
								</colgroup>
								
								<c:forEach items="${pList}" var="pList">
									<tr>
										<td class="text-left"><a href="${pageContext.request.contextPath}/${bMap.ID}?cateNo=${pList.cateNo}&postNo=${pList.postNo}">${pList.postTitle}</a></td>
										<td class="text-right">${pList.regDate}</td>
									</tr>
								</c:forEach>
								
								
							</table>
						</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
$("#btnCmdAdd").on("click",function(){
	console.log("저장버튼클릭");
	
	var userNo = '${authUser.userNo}'
	var postNo = '${pList[0].postNo}'
	
	var cmtContent = $("[name='cmtContent']");
	
	var CommentVo = {
			userNo : userNo
			, postNo : postNo
			, cmtContent : cmtContent
	} 
	
		$.ajax({
		url : "${pageContext.request.contextPath}/{id}/Comment/add",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(CommentVo),
		dataType : "json",
		success : function(){
			
			
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

	
});


</script>

</html>