$(document).ready(function() {

    console.log("h");

});


function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
  
            var roadAddr = data.roadAddress; // 도로명 주소 변수
  
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_detailAddress").readOnly = false;
        }
    }).open();
  }