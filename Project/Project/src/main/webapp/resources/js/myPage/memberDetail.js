$(document).ready(function() {

    checkObj = {
        "memberEmail": { value: false, changed: false },
        "memberTel": { value: false, changed: false }
    };

    initializeFirstItemHighlight();
    dblClickFunction();
    membersTelFunction();
    membersHireDate();
    membersEmailFunction();
    checkValue(inputElement);
    sendMemberData(e);

});

function initializeFirstItemHighlight() {
    var secondItem = $(".myPageWrap > ul > li:nth-child(1)");
    secondItem.addClass("highlighted");

    $(".myPageWrap > ul > li").hover(
        function() {
            secondItem.removeClass("highlighted");
        }, function() {
            secondItem.addClass("highlighted");
        }
    );
}

function dblClickFunction(){
    $('.telInput').dblclick(function(){
        $('.telInput').removeAttr('readonly').focus().select();
    }); 

    $('.emailInput').dblclick(function(){
        $('.memberEmail').removeAttr('readonly').focus().select();
    }); 

    $('#hireDate').dblclick(function() {
        $(this).removeAttr('readonly').focus();
    });

    $('#gradName').dblclick(function(){
        $(this).removeAttr('readonly').focus().select();
    })

    $('#gradValue').dblclick(function(){
        $(this).removeAttr('readonly').focus().select();
    })

    $('#gradHsDate').dblclick(function(){
        $(this).removeAttr('readonly').focus();
    })
    
    $('#gradHeDate').dblclick(function(){
        $(this).removeAttr('readonly').focus();
    })

    $('#expName').dblclick(function(){
        $(this).removeAttr('readonly').focus().select();
    })

    $('#expDept').dblclick(function(){
        $(this).removeAttr('readonly').focus().select();
    })

    $('#expSDate').dblclick(function(){
        $(this).removeAttr('readonly').focus();
    })

    $('#expEate').dblclick(function(){
        $(this).removeAttr('readonly').focus();
    })

    $('.certiName').dblclick(function(){
        $(this).removeAttr('readonly').focus().select();
    })
    $('.certiDate').dblclick(function(){
        $(this).removeAttr('readonly').focus();
    })
}

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

function checkValue(inputElement) {
    const originalValue = $(inputElement).data('original');

    if ($(inputElement).val() === originalValue) {
        alert('새로 입력한 값이 기존 값과 동일합니다.');
        $(inputElement).val(originalValue); 
        return true;  
    }
    return false;
}

function sendMemberData(e) {
    const memberTel = $('#memberTel').val();
    const memberAddr = [
        $('#sample4_postcode').val(),
        $('#sample4_roadAddress').val(),
        $('#sample4_detailAddress').val()
    ];
    const memberEmail = $('#memberEmail').val();
    const memberGender = $('.memberGender option:selected').val();
    const memberLv = $('.memLv option:selected').val();
    const memberGrad = $('.memberGrad option:selected').val();
    const memberHire = $('#hireDate').val();
    const memberBirth = $('#memberBirth').val();

    const data = {
        memberTel: memberTel,
        memberAddr: memberAddr,
        memberEmail: memberEmail,
        memberGender: memberGender,
        memberLv: memberLv,
        memberGrad: memberGrad,
        memberHire: memberHire,
        memberBirth: memberBirth
    };

    console.log(data);
    console.log("checkObj의 상태는 아래와 같습니다:", checkObj.memberEmail, checkObj.memberTel);

    for (let key in checkObj) {
        if (!checkObj[key]) {
            let str = ""; 
            switch (key) {
                case "memberEmail": str = "이메일이"; break;
                case "memberTel": str = "전화번호가"; break;
            }
            str += " 유효하지 않습니다.";

            alert(str);
            document.getElementById(key).focus();
            return false; // 유효성 검사에 실패하면 false를 반환하여 폼 전송을 중지
        }
    }

    return true;
}

