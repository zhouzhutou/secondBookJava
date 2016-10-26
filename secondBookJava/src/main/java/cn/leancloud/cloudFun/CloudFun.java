package cn.leancloud.cloudFun;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;

public class CloudFun {
	/*@EngineFunction("getBookById")
	public static AVObject getBook(@EngineFunctionParam("bookId") String booId)
	{
		AVObject book = null;
		AVQuery<AVObject> avQuery=new AVQuery<>("SecondBook_Book_SellingBook");
		try {
			book=avQuery.get(booId);
		} catch (AVException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
	@EngineFunction("getUserWithBookId")
	public static AVObject getUser(@EngineFunctionParam("bookId") String bookId)
	{
		AVQuery<AVObject> avQuery=new AVQuery<>("SecondBook_Book_SellingBook");
		avQuery.whereEqualTo("objectId", bookId);
		avQuery.include("poiDependentUser");
		AVObject book=null;
		try {
			book=avQuery.find().get(0);
		} catch (AVException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AVObject user=book.getAVObject("poiDependentUser");
		return user;
	}*/
	
	public static AVObject getBook(String booId,String bookTable)
	{
		AVObject book = null;
		AVQuery<AVObject> avQuery=new AVQuery<>(bookTable);
		try {
			book=avQuery.get(booId);
		} catch (AVException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
	public static AVObject getUser(String bookId,String bookTable)
	{
		AVQuery<AVObject> avQuery=new AVQuery<>(bookTable);
		avQuery.whereEqualTo("objectId", bookId);
		avQuery.include("poiDependentUser");
		AVObject book=null;
		try {
			book=avQuery.getFirst();
		} catch (AVException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AVObject user=book.getAVObject("poiDependentUser");
		return user;
	}
	
}
