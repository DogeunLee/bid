
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


});
