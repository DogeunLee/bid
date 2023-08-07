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
    $(".addfileList").empty();

    var file = this.files[0];
    var reader = new FileReader();

    reader.onload = function (e) {
      $(".addfileList").append(
        '<div class="upimgList">' +
         
          '<img src="' +
          e.target.result +
          '">' +
          "</div>"
      );
    };

    reader.readAsDataURL(file);
  }
});


$(document).ready(function() {
    $('#select-mail').change(function() {
      var selectedDomain = $(this).find('option:selected').text();
      
      if (selectedDomain !== "직접입력") {
        $('#email-domain').val(selectedDomain);
      } else {
        $('#email-domain').val('');
      }
    });
  });



$(document).ready(function () {
    var memberId = $("#memberId");
    var memberMessage = $("#memberMessage");

    memberId.on("input", function () {
        if (!memberId.val()) {
            memberMessage.text("아이디를 입력해주세요");
            memberMessage.removeClass("confirm error");
            return;
        }

        var regExp = /^\d{6}$/;;

       if (regExp.test(memberId.val())) { // If email is valid
         $.ajax({
                 url: "memberIdDubCheck",
                 data: { "memberId": memberId.val() },
                 type: "GET",
                 success: function (result) {
                     if (result == 1) { // If email is already in use
                        memberMessage.text("이미 사용 중인 사번 입니다.");
                        memberMessage.addClass("error").removeClass("confirm");
       
                     } else {
                        memberMessage.text("사용 가능한 사번입니다.");
                        memberMessage.addClass("confirm").removeClass("error");
 
                     }
                 },
                 error: function () {
                    // console.log("에러 발생");
                 }
            });
         } else { // If email is invalid
            memberMessage.text("아이디 형식이 유효하지 않습니다.");
            memberMessage.addClass("error").removeClass("confirm");
   
         }
    });
});


let memberPw = $("#memberPw");
let memberPwConfirm = $("#memberPwConfirm");
let pwMessage = $("#pwMessage");

let checkPw = function() {
    const regExp = /^\d{6}$/;;

    if (memberPw.val() === memberPwConfirm.val()) {
        if (regExp.test(memberPw.val())) {
            pwMessage.text("비밀번호가 일치합니다.");
            pwMessage.addClass("confirm").removeClass("error");
        } else {
            pwMessage.text("숫자 6자리만 입력");
            pwMessage.addClass("error").removeClass("confirm");
        }
    } else {
        pwMessage.text("비밀번호가 일치하지 않습니다.");
        pwMessage.addClass("error").removeClass("confirm");
    }
}

memberPw.on("input", function() {
    if (memberPw.val().length == 0) {
        pwMessage.text("숫자 6자리를 입력");
        pwMessage.removeClass("confirm").removeClass("error");
        return;
    }

    const regExp = /^\d{6}$/;;

    if (regExp.test(memberPw.val())) {

        if (memberPwConfirm.val().length == 0) {
            pwMessage.text("");
            pwMessage.addClass("confirm").removeClass("error");
        } else {
            checkPw();
        }
    } else {
        pwMessage.text("숫자 6자리를 입력");
        pwMessage.addClass("error").removeClass("confirm");
    }
});

memberPwConfirm.on("input", checkPw);


var memberName = $("#memberName");
var nameMessage = $("#nameMessage");

memberName.on('input', function () {
    if (memberName.val().length == 0) {
        nameMessage.text("한글 2~10글자 사이로 작성해주세요.");
        nameMessage.removeClass("confirm error");

        checkObj.memberName = false;
        return;
    }

    var regExp = /^[가-힣]{2,10}$/;

    if (regExp.test(memberName.val())) {
                    nameMessage.text("올바른 입력입니다.");
                    nameMessage.addClass("confirm").removeClass("error");

                } else {
                    nameMessage.text("한글 2~10글자 사이로 작성해주세요.");
                    nameMessage.addClass("error").removeClass("confirm");
                }
    });



//     const userTel = document.getElementById("userTel");
// const telMessage = document.getElementById("telMessage");


// userTel.addEventListener("input", function () {

//     // 입력이 되지 않은 경우
//     if (userTel.value.length == 0) {

//         telMessage.innerText = "전화번호를 입력해주세요.(- 제외)";
//         telMessage.classList.remove("confirm", "error");

//         checkObj.userTel = false;
//         return;

//     }

//     // 연락처 정규식
//     const regExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;

//     if (regExp.test(userTel.value)) { // 유효한 경우

//         $.ajax({

//             url: "telDupCheck",

//             data: { "userTel": userTel.value },

//             type: "GET",

//             success: function (result) {


//                 if (result == 1) { //중복 o
//                     telMessage.innerText = "이미 사용 중인 전화번호 입니다.";
//                     telMessage.classList.add("error");
//                     telMessage.classList.remove("confirm");

//                     checkObj.userTel = false;

//                 } else { //중복 x
//                     telMessage.innerText = "";
//                     telMessage.classList.add("confirm");
//                     telMessage.classList.remove("error");

//                     checkObj.userTel = true;

//                 }
//             },

//             error: function () {

//                 //console.log("에러 발생");
//             }

//         });





//     } else { // 유효하지 않은 경우
//         telMessage.innerText = "연락처 형식이 올바르지 않습니다.";
//         telMessage.classList.add("error");
//         telMessage.classList.remove("confirm");

//         checkObj.userTel = false;
//     }
// });
