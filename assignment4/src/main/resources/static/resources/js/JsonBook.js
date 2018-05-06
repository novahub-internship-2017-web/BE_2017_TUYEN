//listAllBook
$(document).ready(function () {
	//$("#tbody").empty();
    getAllBooks(5,1);
});


function getAllBooks(pageSize_,page_) {
	alert(pageSize_+' '+page_);
    $.ajax({
        contentType: "application/json",
        url: "/listBook",
        dataType: 'json',
        type : "GET",
        timeout: 100000,
        data : {
			pageSize : pageSize_,
			page: page_
		},
        success: function (data) {
        	var idRole = $("#idRole").val();
        	var tmp = 'class="hidden"';
        	if(idRole == 1){
        		tmp = "";
        	}
           if (data.content.length > 0) {
   			var title = '<table class="table table-striped table-advance table-hover">'
				+ '<tbody>'
				+ '<tr>'
				+ '<th><i class=""></i>Title</th>'
				+ '<th><i class=""></i>Author</th>'
				+ '<th><i class=""></i>Date created</th>'
				+ '<th><i class=""></i>Date updated</th>'
				+ '<th '+tmp+'><i class=""></i>Status</th>'
				+ '<th><i class=""></i>Action</th>'
				+ ' </tr>';
			$.each(data.content, function(i,book){
				var check = null;
				if(book.enabled == 1){
					check = 'checked="checked"';
				}
				var row = '<tr>' 
						+ '<td>'+ book.title + '</td>'
						+ '<td>'+ book.author + '</td>'
						+ '<td>'+ book.createdAt + '</td>'
						+ '<td>'+ book.updatedAt + '</td>'
						+ '<td '+tmp+'>'
							+ '<input type="checkbox" '	+ check
							+ ' onclick="changeStatusBook('+ book.idBook+ ','+ book.enabled	+ ')">'
						+ '</td>'
						+ '<td>'
							+ '<div class="btn-group">'
								+ '<a class="btn btn-warning"'
								+ ' href="/detailBook/'+book.idBook+'" title="Detail!"><i class="icon_camera_alt"></i></a>'
								+ '<div '+tmp+' style="float:left">'
									+ '<a class="btn btn-success" '
										+'onclick="showEditBook('+ book.idBook + ')" href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
									+ '<a class="btn btn-danger" '
										+'onclick="deleteBook('+ book.idBook+ ')" href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
								+ '</div>'		
							+ '</div>' 
						+ '</td>' 
					+ '</tr>';
				title = title + row;
			});
			title = title + '</tbody>' + '</table>';
			$('#result').html(title);
        }
      },
    });
}


function addLineOfTable(book) {
	//alert('create');
    var tr = $("<tr>");
    //Create td column
    var title = $("<td>").append(book.title);
    var author = $("<td>").append(book.author);
    var createdAt = $("<td>").append(book.createdAt);
    var updatedAt = $("<td>").append(book.updatedAt);
  
     //Create td for detail button
    var detailBook = $("<a/>", {
        href : "/detailBook/" + book.idBook
    });
    detailBook.addClass("btn btn-warning");
    var iconDetail = $("<i>");
    iconDetail.addClass("icon_camera_alt");
    detailBook.append(iconDetail);

  //Create td for delete button
    var editBook = $("<a>");
    editBook.addClass("btn btn-success");
    var iconEdit = $("<i>");
    iconEdit.addClass("icon_pencil-edit");
    editBook.append(iconEdit);
    
    //Create td for delete button
    var deleteBook = $('<a onClick = "deleteBook('+book.idBook+')">');
    deleteBook.addClass("btn btn-danger");
    var iconDelete = $("<i>");
    deleteBook.addClass("icon_close_alt2");
    deleteBook.append(iconDelete);
    

    var action = $("<td>").append(detailBook);
    if ($("#idRole").val() == 1) {
    	action.append(editBook);
        action.append(deleteBook);
    }

    tr.append(title);
    tr.append(author);
    tr.append(createdAt);
    tr.append(updatedAt);
    //<td> change status of book
    if ($("#idRole").val() == 1) {
        var enabled = $("<td>");
        var checkBox = $("<input>");
        checkBox.prop("type", "checkbox");
        checkBox.prop("id", book.idBook);
        enabled.append(checkBox);
        if (book.enabled) {
            checkBox.prop("checked", true);
        }
        tr.append(enabled);
    }
    tr.append(action);
    //return tr;
   $(".trResult").first().before(tr);
}

