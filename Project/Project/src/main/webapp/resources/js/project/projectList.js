var selectedRows = [];

$(document).ready(function () {
  initializeFirstItemHighlight();
  infinityScroll();
  selectProjectsDetail();
  changeOrange();
  modalClose();
  updateMemberSet();
  initializeBigCategoryChange();
  initializeSearchButton();
  initializeSsearchContentClick();
  addProjectList();
  infinityScrollMember();
  removeNewMember();
  addSelected();
});

// aside 태그색상변경
function initializeFirstItemHighlight() {
  var secondItem = $(".myPageWrap > ul > li:nth-child(3)");
  secondItem.addClass("highlighted");

  $(".myPageWrap > ul > li").hover(
    function () {
      secondItem.removeClass("highlighted");
    },
    function () {
      secondItem.addClass("highlighted");
    }
  );
}

// 선택된 프로젝트 불켜기
function changeOrange() {
  $(".default-infoWrap").on("click", ".project_value a", function () {
    $(".project_value a").css("background-color", "");
    $(this).css("background-color", "rgb(251,122,10)");
  });
}

// 기본화면 정보출력 + ajax
function selectProjectsDetail() {
  $(".default-infoWrap").on("click", ".project_value a", function (e) {
    e.preventDefault();

    var projectNo = $(this).data("projectno");

    $(".memberBtnWrap button").attr("data-projectno", projectNo);
    $(".confrimBtnWrap button").attr("data-projectno", projectNo);
    $(".confrimBtnWrap input").attr("value", projectNo);

    var data = {
      projectNo: projectNo,
    };

    $.ajax({
      url: "selectDetailProject",
      type: "Get",
      data: data,
      dataType: "json",
      success: function (response) {
        var projectData = response.selectPDV[0];
        var corpData = projectData.corp;

        $("#projectSDate").val(projectData.projectSDate);
        $("#projectEDate").val(projectData.projectEDate);
        $("#projectPrice").val(projectData.projectPrice);
        $("#sample4_postcode").val(projectData.projectAddr.split(",,")[0]);
        $("#sample4_roadAddress").val(projectData.projectAddr.split(",,")[1]);
        $("#sample4_detailAddress").val(projectData.projectAddr.split(",,")[2]);

        $("#corpName").val(corpData.corpName);
        $("#corpTel").val(corpData.corpTel);
        $("#corpManager").val(corpData.corpManager);
        $("#corpManagerTel").val(corpData.corpManagerTel);
        $("#projectSt").val(projectData.projectSt);

        var members = response.selectPMemberList;
        var tbody = $(".tableWrap tbody");
    
        tbody.empty(); 
    
        $.each(members, function(index, member) {
            var tr = $("<tr>");
            tr.append($("<td>").text(member.memberId));
            tr.append($("<td>").text(member.memberName));
            tr.append($("<td>").text(member.memberLv));
            tr.append($("<td>").text(member.memberTel));
            tr.append($("<td>").text(member.memberGrad));
            tbody.append(tr);
        });

      },
      error: function (error) {
        console.error("Request failed:", error);
      },
    });
  });
}

// 기본화면 프로젝트 무한스크롤
function infinityScroll() {
  var isLoading = false;
  var currentPage = 1;

  $(".default-infoWrap").scroll(function () {
    var wrap = $(this);

    if (
      wrap.scrollTop() + wrap.innerHeight() >= wrap[0].scrollHeight - 100 &&
      !isLoading
    ) {
      isLoading = true;
      currentPage++;

      $.ajax({
        type: "GET",
        url: "getMoreProjectList?cp=" + currentPage,
        dataType: "json",
        success: function (response) {
          if (response) {
            $.each(response.projectList, function (index, project) {
              wrap.append(
                '<div class="project_value"><a data-projectno="' +
                  project.projectNo +
                  '">' +
                  project.projectValue +
                  "</a></div>"
              );
            });
          }
        },
        error: function (error) {
          console.error("Error fetching data:", error);
        },
      });
    }
  });
}

// 모달팝업 시작 + 선택된멤버 AJAX
function updateMemberSet() {
  $(document).on("click", ".updateMemberSet", function() {

    let projectNo = $(this).attr("data-projectno");
    console.log(projectNo);

    if (typeof projectNo === 'undefined' || !projectNo) {
      alert("프로젝트를 선택해주세요");
      return; 
    }
    
    $(".modals").addClass("displayFlex");

     var data = {
      projectNo: projectNo,
    };

    $.ajax({
      url: "selectDetailProject",
      type: "Get",
      data: data,
      dataType: "json",
      success: function (response) {

        var members = response.selectPMemberList;
        var tbody = $(".newTbl tbody");
    
        tbody.empty(); 
    
        $.each(members, function(index, member) {
            var tr = $("<tr class='newMem' data-id='" + member.memberId + "'>");
            tr.append($("<td>").text(member.memberId));
            tr.append($("<td>").text(member.memberName));
            tr.append($("<td>").text(member.memberLv));
            tbody.append(tr);
        });
        addSelected();
      },
      error: function (error) {
        console.error("Request failed:", error);
      },
    });

  });
}

