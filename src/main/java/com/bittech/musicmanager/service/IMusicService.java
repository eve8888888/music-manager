package com.bittech.musicmanager.service;

import com.bittech.musicmanager.entity.Music;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface IMusicService {
    /**
     * 查找所有音乐
     * @return list
     */
    List<Music> findAllMusic();

    /**
     * 添加音乐
     */
    void addMusic();

    /**
     * 通过音乐名进行查找 返回一个音乐对象
     * @param name 音乐名
     * @return music
     */
    Music findMusicByName(String name);

    /**
     * 音乐下载
     * @param request request
     * @param response response
     */
    void downloadMusic(HttpServletRequest request, HttpServletResponse response);

    /**
     * 清除失效的歌曲文件
     */
    void clearMusic();


}