//admin login with index
function showEditBook(idBook) {
	var url = "/admin/showEditBook/"+idBook ;
	window.history.pushState(null, null, url);
	document.getElementById('viewFormEditBook').style.display = 'block';
	$.ajax({
		url : "/admin/showEditBook/" + idBook,
		cache : false,
		type : "PUT",
		contentType : "application/json",
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				$('#formEditBook').find('#titleEdit').val(data.title);
				$('#formEditBook').find('#authorEdit').val(data.author);
				$('#formEditBook').find('#descriptionEdit').val(
						data.description);
				$('#formEditBook').find('#idBookEdit').val(data.idBook);
				$('#formEditBook').find('#idUserBookEdit').val(data.idUser);
			} else {
				document.getElementById('viewFormEditBook').style.display = 'none';
				$('#msgResult').html('Error edited book!');
			}
		},
		error : function() {
			alert('error!');
		}
	});
}

$("#formEditBook").submit(function(event) {
	// alert('validate book!');
	event.preventDefault(); // no submit
	if ($("#formEditBook").valid()) {
		editBook();
	}
});

function editBook() {
	var url = "/admin/editBook/" ;
	window.history.pushState(null, null, url);
	var newBook = {}
	newBook["title"] = $('#titleEdit').val();
	newBook["author"] = $('#authorEdit').val();
	newBook["description"] = $('#descriptionEdit').val();
	newBook["idBook"] = $('#idBookEdit').val();
	newBook["idUser"] = $('#idUserBookEdit').val();
	$
			.ajax({
				type : "PUT",
				contentType : "application/json",
				dataType : 'json',
				url : '/admin/editBook',
				cache : false,
				data : JSON.stringify(newBook),
				success : function(data) {
					var url = "/allBook/";
					window.history.pushState(null, null, url);
					document.getElementById('viewFormEditBook').style.display = 'none';
					var title = '<table class="table table-striped table-advance table-hover">'
							+ '<tbody>'
							+ '<tr>'
							+ '<th><i class=""></i>Title</th>'
							+ '<th><i class=""></i>Author</th>'
							+ '<th><i class=""></i>Date created</th>'
							+ '<th><i class=""></i>Date updated</th>'
							+ '<th><i class=""></i>Status</th>'
							+ '<th><i class=""></i>Action</th>'
							+ ' </tr>';
					for (var i = 0; i < data.length; i++) {
						var check = null;
						if(data[i].enabled == 1){
							check = 'checked="checked"';
						}
						var row = '<tr>' + '<td>'
								+ data[i].title
								+ '</td>'
								+ '<td>'
								+ data[i].author
								+ '</td>'
								+ '<td>'
								+ data[i].createdAt
								+ '</td>'
								+ '<td>'
								+ data[i].updatedAt
								+ '</td>'
								
								+ '<td>'
								+ '<input type="checkbox" '
								+ check
								+ ' onclick="changeStatusBook('
								+ data[i].idBook
								+ ','
								+ data[i].enabled
								+ ')">'
								+ '</td>'
								
								+ '<td>'
								+ '<div class="btn-group">'
								+ '<a class="btn btn-warning"'
								+ ' href="/detailBook/'+data[i].idBook+'" title="Detail!"><i class="icon_camera_alt"></i></a>'
								+ '<a class="btn btn-success" onclick="showEditBook('
								+ data[i].idBook
								+ ')" href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
								+ '<a class="btn btn-danger" onclick="deleteBook('
								+ data[i].idBook
								+ ')" href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
								+ '</div>' + '</td>' + '</tr>';
						title = title + row;
					}

					title = title + '</tbody>' + '</table>';
					$('#result').html(title);
					$('#msgResult').html('Successfully edited book!');
					},
				error : function() {
					alert('Error editing new book!');
				}
			});
}

