package com.example.chetanrajjain.filemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by chetanrajjain on 2/13/18.
 */

public class FileArrayAdapter extends ArrayAdapter<File> {
    private Context mContext;
    private int mresource;
    private List<File> mObjects;

    public FileArrayAdapter(Context c,int mresource,List<File> o){
        super(c,mresource,o);
        mContext = c;
        this.mresource = mresource;
        this.mObjects = o;

    }

    public FileArrayAdapter(Context c,int res){
        super(c,res);
        mContext = c;
        mresource = res;

    }

    public File getItem(int i){

       return  mObjects.get(i);
    }

    public View  getView(int position, View convertView, ViewGroup parentview){

        View v = convertView;

        if(v== null){

            LayoutInflater inflater = (LayoutInflater.from(mContext));
            v = inflater.inflate(mresource,null);


        }

        ImageView iv = (ImageView) v.findViewById(R.id.imageview);
        TextView  tv_name = (TextView) v.findViewById(R.id.name_textView);
        TextView tv_details = (TextView) v.findViewById(R.id.details_textview);


        File file = getItem(position);

        if(file.isDirectory()){

            iv.setImageResource(R.drawable.ic_launcher_foreground);

        }else{

            iv.setImageResource(R.drawable.ic_launcher_background);

                if(file.length() > 0){

                    tv_details.setText(String.valueOf(file));

                }

        }

        tv_name.setText(file.getName());


        return v;

    }









}
