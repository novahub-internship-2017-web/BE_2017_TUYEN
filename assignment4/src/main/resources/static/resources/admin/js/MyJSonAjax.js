function listUserJson(){
	 alert('All users');
    $.ajax({
      url : "/listUser",
      type : "GET",
      contentType : "application/json",
      dataType : 'json',        
      success : function(data) {
    	alert('success');
        if(data.length > 0){
        	var title = '<table class="table table-striped table-advance table-hover">'
           				+'<tbody>'
            			+'<tr>'
              			+'<th><i class="icon_profile"></i> Full Name</th>'
              			+'<th><i class="icon_mail_alt"></i> Email</th>'
              			+'<th><i class=""></i> Active</th>'
              			+'<th><i class=""></i> Role</th>'
              			+'<th><i class="icon_cogs"></i> Action</th>'
           				+' </tr>';
           for (var i = 0; i < data.length; i++) {
        	   var check = null;
        	   var nameRole = 'admin';
        	   if(data[i].enabled == 1){
        		   check='checked="checked"';
        	   }
        	   if(data[i].idRole != 1){
        		   nameRole = 'mod';
        	   }
        	   var row = '<tr>'
                  		+'<td>'+data[i].firstName+' '+data[i].lastName+'</td>'
                   		+'<td>'+data[i].email+'</td>'
                   		+'<td>'
                   		+'<input type="checkbox" '+check+'>'
                   		+'</td>'
                    	+'<td>'+nameRole
                   		+'</td>' 
                   		+'<td>'
                    	+'<div class="btn-group">'
                        /* +'<a class="btn btn-success" href="#" title="Edit book!"><i class="icon_camera_alt"></i></a>' */
                        +'<a class="btn btn-success" href="#" title="Edit book!"><i class="icon_pencil-edit"></i></a>'
                        +'<a class="btn btn-danger" onclick="return deleteUser('+data[i].idUser+')" href="#" title="Delete book!"><i class="icon_close_alt2"></i></a>'
                        +'</div>'
                        +'</td>'
                 		+'</tr>';
        	   title = title + row; 		
           }
           title = title + '</tbody>'
           				 + '</table>';
           $('#result').html(title);
           $('#msgResult').html('');
        } else{
        	 $('#result').html("No user");
        	 $('#msgResult').html('');
        }
        },
        error: function(){      
           alert('error!');
          }
    });
   }

