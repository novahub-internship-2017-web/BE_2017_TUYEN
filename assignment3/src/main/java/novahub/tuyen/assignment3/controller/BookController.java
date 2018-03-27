package novahub.tuyen.assignment3.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import novahub.tuyen.assignment3.entities.Book;
import novahub.tuyen.assignment3.entities.User;
import novahub.tuyen.assignment3.library.RenameFileLibrary;
import novahub.tuyen.assignment3.service.BookService;
import novahub.tuyen.assignment3.service.UserService;

@Controller
public class BookController {

	@Autowired
	BookService bookService;

	@RequestMapping(value = { "/list-book" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String listBook(ModelMap modelMap, HttpSession session, @ModelAttribute(value = "msgBook") String msg) {
		if (session.getAttribute("userLogin") != null) {

			User objUser = (User) session.getAttribute("userLogin");
			modelMap.addAttribute("listBookByUserLogin", bookService.getListBookByUserLogin(objUser.getIdUser()));
			return "book";
		}
		return "redirect:show-login";
	}

	@RequestMapping(value = { "/list-all-book" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String listAllBook(ModelMap modelMap, HttpSession session, @ModelAttribute(value = "msgBook") String msg) {
		if (session.getAttribute("userLogin") != null) {
			User objUser = (User) session.getAttribute("userLogin");
			if (objUser.getIdRole() == 1) {
				// nếu là admin thì mới cho chuyển tới danh sách gồm toàn bộ sách
				modelMap.addAttribute("listBook", bookService.getListBook());
				modelMap.addAttribute("userService", new UserService());
				return "allBook";
			} else {
				return "redirect:list-book";
			}
		}
		return "redirect:show-login";
	}

	@RequestMapping(value = { "/show-detailBook/{id}" })
	public String showDetailBook(@PathVariable int id, Model model) {
		Book objBook = bookService.getBookById(id);
		model.addAttribute("objBook", objBook);
		return "detailBook";
	}

	@RequestMapping(value = { "/show-addBook" }, method = RequestMethod.GET)
	public String showAddBook(ModelMap model) {
		model.put("objBook", new Book());
		return "addBook";
	}

	@RequestMapping(value = { "/addBook" }, method = RequestMethod.POST)
	public String addBook(@ModelAttribute(value = "objBook") Book objBook,
			@RequestParam(value = "fileUpload") CommonsMultipartFile commonsMultipartFiles, HttpServletRequest request,
			HttpSession session, ModelMap modelMap) {
		User objUser = (User) session.getAttribute("userLogin");
		objBook.setIdUser(objUser.getIdUser());

		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String st = simpleDateFormat.format(date);
		//java.sql.Date create_at = java.sql.Date.valueOf(st);

		objBook.setCreated_at(st);
		objBook.setUpdated_at(st);

		// upload File

		// ten anh
		String nameFile = commonsMultipartFiles.getOriginalFilename();
		nameFile = RenameFileLibrary.renameFile(nameFile);
		System.out.println("Tên ảnh sách: " + nameFile);

		// tạo files chứa hình ảnh
		if (!"".equals(nameFile)) {
			objBook.setPictureBook(nameFile);
			String dirFile = request.getSession().getServletContext().getRealPath("/") + "resources/images/";
			System.out.println("Link đến thư mục ảnh sách: " + dirFile);
			File fileDir = new File(dirFile);
			if (!fileDir.exists()) {
				fileDir.mkdir();
			}
			try {
				commonsMultipartFiles.transferTo(new File(fileDir + File.separator + nameFile));
				System.out.println("Upload ảnh sách thành công!");
			} catch (Exception e) {
				System.out.println("Upload ảnh sách thất bại!");
			}
		}
System.out.println("Thông tin ccanaf thêm : "+objBook.toString());
		if (bookService.addBook(objBook) != 0) {
			// thêm sách thành công
			modelMap.addAttribute("msgBook", "Thêm thành công sách mới!");
		} else {
			modelMap.addAttribute("msgBook", "Lỗi xử lý!");
		}
		modelMap.addAttribute("listBook", bookService.getListBook());
		return "redirect:list-book";
	}

	@RequestMapping(value = { "/show-editBook/{id}" }, method = RequestMethod.GET)
	public String showEditBook(@PathVariable int id, Model model) {
		Book objBook = bookService.getBookById(id);
		model.addAttribute("objBook", objBook);
		return "editBook";
	}

	@RequestMapping(value = { "/editBook/{id}" }, method = RequestMethod.POST)
	public String editBook(@ModelAttribute(value = "objBook") Book objBook, @PathVariable int id, ModelMap modelMap,
			HttpSession session, @RequestParam(value = "fileUpload") CommonsMultipartFile commonsMultipartFiles,
			HttpServletRequest request) {
		objBook.setIdBook(id);
		// lay id tac gia
		Book b = bookService.getBookById(id);
		User userLogin = (User) session.getAttribute("userLogin");
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String st = simpleDateFormat.format(date);
		//java.sql.Date create_at = java.sql.Date.valueOf(st);

		objBook.setUpdated_at(st);// cập nhật ngày edit
		// objBook.setCreated_at(b.getCreated_at()); //giữ nguyên ngày tạo

		// xu ly upload ảnh

		String nameFile = commonsMultipartFiles.getOriginalFilename();
		if (nameFile.equals("")) {
			System.out.println("K chọn ảnh mới");
			// không chon anh moi thi mac dinh giu nguyen anh cu
			nameFile = bookService.getBookById(id).getPictureBook();
			objBook.setPictureBook(nameFile);
			System.out.println("Tên ảnh cũ : " + nameFile);
		} else {
			// chon anh moi
			// xoa anh cu
			// lay duong dan anh cu
			System.out.println("Chọn ảnh mới");

			if (!"".equals(bookService.getBookById(id).getPictureBook())) { // nếu có file ảnh thì xóa file ảnh đó!
				// thực hiện chức năng xóa hình ảnh
				String urlFileDel = request.getSession().getServletContext().getRealPath("/") + "resources/images"
						+ File.separator + bookService.getBookById(id).getPictureBook();
				System.out.println("anh cần xóa: " + urlFileDel);
				File delFile = new File(urlFileDel);
				if (delFile.exists()) {
					delFile.delete();
				}
			}

			// tên ảnh mới
			nameFile = RenameFileLibrary.renameFile(nameFile);
			objBook.setPictureBook(nameFile);
			System.out.println("Tên ảnh mới: " + nameFile);
			// duong dan anh
			// tạo files chứa hình ảnh
			if (!"".equals(nameFile)) {
				String dirFile = request.getSession().getServletContext().getRealPath("/") + "resources/images/";
				System.out.println("Link ảnh mới: " + dirFile);
				File fileDir = new File(dirFile);
				if (!fileDir.exists()) {
					fileDir.mkdir();
				}
				try {
					commonsMultipartFiles.transferTo(new File(fileDir + File.separator + nameFile));
					System.out.println("Upload file thành công!");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("Upload file thất bại!");
				}
			}

		}
System.out.println("Thông tin cần sửa: "+objBook.toString());
		if ((userLogin.getIdRole() == 1) || (userLogin.getIdUser() == b.getIdUser())) {
			// admin hoặc người tạo ra cuốn sách mới có quyền chỉnh sửa
			if (bookService.editBook(objBook) != 0) {
				// update thanh cong
				modelMap.addAttribute("msgBook", "Chỉnh sửa thành công!");
			} else {
				// fail
				modelMap.addAttribute("msgBook", "Lỗi xử lý");
			}

		}
		if ((userLogin.getIdRole() == 1) && (b.getIdUser() != userLogin.getIdUser())) {
			// nếu admin sửa sách của user thì trả kết quả về list-allBook
			modelMap.addAttribute("listBook", bookService.getListBook());
			return "allBook";
		} else {
			modelMap.addAttribute("listBookByUserLogin", bookService.getListBookByUserLogin(userLogin.getIdUser()));
			return "book";
		}
	}

	@RequestMapping(value = { "/deleteBook/{id}" })
	public String deleteBook(@PathVariable int id, Model model, HttpSession session,HttpServletRequest request) {
		User userLogin = (User) session.getAttribute("userLogin");
		Book b = bookService.getBookById(id);
		if ((userLogin.getIdRole() == 1) || (b.getIdUser() == userLogin.getIdUser())) {
			// admin hoặc tác giả thì được phép xóa
			//xóa hinh anh
			if(!"".equals(bookService.getBookById(id).getPictureBook())){ // nếu có file ảnh thì xóa file ảnh đó!
				// thực hiện chức năng xóa hình ảnh 
				System.out.println("Thuc hien xoa anh!");
				String urlFileDel = request.getSession().getServletContext().getRealPath("/") + "resources/images"
						+ File.separator + bookService.getBookById(id).getPictureBook();
				System.out.println("anh cần xóa: "+urlFileDel);
				File delFile = new File(urlFileDel);
				if(delFile.exists()){
					delFile.delete();
				}
			}
			
			if (bookService.deleteBook(id) != 0) {
				// xóa thành công
				model.addAttribute("msgBook", "Xóa thành công!");
			} else {
				model.addAttribute("msgBook", "Lỗi xử lý!");
			}

		}
		if ((userLogin.getIdRole() == 1) && (b.getIdUser() != userLogin.getIdUser())) {
			// nếu admin xóa sách của user thì trả kết quả về list-allBook
			model.addAttribute("listBook", bookService.getListBook());
			return "allbook";
		} else {
			model.addAttribute("listBookByUserLogin", bookService.getListBookByUserLogin(userLogin.getIdUser()));
			return "book";
		}

	}

	/*
	 * @RequestMapping(value = { "/searchBook" }, method = RequestMethod.POST)
	 * public String searchBook(@ModelAttribute(value = "objBook") Book objBook,
	 * HttpSession session, ModelMap modelMap) {
	 * objBook.setAuthor(objBook.getTitle()); User objUser = (User)
	 * session.getAttribute("userLogin"); modelMap.addAttribute("objBook", new
	 * Book()); modelMap.addAttribute("keySearch", objBook.getTitle());
	 * System.out.println("Keysearch: " + objBook.getTitle()); System.out.println(
	 * "Kết quả: " + bookService.getListSearchBookByUserLogin(objUser.getIdUser(),
	 * objBook.getTitle()).size()); modelMap.addAttribute("msgBook", "Tìm thấy " +
	 * bookService.getListSearchBookByUserLogin(objUser.getIdUser(),
	 * objBook.getTitle()).size() + " kết quả với '" + objBook.getTitle() + "'");
	 * modelMap.addAttribute("listBookByUserLogin",
	 * bookService.getListSearchBookByUserLogin(objUser.getIdUser(),
	 * objBook.getTitle())); return "book"; }
	 */
	@RequestMapping(value = { "/searchBook" }, method = RequestMethod.POST)
	public String searchBook(@RequestParam String keySearch, HttpSession session, ModelMap modelMap) {
		User objUser = (User) session.getAttribute("userLogin");
		modelMap.addAttribute("keySearch", keySearch);
		System.out.println("Keysearch: " + keySearch);
		System.out
				.println("Kết quả: " + bookService.getListSearchBookByUserLogin(objUser.getIdUser(), keySearch).size());
		modelMap.addAttribute("msgBook",
				"Tìm thấy " + bookService.getListSearchBookByUserLogin(objUser.getIdUser(), keySearch).size()
						+ " kết quả với '" + keySearch + "'");
		modelMap.addAttribute("listBookByUserLogin",
				bookService.getListSearchBookByUserLogin(objUser.getIdUser(), keySearch));
		return "book";
	}

	@RequestMapping(value = { "/searchAllBook" }, method = RequestMethod.POST)
	public String searchAllBook(@RequestParam String keySearch, HttpSession session, ModelMap modelMap) {
		User objUser = (User) session.getAttribute("userLogin");
		modelMap.addAttribute("keySearch", keySearch);
		System.out.println("Keysearch: " + keySearch);
		if (objUser.getIdRole() == 1) {
			System.out.println("Kết quả: " + bookService.getListSearchAllBook(keySearch).size());
			modelMap.addAttribute("msgBook", "Tìm thấy " + bookService.getListSearchAllBook(keySearch).size()
					+ " kết quả với '" + keySearch + "'");
			modelMap.addAttribute("listBookByUserLogin", bookService.getListSearchAllBook(keySearch));
		}

		return "book";
	}
	
	
/*	@RequestMapping(value = "/searchBookAjax", method = RequestMethod.GET)
	public @ResponseBody String searchBookAjax(HttpServletRequest request) {
//		String query = request.getParameter("name");
//		List<Book> listBook = bookService.getListSearchBookByUserLogin(1, query);
//		List<Book> list = new ArrayList<Book>();
//		Book objBook = new Book();
//		for (Book book : listBook) {
//			objBook = book;
//			list.add(objBook);
//		}
//		ObjectMapper mapper = new ObjectMapper();
//		String ajaxResponse = "";
//		try {
//			ajaxResponse = mapper.writeValueAsString(objBook);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}

		//return ajaxResponse;
return "";
	}*/
}
