package com.jerry.fragmentanimation;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by test on 12/11/2015.
 */
public class MP3MusicFileFilter implements FileFilter {
    private final String[] musicExtension = new String[]{"mp3","ogg"};

    @Override
    public boolean accept(File file){
        for(String extension:musicExtension){
            if(file.getName().toLowerCase().endsWith(extension)){
                return true;
            }
        }
        return false;
    }
}
