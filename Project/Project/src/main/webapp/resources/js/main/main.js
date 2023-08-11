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
    
        $('#searchInput').on('focus', function() {
        $(this).select();
    });


});

$(document).ready(function() {
$("#bigCategory").change(function() {
    var selectedValue = $(this).val();

    if (selectedValue === "memberName") {
        $("#nameInput").show();
    } else {
        $("#nameInput").hide();
    }
});
});

 $(document).ready(function() {
     $("#bigCategory").change(function() {
        var selectedCodeNo = $(this).val();

        $("#subCategory").remove();

        $.ajax({
            url: "getSubCategories",
            type: "GET",
            data: { codeNo: selectedCodeNo },
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
     });
 });



 $(document).ready(function() {
    $('.searchBtn').click(function(e) {
        e.preventDefault(); // 폼 제출을 방지

        $.ajax({
            type: 'GET',
            url: 'searchMembers',
            data: {
                bcategory: $('#bigCategory').val(),
                subCategory: $('#subCategory').val(),
                memberName: $('#nameInput').val()
            },
            success: function(data) {
                console.log(data);
                updateTable(data);
            },
            error: function(error) {
                console.error('Error fetching data', error);
            }
        });
    });
});

function updateTable(data) {
    console.log(data.searchMemberList);  // 배열의 내용 확인

    var tbody = $('.table-wrap tbody');
    tbody.empty();

    // searchMemberList를 직접 접근
    data.searchMemberList.forEach(function(member) {
        var row = '<tr class="row">' +
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