// 모달팝업 멤버리스트 무한스크롤
function infinityScrollMember() {
  var isLoading = false;
  var currentPage = 1;

  $(".table-wrap").scroll(function () {
    var wrap = $(this);

    if (
      wrap.scrollTop() + wrap.innerHeight() >= wrap[0].scrollHeight - 100 &&
      !isLoading
    ) {
      isLoading = true;
      currentPage++;

      $.ajax({
        type: "GET",
        url: "getMoreMemberList?cp=" + currentPage,
        dataType: "json",
        success: function (response) {
          if (response && response.memberList) {
            $.each(response.memberList, function (index, member) {
           
              var newRow = `<tr class="row" data-id="${member.memberId}">
                                  <td>${member.memberId}</td>
                                  <td>${member.memberName}</td>
                                  <td>${member.memberLv}</td>
                                  <td>${member.memberTel}</td>
                                  <td>${member.memberGrad}</td>
                                  <td>${member.memberSt}</td>
                              </tr>`;

              $("#memberListTbody").append(newRow);
              addSelected();
            });
          }
          isLoading = false;
        },
        error: function (error) {
          console.error("Error fetching data:", error);
          isLoading = false;
        },
      });
    }
  });
  addSelected();
}

// 모달팝업 검색기능 카테고리변경시 작동
function initializeBigCategoryChange() {
  $(document).on("change", "#bigCategory", function () {
    var selectedValue = $(this).val();
    console.log("hhhhhhhhhhhhhhhh");

    if (selectedValue === "9999") {
      $("#subCategory").remove();
      $(".waterbooom").hide();
      $('select[name="ssearch"]').css("display", "none");
      $("#memberName").show();
    } else {
      $(".hireDate").hide();
      $("#memberName").hide();
      $(".waterbooom").hide();
    }

    if (selectedValue !== "7942") {
      $("#subCategory").remove();
      $(".hireDate").hide();
      $(".waterbooom").hide();
      $('select[name="ssearch"]').css("display", "none");

      console.log(selectedValue);
      $.ajax({
        url: "getSubCategories",
        type: "GET",
        data: { codeNo: selectedValue },
        success: function (response) {
          var subCategories = response.subCategories;
          if (subCategories && subCategories.length > 0) {
            var subCategorySelect = $("<select></select>")
              .attr("id", "subCategory")
              .attr("name", "subCategory");

            $.each(subCategories, function (index, item) {
              subCategorySelect.append(
                $("<option></option>")
                  .attr("value", item.codeId)
                  .text(item.codeName)
              );
            });

            $("#bigCategory").after(subCategorySelect);
          }
        },
        error: function (error) {
          console.error("Error fetching sub-categories:", error);
        },
      });
    } else if ($(this).val() == "7942") {
      $("#subCategory").remove();
      $(".hireDate").show();
      $('select[name="ssearch"]').css("display", "block");
      $("#memberName").show();
      $(".waterbooom").show();
    } else {
      $(".hireDate").hide();
      $('select[name="ssearch"]').css("display", "none");
      $("#memberName").hide();
      $(".waterbooom").hide();
    }
  });
  addSelected();
}

// 모달팝업 종료
function modalClose() {
  $(".closeModal").click(function () {
    $(".modals").addClass("displayHidden").removeClass("displayFlex");
  });

  $(".modals").click(function (e) {
    if (
      !$(e.target).hasClass("newMemberInput") &&
      $(e.target).parents(".newMemberInput").length === 0
    ) {
      $(".modals").addClass("displayHidden").removeClass("displayFlex");
    }
  });
}

// 검색 후 멤버리스트 업데이트
function updateTable(data) {
  var tbody = $(".table-wrap tbody");
  tbody.empty();

  data.searchMemberList.forEach(function (member) {
  
    var contextPath = "${contextPath}";
    var row =
    '<tr class="row" data-id="' + member.memberId + '">'+
      "<td>" +
      member.memberId +
      "</td>" +
      "<td>" +
      member.memberName +
      "</td>" +
      "<td>" +
      member.memberLv +
      "</td>" +
      "<td>" +
      member.memberTel +
      "</td>" +
      "<td>" +
      member.memberGrad +
      "</td>" +
      "<td>" +
      member.memberSt +
      "</td>" +
      "</tr>";
    tbody.append(row);
    const tableCells = document.querySelectorAll("td");

    tableCells.forEach((cell) => {
      if (cell.textContent.trim() === "undefined") {
        cell.textContent = "";
      }
    });
    
  });
  infinityScrollMember();

}