function deleteBook(idBook) {
	var url = "/admin/deleteBook/"+idBook ;
	window.history.pushState(null, null, url);
	if (confirm('Are you sure your want to delete?')) {
		$
				.ajax({
					url : "/admin/deleteBook/" + idBook,
					type : "GET",
					contentType : "application/json",
					dataType : 'json',
					success : function(data) {
						$('#trResult').html("");
						getAllBooks();
						$('#msgResult').html('Successfully deleted book!');
						},
					error : function() {
						alert('Error deleting new book!');
					}
				});
	}
}

function changeStatusBook(idBook, st) {
	var url = "/admin/changeStatusBook/"+idBook ;
	window.history.pushState(null, null, url);
	$.ajax({
		url : "/admin/changeStatusBook/" + idBook,
		type : "GET",
		cache : false,
		contentType : "application/json",
		dataType : 'json',
		data : {
			enabled : st
		},
		success : function(data) {
			var url = "/allBook/";
			window.history.pushState(null, null, url);
			var title = '<table class="table table-striped table-advance table-hover">'
					+ '<tbody>'
					+ '<tr>'
					+ '<th><i class=""></i>Title</th>'
					+ '<th><i class=""></i>Author</th>'
					+ '<th><i class=""></i>Date created</th>'
					+ '<th><i class=""></i>Date updated</th>'
					+ '<th><i class=""></i>Status</th>'
					+ '<th><i class=""></i>Action</th>'
					+ ' </tr>';
			for (var i = 0; i < data.length; i++) {
				var check = null;
				if(data[i].enabled == 1){
					check = 'checked="checked"';
				}
				var row = '<tr>' + '<td>'
						+ data[i].title
						+ '</td>'
						+ '<td>'
						+ data[i].author
						+ '</td>'
						+ '<td>'
						+ data[i].createdAt
						+ '</td>'
						+ '<td>'
						+ data[i].updatedAt
						+ '</td>'
						
						+ '<td>'
						+ '<input type="checkbox" '
						+ check
						+ ' onclick="changeStatusBook('
						+ data[i].idBook
						+ ','
						+ data[i].enabled
						+ ')">'
						+ '</td>'
						
						+ '<td>'
						+ '<div class="btn-group">'
						+ '<a class="btn btn-warning"'
						+ ' href="/detailBook/'+data[i].idBook+'" title="Detail!"><i class="icon_camera_alt"></i></a>'
						+ '<a class="btn btn-success" onclick="showEditBook('
						+ data[i].idBook
						+ ')" href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
						+ '<a class="btn btn-danger" onclick="deleteBook('
						+ data[i].idBook
						+ ')" href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
						+ '</div>' + '</td>' + '</tr>';
				title = title + row;
			}

			title = title + '</tbody>' + '</table>';
			$('#result').html(title);
			$('#msgResult').html('Successfully changed status book!');
			},
		error : function() {
			alert('Error change status book!');
		}
	});
}


//user/ admin login with myBook
$("#formAddMyBook").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formAddMyBook").valid()) {
		addMyBook();
	}
});

