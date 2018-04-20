function checkEmail() {
	var email = $("#email").val();
	if (!validateEmail(email)) {
		// Wrong email format
		$('#check')
				.html(
						'<td><label ><b>Email<span style=\"color: red\">(*)</span></b></label></td>'
								+ '<td><input onblur=\"return checkEmail()\" value=\"'
								+ email
								+ '\" autocomplete=\"email\" type=\"email\" name=\"email\"'
								+ 'id=\"email\" class=\"form-control\" required>'
								+ '<label id=\"email-error\" class=\"error\" for=\"email\">Enter the correct email format! Ex: abc@gmail.com</label></td>');
		return false;
	} else {
		$.ajax({
			url : '/admin/checkEmail',
			type : 'POST',
			cache : false,
			data : {
				aemail : email
			},
			success : function(data) {
				$("#check").html(data);
			},
			error : function() {
				// Xử lý nếu có lỗi
				// alert("");
			}
		});
	}
}

$("#formAddUser").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formAddUser").valid()) {
		addUserJson();
	}
});
function addUserJson() {
	var newUser = {}
	newUser["firstName"] = $('#firstName').val();
	newUser["lastName"] = $('#lastName').val();
	newUser["email"] = $('#email').val();
	newUser["idRole"] = $('#idRole').val();
	newUser["password"] = $('#password').val();
	$
			.ajax({
				type : "post",
				contentType : "application/json",
				dataType : 'json',
				url : '/admin/addUser',
				cache : false,
				data : JSON.stringify(newUser),
				success : function(data) {
					document.getElementById('viewFormAddUser').style.display = 'none';
					var title = '<table class="table table-striped table-advance table-hover">'
							+ '<tbody>'
							+ '<tr>'
							+ '<th><i class=""></i> Full Name</th>'
							+ '<th><i class=""></i> Email</th>'
							+ '<th><i class=""></i> Active</th>'
							+ '<th><i class=""></i> Role</th>'
							+ '<th><i class="icon_cogs"></i> Action</th>'
							+ ' </tr>';
					for (var i = 0; i < data.length; i++) {
						var check = null;
						var nameRole = 'admin';
						if (data[i].enabled == 1) {
							check = 'checked="checked"';
						}
						if (data[i].idRole != 1) {
							nameRole = 'user';
						}
						var row = '<tr>' + '<td>'
								+ data[i].firstName
								+ ' '
								+ data[i].lastName
								+ '</td>'
								+ '<td>'
								+ data[i].email
								+ '</td>'
								+ '<td>'
								+ '<input type="checkbox" '
								+ check
								+ ' onclick="changeStatus('
								+ data[i].idUser
								+ ','
								+ data[i].enabled
								+ ')">'
								+ '</td>'
								+ '<td>'
								+ nameRole
								+ '</td>'
								+ '<td>'
								+ '<div class="btn-group">'
								+ '<a class="btn btn-success" onclick="showEditUser('
								+ data[i].idUser
								+ ')" href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
								+ '<a class="btn btn-danger" onclick="return deleteUser('
								+ data[i].idUser
								+ ')" href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
								+ '</div>' + '</td>' + '</tr>';
						title = title + row;
					}
					title = title + '</tbody>' + '</table>';
					$('#result').html(title);
					$('#msgResult').html('Successfully added new user!');
				},
				error : function() {
					alert('Error adding new user!');
				}
			});
}

function display(data) {
	var json = "<h4>Ajax Response</h4><pre>" + JSON.stringify(data, null, 4)
			+ "</pre>";
	$('#result').html(json);
}

function deleteUser(idUser) {
	if (confirm('Are you sure your want to delete?')) {
		$
				.ajax({
					url : "/admin/deleteUser/" + idUser,
					type : "DELETE",
					contentType : "application/json",
					dataType : 'json',
					success : function(data) {
						if (data.length > 0) {
							var title = '<table class="table table-striped table-advance table-hover">'
									+ '<tbody>'
									+ '<tr>'
									+ '<th><i class=""></i> Full Name</th>'
									+ '<th><i class=""></i> Email</th>'
									+ '<th><i class=""></i> Active</th>'
									+ '<th><i class=""></i> Role</th>'
									+ '<th><i class="icon_cogs"></i> Action</th>'
									+ ' </tr>';
							for (var i = 0; i < data.length; i++) {
								var check = null;
								var nameRole = 'admin';
								if (data[i].enabled == 1) {
									check = 'checked="checked"';
								}
								if (data[i].idRole != 1) {
									nameRole = 'user';
								}
								var row = '<tr>' + '<td>'
										+ data[i].firstName
										+ ' '
										+ data[i].lastName
										+ '</td>'
										+ '<td>'
										+ data[i].email
										+ '</td>'
										+ '<td>'
										+ '<input type="checkbox" '
										+ check
										+ ' onclick="changeStatus('
										+ data[i].idUser
										+ ','
										+ data[i].enabled
										+ ')">'
										+ '</td>'
										+ '<td>'
										+ nameRole
										+ '</td>'
										+ '<td>'
										+ '<div class="btn-group">'
										+ '<a class="btn btn-success" onclick="showEditUser('
										+ data[i].idUser
										+ ')" href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
										+ '<a class="btn btn-danger" onclick="deleteUser('
										+ data[i].idUser
										+ ')" href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
										+ '</div>' + '</td>' + '</tr>';
								title = title + row;
							}
							title = title + '</tbody>' + '</table>';
							$('#result').html(title);
							$('#msgResult').html('Successfully deleted user!');
						} else {
							$('#result').html("No user");
							$('#msgResult').html('');
						}
					},
					error : function() {
						alert('error!');
					}
				});
	}
}
function showEditUser(idUser) {
	document.getElementById('viewFormEditUser').style.display = 'block';
	$.ajax({
		url : "/admin/showEditUser/" + idUser,
		cache : false,
		type : "PUT",
		contentType : "application/json",
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				$('#formEditUser').find('#emailEdit').val(data.email);
				$('#formEditUser').find('#firstNameEdit').val(data.firstName);
				$('#formEditUser').find('#lastNameEdit').val(data.lastName);
				$('#formEditUser').find('#idRoleEdit').val(data.idRole);
				$('#formEditUser').find('#idUserEdit').val(data.idUser);
			} else {
				document.getElementById('formEditUser').style.display = 'none';
				$('#msgResult').html('Error edited user!');
			}
		},
		error : function() {
			alert('error!');
		}
	});
}

