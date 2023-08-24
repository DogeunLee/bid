$(document).ready(function() {

    console.log("h");

    initializeFirstItemHighlight();

});

function initializeFirstItemHighlight() {
    var secondItem = $(".myPageWrap > ul > li:nth-child(4)");
    secondItem.addClass("highlighted");

    $(".myPageWrap > ul > li").hover(
        function() {
            secondItem.removeClass("highlighted");
        }, function() {
            secondItem.addClass("highlighted");
        }
    );
}