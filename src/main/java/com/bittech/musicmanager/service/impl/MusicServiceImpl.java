package com.bittech.musicmanager.service.impl;

import com.bittech.musicmanager.entity.Music;
import com.bittech.musicmanager.mapper.MusicMapper;
import com.bittech.musicmanager.service.IMusicService;
import com.bittech.musicmanager.util.FormatTime;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @Author: Eve
 * @Date: 2019/2/19 11:36
 * @Version 1.0
 */

@Service("msi")
public class MusicServiceImpl implements IMusicService {
    @Resource
    private MusicMapper musicMapper;

    /**
     * 查找所有音乐
     * @return list
     */
    @Override
    public List<Music> findAllMusic() {
        addMusic();
        return musicMapper.findAllMusic();
    }

    /**
     * 自动扫描设置文件夹下所有音乐并添加到数据库中
     */
    @Override
    public void addMusic() {
        MP3File mp3File = null;
        File[] files =
                new File(System.getProperty("user.dir")
                        + "\\src\\main\\resources\\music").listFiles();
        if (files!=null) {
            for (File f :
                    files) {
                try {
                    mp3File = new MP3File(f.getPath());
                } catch (IOException | TagException | ReadOnlyFileException | InvalidAudioFrameException e) {
                    e.printStackTrace();
                }
                Music music = new Music();
                MP3AudioHeader header = null;
                if (mp3File != null) {
                    header = mp3File.getMP3AudioHeader();
                }
                String singer = f.getName().split("-")[0];
                String name = f.getName().split("-")[1];
                String time = null;
                if (header != null) {
                    time = FormatTime.convertTime(header.getTrackLength());
                }
                music.setName(name);
                music.setSinger(singer);
                music.setTime(time);
                String path = "<a href=\"download.action?name="+f.getName()+"\" download=\""+name+"\">点击下载</a>";
                music.setPath(path);
                if(findMusicByName(name)==null){
                    musicMapper.addMusic(music);
                }
            }
        }
    }

    /**
     * 通过名称进行查找
     * @param name 音乐名称
     * @return music
     */
    @Override
    public Music findMusicByName(String name) {
        return musicMapper.findMusicByName(name);
    }

    /**
     * 实现音乐下载
     * @param request request
     * @param response response
     */
    @Override
    public void downloadMusic(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("name");// 设置文件名，根据业务需要替换成要下载的文件名
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
    }
}
