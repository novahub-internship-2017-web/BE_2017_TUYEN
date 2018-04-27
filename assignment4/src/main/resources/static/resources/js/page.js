$(document).ready(function() {
	changePageAndSize();
});

function changePageAndSize() {
	$('#pageSizeSelect').change(function(evt) {
		window.location.replace("/myBook?pageSize=" + this.value + "&page=1");
	});
}

function pageBook(pageSize_,page_) {
	var url = "/?pageSize="+pageSize_+"&page="+page_ ;
	window.history.pushState(null, null, url);
	//alert('hihi'+page_);
	$.ajax({
		url : "/listBook",
		type : "GET",
		cache : false,
		contentType : "application/json",
		dataType : 'json',
		data : {
			pageSize : pageSize_,
			page: page_
		},
		success : function(data) {
//			var url = "/pageSize="+pageSize_+"&page="+page_ ;
//			window.history.pushState(null, null, url);
		//	window.location.replace("/listBook?pageSize=" + pageSize_+ "&page=" + page_);
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
			$.each(data.content, function(i,book){
				var check = null;
				if(book.enabled == 1){
					check = 'checked="checked"';
				}
				var row = '<tr>' + '<td>'
						+ book.title
						+ '</td>'
						+ '<td>'
						+ book.author
						+ '</td>'
						+ '<td>'
						+ book.createdAt
						+ '</td>'
						+ '<td>'
						+ book.updatedAt
						+ '</td>'
						
						+ '<td>'
						+ '<input type="checkbox" '
						+ check
						+ ' onclick="changeStatusBook('
						+ book.idBook
						+ ','
						+ book.enabled
						+ ')">'
						+ '</td>'
						
						+ '<td>'
						+ '<div class="btn-group">'
						+ '<a class="btn btn-warning"'
						+ ' href="/detailBook/'+book.idBook+'" title="Detail!"><i class="icon_camera_alt"></i></a>'
						+ '<a class="btn btn-success" onclick="showEditBook('
						+ book.idBook
						+ ')" href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
						+ '<a class="btn btn-danger" onclick="deleteBook('
						+ book.idBook
						+ ')" href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
						+ '</div>' + '</td>' + '</tr>';
				title = title + row;
			});
			title = title + '</tbody>' + '</table>';
			$('#result').html(title);
		},
		error : function() {
			alert('Error!');
		}
	});
}