function addMyBook() {
	var url = "/addMyBook/" ;
	window.history.pushState(null, null, url);
	var newBook = {}
	newBook["title"] = $('#title').val();
	newBook["author"] = $('#author').val();
	newBook["description"] = $('#description').val();
	$
			.ajax({
				type : "post",
				contentType : "application/json",
				dataType : 'json',
				url : '/addBook',
				cache : false,
				data : JSON.stringify(newBook),
				success : function(data) {
					var url = "/myBook/";
					window.history.pushState(null, null, url);
					document.getElementById('viewFormAddMyBook').style.display = 'none';
					var colUser = "";
					var colAdmin = "";
					var rs = $('#checkUser').val();
					if(rs != "isUser"){
						colUser = 'class="hidden"';
					}else{
						colAdmin = 'class="hidden"';
					}
					var title = '<table class="table table-striped table-advance table-hover">'
							+ '<tbody>'
							+ '<tr>'
							+ '<th><i class=""></i>Title</th>'
							+ '<th><i class=""></i>Author</th>'
							+ '<th><i class=""></i>Date created</th>'
							+ '<th><i class=""></i>Date updated</th>'
							+ '<th><i class=""></i>Status</th>'
							+ '<th><i class=""></i>Action</th>'
							+ ' </tr>';
					for (var i = 0; i < data.length; i++) {
						var check = 'checked="checked"';
						var status = "Approved";
						if(data[i].enabled == 0){
							status = "Waiting";
							check = null;
						}
						var row = '<tr>' + '<td>'
								+ data[i].title
								+ '</td>'
								+ '<td>'
								+ data[i].author
								+ '</td>'
								+ '<td>'
								+ data[i].createdAt
								+ '</td>'
								+ '<td>'
								+ data[i].updatedAt
								+ '</td>'
								
								+ '<td '+colUser+'>'
								+ status
								+ '</td>'
								
								+ '<td '+colAdmin+'>'
								+ '<input type="checkbox" '
								+ check
								+ ' onclick="changeStatusMyBook('
								+ data[i].idBook
								+ ','
								+ data[i].enabled
								+ ')">'
								+ '</td>'
								+ '<td>'
								+ '<div class="btn-group">'
								+ '<a class="btn btn-warning"'
								+ ' href="/detailBook/'+data[i].idBook+'" title="Detail!"><i class="icon_camera_alt"></i></a>'
								+ '<a class="btn btn-success" onclick="showEditMyBook('
								+ data[i].idBook
								+ ')" href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
								+ '<a class="btn btn-danger" onclick="deleteMyBook('
								+ data[i].idBook
								+ ')" href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
								+ '</div>' + '</td>' + '</tr>';
						title = title + row;
					}

					title = title + '</tbody>' + '</table>';
					$('#result').html(title);
					$('#msgResult').html('Successfully added book!');
				},
				error : function() {
					alert('Error adding new book!');
				}
			});
}

function showEditMyBook(idBook) {
	var url = "/showEditMyBook/"+idBook ;
	window.history.pushState(null, null, url);
	document.getElementById('viewFormEditMyBook').style.display = 'block';
	$.ajax({
		url : "/showEditMyBook/" + idBook,
		cache : false,
		type : "PUT",
		contentType : "application/json",
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				$('#formEditMyBook').find('#titleEdit').val(data.title);
				$('#formEditMyBook').find('#authorEdit').val(data.author);
				$('#formEditMyBook').find('#descriptionEdit').val(
						data.description);
				$('#formEditMyBook').find('#idBookEdit').val(data.idBook);
				$('#formEditMyBook').find('#idUserBookEdit').val(data.idUser);
			} else {
				document.getElementById('viewFormEditMyBook').style.display = 'none';
				$('#msgResult').html('You do not have permission to edit!');
			}
		},
		error : function() {
			document.getElementById('viewFormEditMyBook').style.display = 'none';
			alert('You do not have permission to edit!');
		}
	});
}

$("#formEditMyBook").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formEditMyBook").valid()) {
		editMyBook();
	}
});

