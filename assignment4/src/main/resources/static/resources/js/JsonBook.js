//admin login with index
function showEditBook(idBook) {
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
	if (confirm('Are you sure your want to delete?')) {
		$
				.ajax({
					url : "/admin/deleteBook/" + idBook,
					type : "GET",
					contentType : "application/json",
					dataType : 'json',
					success : function(data) {
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
						$('#msgResult').html('Successfully deleted book!');
						},
					error : function() {
						alert('Error deleting new book!');
					}
				});
	}
}

function changeStatusBook(idBook, st) {
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


function deleteComment(idComment,idB) {
	if (confirm('Are you sure your want to delete?')) {
		$
				.ajax({
					url : "/deleteComment/" + idComment,
					type : "GET",
					contentType : "application/json",
					dataType : 'json',
					data : {
						idBook : idB
					},
					success : function(data) {
						
					},
					error : function() {
						alert('error!');
					}
				});
	}
}

//user/ admin login with myBook
$("#formAddMyBook").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formAddMyBook").valid()) {
		addMyBook();
	}
});

function addMyBook() {
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
	if (confirm('Are you sure your want to delete?')) {
		$
				.ajax({
					url : "/deleteMyBook/" + idBook,
					type : "GET",
					contentType : "application/json",
					dataType : 'json',
					success : function(data) {
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