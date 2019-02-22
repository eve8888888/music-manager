package com.bittech.musicmanager.service.impl;

import com.bittech.musicmanager.service.IMusicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicServiceImplTest {
    @Resource
    private IMusicService ims;
    @Test
    public void findAllMusic() {
        System.out.println(ims.findAllMusic());
    }
    @Test
    public void findMusicByName(){
        System.out.println(ims.findMusicByName("一丝不挂.mp3"));
    }
    @Test
    public void addMusic(){
        ims.addMusic();
    }

    @Test
    public void clearFile(){
        ims.clearMusic();
    }
}