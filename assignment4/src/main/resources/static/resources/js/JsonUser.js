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
			url : '/checkEmail',
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
		addUser();
	}
});
function addUser() {
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
			alert('Error change status user!');
		}
	});
}

$("#formSignUp").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formSignUp").valid()) {
		signUp();
	}
});

function signUp() {
	var newUser = {}
	newUser["email"] = $('#email').val();
	newUser["password"] = $('#password').val();
	$
			.ajax({
				type : "post",
				contentType : "application/json",
				dataType : 'json',
				url : '/signUp',
				cache : false,
				data : JSON.stringify(newUser),
				success : function(data) {
					alert('Account created successfully!');
					document.getElementById('viewFormSignUp').style.display = 'none';
				},
				error : function() {
					alert('Error creating!');
				}
			});
}

function showEditUserLogin() {
	document.getElementById('viewFormEditUserLogin').style.display = 'block';
	$.ajax({
		url : "/showEditUserLogin/",
		cache : false,
		type : "PUT",
		contentType : "application/json",
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				$('#formEditUserLogin').find('#emailEditLogin').val(data.email);
				$('#formEditUserLogin').find('#firstNameEditLogin').val(data.firstName);
				$('#formEditUserLogin').find('#lastNameEditLogin').val(data.lastName);
				$('#formEditUserLogin').find('#idRoleEditLogin').val(data.idRole);
			} else {
				document.getElementById('formEditUserLogin').style.display = 'none';
				$('#msgResult').html('Error edited user!');
			}
		},
		error : function() {
			alert('error!');
		}
	});
}

$("#formEditUserLogin").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formEditUserLogin").valid()) {
		editUserLogin();
	}
});
function editUserLogin() {
	var newUser = {}
	newUser["firstName"] = $('#firstNameEditLogin').val();
	newUser["lastName"] = $('#lastNameEditLogin').val();
	$
			.ajax({
				type : "PUT",
				contentType : "application/json",
				dataType : 'json',
				url : '/editUserLogin',
				cache : false,
				data : JSON.stringify(newUser),
				success : function(data) {
					alert('successfully!');
					document.getElementById('viewFormEditUserLogin').style.display = 'none';
				},
				error : function() {
					alert('Error!');
				}
			});
}
