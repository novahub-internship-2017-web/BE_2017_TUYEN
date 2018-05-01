//function deleteComment(idComment,idB) {
//	if (confirm('Are you sure your want to delete?')) {
//		$
//				.ajax({
//					url : "/deleteComment/" + idComment,
//					type : "GET",
//					contentType : "application/json",
//					dataType : 'json',
//					data : {
//						idBook : idB
//					},
//					success : function(data) {
//						
//					},
//					error : function() {
//						alert('error!');
//					}
//				});
//	}
//}
$(document).ready(function () {

    getAllComments();

});

function addCommentLine(res) {
    var comment = $("<div>");
    var commentOwner = $("<div>");
    var commentContent = $("<div>");

    commentOwner.html(res.user.lastName);
    commentContent.html(res.message);

    commentOwner.addClass("user");
    commentContent.addClass("comment-content");

    comment.append(commentOwner);
    comment.append(commentContent);
    comment.addClass("col col-md-12 comment");
    
    $(".comment").first().before(comment);
}
function getAllComments() {
	var idBook = $("#idBook").val();
	alert(idBook);
    $.ajax({
        contentType: "application/json",
        url: "/allComments/" + 6,
        dataType: 'json',
        type : "GET",
        timeout: 100000,
        success: function (res) {
            if (res.length > 0) {
            	for (var i = 0; i < res.length; i++ ) {
                    addCommentLine(res[i]);
                }
            }
        }
    });
}