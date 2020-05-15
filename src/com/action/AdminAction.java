package com.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.Admin;
import com.entity.Book;
import com.entity.Category;
import com.entity.Items;
import com.entity.Users;
import com.service.AdminService;
import com.service.BookService;
import com.service.CategoryService;
import com.service.UserService;
import com.util.PageUtil;
import com.util.SafeUtil;
import com.util.UploadUtil;

@Namespace("/admin")
@Results({
	@Result(name="login",location="/admin/login.jsp"),
	@Result(name="main",location="/admin/main.jsp"),
	@Result(name="indent",location="/admin/pages/indent-list.jsp"),
	@Result(name="reindent",type="redirect",location="indentList.action?status=${status}&page=${page}"),
	@Result(name="item",location="/admin/pages/item-list.jsp"),
	@Result(name="user",location="/admin/pages/user-list.jsp"),
	@Result(name="useradd",location="/admin/pages/user-add.jsp"),
	@Result(name="userreset",location="/admin/pages/user-reset.jsp"),
	@Result(name="userupdate",location="/admin/pages/user-update.jsp"),
	@Result(name="reuser",type="redirect",location="userList.action?page=${page}"),
	@Result(name="book",location="/admin/pages/book-list.jsp"),
	@Result(name="bookadd",location="/admin/pages/book-add.jsp"),
	@Result(name="bookupdate",location="/admin/pages/book-update.jsp"),
	@Result(name="rebook",type="redirect",location="bookList.action?status=${status}&page=${page}"),
	@Result(name="category",location="/admin/pages/category-list.jsp"),
	@Result(name="categoryupdate",location="/admin/pages/category-update.jsp"),
	@Result(name="recategory",type="redirect",location="categoryList.action?page=${page}"),
	@Result(name="admin",location="/admin/pages/admin-list.jsp"),
	@Result(name="adminadd",location="/admin/pages/admin-add.jsp"),
	@Result(name="adminreset",location="/admin/pages/admin-reset.jsp"),
	@Result(name="readmin",type="redirect",location="adminList.action?page=${page}"),
	@Result(name="orderList",type="redirect",location="bookOrderList.action?page=${page}"),
	@Result(name="buyerror",location="/admin/pages/buyerror.jsp"),
})	
public class AdminAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private static final int rows = 10;
	
	@Resource
	private AdminService adminService;
	@Resource
	private UserService userService;
	@Resource
	private BookService bookService;
	@Resource
	private CategoryService categoryService;
	
	private List<Items> itemList;
	private List<Users> userList;
	private List<Book> bookList;
	private List<Category> categoryList;
	private List<Admin> adminList;
	
	private Users user;
	private Book book;
	private Category category;
	private Admin admin;
	private int status;
	private int flag;
	private int id;
	private int userid;
	
	private int isbncode;
	private String author;
	private String bookname;
	private String chuban;
	
	private File photo;		
    private String photoFileName;	
    private String photoContentType;	
	
    
	/**
	 * 登录
	 * @return
	 */
    @Action("login")
	public String login() {
		if (adminService.checkUser(admin.getUsername(), admin.getPassword())) {
			getSession().put("admin", admin.getUsername());
			return "main";
		}
		addActionError("用户名或密码错误!");
		return "login";
	}
	
	
	
	
	
	
	
	
	/**
	 * 用户列表
	 * @return
	 */
    @Action("userList")
	public String userList(){
		userList = userService.getList(page, rows);
		pageTool = PageUtil.getPageToolAdmin(servletRequest, userService.getTotal(), page, rows);
		return "user";
	}
	
	/**
	 *添加用户
	 * @return
	 */
    @Action("userAdd")
	public String userAdd(){
		if (userService.isExist(user.getUsername())) {
			addActionError("用户已存在，请重新输入");
			return "useradd";
		}
		 userService.add(user);
		 return "reuser";
	}
	
	/**
	 * 查用户
	 * @return
	 */
    @Action("userRe")
	public String userRe(){
		user = userService.get(id);
		return "userreset";
	}
	
	/**
	 * 重置密码
	 * @return
	 */
    @Action("userReset")
	public String userReset(){
		String password = SafeUtil.encode(user.getPassword());
		user = userService.get(user.getId());
		user.setPassword(password);
		userService.update(user);
		return "reuser";
	}
	
	/**
	 * 查用户
	 * @return
	 */
    @Action("userUp")
	public String userUp(){
		user = userService.get(id);
		return "userupdate";
	}
	
	/**
	 * 修改用户
	 * @return
	 */
    @Action("userUpdate")
	public String userUpdate(){
		userService.update(user);
		return "reuser";
	}
	
	/**
	 * 删除用户
	 * @return
	 */
    @Action("userDelete")
	public String userDelete(){
		userService.delete(id);
		return "reuser";
	}
	
    
    /**
	 * 按条件查
	 * @return
	 */
    @Action("chaxun")
	public String bookListBychaxun(){
    	bookList = bookService.getbookListBychaxun(isbncode,bookname,author,chuban,status, page, rows);
		pageTool = PageUtil.getPageToolAdmin(servletRequest, bookList.size(), page, rows);
		return "book";
	}
	
	/**
	 * 书列表
	 * @return
	 */
    @Action("bookList")
	public String bookList(){
		bookList = bookService.getList(status, page, rows);
		pageTool = PageUtil.getPageToolAdmin(servletRequest, bookService.getTotal(status), page, rows);
		return "book";
	}
	
	/**
	 * 鍥句功娣诲姞
	 * @return
	 */
    @Action("bookAd")
	public String bookAd(){
		categoryList = categoryService.getList();
		return "bookadd";
	}
	
    
	/**
	 * 如果isbn号已存在就提示
	 * @return
	 */
    @Action("bookAdd")
	public String bookAdd(){
    	if (userService.isExistISBN(book.getISBNcode())) {
			addActionError("该书以存在，请重新输入");
			return "bookadd";
		}
		book.setCover(UploadUtil.fileUpload(photo, photoFileName, "picture"));
		bookService.add(book);
		return "rebook";
	}
	
	/**
	 * 分类查
	 * @return
	 */
    @Action("bookUp")
	public String bookUp(){
		categoryList = categoryService.getList();
		book = bookService.get(id);
		return "bookupdate";
	}
	
	/**
	 * 修改书
	 * @return
	 */
    @Action("bookUpdate")
	public String bookUpdate(){
		if (photo != null) {
			book.setCover(UploadUtil.fileUpload(photo, photoFileName, "picture"));
		}
		bookService.update(book);
		return "rebook";
	}
	
	/**
	 * 删除书
	 * @return
	 */
    @Action("bookDelete")
	public String bookDelete(){
		bookService.delete(book);
		return "rebook";
	}
	

    
    /**
   	 * 所有订单
   	 * @return
   	 */
    @Action("bookOrderList")
	public String orderBookList(){
    	itemList = bookService.getItemList(status, page, rows);
		pageTool = PageUtil.getPageToolAdmin(servletRequest, bookService.getItemTotal(status), page, rows);
		return "item";
	}
    
    /**
	 * 加订单
	 * @return
	 */
    @Action("bookOrder")
	public String orderBook(){
    	String username = (String) getSession().get("admin");
    	book = bookService.get(id);
    	if(book.getCount()>0) {
    		bookService.addOrder(username,book);
    		return "orderList";
    	}
    	addActionError("库存不够!");
    	System.out.println("库存不够");
		return "buyerror";
	}
	
	
	/**
	 * 分类
	 * @return
	 */
    @Action("categoryList")
	public String categoryList(){
		categoryList = categoryService.getList(page, rows);
		pageTool = PageUtil.getPageToolAdmin(servletRequest, categoryService.getTotal(), page, rows);
		return "category";
	}
	
	/**
	 * 添加分类
	 * @return
	 */
    @Action("categoryAdd")
	public String categoryAdd(){
		categoryService.add(category);
		return "recategory";
	}
	
	/**
	 * 查分类
	 * @return
	 */
    @Action("categoryUp")
	public String categoryUp(){
		category = categoryService.get(id);
		return "categoryupdate";
	}
	
	/**
	 * 修改分类
	 * @return
	 */
    @Action("categoryUpdate")
	public String categoryUpdate(){
		categoryService.update(category);
		return "recategory";
	}
	
	/**
	 * 删除分类
	 * @return
	 */
    @Action("categoryDelete")
	public String categoryDelete(){
		categoryService.delete(category);
		return "recategory";
	}
	
	
	/**
	 * 管理员
	 * @return
	 */
    @Action("adminList")
	public String adminList(){
		adminList = adminService.getList(page, rows);
		pageTool = PageUtil.getPageToolAdmin(servletRequest, adminService.getTotal(), page, rows);
		return "admin";
	}
	
	/**
	 * 添加
	 * @return
	 */
    @Action("adminAdd")
	public String adminAdd(){
		if (adminService.isExist(admin.getUsername())) {
			addActionError("鐢ㄦ埛鍚嶅凡瀛樺湪!");
			return "adminadd";
		}
		adminService.add(admin);
		return "readmin";
	}
	
	/**
	 * 查
	 * @return
	 */
    @Action("adminRe")
	public String adminRe(){
		admin = adminService.get(id);
		return "adminreset";
	}
	
	/**
	 * 重置密码
	 * @return
	 */
    @Action("adminReset")
	public String adminReset(){
		admin.setPassword(SafeUtil.encode(admin.getPassword()));
		adminService.update(admin);
		return "readmin";
	}
	
	/**
	 * 删除
	 * @return
	 */
    @Action("adminDelete")
	public String adminDelete(){
		adminService.delete(admin);
		return "readmin";
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}


	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Items> getItemList() {
		return itemList;
	}

	public void setItemList(List<Items> itemList) {
		this.itemList = itemList;
	}

	public List<Users> getUserList() {
		return userList;
	}

	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public List<Admin> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}


	public int getIsbncode() {
		return isbncode;
	}


	public void setIsbncode(int isbncode) {
		this.isbncode = isbncode;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getBookname() {
		return bookname;
	}


	public void setBookname(String bookname) {
		this.bookname = bookname;
	}


	public String getChuban() {
		return chuban;
	}


	public void setChuban(String chuban) {
		this.chuban = chuban;
	}
	
}
