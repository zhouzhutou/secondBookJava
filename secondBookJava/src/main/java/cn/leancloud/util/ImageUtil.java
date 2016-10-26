package cn.leancloud.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class ImageUtil {
    public static List<String> getDisplayImages(List<JSONObject> jsonObjects,String key)
    {
    	List<String> images=new ArrayList<>();
    	for(int i=0;i<jsonObjects.size();i++)
    	{
    		String url=jsonObjects.get(i).getString(key);
    		if(!url.isEmpty()&&url!=null)
    		{
    			images.add(url);
    		}
    	}
    	return images;
    }
}