function editMyBook() {
	var url = "/editMyBook/";
	window.history.pushState(null, null, url);
	var newBook = {}
	newBook["title"] = $('#titleEdit').val();
	newBook["author"] = $('#authorEdit').val();
	newBook["description"] = $('#descriptionEdit').val();
	newBook["idBook"] = $('#idBookEdit').val();
	newBook["idUser"] = $('#idUserBookEdit').val();
	$
			.ajax({
				type : "PUT",
				contentType : "application/json",
				dataType : 'json',
				url : '/editMyBook',
				cache : false,
				data : JSON.stringify(newBook),
				success : function(data) {
					var url = "/myBook/";
					window.history.pushState(null, null, url);
					document.getElementById('viewFormEditMyBook').style.display = 'none';
					var colUser = "";
					var colAdmin = "";
					var rs = $('#checkUser').val();
					alert('rs: '+rs);
					if(rs != "isUser"){
						colUser = 'class="hidden"';
					}else{
						colAdmin = 'class="hidden"';
					}
					alert('col: '+colUser);
					var title = '<table class="table table-striped table-advance table-hover">'
							+ '<tbody>'
							+ '<tr>'
							+ '<th><i class=""></i>Title</th>'
							+ '<th><i class=""></i>Author</th>'
							+ '<th><i class=""></i>Date created</th>'
							+ '<th><i class=""></i>Date updated</th>'
							+ '<th><i class=""></i>Status</th>'
							+ '<th><i class=""></i>Action</th>'
							+ ' </tr>';
					for (var i = 0; i < data.length; i++) {
						var check = 'checked="checked"';
						var status = "Approved";
						if(data[i].enabled == 0){
							status = "Waiting";
							check = null;
						}
						var row = '<tr>' + '<td>'
								+ data[i].title
								+ '</td>'
								+ '<td>'
								+ data[i].author
								+ '</td>'
								+ '<td>'
								+ data[i].createdAt
								+ '</td>'
								+ '<td>'
								+ data[i].updatedAt
								+ '</td>'
								
								+ '<td '+colUser+'>'
								+ status
								+ '</td>'
								
								+ '<td '+colAdmin+'>'
								+ '<input type="checkbox" '
								+ check
								+ ' onclick="changeStatusMyBook('
								+ data[i].idBook
								+ ','
								+ data[i].enabled
								+ ')">'
								+ '</td>'
								+ '<td>'
								+ '<div class="btn-group">'
								+ '<a class="btn btn-warning"'
								+ ' href="/detailBook/'+data[i].idBook+'" title="Detail!"><i class="icon_camera_alt"></i></a>'
								+ '<a class="btn btn-success" onclick="showEditMyBook('
								+ data[i].idBook
								+ ')" href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
								+ '<a class="btn btn-danger" onclick="deleteMyBook('
								+ data[i].idBook
								+ ')" href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
								+ '</div>' + '</td>' + '</tr>';
						title = title + row;
					}

					title = title + '</tbody>' + '</table>';
					$('#result').html(title);
					$('#msgResult').html('Successfully edited book!');
				},
				error : function() {
					alert('You do not have permission to edit!');
				}
			});
}

