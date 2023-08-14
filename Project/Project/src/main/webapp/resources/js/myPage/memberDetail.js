$(document).ready(function() {

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

});

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
});