function membersTelFunction(){
    $('.telInput').on('input', function() {
        var value = $(this).val();
        if (value && !/^\d+$/.test(value)) { // 값이 있고, 숫자만으로 이루어져 있지 않을 경우
            alert('숫자만 입력해주세요.');
            $(this).val(value.replace(/\D/g, '')); // 숫자가 아닌 문자들을 모두 제거
        }
    });
        
    $('.telInput').keydown(function(e) {
        if (e.which === 13 || e.which === 9) {
            console.log("==============================================")
            checkValue(this);
        }
    });

    $("#memberTel").on("input", function() {
        var $telMessage = $("#telMessage");
    
        if (this.value.length == 0) {
            $telMessage.removeClass("confirm error");
            checkObj.memberTel = false;
            $telMessage.addClass("confirm");
            $telMessage.removeClass("error");
        
            console.log(checkObj.memberTel);
            return;
        }
    
        var regExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;
    
        if (checkValue(this)) {
            $telMessage.text("기존");
            checkObj.memberTel = true;
        
            console.log(checkObj.memberTel);
            return;
        }
    
        checkObj.memberTel.changed = true; 
    
    
        if (regExp.test(this.value)) {
            $.ajax({
                url: "telDupCheck",
                data: { "memberTel": this.value },
                type: "GET",
                success: function(result) {
                    if (result == 1) {
                        $telMessage.text("중복");
                        $telMessage.removeClass("confirm").addClass("error");
                        checkObj.memberTel = false;
                        console.log(checkObj.memberTel);
                    } else {
                        $telMessage.text("가능");
                        $telMessage.removeClass("error").addClass("confirm");
                        checkObj.memberTel = true;
                        console.log(checkObj.memberTel);
                    }
                },
                error: function() {
                }
            });
        } else {
            $telMessage.text("불가");
            $telMessage.removeClass("confirm").addClass("error");
            checkObj.memberTel = false;
            console.log(checkObj.memberTel);
        }
    });

}

function membersEmailFunction(){
    $('.emailInput').keydown(function(e) {
        if (e.which === 13 || e.which === 9) {
            console.log("==============================================")
            checkValue(this);
        }
    });

    
    $('#memberEmail').on("input", function () {
        const emailMessage = $("#emailMessage");

        if (this.value.length == 0) {
            emailMessage.text("불가");
            emailMessage.removeClass("confirm error");
            checkObj.memberEmail = false;
            return;
        }

        const regExp = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+){1,3}$/;

        if (checkValue(this)) {
            emailMessage.text("기존");
            emailMessage.addClass("confirm");
            emailMessage.removeClass("error");
            checkObj.memberEmail = true;
            console.log(checkObj.memberEmail);
            return;
        }

        checkObj.memberEmail.changed = true; 
    
        if (regExp.test(this.value)) { 
            $.ajax({
                url: "emailDupCheck",
                data: { "memberEmail": this.value },
                type: "GET",
                success: function (result) {
                    if (result == 1) { //중복 o
                        emailMessage.text("중복");
                        emailMessage.addClass("error");
                        emailMessage.removeClass("confirm");
                        checkObj.memberEmail = false;
                    } else { //중복 x
                        emailMessage.text("가능");
                        emailMessage.addClass("confirm");
                        emailMessage.removeClass("error");
                        checkObj.memberEmail = true;
                    }
                },
                error: function () {
                    console.log("에러 발생");
                }
            });
        } else {
            emailMessage.text("불가");
            emailMessage.addClass("error");
            emailMessage.removeClass("confirm");
            checkObj.memberEmail = false;
        }
    });

    $(document).on("keypress", "form", function(event) { 
        return event.keyCode != 13;
    });
}

function membersHireDate(){
    $('#hireDate').on('change', function() {
        const selectedDate = new Date($(this).val());
        const today = new Date();
        const eightyYearsAgo = new Date(today);

        // 80년을 빼기
        eightyYearsAgo.setFullYear(today.getFullYear() - 80);

        // 시간, 분, 초를 무시하기 위해 날짜만 비교
        selectedDate.setHours(0, 0, 0, 0);
        today.setHours(0, 0, 0, 0);
        eightyYearsAgo.setHours(0, 0, 0, 0);

        if (selectedDate > today) {
            alert("오늘날짜보다 빠릅니다");
            $(this).val(''); // value를 초기화
        } else if (selectedDate < eightyYearsAgo) {
            alert("80년 이전의 날짜입니다");
            $(this).val(''); // value를 초기화
        }
    });
}
