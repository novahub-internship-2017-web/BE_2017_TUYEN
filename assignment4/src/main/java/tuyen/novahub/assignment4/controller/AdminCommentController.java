package tuyen.novahub.assignment4.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tuyen.novahub.assignment4.model.Comment;
import tuyen.novahub.assignment4.service.CommentService;

@RestController
public class AdminCommentController {
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value = "/deleteComment/{idComment}", method = RequestMethod.GET)
	public List<Comment> deleteComment(Model model, @PathVariable int idComment,@RequestParam int idBook) {
		Logger.getLogger("dddd");
		Comment del = commentService.findByIdComment(idComment);
		commentService.save(del);
		return commentService.findByIdBook(idBook);
	}
	
	@RequestMapping(value = "/allComments/{idBook}", method = RequestMethod.GET)
	public List<Comment> allComment(Model model, @PathVariable int idBook) {
		Logger.getLogger("allComments");
		return commentService.findByIdBook(idBook);
	}
}