$("#formEditUser").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formEditUser").valid()) {
		editUser();
	}
});

function editUser() {
	var newUser = {}
	newUser["firstName"] = $('#firstNameEdit').val();
	newUser["lastName"] = $('#lastNameEdit').val();
	newUser["email"] = $('#emailEdit').val();
	newUser["idRole"] = $('#idRoleEdit').val();
	newUser["idUser"] = $('#idUserEdit').val();
	newUser["password"] = $('#passwordEdit').val();
	$
			.ajax({
				type : "PUT",
				contentType : "application/json",
				dataType : 'json',
				url : '/admin/editUser',
				cache : false,
				data : JSON.stringify(newUser),
				success : function(data) {
					document.getElementById('viewFormEditUser').style.display = 'none';
					var title = '<table class="table table-striped table-advance table-hover">'
							+ '<tbody>'
							+ '<tr>'
							+ '<th><i class=""></i> Full Name</th>'
							+ '<th><i class=""></i> Email</th>'
							+ '<th><i class=""></i> Active</th>'
							+ '<th><i class=""></i> Role</th>'
							+ '<th><i class=""></i> Action</th>' + ' </tr>';
					for (var i = 0; i < data.length; i++) {
						var check = null;
						var nameRole = 'admin';
						if (data[i].enabled == 1) {
							check = 'checked="checked"';
						}
						if (data[i].idRole != 1) {
							nameRole = 'user';
						}
						var row = '<tr>' + '<td>'
								+ data[i].firstName
								+ ' '
								+ data[i].lastName
								+ '</td>'
								+ '<td>'
								+ data[i].email
								+ '</td>'
								+ '<td>'
								+ '<input type="checkbox" '
								+ check
								+ ' onclick="changeStatus('
								+ data[i].idUser
								+ ','
								+ data[i].enabled
								+ ')">'
								+ '</td>'
								+ '<td>'
								+ nameRole
								+ '</td>'
								+ '<td>'
								+ '<div class="btn-group">'
								+ '<a class="btn btn-success" onclick="showEditUser('
								+ data[i].idUser
								+ ')" href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
								+ '<a class="btn btn-danger" onclick="deleteUser('
								+ data[i].idUser
								+ ')" href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
								+ '</div>' + '</td>' + '</tr>';
						title = title + row;
					}
					title = title + '</tbody>' + '</table>';
					$('#result').html(title);
					$('#msgResult').html('Successfully edited user!');
				},
				error : function() {
					alert('Error edit new user!');
				}
			});
}

function changeStatus(idUser, st) {
	$.ajax({
		url : "/admin/changeStatus/" + idUser,
		type : "GET",
		cache : false,
		contentType : "application/json",
		dataType : 'json',
		data : {
			enabled : st
		},
		success : function(data) {
			$('#msgResult').html('Successfully change status user!');
		},
		error : function() {
			alert('Error change status user!');
		}
	});
}

$("#formAddBook").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formAddBook").valid()) {
		addBook();
	}
});

function addBook() {
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
					document.getElementById('viewFormAddBook').style.display = 'none';
					var title = '<table class="table table-striped table-advance table-hover">'
							+ '<tbody>'
							+ '<tr>'
							+ '<th><i class=""></i>Title</th>'
							+ '<th><i class=""></i>Author</th>'
							+ '<th><i class=""></i>Date created</th>'
							+ '<th><i class=""></i>Date updated</th>'
							+ '<th><i class=""></i>Action</th>' + ' </tr>';

					for (var i = 0; i < data.length; i++) {
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
								+ '<div class="btn-group">'
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
					$('#msgResult').html('Successfully added new book!');
				},
				error : function() {
					alert('Error adding new book!');
				}
			});
}

function showEditBook(idBook) {
	document.getElementById('viewFormEditBook').style.display = 'block';
	$.ajax({
		url : "/showEditBook/" + idBook,
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
				document.getElementById('formEditBook').style.display = 'none';
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
				url : '/editBook',
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
							+ '<th><i class=""></i>Action</th>' + ' </tr>';
					for (var i = 0; i < data.length; i++) {
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
								+ '<div class="btn-group">'
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
					url : "/deleteBook/" + idBook,
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
								+ '<th><i class=""></i>Action</th>' + ' </tr>';
						for (var i = 0; i < data.length; i++) {
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
									+ '<div class="btn-group">'
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
						alert('Error delete new book!');
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
			$('#msgResult').html('Successfully change status book!');
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
