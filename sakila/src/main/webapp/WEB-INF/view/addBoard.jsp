<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
    crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
    integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
    crossorigin="anonymous">
 
<!-- jquery를 사용하기위한 CDN주소 -->
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 
<!-- bootstrap javascript소스를 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified JavaScript -->
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
    crossorigin="anonymous"></script>
 
<script>
	// 제이쿼리
    $(document).ready(function() {
        $('#addButton').click(function() {
        	
        	// 별도
        	// 파일들중 하나라도 첨부되지 않으면 ck = true;
        	let ck = false;
        	let boardfile = $('.boardfile'); // 배열
        	// boardfile.each() 안쓰는 이유 -> break키워드를 사용하기 위해서 for반복문을 사용
        	for(let item of boardfile) {
        		if($(item).val() == '') { //공백이면
        			ck = true;
        			console.log('첨부되지 않은 파일이 있습니다! 파일을 선택해 주세요!')
        			break;
        		}
        	}
        	
        	/*
        	$('.boardfile').each(function(index, item) { //클래스명이 boardfile인것 , each ->반복문 돌리겠다
        		// item --> <input type="file" name="boardfile" class="boardfile"> <--이게 없으면 돌아가지 않음.
        		if($(item).val() == '') {//제이쿼리 매소드로 표현 할 수 있음
        			// alert('첨부되지 않은 파일이 있습니다! 파일을 선택하세요!') //파일을 선택하지 않았을 경우
        			console.log('첨부되지 않은 파일이 있습니다! 파일을 선택하세요!');
        			ck = true;	
        			return ;
        		} 
        	});
        	*/
        	
        	// 파일 첨부 제이쿼리
        	if(ck) { // if(ck == true)
        		alert('첨부되지 않은 파일이 있습니다! 파일을 선택해 주세요!');
        	// 글자 적는 제이쿼리
        	} else if ($('#boardPw').val().length < 4) {
                alert('boardPw는 4자이상 이어야 합니다');
                $('#boardPw').focus();
            } else if ($('#boardTitle').val() == '') {
                alert('boardTitle을 입력하세요');
                $('#boardTitle').focus();
            } else if ($('#boardContent').val() == '') {
                alert('boardContent을 입력하세요');
                $('#boardContent').focus();
            } else if ($('#staffId').val() == '') {
                alert('staffId을 입력하세요');
                $('#staffId').focus();
            } else {
                $('#addForm').submit();
            }
        });
        // 섞이지 않게 해야함.
        
       
        // 파일 제이쿼리 input type="file" 추가  (마지막 태그를 추가(파일))
        $('#addFileBtn').click(function() {
        	console.log('#addFileBtn click!');
        	$('#inputFile').append('<input type="file" name="boardfile" class="boardfile">'); //파일을 여러개 생성 가능
        });
        
        
     	// 파일 제이쿼리 input type="file" 삭제 (마지막 태그를 삭제(파일))
        $('#deleteFileBtn').click(function() {
        	console.log('#deleteFileBtn click!');
        	$('#inputFile').children().last().remove();
        });
    });
</script>
<title>addBoard</title>
</head>
<body>
    <div class="container">
        <h1>addBoard</h1>
        <form id="addForm" action="${pageContext.request.contextPath}/admin/addBoard" 
        		method="post"
        		enctype="multipart/form-data">
            <!-- BoardFile -->
            <div>
            	<div>
            		<button id="addFileBtn" type="button">파일 추가</button>
            		<button id="deleteFileBtn" type="button">파일 삭제</button>
            	</div>
            	
            	<div id="inputFile"> <!-- 파일 추가 버튼을 누르면 파일찾는게 생김 -->
            		<!--<div></div>--> <!-- 자식까지는 생성 가능하지만 자식의 자식은 생성 x -->
            	</div>
            </div>
            
            <div class="form-group">
                <label for="boardPw">boardPw :</label> <input class="form-control"
                    name="board.boardPw" id="boardPw" type="password">
            </div>
            <div class="form-group">
                <label for="boardPw">boardTitle :</label> <input
                    class="form-control" name="board.boardTitle" id="boardTitle" type="text">
            </div>
            <div class="form-group">
                <label for="boardContent">boardContent :</label>
                <textarea class="form-control" name="board.boardContent" id="boardContent"
                    rows="5" cols="50"></textarea>
            </div>
            <div class="form-group">
                <label for="staffId">staffId :</label> <input
                    class="form-control" name="board.staffId" id="staffId" type="text">
            </div>
            <div>
                <input class="btn btn-default" id="addButton" type="button" value="글입력">
                <input class="btn btn-default" type="reset" value="초기화"> 
                <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getBoardList">글목록</a>
            </div>
        </form>
    </div>
</body>
</html>