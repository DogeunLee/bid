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

        if (selectedValue === "9999" || selectedValue === "7942") {
            $("#memberName").show();
        } else {
            $("#memberName").hide();
        }

        if (selectedValue !== "7942") {
            // sub-category logic
            $("#subCategory").remove();

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
    });

    $('.searchBtn').click(function(e) {
        e.preventDefault();

        $.ajax({
            type: 'GET',
            url: 'searchMembers',
            data: {
                bcategory: $('#bigCategory').val(),
                subCategory: $('#subCategory').val(),
                memberName: $('#memberName').val()
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
