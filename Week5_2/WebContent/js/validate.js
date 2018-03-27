$(document).ready( function () {
	$("#addUser").validate( {
		rules: {
			"username": {
				required: true,
				minlength: 4,
				maxlength: 40
			},
			"password": {
				required: true,				
				minlength: 6,
				maxlength: 40
			},
			"repassword": {
				required: true,
				minlength: 6,
				maxlength: 40,
				equalTo: "#password",
			},
			"fullname": {
				"required": true,
				"minlength": 6,
				"maxlength": 40,
			},
			"address": {
				required: true,
				minlength: 6,
				maxlength: 40
			},
			"tinh": {	
				required: true,
				digits: true,
				min: 0,
				max: 100
			},
			"hesoluong": {	
				required: true,
				number: true,
				min: 0,
				max: 100
			},
			
		},
		messages: {
			"username": {
				required: "Không được bỏ trống!",
				minlength: "Tối thiểu 4 kí tự!",
				maxlength: "Tối đa 40 kí tự!"
			},
			"password": {
					required: "Không được để trống!",					
					minlength: "Tối thiểu 6 kí tự!",
					maxlength: "Tối đa 40 kí tự!"
			},	
			"repassword": {
				required: "Không được để trống!",					
				minlength: "Tối thiểu 6 kí tự!",
				maxlength: "Tối đa 40 kí tự!",
				equalTo: "Mật khẩu phải trùng với mật khẩu đã nhập!", 	
			},
			"fullname": {
				required: "Không được bỏ trống!",
				minlength: "Tối thiểu 6 kí tự!",
				maxlength: "Tối đa 40 kí tự!",
				
			},
			"address": {
				required: "Không được bỏ trống!",
				minlength: "Tối thiểu 6 kí tự!",
				maxlength: "Tối đa 40 kí tự!"
			},
			"tinh": {
				required: "Không được bỏ trống!",
				digits: "Phải là số nguyên dương!",
				min: "Tối thiểu 0",
				max: "Tối đa 100"
			},	
			"hesoluong": {
				required: "Không được bỏ trống!",
				number: "Phải là số!",
				min: "Tối thiểu 0",
				max: "Tối đa 100"
			},	
			
		}
	});

});

$(document).ready( function () {
	$("#editUser").validate( {
		rules: {
			"fullname": {
				"required": true,
				"minlength": 6,
				"maxlength": 40
			},
			"address": {
				required: true,
				minlength: 6,
				maxlength: 40
			},
			"tinh": {	
				required: true,
				digits: true,
				min: 0,
				max: 100
			},
			"hesoluong": {	
				required: true,
				number: true,
				min: 0,
				max: 100
			},
			
		},
		messages: {
			"fullname": {
				required: "Không được bỏ trống!",
				minlength: "Tối thiểu 6 kí tự!",
				maxlength: "Tối đa 40 kí tự!"
			},
			"address": {
				required: "Không được bỏ trống!",
				minlength: "Tối thiểu 6 kí tự!",
				maxlength: "Tối đa 40 kí tự!"
			},
			"tinh": {
				required: "Không được bỏ trống!",
				digits: "Phải là số nguyên dương!",
				min: "Tối thiểu 0",
				max: "Tối đa 100"
			},	
			"hesoluong": {
				required: "Không được bỏ trống!",
				number: "Phải là số!",
				min: "Tối thiểu 0",
				max: "Tối đa 100"
			},	
			
		}
	});

});

$(document).ready( function () {
	$("#changePassword").validate( {
		rules: {
			"oldPassword": {
				required: true,				
				
			},
			"password": {
				required: true,				
				minlength: 6,
				maxlength: 40
			},
			"repassword": {
				required: true,
				minlength: 6,
				maxlength: 40,
				equalTo: "#password",
			},
			
			
		},
		messages: {
			
			"oldPassword": {
					required: "Không được để trống!",					
			},
			"password": {
				required: "Không được để trống!",					
				minlength: "Tối thiểu 6 kí tự!",
				maxlength: "Tối đa 40 kí tự!"
		},	
			"repassword": {
				required: "Không được để trống!",					
				minlength: "Tối thiểu 6 kí tự!",
				maxlength: "Tối đa 40 kí tự!",
				equalTo: "Mật khẩu phải trùng với mật khẩu đã nhập!", 	
			},
			
		}
	});

});

