	// DO GET
	function listBookJson(){
		 alert('Tất cả sách');
	    $.ajax({
	      url : "/listUser",
	      type : "GET",
	      contentType : "application/json",
	      dataType : 'json',        
	      success : function(data) {
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
	        	   var row = '<tr>'
	                  		+'<td>'+data[i].firstName+'</td>'
	                   		+'<td>'+data[i].email+'</td>'
	                   		+'<td>'
	                   		+'<input type="checkbox">'
	                   		+'</td>'
	                    	+'<td> admin'
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
	        }
	        },
	        error: function(){      
	           alert('Lỗi xử lý!');
	          }
	    });
	   }
