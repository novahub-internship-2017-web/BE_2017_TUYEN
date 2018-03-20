$(document).ready( function () {
	$("#addBook").validate( {
		rules: {
			"title": {
				required: true,
				minlength: 4,
				maxlength: 40
			},
			"author": {
				required: true,				
				minlength: 6,
				maxlength: 40
			},
			
			
		},
		messages: {
			"title": {
				required: "Không được bỏ trống!",
				minlength: "Tối thiểu 4 kí tự!",
				maxlength: "Tối đa 40 kí tự!"
			},
			"author": {
					required: "Không được để trống!",					
					minlength: "Tối thiểu 6 kí tự!",
					maxlength: "Tối đa 40 kí tự!"
			},	
			
		}
	});

});

$(document).ready( function () {
	$("#addUser").validate( {
		rules: {
			"firstName": {
				required: true,
				minlength: 4,
				maxlength: 40
			},
			"email": {
				required: true,
				email: true,
			},
			
			"password": {
				required: true,				
				minlength: 3,
				maxlength: 40
			},
			
			"rePassword": {
				required: true,
				equalTo: "#password",
			},
			
		},
		messages: {
			"firstName": {
				required: "Tên không được bỏ trống!",
				minlength: "Tối thiểu 4 kí tự!",
				maxlength: "Tối đa 40 kí tự!"
			},
			"email": {
				required: "Vui lòng nhập email!",
				email: "Nhập đúng định dạng email! VD: abc@gmail.com",
			},
			"password": {
					required: "Không được để trống!",					
					minlength: "Tối thiểu 3 kí tự!",
					maxlength: "Tối đa 40 kí tự!"
			},	
			
			"rePassword": {
				required: "Không được để trống!",					
				equalTo: "Mật khẩu phải trùng với mật khẩu đã nhập!",
		},	

		}
	});

});


$(document).ready( function () {
	$("#changePassword").validate( {
		rules: {
			"oldPassword": {
				required: true,
				minlength: 3,
				maxlength: 40
			},
			"newPassword": {
				required: true,
				minlength: 3,
				maxlength: 40
			},
			
			"rePassword": {
				required: true,
				equalTo: "#newPassword",
			},
			
		},
		messages: {
			"oldPassword": {
				required: "Vui lòng nhập mật khẩu của bạn!",					
				minlength: "Tối thiểu 3 kí tự!",
				maxlength: "Tối đa 40 kí tự!"
			},	
			"newPassword": {
				required: "Vui lòng nhập mật khẩu mới!",					
				minlength: "Tối thiểu 3 kí tự!",
				maxlength: "Tối đa 40 kí tự!"
			},	
			
			"rePassword": {
				required: "Không được để trống!",					
				equalTo: "Mật khẩu phải trùng với mật khẩu đã mới đã nhập!",
		},	

		}
	});

});


$(document).ready( function () {
	$("#form-index").validate( {
		rules: {
			"firstName": {
				required: true,
				minlength: 4,
				maxlength: 40
			},
			
			
			
		},
		messages: {
			"firstName": {
				required: "Không được bỏ trống!",
				minlength: "Tối thiểu 4 kí tự!",
				maxlength: "Tối đa 40 kí tự!"
			},
			
		}
	});

});

$(document).ready( function () {
	$("#form-editUser").validate( {
		rules: {
			"firstName": {
				required: true,
				minlength: 4,
				maxlength: 40
			},
			
			
			
		},
		messages: {
			"firstName": {
				required: "Không được bỏ trống!",
				minlength: "Tối thiểu 4 kí tự!",
				maxlength: "Tối đa 40 kí tự!"
			},
			
		}
	});

});

$(document).ready( function () {
	$("#form-changePasswordLogin").validate( {
		rules: {
			"oldPassword": {
				required: true,
				minlength: 3,
				maxlength: 40
			},
			"newPassword": {
				required: true,
				minlength: 3,
				maxlength: 40
			},
			
			"rePassword": {
				required: true,
				equalTo: "#newPassword",
			},
			
		},
		messages: {
			"oldPassword": {
				required: "Vui lòng nhập mật khẩu của bạn!",					
				minlength: "Tối thiểu 3 kí tự!",
				maxlength: "Tối đa 40 kí tự!"
			},	
			"newPassword": {
				required: "Vui lòng nhập mật khẩu mới!",					
				minlength: "Tối thiểu 3 kí tự!",
				maxlength: "Tối đa 40 kí tự!"
			},	
			
			"rePassword": {
				required: "Không được để trống!",					
				equalTo: "Mật khẩu phải trùng với mật khẩu đã mới đã nhập!",
		},	

		}
	});

});




