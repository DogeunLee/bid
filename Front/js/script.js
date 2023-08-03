const memberId = document.getElementById("memberId");
const memberPw = document.getElementById("memberPw");

function loginValidate() {

  if (memberId.value.trim().length == 0) {
    alert("Input your ID first");
    memberId.value = ""; 
    memberId.focus();
    return false;
  }

  if (memberPw.value.trim() == "") {
    alert("Input your Password");
    memberPw.value = "";
    memberPw.focus();
    return false;
  }

  return true;
}
