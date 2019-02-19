package com.bittech.musicmanager.mapper;

import com.bittech.musicmanager.entity.Music;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MusicMapper {
    void addMusic(Music music);
    List<Music> findAllMusic();
    Music findMusicByName(String name);
}
