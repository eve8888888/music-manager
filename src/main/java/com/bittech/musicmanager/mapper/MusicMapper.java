package com.bittech.musicmanager.mapper;

import com.bittech.musicmanager.entity.Music;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MusicMapper {

    /**
     * 添加音乐
     * @param music 音乐对象
     */
    void addMusic(Music music);

    /**
     * 查询所有音乐
     * @return list
     */
    List<Music> findAllMusic();

    /**
     * 通过音乐名查找音乐
     * @param name 音乐名
     * @return music
     */
    Music findMusicByName(String name);
}
