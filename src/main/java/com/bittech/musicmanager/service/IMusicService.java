package com.bittech.musicmanager.service;

import com.bittech.musicmanager.entity.Music;

import java.util.List;


public interface IMusicService {
    List<Music> findAllMusic();
    void addMusic();
    Music findMusicByName(String name);
}
