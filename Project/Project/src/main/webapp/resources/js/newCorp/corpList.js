$(document).ready(function() {

    console.log("h");

    initializeFirstItemHighlight();
    initializeRowClick();

});

function initializeFirstItemHighlight() {
    var secondItem = $(".myPageWrap > ul > li:nth-child(5)");
    secondItem.addClass("highlighted");

    $(".myPageWrap > ul > li").hover(
        function() {
            secondItem.removeClass("highlighted");
        }, function() {
            secondItem.addClass("highlighted");
        }
    );
}


function initializeRowClick() {
    $(document).on('click', '.row', function(e) {
        var url = $(this).data("url");
        window.location.href = url;
    });
}