package com.bittech.musicmanager.action;

import com.bittech.musicmanager.entity.Music;
import com.bittech.musicmanager.service.IMusicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @Author: Eve
 * @Date: 2019/2/19 12:05
 * @Version 1.0
 */
@Controller
public class MusicAction {
    @Resource
    IMusicService ims;

    @RequestMapping("/findAllMusic.action")
    public @ResponseBody
    List<Music> findAllMusic() {
        return ims.findAllMusic();
    }

    @RequestMapping("/download.action")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String musicName = request.getParameter("name");
        String fileName = musicName;// 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            //设置文件路径
            String realPath = "E:\\Eve\\music-manager\\src\\main\\resources\\music";
            File file = new File(realPath, fileName);
            if (file.exists()) {
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
