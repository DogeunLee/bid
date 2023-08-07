$(document).ready(function() {
    var secondItem = $(".myPageWrap > ul > li:nth-child(2)");
    secondItem.addClass("highlighted");

    $(".myPageWrap > ul > li").hover(
        function() {
            secondItem.removeClass("highlighted");
        }, function() {
            secondItem.addClass("highlighted");
        }
    );
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

let fileInput = $("#fileInput");

$("#fileInput").on("change", function () {
  if (this.files && this.files[0]) {
    // Clear the previous image
    $(".addfileList").empty();

    var file = this.files[0];
    var reader = new FileReader();

    reader.onload = function (e) {
      // 이미지를 브라우저에 표시합니다.
      // Add the delete button (x) to the image
      $(".addfileList").append(
        '<div class="upimgList">' +
         
          '<img src="' +
          e.target.result +
          '">' +
          "</div>"
      );
    };

    // 이미지 파일을 읽습니다.
    reader.readAsDataURL(file);
  }
});

const checkObj = {
  "memberId": false,
  "memberPw": false,
  "memberPwConfirm": false,
  "memberName": false,
  "memberBirth": false,
  "memberHire": false,
  "memberTel": false,
};


