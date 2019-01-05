package lyb.util;

import com.google.gson.Gson;


import lyb.gson.NewsList;

/**
 * Created by len_titude on 2017/5/4.
 */

public class Utility {
    public static NewsList parseJsonWithGson(final String requestText){
        Gson gson = new Gson();
        //把得到的json数据转换为newList对象
        return gson.fromJson(requestText, NewsList.class);
    }

}
