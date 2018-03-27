<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@include file="../views/checkLogin.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<title>Json</title>
	 <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/templatemo-style.css" rel="stylesheet">

<style>
.modal{
    z-index:3;
    left: 0;
    top: 0;
    width: 100%; 
    height: 100%; 
    overflow: auto; 
    padding-top: 60px;
 }
 .modal-content {
    background-color: #fefefe;
    margin: auto auto  auto 30%;
    width: 50%;
    padding-right: 1%;
}
</style>
<script type="text/javascript">
 function addBookJson(){
  // alert("Them sach!");
   var newBook = {}
   newBook["title"] = $('#title').val();
   newBook["author"]= $('#author').val(); 
   newBook["description"]= $('#description').val(); 
  // alert(newBook);
   if ($('#title').val() == ''){
       alert('Bạn chưa nhập tiêu đề!');
       return false;
   }
   if ($('#title').val().length > 40 ){
       alert('Tiêu đề không quá 40 ký tự!!');
       return false;
   }
   if ($('#author').val() == ''){
       alert('Bạn chưa nhập tên tác giả!');
       return false;
   }
   
   if ($('#author').val().length > 40 ){
       alert('Tiêu đề không quá 40 ký tự!!');
       return false;
   }
   $.ajax({
    type: "post",
    contentType : "application/json",
    dataType : 'json',
    url: '<%=request.getContextPath()%>/json/add',
    cache: false,    
    data:JSON.stringify(newBook),
    success: function(data){
    document.getElementById('formAddBook').style.display='none';

      var title = '<table class="table table-striped table-bordered templatemo-user-table">'
               +' <thead>'
               +' <tr>'
               +' <td align="center" valign="middle" ><a href="#" class="white-text templatemo-sort-by">ID<span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Tên sách <span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Tác giả <span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Ngày tạo<span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Cập nhật<span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Chức năng<span class=""></span></a></td>'
               +' </tr>'
               +' </thead>'
               +' <tbody>';
               for (var i = 0; i < data.length; i++) {
                   var row = '<tr><td>' + data[i].idBook+ '</td>'+
                       '<td>' + data[i].title + '</td>'+
                       '<td>' + data[i].author + '</td>'+
                       '<td>' + data[i].created_at + '</td>'+
                       '<td>' + data[i].updated_at + '</td>'+
                       '<td align="center" valign="middle">' +
                       '<a title="Chi tiết" href="${pageContext.request.contextPath}/show-detailBook/'+data[i].idBook+'">'+
                       '<img alt="Xem!" title="Xem" src="${pageContext.request.contextPath}/resources/images/view.png">'+
                       '</a> </td>'+
                       '</tr>';
                   title = title + row;
               }
        title = title +'</tbody>'
                +'</table>'  ;
        $('#result').html(title);
        $('#msgResult').html('');
    },
    error: function(){      
       alert('Lỗi xử lý!');
      }
   });
  }
 
 function display(data) {
    var json = "<h4>Ajax Response</h4><pre>"
        + JSON.stringify(data, null, 4) + "</pre>";
    $('#result').html(json);
  }
   
</script>

<script type="text/javascript">

function listBookJson(){
	// alert('Tất cả sách');
    $.ajax({
      url : '<%=request.getContextPath()%>/json/listBook',
      type : "GET",
      contentType : "application/json",
      dataType : 'json',        
      success : function(data) {
         var title = '<table class="table table-striped table-bordered templatemo-user-table">'
               +' <thead>'
               +' <tr>'
               +' <td align="center" valign="middle" ><a href="#" class="white-text templatemo-sort-by">ID<span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Tên sách <span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Tác giả <span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Ngày tạo<span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Cập nhật<span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Chức năng<span class=""></span></a></td>'
               +' </tr>'
               +' </thead>'
               +' <tbody>';
               for (var i = 0; i < data.length; i++) {
                   var row = '<tr><td>' + data[i].idBook+ '</td>'+
                       '<td>' + data[i].title + '</td>'+
                       '<td>' + data[i].author + '</td>'+
                       '<td>' + data[i].created_at + '</td>'+
                       '<td>' + data[i].updated_at + '</td>'+
                       '<td align="center" valign="middle">' +
                       '<a title="Chi tiết" href="${pageContext.request.contextPath}/show-detailBook/'+data[i].idBook+'">'+
                       '<img alt="Xem!" title="Xem" src="${pageContext.request.contextPath}/resources/images/view.png">'+
                       '</a> </td>'+
                       '</tr>';
                   title = title + row;
               }
        title = title +'</tbody>'
                +'</table>'  ;
                $('#msgResult').html('');
        $('#result').html(title);
        },
        error: function(){      
           alert('Lỗi xử lý!');
          }
    });
   }
</script>

