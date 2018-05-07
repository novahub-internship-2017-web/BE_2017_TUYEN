$(document).ready(function() {
	changePageAndSize();
});

function changePageAndSize() {
	$('#pageSizeSelect').change(function(evt) {
		window.history.pushState(null, null, '/myBook');
		getMyBooks(this.value,1);
	});
}
