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
import java.io.File;
import java.io.IOException;
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
    @Override
    public List<Music> findAllMusic() {
        addMusic();
        return musicMapper.findAllMusic();
    }

    @Override
    public void addMusic() {
        MP3File mp3File = null;
        File[] files =
                new File(System.getProperty("user.dir")
                        + "\\src\\main\\resources\\music").listFiles();
        for (File f :
                files) {
            try {
                mp3File = new MP3File(f.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TagException e) {
                e.printStackTrace();
            } catch (ReadOnlyFileException e) {
                e.printStackTrace();
            } catch (InvalidAudioFrameException e) {
                e.printStackTrace();
            }
            Music music = new Music();
            MP3AudioHeader header = mp3File.getMP3AudioHeader();
            String singer = f.getName().split("-")[0];
            String name = f.getName().split("-")[1];
            String time = FormatTime.convertTime(header.getTrackLength());
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

    @Override
    public Music findMusicByName(String name) {
        return musicMapper.findMusicByName(name);
    }
}