<script type="text/javascript">
function searchJson(){
//  alert('Bạn đang tìm kiếm!');
  var key = $('#keywordJson').val();
    var searchBook = {}
    searchBook["title"] = $('#keywordJson').val(); 
    if ($('#keywordJson').val() == ''){
        alert('Bạn chưa nhập từ khóa');
        return false;
    }
$.ajax({
  url : "${pageContext.request.contextPath}/json/search",
  type : "GET",
  contentType : "application/json",
  dataType : 'json',    
  data: {title:$('#keywordJson').val()},
  success : function(data) {
    if(data.length > 0){
       var title = '<table class="table table-striped table-bordered templatemo-user-table">'
               +' <thead>'
               +' <tr>'
               +' <td align="center" valign="middle" ><a href="#" class="white-text templatemo-sort-by">ID<span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Tên sách <span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Tác giả <span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Ngày tạo<span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Cập nhật<span class=""></span></a></td>'
               +' <td align="center" valign="middle"><a href="#" class="white-text templatemo-sort-by">Chức năng<span class=""></span></a></td>'
               +' </tr>'
               +' </thead>'
               +' <tbody>';
               for (var i = 0; i < data.length; i++) {
                   var row = '<tr><td>' + data[i].idBook+ '</td>'+
                       '<td>' + data[i].title + '</td>'+
                       '<td>' + data[i].author + '</td>'+
                       '<td>' + data[i].created_at + '</td>'+
                       '<td>' + data[i].updated_at + '</td>'+
                       '<td align="center" valign="middle">' +
                       '<a title="Chi tiết" href="${pageContext.request.contextPath}/show-detailBook/'+data[i].idBook+'">'+
                       '<img alt="Xem!" title="Xem" src="${pageContext.request.contextPath}/resources/images/view.png">'+
                       '</a> </td>'+
                       '</tr>';
                   title = title + row;
               }
               title = title +'</tbody>'
                +'</table>'  ;
    $('#result').html(title); 
    $('#msgResult').html('<h3 style="color: blue"> Có '+data.length+' kết quả tìm kiếm với "<i>'+key+'"</i></p></h3>');
    }
    else{
    	 $('#msgResult').html('<h3 style="color: red"> Không có kết quả tìm kiếm với "<i>'+key+'"</i></p></h3>');
    }
    
    },
    error: function(){      
       alert('Lỗi xử lý!');
      }
});
}
</script>
</head>
<body>
<!-- header -->
<!-- //header -->
<!-- header-bot -->
<div class="templatemo-flex-row">
 <div class="templatemo-sidebar">
       <%@include file="/resources/inc/leftbar.jsp" %> 
      </div>
      
       <div class="templatemo-content col-1 light-gray-bg">
       <div class="templatemo-content-container">
       
       <div id="msgResult">  <br>
        </div>
          <div class="pagination-wrap">
            <ul class="pagination">
            <li><a href="#" onclick="document.getElementById('formAddBook').style.display='block'">&nbsp;&nbsp;Thêm sách</a></li>
            <li><a href="#" onclick="return listBookJson();" title="Tất cả sách!">Tất cả sách</a></li>
            </ul>
         </div>
 <div class="templatemo-content-widget no-padding">
           <!-- Search box -->
          <!--  <div class="row form-group">
            <div class="col-lg-6 col-md-6 form-group"> 
              <input class="form-control" value="" id="keywordJson" name="keywordJson" required="required" type="search" class="" placeholder="Nhập nội dung bạn muốn tìm kiếm"/>
              <input type="submit" onclick="return searchJson();" class="" value="Tìm kiếm"/>
       	  </div>
       	  </div> -->
       	  <div class="templatemo-search-form">
       	    <div class="mobile-menu-icon">
            <i class="fa fa-search"></i>
        	</div>
       	  <div class="input-group">
              <button  onclick="return searchJson();" type="submit" class="fa fa-search"></button>
              <input   id="keywordJson" name="keywordJson" required="required" type="search" class="form-control" placeholder="Nhập nội dung bạn muốn tìm kiếm"></input>
          </div>
          </div>
         
         <br>
            <div class="panel panel-default table-responsive" id="result">
              
            </div>                          
          </div>   
    
    <div id="formAddBook" class="modal text-center">
           <form class="modal-content animate" name="" method="post"> 
           <br><h3>Thêm sách mới</h3><br>
        <table style=" width:100%"  class="">
         <tr>
          <td><label ><b>Tiêu đề</b></label></td>
          <td><input type="text" name="title" id="title" class="form-control" required></td>
         </tr>
          <tr>
          <td><br></td>
          <td><br></td>
         </tr>
         <tr>
          <td><label ><b>Tác giả</b></label></td>
          <td> <input type="text" name="author" id="author" class="form-control" required></td>
         </tr>
          <tr>
          <td><br></td>
          <td><br></td>
         </tr>
         <tr>
          <td><label ><b>Mô tả</b></label></td>
          <td> <textarea style="margin: 0px; height: 200px;" name="description" cols="30" id="description" class="form-control"></textarea>
         </tr>
          <tr>
          <td><br></td>
          <td><br></td>
         </tr>
         <tr>
          <td></td>
          <td>
            <input type="button" value="Thêm" class="btn btn-primary "
                 onclick="addBookJson();">
            <input type="button" value="Hủy" class="btn btn-danger"
              onclick="document.getElementById('formAddBook').style.display='none'">
            
          </td>
         </tr>
          <tr>
          <td><br></td>
          <td><br></td>
         </tr>
        </table>
       </form>
    </div>
    
      <footer class="text-right">
            <%@include file="/resources/inc/footer.jsp" %> 
          </footer> 
    </div>
  </div>
  </div>
  <!-- JS -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.11.2.min.js"></script>        <!-- jQuery -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-filestyle.min.js"></script>  <!-- http://markusslima.github.io/bootstrap-filestyle/ -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/templatemo-script.js"></script>        <!-- Templatemo Script -->
  
  	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.1.1.min.js" type="text/javascript"></script> 
</body>
</html>
