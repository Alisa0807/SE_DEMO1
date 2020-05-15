package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BookDao;
import com.entity.Book;
import com.entity.Items;

@Service	// 注解为service层spring管理bean
@Transactional	// 注解此类所有方法加入spring事务, 具体设置默认
public class BookService {

	@Resource	
	private BookDao bookDao;
	
	
	/**
	 * 通过名称搜索
	 * @param category
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Book> getList(String name, int page, int size) {
		return bookDao.getList(name, page, size);
	}
	
	/**
	 * 分类数量
	 * @return 无记录返回空集合
	 */
	public long getTotal(String name){
		return bookDao.getTotal(name);
	}
	
	/**
	 * 通过分类搜索
	 * @param categoryid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Book> getCategoryList(int categoryid, int page, int size) {
		return bookDao.getCategoryList(categoryid, page, size);
	}
	
	/**
	 * 分类数量
	 * @return 无记录返回空集合
	 */
	public long getCategoryTotal(int categoryid){
		return bookDao.getCategoryTotal(categoryid);
	}
	
	/**
	 * 获取特卖列表
	 * @return 无记录返回空集合
	 */
	public List<Book> getSpecialList(int type, int page, int size){
		return bookDao.getSpecialList(type, page, size);
	}
	
	/**
	 * 获取特卖列表
	 * @return 无记录返回空集合
	 */
	public long getSpecialTotal(int type){
		return bookDao.getSpecialTotal(type);
	}

	/**
	 * 通过id获取
	 * @param bookid
	 * @return
	 */
	public Book get(int bookid) {
		return bookDao.get(Book.class, bookid);
	}

	/**
	 * 图书列表
	 * @param status
	 * @return
	 */
	public List<Book> getList(int status, int page, int rows) {
		return bookDao.getList(status, page, rows);
	}

	/**
	 * 按查询条件图书列表
	 * @param status
	 * @param rows2 
	 * @param chuban 
	 * @param author 
	 * @param bookname 
	 * @return
	 */
	public List<Book> getbookListBychaxun(int isbncode,String bookname,String author,String chuban ,int status,int page, int size) {
		return bookDao.getbookListBychaxun(isbncode,bookname,author,chuban, status,page, size);
	}
	/**
	 * 总数
	 * @return
	 */
	public long getTotal(int status) {
		return bookDao.getTotal(status);
	}
	
	/**
	 * 订单总数
	 * @return
	 */
	public long getItemTotal(int status) {
		return bookDao.getItemTotal(status);
	}

	/**
	 * 添加
	 * @param book
	 */
	public Integer add(Book book) {
		return bookDao.save(book);
	}

	/**
	 * 修改
	 * @param book
	 * @return 
	 */
	public boolean update(Book book) {
		return bookDao.update(book);
	}

	/**
	 * 删除
	 * @param book
	 */
	public boolean delete(Book book) {
		return bookDao.delete(book);
	}

	public Object addOrder(String name,Book book) {
		return bookDao.addOrder(name,book);
	}

	public List<Items> getItemList(int status, int page, int rows) {
		return bookDao.getitemList(status, page, rows);
	}

	
	
}