function addUserJson(){
	// alert("add user!");
   var newUser = {}
   newUser["firstName"] = $('#firstName').val();
   newUser["lastName"] = $('#lastName').val();
   newUser["email"]= $('#email').val(); 
   newUser["idRole"]= $('#idRole').val(); 
   newUser["password"]= $('#password').val(); 
   if ($('#firstName').val() == ''){
       alert('FirstName can not be empty!');
       return false;
   }
   if ($('#firstName').val().length > 40 ){
       alert('FirstName is no more than 40 characters!');
       return false;
   }
   if ($('#lastName').val() == ''){
       alert('LastName can not be empty!');
       return false;
   }
   if ($('#lastName').val().length > 40 ){
       alert('LastName is no more than 40 characters!');
       return false;
   }
   if ($('#password').val() == ''){
       alert('password can not be empty!');
       return false;
   }
   
   if ($('#password').val().length > 40 ){
       alert('password is no more than 40 characters');
       return false;
   }
   if ($('#rePassword').val() != $('#password').val()){
       alert('confirm password must be like password!');
       return false;
   }
   $.ajax({
    type: "post",
    contentType : "application/json",
    dataType : 'json',
    url: '/addUser',
    cache: false,    
    data:JSON.stringify(newUser),
    success: function(data){
    document.getElementById('formAddUser').style.display='none';
    alert('success');
    	var title = '<table class="table table-striped table-advance table-hover">'
       				+'<tbody>'
        			+'<tr>'
          			+'<th><i class="icon_profile"></i> Full Name</th>'
          			+'<th><i class="icon_mail_alt"></i> Email</th>'
          			+'<th><i class=""></i> Active</th>'
          			+'<th><i class=""></i> Role</th>'
          			+'<th><i class="icon_cogs"></i> Action</th>'
       				+' </tr>';
       for (var i = 0; i < data.length; i++) {
    	   var check = null;
    	   var nameRole = 'admin';
    	   if(data[i].enabled == 1){
    		   check='checked="checked"';
    	   }
    	   if(data[i].idRole != 1){
    		   nameRole = 'mod';
    	   }
    	   var row = '<tr>'
              		+'<td>'+data[i].firstName+'</td>'
               		+'<td>'+data[i].email+'</td>'
               		+'<td>'
               		+'<input type="checkbox" '+check+'>'
               		+'</td>'
                	+'<td>'+nameRole
               		+'</td>' 
               		+'<td>'
                	+'<div class="btn-group">'
                    +'<a class="btn btn-success" href="#" title="Edit book!"><i class="icon_pencil-edit"></i></a>'
                    +'<a class="btn btn-danger" href="#" title="Delete book!"><i class="icon_close_alt2"></i></a>'
                    +'</div>'
                    +'</td>'
             		+'</tr>';
    	   title = title + row; 		
       }
       title = title + '</tbody>'
       				 + '</table>';
       $('#result').html(title);
       $('#msgResult').html('Successfully added new user!');
    },
    error: function(){      
       alert('Error adding new user!');
      }
   });
  }
 
 function display(data) {
    var json = "<h4>Ajax Response</h4><pre>"
        + JSON.stringify(data, null, 4) + "</pre>";
    $('#result').html(json);
  }
 
 
 function deleteUser(idUser){
	 alert('delete user');
	 alert(idUser)
    $.ajax({
      url : "/deleteUser/"+idUser,
      type : "GET",
      contentType : "application/json",
      dataType : 'json',        
      success : function(data) {
    	alert('delete success');
        if(data.length > 0){
        	var title = '<table class="table table-striped table-advance table-hover">'
           				+'<tbody>'
            			+'<tr>'
              			+'<th><i class="icon_profile"></i> Full Name</th>'
              			+'<th><i class="icon_mail_alt"></i> Email</th>'
              			+'<th><i class=""></i> Active</th>'
              			+'<th><i class=""></i> Role</th>'
              			+'<th><i class="icon_cogs"></i> Action</th>'
           				+' </tr>';
           for (var i = 0; i < data.length; i++) {
        	   var check = null;
        	   var nameRole = 'admin';
        	   if(data[i].enabled == 1){
        		   check='checked="checked"';
        	   }
        	   if(data[i].idRole != 1){
        		   nameRole = 'mod';
        	   }
        	   var row = '<tr>'
                  		+'<td>'+data[i].firstName+' '+data[i].lastName+'</td>'
                   		+'<td>'+data[i].email+'</td>'
                   		+'<td>'
                   		+'<input type="checkbox" '+check+'>'
                   		+'</td>'
                    	+'<td>'+nameRole
                   		+'</td>' 
                   		+'<td>'
                    	+'<div class="btn-group">'
                        /* +'<a class="btn btn-success" href="#" title="Edit book!"><i class="icon_camera_alt"></i></a>' */
                        +'<a class="btn btn-success" href="#" title="Edit book!"><i class="icon_pencil-edit"></i></a>'
                        +'<a class="btn btn-danger" href="#" title="Delete book!"><i class="icon_close_alt2"></i></a>'
                        +'</div>'
                        +'</td>'
                 		+'</tr>';
        	   title = title + row; 		
           }
           title = title + '</tbody>'
           				 + '</table>';
           $('#result').html(title);
           $('#msgResult').html('Successfully deleted user!');
        } else{
        	 $('#result').html("No user");
        	 $('#msgResult').html('');
        }
        },
        error: function(){      
           alert('error!');
          }
    });
   }