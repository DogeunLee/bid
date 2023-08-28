$(document).ready(function() {

    initializeFirstItemHighlight();
    selectProjectsDetail();
    infinityScroll();

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
            var projectData = response.selectPDV[0];
            var corpData = projectData.corp;
    
            $('#projectSDate').val(projectData.projectSDate); 
            $('#projectEDate').val(projectData.projectEDate);
            $('#projectPrice').val(projectData.projectPrice);
            $('#sample4_postcode').val(projectData.projectAddr.split(",,")[0]);
            $('#sample4_roadAddress').val(projectData.projectAddr.split(",,")[1]);
            $('#sample4_detailAddress').val(projectData.projectAddr.split(",,")[2]);
        
            $('#corpName').val(corpData.corpName);
            $('#corpTel').val(corpData.corpTel);
            $('#corpManager').val(corpData.corpManager);
            $('#corpManagerTel').val(corpData.corpManagerTel);
        },
        error: function(error) {
            console.error("Request failed:", error);
        }
    });

        });
};

function infinityScroll() {
    var isLoading = false; // 중복 로딩 방지
    var currentPage = 1; // 현재 페이지 번호

    $(".default-infoWrap").scroll(function() {
        var wrap = $(this); // 현재 스크롤하는 요소

        if (wrap.scrollTop() + wrap.innerHeight() >= wrap[0].scrollHeight - 100 && !isLoading) {
            isLoading = true;
            currentPage++; // 페이지 번호 증가

            console.log("h");

            $.ajax({
                url: '?cp=' + currentPage, // 서버에 요청할 URL
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    // 데이터 추가
                    $.each(data.projectList, function(index, project) {
                        wrap.append('<div class="project_value"><a data-projectno="' + project.projectNo + '">' + project.projectValue + '</a></div>');
                    });

                    isLoading = false; // 로딩 완료

                    // 만약 더 이상 로드할 데이터가 없다면 
                    if (data.projectList.length == 0) {
                        wrap.off('scroll'); // 스크롤 이벤트 제거
                    }
                },
                error: function() {
                    isLoading = false;
                    alert('데이터 로드 중 오류가 발생했습니다.');
                }
            });
        }
    });
}