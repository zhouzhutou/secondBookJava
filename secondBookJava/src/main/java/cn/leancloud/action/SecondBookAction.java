package cn.leancloud.action;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.New;

import org.json.JSONObject;


import com.avos.avoscloud.AVObject;
import com.opensymphony.xwork2.ActionSupport;

import cn.leancloud.cloudFun.CloudFun;
import cn.leancloud.util.DateUtil;
import cn.leancloud.util.ImageUtil;


public class SecondBookAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String BOOK_NOT_FOUND="bookNotFound";
	private AVObject book=new AVObject("SecondBook_Book_SellingBook");
	private AVObject user=new AVObject("_User");
	private String bookId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookDescription() {
		return bookDescription;
	}
	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}
    public List<String> getImages()
    {
    	return images;
    }
    public void setImages(List<String> images)
    {
    	this.images=images;
    }
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublishYear() {
		return publishYear;
	}
	public void setPublishYear(String publishYear) {
		this.publishYear = publishYear;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	} 
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public boolean getBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
    public int getNumStock() {
		return numStock;
	}
	public void setNumStock(int numStock) {
		this.numStock = numStock;
	}

	private int numStock;
	private int days;
	private int bookPrice;
	private String userName;
	private String bookName;
	private String avatar=null;
	private String address;
	private String bookDescription;
	private List<String>images=new ArrayList<>();
	private String ISBN;
	private String press;
	private String author;
	private String publishYear;
	private boolean bool=false;//判断是否时集思广益，true表示集思广益，false表示普通用户
	private static final String jsgy="575d1f82d342d3005799a18a";
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public AVObject getBook() {
		return book;
	}
	public void setBook(AVObject book) {
		this.book = book;
	}
	public AVObject getUser() {
		return user;
	}
	public void setUser(AVObject user) {
		this.user = user;
	}
	
	@Override 
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//Map request=(Map) ActionContext.getContext().get("request");
		//String bookId=(String) request.get("bookId");
		//System.out.println(bookId);
		/*
		 Map<String,Object> parameters=new HashMap<>();
		parameters.put("bookId", bookId);
		book=AVCloud.callFunction("getBookById", parameters);
		user=AVCloud.callFunction("getUserWithBookId", parameters);
		*/
		//判断是否用户为集思广益，是则isJsgy设为true
		book=CloudFun.getBook(bookId,new String("SecondBook_Book_SellingBook"));
		if(book==null)    return BOOK_NOT_FOUND;
		System.out.println("book: "+book);
		user=CloudFun.getUser(bookId,new String("SecondBook_Book_SellingBook"));
		if(user.getObjectId().equals(jsgy))
		{
			bool=true;
		}
	   if(user.getAVFile("userAvatar")!=null)
	    {
	    	avatar=user.getAVFile("userAvatar").getUrl();
	    }
		userName=user.getString("username");
		address=user.getString("strCampus");
		bookPrice=book.getInt("numPrice");
		numStock=book.getInt("numStock");
		days=DateUtil.getDayFromPrev(book.getDate("createdAt"));
		bookName=book.getString("strName");
		bookDescription=book.getString("strOutlining");
		ISBN=book.getString("strISBN");
		press=book.getString("strPress");
		author=book.getString("strAuthor");
		publishYear=book.getString("strTime");
		//获取书籍图片对象
		List<JSONObject> jsonObjects=new ArrayList<>();
		if(book.getAVFile("filPicture1")!=null)
		jsonObjects.add(book.getJSONObject("filPicture1"));
		if(book.getAVFile("filPicture2")!=null)
	    jsonObjects.add(book.getJSONObject("filPicture2"));
		if(book.getAVFile("filPicture3")!=null)
	    jsonObjects.add(book.getJSONObject("filPicture3"));
	    images=ImageUtil.getDisplayImages(jsonObjects, "url");
		return SUCCESS;
	}
      
}
