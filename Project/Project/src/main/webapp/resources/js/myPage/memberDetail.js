const checkObj = {
    "memberEmail": false,
    "memberTel": false
};

$(document).ready(function() {

    var secondItem = $(".myPageWrap > ul > li:nth-child(1)");
    secondItem.addClass("highlighted");

    $(".myPageWrap > ul > li").hover(
        function() {
            secondItem.removeClass("highlighted");
        }, function() {
            secondItem.addClass("highlighted");
        }
    );

    $('.telInput').dblclick(function(){
        $('.telInput').removeAttr('readonly').focus().select();
    }); 

    $('.telInput').on('input', function() {
        var value = $(this).val();
        if (value && !/^\d+$/.test(value)) { // 값이 있고, 숫자만으로 이루어져 있지 않을 경우
            alert('숫자만 입력해주세요.');
            $(this).val(value.replace(/\D/g, '')); // 숫자가 아닌 문자들을 모두 제거
        }
    });

    $('.emailInput').dblclick(function(){
        $('.emailInput').removeAttr('readonly').focus().select();
    }); 

    const memberEmail = $(".emailInput"); 
    const emailMessage = $("#emailMessage");
    
    memberEmail.on("input", function () {
        // 입력이 되지 않은 경우
        if (memberEmail.val().length == 0) {
            emailMessage.text("불가");
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

        $('#hireDate').dblclick(function() {
            $(this).removeAttr('readonly').focus();
        });
 

    $(document).on("keypress", "form", function(event) { 
        return event.keyCode != 13;
    });


    $("#memberTel").on("input", function() {
        
        var $telMessage = $("#telMessage");

        if (this.value.length == 0) {
            $telMessage.text("전화번호를 입력해주세요.(- 제외)");
            $telMessage.removeClass("confirm error");
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
                        $telMessage.text("중복");
                        $telMessage.removeClass("confirm").addClass("error");
                    } else {
                        $telMessage.text("가능");
                        $telMessage.removeClass("error").addClass("confirm");
                    }
                },
                error: function() {
                }
            });
        } else {
            $telMessage.text("불가");
            $telMessage.removeClass("confirm").addClass("error");
        }
    });


    

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