// ajax 페이지네이션 업데이트
function updatePagination(pagination) {
  var pageNationDiv = $(".page_Nation");
  pageNationDiv.empty();

  var url = "?cp=";
  var content = "";

  // Create anchors with data-page attribute to store the page number
  content += '<div><a href="#" data-page="1">&lt;&lt;</a></div>';
  content +=
    '<div><a href="#" data-page="' + pagination.prevPage + '">&lt;</a></div>';

  for (var i = pagination.startPage; i <= pagination.endPage; i++) {
    if (i === pagination.currentPage) {
      content +=
        '<div><a class="selected_Cp" data-page="' + i + '">' + i + "</a></div>";
    } else {
      content += '<div><a href="#" data-page="' + i + '">' + i + "</a></div>";
    }
  }

  content +=
    '<div><a href="#" data-page="' + pagination.nextPage + '">&gt;</a></div>';
  content +=
    '<div><a href="#" data-page="' +
    pagination.maxPage +
    '">&gt;&gt;</a></div>';

  pageNationDiv.append(content);

  pageNationDiv.find("a").on("click", function (e) {
    e.preventDefault();
    var page = $(this).data("page"); 
    fetchDataForPage(page);
  });
}

// 검색기능 옵션 변경하기
function fetchOptions(e) {
  var selectedValue = $(this).data("codeno");

  console.log("==================== ajax Test");
  console.log(selectedValue);

  $.ajax({
    url: "getSubOptions",
    type: "GET",
    data: { codeNo: selectedValue },
    success: function (response) {
      var subOptions = response.subCategories;
      var selectBox = $(e.currentTarget);
      if (subOptions && subOptions.length > 0) {
       
        $.each(subOptions, function (index, item) {
          selectBox.append(
            $("<option></option>")
              .attr("value", item.codeId)
              .text(item.codeName)
          );
        });
      }
      selectBox.off("click", fetchOptions);
    },
    error: function (error) {
      console.error("Error fetching sub-options:", error);
    },
  });
  addSelected();
}

// 검색기능 멤버옵션 변경하기
function fetchDataForPage(page) {
  var data = {
    bcategory: $("#bigCategory").val(),
    subCategory: $("#subCategory").val(),
    startDate: $("#startDate").val(),
    endDate: $("#endDate").val(),
    memberName: $("#memberName").val(),
    cp: page, // Include the current page in the request
  };

  $(".ssearch-content").each(function (index) {
    if ($(this).val()) {
      var key = "searchValue" + (index + 1);
      data[key] = $(this).val();
    }
  });

  $.ajax({
    type: "GET",
    url: "searchMembers",
    data: data,
    success: function (data) {
      updateTable(data);
      updatePagination(data.pagination);
    },
    error: function (error) {
      console.error("Error fetching data", error);
    },
  });

}

// 검색기능 검색버튼 클릭 시 
function initializeSearchButton() {
  $(".searchBtn").on("click", function (e) {
    e.preventDefault();

    var data = {
      bcategory: $("#bigCategory").val(),
      subCategory: $("#subCategory").val(),
      startDate: $("#startDate").val(),
      endDate: $("#endDate").val(),
      memberName: $("#memberName").val(),
    };

    $(".ssearch-content").each(function (index) {
      if ($(this).val()) {
        var key = "searchValue" + (index + 1);
        data[key] = $(this).val();
      }
    });


    $.ajax({
      type: "GET",
      url: "searchMembers",
      data: data,
      success: function (data) {
        console.log(data);
        updateTable(data); // 테이블 업데이트
        updatePagination(data.pagination);
        addSelected();
      },
      error: function (error) {
        console.error("Error fetching data", error);
      },
    });

  });
}

function initializeSsearchContentClick() {
  $(".ssearch-content").on("click", fetchOptions);
}

// 프로젝트 멤버 추가관련
function addProjectList() {
  $("tbody").on("click", ".row", function () {

    var memberId = $(this).find("td:eq(0)").text();
    var memberName = $(this).find("td:eq(1)").text();
    var memberLevel = $(this).find("td:eq(2)").text();

    if ($(this).hasClass('selected')) {
      alert("멤버제거는 오른쪽에서 진행해주세요");
      return;
    }
    
    var newRow = "<tr class='newMem'" + 'data-id=' + memberId + ">";
    newRow += "<td>" + memberId + "<input type='hidden' name='memberId' value='" + memberId + "'></td>";
    newRow += "<td>" + memberName;
    newRow += "<td>" + memberLevel;
    newRow += "</tr>";

    $(".newTbl tbody").append(newRow);
    selectedRows.push(memberId);

    $('#memberListTbody .row').removeClass('selected');

    addSelected();
  });
}

function removeNewMember() {
  $(".newTbl tbody").on("click", ".newMem", function (e) {
    e.stopPropagation();
  
    $(this).remove();

    console.log("================ 리무브 적용?");
    addSelected();
  });

}

function addSelected() {
  $('#memberListTbody .row').each(function() {
      var firstTableDataId = $(this).data('id');

      $(this).removeClass('selected');

      $('.newTbl .newMem').each(function() {
          var secondTableDataId = $(this).data('id');

          if (firstTableDataId == secondTableDataId) {
              $('#memberListTbody .row[data-id="' + firstTableDataId + '"]').addClass('selected');
          }
      });
  });
}
