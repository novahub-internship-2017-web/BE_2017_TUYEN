$(document).ready(function() {
	changePageAndSize();
});

function changePageAndSize() {
	$('#pageSizeSelect').change(function(evt) {
		window.history.pushState(null, null, '/');
		getAllBooks(this.value,1);
	});
}
