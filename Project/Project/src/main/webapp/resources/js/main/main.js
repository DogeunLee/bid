$(document).ready(function() {
    var firstItem = $(".myPageWrap > ul > li:nth-child(1)");
    firstItem.addClass("highlighted");

    $(".myPageWrap > ul > li").hover(
        function() {
            firstItem.removeClass("highlighted");
        }, function() {
            firstItem.addClass("highlighted");
        }
    );
});

