function checkEmail() {
	var email = $("#email").val();
	if (!validateEmail(email)) {
		// Wrong email format
		$('#check').html('<td><label ><b>Email<span style=\"color: red\">(*)</span></b></label></td>'
						+ '<td><input onblur=\"return checkEmail()\" value=\"'
						+ email
						+ '\" autocomplete=\"off\" type=\"email\" name=\"email\"'
						+ 'id=\"email\" class=\"form-control\" required>'
						+ '<label id=\"email-error\" class=\"error\" for=\"email\">Enter the correct email format!</label></td>'); 
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
				if(data == false){
					$('#check').html('<td><label ><b>Email<span style=\"color: red\">(*)</span></b></label></td>'
									+ '<td><input onblur=\"return checkEmail()\" value=\"'
									+ '\" autocomplete=\"off\" type=\"email\" name=\"email\"'
									+ 'id=\"email\" class=\"form-control\" required>'
									+ '<label id=\"email-error\" class=\"error\" for=\"email\">Email already exists!</label></td>'); 
					return false;
				}
			},
			error : function() {
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
	var url = "/admin/addUser/";
	window.history.pushState(null, null, url);
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
					var url = "/admin/allUser/";
					window.history.pushState(null, null, url);
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
	var url = "/admin/deleteUser/"+idUser ;
	window.history.pushState(null, null, url);
	if (confirm('Are you sure your want to delete?')) {
		$
				.ajax({
					url : "/admin/deleteUser/" + idUser,
					type : "DELETE",
					contentType : "application/json",
					dataType : 'json',
					success : function(data) {
						var url = "/admin/allUser/";
						window.history.pushState(null, null, url);
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
	var url = "/admin/showEditUser/"+idUser ;
	window.history.pushState(null, null, url);
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
					var url = "/admin/allUser/";
					window.history.pushState(null, null, url);
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
	var url = "/admin/changeStatusUser/"+idUser ;
	window.history.pushState(null, null, url);
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
			var url = "/admin/allUser/";
			window.history.pushState(null, null, url);
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
	var url = "/signUp/";
	window.history.pushState(null, null, url);
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
					document.getElementById("formSignUp").reset();
					document.getElementById('viewFormSignUp').style.display = 'none';
				},
				error : function() {
					alert('Error creating!');
				}
			});
}

function showEditUserLogin(){
	var url = "/admin/showEditUserLogin/" ;
	window.history.pushState(null, null, url);
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
					var url = "/editUserLogin/msg=success";
					window.history.pushState(null, null, url);
					alert('successfully!');
					document.getElementById('viewFormEditUserLogin').style.display = 'none';
				},
				error : function() {
					alert('Error!');
				}
			});
}

$("#formChangePasswordUserLogin").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formChangePasswordUserLogin").valid()) {
		changePasswordUserLogin();
	}
});

function changePasswordUserLogin() {
	var url = "/changePasswordUserLogin/";
	window.history.pushState(null, null, url);
	var oldPass = $('#oldPassword').val();
	var newPass = $('#newPassword').val();
	$
			.ajax({
				type : "GET",
				contentType : "application/json",
				dataType : 'json',
				url : '/changePasswordUserLogin',
				cache : false,
				data : {
					oldPassword : oldPass,
					newPassword : newPass
				},
				success : function(data) {
					if(data != null){
						var url = "/changePasswordUserLogin/msg=success";
						window.history.pushState(null, null, url);
						alert('Successfully updated the new password. You must log in again to continue!');
						document.getElementById('viewFormChangePasswordUserLogin').style.display = 'none';
						window.location.replace("/logout");
					}else{
						$('#error').html('<td>Old password is incorrect</td>'
					        	+'<td></td>');
					}
					
				},
				error : function() {
					$('#error').html('<td>Old password is incorrect</td>'
				        	+'<td></td>');
				}
			});
}


