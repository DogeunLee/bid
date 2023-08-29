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
});

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

function selectProjectsDetail() {
  $(".default-infoWrap").on("click", ".project_value a", function (e) {
    e.preventDefault();

    var projectNo = $(this).data("projectno");

    console.log(projectNo);

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
        $("#projectSt").val(projectData.projectSt)
      },
      error: function (error) {
        console.error("Request failed:", error);
      },
    });
  });
}

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

function changeOrange() {
  $(".default-infoWrap").on("click", ".project_value a", function () {
    $(".project_value a").css("background-color", "");
    $(this).css("background-color", "rgb(251,122,10)");
  });
}

function modalClose(){
    $('.closeModal').click(function() {
        $('.modals').addClass('displayHidden').removeClass('displayFlex');
    });

    $('.modals').click(function(e) {
        if (!$(e.target).hasClass('newMemberInput') && $(e.target).parents('.newMemberInput').length === 0) {
            $('.modals').addClass('displayHidden').removeClass('displayFlex');
        }
    });
}

function updateMemberSet() {
  $('.updateMemberSet').on("click", function () {
      $('.modals').addClass('displayFlex');
  });
}

function initializeBigCategoryChange() {

  $(document).on('change', '#bigCategory', function(){
      var selectedValue = $(this).val();
      console.log("hhhhhhhhhhhhhhhh");

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

          console.log(selectedValue);
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
}

function updateTable(data) {
  var tbody = $('.table-wrap tbody');
  tbody.empty();

  data.searchMemberList.forEach(function(member) {
      var contextPath = "${contextPath}";
      var row = '<tr class="row">' +
      '<td>' + member.memberId + '</td>' +
      '<td>' + member.memberName + '</td>' +
      '<td>' + member.memberLv + '</td>' +
      '<td>' + member.memberTel + '</td>' +
      '<td>' + member.memberGrad + '</td>' +
      '<td>' + member.memberSt + '</td>' +
      '</tr>';
      tbody.append(row);
      const tableCells = document.querySelectorAll("td"); 

      tableCells.forEach(cell => {
          if (cell.textContent.trim() === "undefined") {
              cell.textContent = "";
          }
      });
  });
}

function updatePagination(pagination) {
  var pageNationDiv = $('.page_Nation');
  pageNationDiv.empty();

  var url = "?cp=";
  var content = '';

  // Create anchors with data-page attribute to store the page number
  content += '<div><a href="#" data-page="1">&lt;&lt;</a></div>';
  content += '<div><a href="#" data-page="' + pagination.prevPage + '">&lt;</a></div>';

  for (var i = pagination.startPage; i <= pagination.endPage; i++) {
      if (i === pagination.currentPage) {
          content += '<div><a class="selected_Cp" data-page="' + i + '">' + i + '</a></div>';
      } else {
          content += '<div><a href="#" data-page="' + i + '">' + i + '</a></div>';
      }
  }

  content += '<div><a href="#" data-page="' + pagination.nextPage + '">&gt;</a></div>';
  content += '<div><a href="#" data-page="' + pagination.maxPage + '">&gt;&gt;</a></div>';

  pageNationDiv.append(content);

  // Attach click event to anchor tags
  pageNationDiv.find('a').on('click', function(e) {
      e.preventDefault();
      var page = $(this).data('page'); // Get the page number
      fetchDataForPage(page);
  });
}

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

function fetchDataForPage(page) {
  var data = {
      bcategory: $('#bigCategory').val(),
      subCategory: $('#subCategory').val(),
      startDate: $('#startDate').val(),
      endDate: $('#endDate').val(),
      memberName: $('#memberName').val(),
      cp: page // Include the current page in the request
  };

  $('.ssearch-content').each(function(index) {
      if ($(this).val()) {
          var key = 'searchValue' + (index + 1);
          data[key] = $(this).val();
      }
  });

  $.ajax({
      type: 'GET',
      url: 'searchMembers',
      data: data,
      success: function(data) {
          updateTable(data);
          updatePagination(data.pagination);
      },
      error: function(error) {
          console.error('Error fetching data', error);
      }
  });
}

function initializeSearchButton() {
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
              var key = 'searchValue' + (index + 1);
              data[key] = $(this).val();
          }
      });
  
      console.log(data);
  
      $.ajax({
          type: 'GET',
          url: 'searchMembers',
          data: data,
          success: function(data) {
              console.log(data);
              updateTable(data);
              updatePagination(data.pagination);
          },
          error: function(error) {
              console.error('Error fetching data', error);
          }
      });
  });
}

function initializeSsearchContentClick() {
  $(".ssearch-content").on('click', fetchOptions);
}

function addProjectList(){
  $('.row').on("click", function(){
    $(this).addClass("selected");
  })

}