function deleteMyBook(idBook) {
	var url = "/deleteMybook/"+idBook ;
	window.history.pushState(null, null, url);
	if (confirm('Are you sure your want to delete?')) {
		$
				.ajax({
					url : "/deleteMyBook/" + idBook,
					type : "GET",
					contentType : "application/json",
					dataType : 'json',
					success : function(data) {
						var url = "/myBook/";
						window.history.pushState(null, null, url);
						var colUser = "";
						var colAdmin = "";
						var rs = $('#checkUser').val();
						alert('rs: '+rs);
						if(rs != "isUser"){
							colUser = 'class="hidden"';
						}else{
							colAdmin = 'class="hidden"';
						}
						alert('col: '+colUser);
						var title = '<table class="table table-striped table-advance table-hover">'
								+ '<tbody>'
								+ '<tr>'
								+ '<th><i class=""></i>Title</th>'
								+ '<th><i class=""></i>Author</th>'
								+ '<th><i class=""></i>Date created</th>'
								+ '<th><i class=""></i>Date updated</th>'
								+ '<th><i class=""></i>Status</th>'
								+ '<th><i class=""></i>Action</th>'
								+ ' </tr>';
						for (var i = 0; i < data.length; i++) {
							var check = 'checked="checked"';
							var status = "Approved";
							if(data[i].enabled == 0){
								status = "Waiting";
								check = null;
							}
							var row = '<tr>' + '<td>'
									+ data[i].title
									+ '</td>'
									+ '<td>'
									+ data[i].author
									+ '</td>'
									+ '<td>'
									+ data[i].createdAt
									+ '</td>'
									+ '<td>'
									+ data[i].updatedAt
									+ '</td>'
									
									+ '<td '+colUser+'>'
									+ status
									+ '</td>'
									
									+ '<td '+colAdmin+'>'
									+ '<input type="checkbox" '
									+ check
									+ ' onclick="changeStatusMyBook('
									+ data[i].idBook
									+ ','
									+ data[i].enabled
									+ ')">'
									+ '</td>'
									+ '<td>'
									+ '<div class="btn-group">'
									+ '<a class="btn btn-warning"'
									+ ' href="/detailBook/'+data[i].idBook+'" title="Detail!"><i class="icon_camera_alt"></i></a>'
									+ '<a class="btn btn-success" onclick="showEditMyBook('
									+ data[i].idBook
									+ ')" href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
									+ '<a class="btn btn-danger" onclick="deleteMyBook('
									+ data[i].idBook
									+ ')" href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
									+ '</div>' + '</td>' + '</tr>';
							title = title + row;
						}

						title = title + '</tbody>' + '</table>';
						$('#result').html(title);
						$('#msgResult').html('Successfully deleted book!');
					},
					error : function() {
						alert('Error deleting new book!');
					}
				});
	}
}

function changeStatusMyBook(idBook, st) {
	var url = "/admin/changeStatusMyBook/"+idBook ;
	window.history.pushState(null, null, url);
	$.ajax({
		url : "/admin/changeStatusMyBook/" + idBook,
		type : "GET",
		cache : false,
		contentType : "application/json",
		dataType : 'json',
		data : {
			enabled : st
		},
		success : function(data) {
			var url = "/myBook/" ;
			window.history.pushState(null, null, url);
			var title = '<table class="table table-striped table-advance table-hover">'
					+ '<tbody>'
					+ '<tr>'
					+ '<th><i class=""></i>Title</th>'
					+ '<th><i class=""></i>Author</th>'
					+ '<th><i class=""></i>Date created</th>'
					+ '<th><i class=""></i>Date updated</th>'
					+ '<th><i class=""></i>Status</th>'
					+ '<th><i class=""></i>Action</th>'
					+ ' </tr>';
			for (var i = 0; i < data.length; i++) {
				var check = null;
				if(data[i].enabled == 1){
					check = 'checked="checked"';
				}
				var row = '<tr>' + '<td>'
						+ data[i].title
						+ '</td>'
						+ '<td>'
						+ data[i].author
						+ '</td>'
						+ '<td>'
						+ data[i].createdAt
						+ '</td>'
						+ '<td>'
						+ data[i].updatedAt
						+ '</td>'
						
						+ '<td>'
						+ '<input type="checkbox" '
						+ check
						+ ' onclick="changeStatusMyBook('
						+ data[i].idBook
						+ ','
						+ data[i].enabled
						+ ')">'
						+ '</td>'
						
						+ '<td>'
						+ '<div class="btn-group">'
						+ '<a class="btn btn-warning"'
						+ ' href="/detailBook/'+data[i].idBook+'" title="Detail!"><i class="icon_camera_alt"></i></a>'
						+ '<a class="btn btn-success" onclick="showEditBook('
						+ data[i].idBook
						+ ')" href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
						+ '<a class="btn btn-danger" onclick="deleteBook('
						+ data[i].idBook
						+ ')" href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
						+ '</div>' + '</td>' + '</tr>';
				title = title + row;
			}

			title = title + '</tbody>' + '</table>';
			$('#result').html(title);
			$('#msgResult').html('Successfully changed status book!');
			},
		error : function() {
			alert('Error change status book!');
		}
	});
}