// 사용자가 답변을 했을 때 서버로 데이터가 바로 이동하지 않는 기능
$(".answer-write input[type=submit]").click(addAnswer)	// $부분 html태그에 click 이벤트 발생시 addAnswer 호출

function addAnswer(e) {		//parameter e는 이벤트
	e.preventDefault();		// 서버로 데이터가 바로 전송 되지 않게
	console.log("click me");
	
	var queryString = $(".answer-write").serialize();		//서버로 전달할 data를 태그에서 읽어온다
	console.log("query : " + queryString);
	
	var url = $(".answer-write").attr("action");
	console.log("url : " + url);
	
	$.ajax({
		type : 'post',
		url : url,
		data : queryString,
		dataType : 'json',
		error : onError,			//에러가 발생하면 onError 호출
		success : onSuccess})
}

function onError() {
	
}

function onSuccess(data, status) {
	console.log(data);
}