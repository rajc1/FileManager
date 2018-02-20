package com.example.chetanrajjain.filemanager;


import android.net.Uri;
import android.nfc.Tag;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.io.File;
import java.util.Stack;
/**
 * Created by chetanrajjain on 2/10/18.
 */

public class Model {
    private File currentDir;
    private File previousDir;
    private Stack<File>  navHistory;
    private static final String TAG = "Current Dir";

    public Model(){

        init();

    }

    public void init() {
        navHistory = new Stack<>();

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            currentDir = Environment.getExternalStorageDirectory();

            Log.i("TAG", String.valueOf(currentDir));

        } else {

            Log.i("TAG", "no directory found");

        }
    }

    public File getCurrentDir(){

        return currentDir;


    }

    public void setCurrentDir(File currentDir){

        this.currentDir = currentDir;

    }

    public boolean hasPrevDir(){

        return !navHistory.isEmpty();

    }

    public File getremovePreviousDir(){

        return navHistory.pop();

    }

    public void setPreviousDir(File previousDir){

        this.previousDir = previousDir;
        navHistory.add(previousDir);

    }

    public File getPreviousDir(){

        return previousDir;

    }

    public List<File> allDirFile (File f){
        File[] allDirfiles = f.listFiles();

        List<File>  files= new ArrayList<>();
        List<File>  Dir = new ArrayList<>();

        for (File file : allDirfiles){
            if(file.isDirectory()){
                Dir.add(file);
            }else{
                files.add(file);

            }


        }
        Collections.sort(Dir);
        Collections.sort(files);

        Dir.addAll(files);

        return Dir;
    }

    public String getmimetype(Uri uri){

        String mimetype = null;

        String extension = MimeTypeMap.getFileExtensionFromUrl(uri.getPath());

        if(MimeTypeMap.getSingleton().hasExtension(extension)){

            mimetype =  MimeTypeMap.getSingleton().getExtensionFromMimeType(extension);

        }

    return mimetype;

    }





    }


