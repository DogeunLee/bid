$(document).ready(function() {

    var firstItem = $(".myPageWrap > ul > li:nth-child(1)");
    firstItem.addClass("highlighted");

    $(".myPageWrap > ul > li").hover(
        function() {
            firstItem.removeClass("highlighted");
        },
        function() {
            firstItem.addClass("highlighted");
        }
    );

    $('#searchInput').on('focus', function() {
        $(this).select();
    });

    $("#bigCategory").change(function() {
        var selectedValue = $(this).val();

        if (selectedValue === "9999") {
            $("#subCategory").remove();
            $('.waterbooom').hide();
            $('select[name="ssearch"]').css('display', 'none');
            $("#memberName").show();
        } else {
            $('.hireDate').hide();
            $("#memberName").hide();
            $('.waterbooom').hide();

        }

        if (selectedValue !== "7942") {
     
            $("#subCategory").remove();
            $('.hireDate').hide();
            $('.waterbooom').hide();
            $('select[name="ssearch"]').css('display', 'none');
            $.ajax({
                url: "getSubCategories",
                type: "GET",
                data: { codeNo: selectedValue },
                success: function(response) {
                    var subCategories = response.subCategories;
                    if (subCategories && subCategories.length > 0) {
                        var subCategorySelect = $("<select></select>").attr("id", "subCategory").attr("name", "subCategory");
                
                        $.each(subCategories, function(index, item) {
                            subCategorySelect.append($("<option></option>").attr("value", item.codeId).text(item.codeName));
                        });

                        $("#bigCategory").after(subCategorySelect);
                  
                    }
                },
                error: function(error) {
                    console.error("Error fetching sub-categories:", error);
                }
            });
        }  
           else if ($(this).val() == "7942") {
            $("#subCategory").remove();
                $('.hireDate').show();
                $('select[name="ssearch"]').css('display', 'block');
                $("#memberName").show();
                $('.waterbooom').show();
            } else {
                $('.hireDate').hide();
                $('select[name="ssearch"]').css('display', 'none');
                $("#memberName").hide();
                $('.waterbooom').hide();
            }
       
    });

    $('.searchBtn').click(function(e) {
        e.preventDefault();
    
        var data = {
            bcategory: $('#bigCategory').val(),
            subCategory: $('#subCategory').val(),
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val(),
            memberName: $('#memberName').val()
        };
    
        $('.ssearch-content').each(function(index) {
            if ($(this).val()) {
                // index 값을 기반으로 "searchValue1", "searchValue2" 등의 키를 생성합니다.
                var key = 'searchValue' + (index + 1);
                data[key] = $(this).val();
            }
        });
    
        console.log(data); // 로그에 선택된 값을 출력합니다.
    
        $.ajax({
            type: 'GET',
            url: 'searchMembers',
            data: data,
            success: function(data) {
                console.log(data);
                updateTable(data);
            },
            error: function(error) {
                console.error('Error fetching data', error);
            }
        });
    });
    
    

    $(document).on('click', '.row', function(e) {
        var url = $(this).data("url"); 
        window.location.href = url; 
    });

    function updateTable(data) {
        console.log(data.searchMemberList);

        var tbody = $('.table-wrap tbody');
        tbody.empty();

        data.searchMemberList.forEach(function(member) {
            var contextPath = "${contextPath}";

            var row = '<tr class="row" data-url="/board/myPage/memberDetail/' + member.memberNo + '">' +
            '<td>' + member.memberId + '</td>' +
            '<td>' + member.memberName + '</td>' +
            '<td>' + member.memberGender + '</td>' +
            '<td>' + member.memberBirth + '</td>' +
            '<td>' + member.memberHire + '</td>' +
            '<td>' + member.memberLv + '</td>' +
            '<td>' + member.memberTel + '</td>' +
            '<td>' + member.memberEmail + '</td>' +
            '<td>' + member.memberAddr + '</td>' +
            '<td>' + member.memberGrad + '</td>' +
            '<td>' + member.memberSt + '</td>' +
            '</tr>';
            tbody.append(row);
        });
    }
});

$(document).ready(function() {
    $(".ssearch-content").on('click', fetchOptions);

    function fetchOptions(e) {
        var selectedValue = $(this).data("codeno");
        
        console.log("==================== ajax Test");
        console.log(selectedValue);

        $.ajax({
            url: "getSubOptions",
            type: "GET",
            data: { codeNo: selectedValue },
            success: function(response) {
                var subOptions = response.subCategories;
                var selectBox = $(e.currentTarget);
                if (subOptions && subOptions.length > 0) {
                    // 기존 옵션을 삭제합니다.
                    // selectBox.find("option:not(:first)").remove();
                    
                    // 새로운 하위 옵션들을 추가합니다.
                    
                    $.each(subOptions, function(index, item) {
                        selectBox.append($("<option></option>").attr("value", item.codeId).text(item.codeName));
                    });
                }
                // AJAX 호출 후 새로운 option 요소들이 추가되었으므로, 클릭 이벤트를 비활성화합니다.
                selectBox.off('click', fetchOptions);
            },
            error: function(error) {
                console.error("Error fetching sub-options:", error);
            }
        });
    }
});

$(document).ready(function() {

    // 날짜가 변경될 때마다 유효성 검사
    $('.hireDate').on('change', function() {
        let startDate = new Date($('#startDate').val());
        let endDate = new Date($('#endDate').val());
        let currentDate = new Date();

        // startDate가 현재 날짜보다 미래인지 확인
        if (startDate > currentDate) {
            alert("시작 날짜는 현재 날짜보다 미래일 수 없습니다!");
            $('#startDate').val('');
            return;
        }

        // endDate가 startDate보다 이전인지 확인
        if (endDate < startDate) {
            alert("종료 날짜는 시작 날짜보다 이전일 수 없습니다!");
            $('#endDate').val('');
            return;
        }

        // 두 날짜 간의 차이가 80년을 초과하는지 확인
        let yearsDifference = endDate.getFullYear() - startDate.getFullYear();
        if (yearsDifference > 80) {
            alert("두 날짜의 차이는 80년을 초과할 수 없습니다!");
            $('#endDate').val('');
            return;
        }

        // startDate가 endDate보다 80년 이상 후인지 확인
        if (yearsDifference < -80) {
            alert("두 날짜의 차이는 80년을 초과할 수 없습니다!");
            $('#startDate').val('');
            return;
        }
    });
});



function resetSelects() {
    $('select').prop('selectedIndex', 0);  // reset all select elements to their first option
    // any other reset logic you might want
}

window.onbeforeunload = function() {
    resetSelects();
};

window.addEventListener('popstate', function(event) {
    resetSelects();
});

