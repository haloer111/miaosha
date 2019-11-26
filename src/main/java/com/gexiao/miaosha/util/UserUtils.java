package com.gexiao.miaosha.util;

import com.alibaba.fastjson.JSON;
import com.gexiao.miaosha.common.ResultEntity;
import com.gexiao.miaosha.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @author : gexiao
 * @since : 2019/11/19 16:21
 */
public class UserUtils {
    public static final String USER = "user";

    public static User get() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = (User) request.getAttribute(USER);
        return user;
    }

    public static void createUser(List<User> users, int count) throws Exception {
//        List<User> users = new ArrayList<User>(count);
//        //生成用户
//        for(int i=0;i<count;i++) {
//            User user = new User();
//            user.setId(13000000000L+i);
//            user.setName("user"+i);
//            user.setPassword(MD5Utils.getMD5("123"));
//            users.add(user);
//        }
        //登录，生成token
        File file = new File("D:/tokens.txt");
        if (file.exists()) {
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        file.createNewFile();
        raf.seek(0);
        for (int i = 0; i < count; i++) {
            User user = users.get(i);
            String urlString = "http://localhost:8080/login?id=" + user.getId() + "&password=123";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                ResultEntity entity = (ResultEntity) JSON.parseObject(inputStream, ResultEntity.class);
                String token = (String) entity.getData();
                String row = user.getId() + "," + token;
                raf.seek(raf.length());
                raf.write(row.getBytes());
                raf.write("\r\n".getBytes());
                System.out.println("write to file : " + user.getId());
            }
        }
        raf.close();

        System.out.println("over");
    }


}
