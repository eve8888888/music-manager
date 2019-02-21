package com.bittech.musicmanager.action;

import com.bittech.musicmanager.entity.Music;
import com.bittech.musicmanager.service.IMusicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /**
     * 查询所有音乐,将结果返回到前端
     * @return list
     */
    @RequestMapping("/findAllMusic.action")
    public @ResponseBody
    List<Music> findAllMusic() {
        return ims.findAllMusic();
    }

    /**
     * 实现音乐下载功能
     * @param request request
     * @param response response
     */
    @RequestMapping("/download.action")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        ims.downloadMusic(request,response);
    }

}
