$(document).ready(function() {

    initializeFirstItemHighlight();
    selectProjectsDetail();

});

function initializeFirstItemHighlight() {
    var secondItem = $(".myPageWrap > ul > li:nth-child(3)");
    secondItem.addClass("highlighted");

    $(".myPageWrap > ul > li").hover(
        function() {
            secondItem.removeClass("highlighted");
        }, function() {
            secondItem.addClass("highlighted");
        }
    );
}

function selectProjectsDetail(){
$('.project_value a').click(function(e) {
    e.preventDefault();

    var projectNo = $(this).data('projectno');

    console.log(projectNo);
   
    var data = {
        projectNo: projectNo
    };


    $.ajax({
        url: 'selectDetailProject',
        type: 'Get',
        data: data,
        dataType: 'json',
        success: function(response) {
           
        },
        error: function(error) {
            
            console.error("Request failed:", error);
        }
    });

        });
};