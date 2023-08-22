const checkObj = {
    "memberId":false,
    "memberPw": false,
    "memberEmail": false,
    "memberPwConfirm": false,
    "memberName": false,
    "memberTel": false
};

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


// 멤버ID 정상작동확인
$(document).ready(function () {
    var memberId = $("#memberId");
    var memberMessage = $("#memberMessage");

    memberId.on("input", function () {
        if (!memberId.val()) {
            memberMessage.text("아이디를 입력해주세요");
            memberMessage.removeClass("confirm error");
            checkObj.memberId = false;
            return;
        }

        var regExp = /^\d{6}$/;;

       if (regExp.test(memberId.val())) {
         $.ajax({
                 url: "memberIdDubCheck",
                 data: { "memberId": memberId.val() },
                 type: "GET",
                 success: function (result) {
                     if (result == 1) { // If email is already in use
                        memberMessage.text("이미 사용 중인 사번 ");
                        memberMessage.addClass("error").removeClass("confirm");
                        checkObj.memberId = false;
                     } else {
                        memberMessage.text("사용가능한 사번입니다.");
                        memberMessage.addClass("confirm").removeClass("error");
                        checkObj.memberId = true;
                        console.log(checkObj.memberId);
                     }
                 },
                 error: function () {
                    // console.log("에러 발생");
                 }
            });
         } else {
            memberMessage.text("유효하지않은 사번형식.");
            memberMessage.addClass("error").removeClass("confirm");
            checkObj.memberId = false;

         }
    });
});


let memberPw = $("#memberPw");
let memberPwConfirm = $("#memberPwConfirm");
let pwMessage = $("#pwMessage");

// 비밀번호 비교
let checkPw = function() {
    const regExp = /^\d{6}$/;;

    if (memberPw.val() === memberPwConfirm.val()) {
        if (regExp.test(memberPw.val())) {
            pwMessage.text("비밀번호가 일치합니다.");
            pwMessage.addClass("confirm").removeClass("error");
            checkObj.memberPw = true;
            checkObj.memberPwConfirm = true;
            console.log(checkObj.memberPw);
            console.log(checkObj.memberPwConfirm);

        } else {
            pwMessage.text("숫자 6자리만 입력");
            pwMessage.addClass("error").removeClass("confirm");
            checkObj.memberPw = false;
            checkObj.memberPwConfirm = false;
        }
    } else {
        pwMessage.text("비밀번호가 일치하지 않습니다.");
        pwMessage.addClass("error").removeClass("confirm");
        checkObj.memberPw = false;
        checkObj.memberPwConfirm = false;
    }
}

// 비밀번호 정규표현식
memberPw.on("input", function() {
    if (memberPw.val().length == 0) {
        pwMessage.text("숫자 6자리를 입력");
        pwMessage.removeClass("confirm").removeClass("error");
        checkObj.memberPw = false;
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
        checkObj.memberPw = false;
    }
});

memberPwConfirm.on("input", checkPw);

var memberName = $("#memberName");
var nameMessage = $("#nameMessage");


// 멤버이름 입력 >> checkObj 정상
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
                    checkObj.memberName = true;
                    //정상작동확인
                    console.log(checkObj.memberName);

                } else {
                    nameMessage.text("한글 2~10글자 사이로 작성해주세요.");
                    nameMessage.addClass("error").removeClass("confirm");
                    checkObj.memberName = false;

                    // 정상작동 확인
                    // console.log(checkObj.memberName);

                }
    });

