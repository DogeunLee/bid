const checkObj = {
    "memberPw": false,
    "newMemberPw": false,
    "newMemberPwCheck": false
};


$(document).ready(function() {

    console.log("systemOn");

    var secondItem = $(".myPageWrap > ul > li:nth-child(1)");
    secondItem.addClass("highlighted");

    $(".myPageWrap > ul > li").hover(
        function() {
            secondItem.removeClass("highlighted");
        }, function() {
            secondItem.addClass("highlighted");
        }
    );

    const regExp = /^[\w!@#_-]{6,20}$/;
    let hasCheckStarted = false; // 새로운 변수 추가

    $(".newPasswordIp").on("input", function () {
        if (!regExp.test($(this).val())) {
            $("#newPwMessage").text("영어, 숫자, 특수문자(!,@,#,-,_) 6~20글자 사이로 작성해주세요.")
                .addClass("error")
                .removeClass("confirm");
                checkObj.newMemberPw = false;
                console.log(checkObj.newMemberPw)
        } else {
            $("#newPwMessage").text("")
                .removeClass("error confirm");
                checkObj.newMemberPw = true;
                console.log(checkObj.newMemberPw)
        }
        
        if (hasCheckStarted) { // 비교가 시작되었을 때만 실행
            checkPasswordConfirmation();
        }
    });

    $(".newPasswordIpCheck").on("input", function() {
        hasCheckStarted = true; // 입력 시작을 나타내는 변수를 true로 설정
        checkPasswordConfirmation();
    });

    function checkPasswordConfirmation() {
        if ($(".newPasswordIp").val() !== $(".newPasswordIpCheck").val()) {
            $("#newPwCheckMessage").text("비밀번호가 일치하지 않습니다.")
                .addClass("error")
                .removeClass("confirm");
                checkObj.newMemberPwCheck = false;
                console.log(checkObj.newMemberPwCheck)
        } else if (!regExp.test($(".newPasswordIpCheck").val())) {
            $("#newPwCheckMessage").text("영어, 숫자, 특수문자(!,@,#,-,_) 6~20글자 사이로 작성해주세요.")
                .addClass("error")
                .removeClass("confirm");
                checkObj.newMemberPwCheck = false;
                console.log(checkObj.newMemberPwCheck)
        } else {
            $("#newPwCheckMessage").text("")
                .removeClass("error confirm");
                checkObj.newMemberPwCheck = true;
                console.log(checkObj.newMemberPwCheck)
        }
    }

});

function checkPwValidation() {
    if (!checkObj.newMemberPw || !checkObj.newMemberPwCheck) {
        alert("비밀번호를 올바르게 입력해주세요.");
        return false; // 폼 제출 중지
    }
    return true; // 폼 제출 진행
}