// 멤버 주민등록번호
$(document).ready(function() {
    $("input[name='memberBirth']").on('keyup', function() {
        var value = $(this).val().replace(/-/g, ''); // 하이픈 제거

        if (value.length == 6) {
            value = value.substring(0, 6) + "-" + value.substring(6);
            $(this).val(value);
        }
    });
});
    
    
// 멤버 전화번호 >> checkObj 정상
 $(document).ready(function() {
        $("#memberTel").on("input", function() {
            var $telMessage = $("#telMessage");
    
            if (this.value.length == 0) {
                $telMessage.text("전화번호를 입력해주세요.(- 제외)");
                $telMessage.removeClass("confirm error");
                checkObj.memberTel = false;
                return;
            }
    
            var regExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;
    
            if (regExp.test(this.value)) {
                $.ajax({
                    url: "telDupCheck",
                    data: { "memberTel": this.value },
                    type: "GET",
                    success: function(result) {
                        if (result == 1) {
                            $telMessage.text("이미 사용 중인 전화번호 입니다.");
                            checkObj.memberTel = false;
                            console.log(checkObj.memberTel);
                            $telMessage.removeClass("confirm").addClass("error");
                        } else {
                            $telMessage.text("사용가능한 전화번호입니다.");
                            checkObj.memberTel=true;
                            console.log(checkObj.memberTel);
                            $telMessage.removeClass("error").addClass("confirm");
                        }
                    },
                    error: function() {
                    }
                });
            } else {
                $telMessage.text("올바르지않은 연락처형식");
                $telMessage.removeClass("confirm").addClass("error");
                checkObj.memberTel = false;
                console.log(checkObj.memberTel);
            }
        });
    });

        const memberEmail = $("#memberEmail");
        const emailMessage = $("#emailMessage");
        
        memberEmail.on("input", function () {
            // 입력이 되지 않은 경우
            if (memberEmail.val().length == 0) {
                emailMessage.text("메일을 받을 수 있는 이메일을 입력해주세요.");
                emailMessage.removeClass("confirm error");
                checkObj.memberEmail = false;
                return;
            }
        
            const regExp = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+){1,3}$/;
        
            if (regExp.test(memberEmail.val())) { 
                $.ajax({
                    url: "emailDupCheck",
                    data: { "memberEmail": memberEmail.val() },
                    type: "GET",
                    success: function (result) {
                        if (result == 1) { //중복 o
                            emailMessage.text("이미 사용 중인 이메일 입니다.");
                            emailMessage.addClass("error");
                            emailMessage.removeClass("confirm");
                            checkObj.memberEmail = false;
                        } else { //중복 x
                            emailMessage.text("사용 가능한 이메일입니다.");
                            emailMessage.addClass("confirm");
                            emailMessage.removeClass("error");
                            checkObj.memberEmail = true;
                        }
                    },
                    error: function () {
                        // console.log("에러 발생");
                    }
                });
            } else {
                emailMessage.text("이메일 형식이 유효하지 않습니다.");
                emailMessage.addClass("error");
                emailMessage.removeClass("confirm");
                checkObj.memberEmail = false;
            }
        });


// 도로명주소 API    
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



function signUpValidate() {
    let str;

    console.log(
        "checkObj의 상태는 아래와같습니다--------------------------------------------------------------------"+
        checkObj.memberEmail, checkObj.memberId, checkObj.memberName, checkObj.memberPw, checkObj.memberPwConfirm, checkObj.memberTel);

    for (let key in checkObj) {
        if (!checkObj[key]) {
            switch (key) {
                case "memberId": str= "아이디가"; break;
                case "memberEmail": str = "이메일이"; break;
                case "memberPw": str = "비밀번호가"; break;
                case "memberPwConfirm": str = "비밀번호 확인이"; break;
                case "memberName": str = "이름이"; break;
                case "memberTel": str = "전화번호가"; break;
            }
            str += " 유효하지 않습니다.";

            alert(str);

            document.getElementById(key).focus();

            return false;
        }
    }

    return true;

}


fileInput.on("change", function (e) {

    for (var i = 0; i < e.target.files.length; i++) {
      if (!checkExtension(e.target.files[i].name, e.target.files[i].size)) {
        movie_image1.val("");
        return false;
      }
      uploadImageFile1(e.target.files[i]);
    }
  });

  let regex = new RegExp("(.*?.(png|jpg|gif|jpeg)$)");
  let maxSize = 5000000; // 5MB 제한

  function checkExtension(fileName, fileSize) {
    if (fileSize >= maxSize) {
      alert("파일 사이즈 초과");
      return false;
    }
    if (!regex.test(fileName)) {
      alert(
        "해당 종류 파일은 업로드 안됨.\n PNG, JPG, GIF, JPEG 만 가능합니다."
      );
      return false;
    }
    return true;
  }

  let imageUrl1;

  function uploadImageFile1(file) {
    var data = new FormData();
    data.append("file", file);
    $.ajax({
      url: "uploadImage",
      type: "POST",
      data: data,
      cache: false,
      contentType: false,
      processData: false,
      success: function (data1) {
        var jsonObject = JSON.parse(data1);
        imageUrl1 = jsonObject.url;
        console.log("Image URL:", imageUrl1);

        document.getElementById('imageURL').value = imageUrl1; 
      },
      error: function (e) {
        console.error("An error occurred:", e); // 에러 출력
      },
    });
  }

  