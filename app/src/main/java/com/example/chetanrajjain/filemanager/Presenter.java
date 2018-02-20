package com.example.chetanrajjain.filemanager;

import android.app.LoaderManager;
import android.content.ActivityNotFoundException;
import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chetanrajjain on 2/17/18.
 */

public class Presenter  implements LoaderManager.LoaderCallbacks<List<File>>{
    private UIjava uIjava;
    private Model model;
    private FileArrayAdapter mfilearrayadapter;
    private List<File> data;
    private AsyncTaskLoader<List<File>> asyncTaskLoader;

    public Presenter(UIjava uIjava){

            this.uIjava = uIjava;
            model = new Model();
            data = new ArrayList<>();

            init();


    }

    private void init(){

        mfilearrayadapter =  new FileArrayAdapter(uIjava.getActivity(),R.layout.listview_row,data);
        uIjava.setListAdapter(mfilearrayadapter);

        uIjava.getActivity().getLoaderManager().initLoader(0,null,this);

        asyncTaskLoader.forceLoad();


    }


    private void updateadapter(List<File> data){

        mfilearrayadapter.clear();
        mfilearrayadapter.addAll(data);
        mfilearrayadapter.notifyDataSetChanged();


    }



    public void listItemClick(ListView listview,android.view.View view,int position,long id){

        File fileclicked = mfilearrayadapter.getItem(position);


        if(fileclicked.isDirectory()){


            model.setPreviousDir(model.getCurrentDir());

            model.setCurrentDir(fileclicked);

                if(asyncTaskLoader.isStarted()){


                    asyncTaskLoader.onContentChanged();

                }

        } else {

            openFile(Uri.fromFile(fileclicked));
        }

    }

    public void settings(){

        Toast.makeText(uIjava.getActivity(),"Settings Clicked",Toast.LENGTH_LONG).show();

    }

    private void openFile(Uri fileUri){

        String mimeType = model.getmimetype(fileUri);


        if(mimeType != null){

            try {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(fileUri, mimeType);

                uIjava.getActivity().startActivity(intent);

            }catch(ActivityNotFoundException e){

                Toast.makeText(uIjava.getActivity(),"The system Understands this file type, but not app installed",Toast.LENGTH_LONG).show();

            }

        } else{

            Toast.makeText(uIjava.getActivity(),"Unable to recognise file type",Toast.LENGTH_LONG).show();

        }



    }

    public void homePressed(){

        if(model.hasPrevDir()){

            model.setCurrentDir(model.getremovePreviousDir());

            asyncTaskLoader.onContentChanged();

        }


    }

    public Loader<List<File>> onCreateLoader(int i,Bundle args){

        asyncTaskLoader = new AsyncTaskLoader<List<File>>(uIjava.getActivity()) {
            @Override
            public List<File> loadInBackground() {

                return model.allDirFile(model.getCurrentDir());
            }


        };

        return asyncTaskLoader;

    }

    public void onLoadFinished(Loader<List<File>> loader,List<File> data){

        this.data = data;


        updateadapter(data);


    }

    public void onLoaderReset(Loader<List<File>> loader){



    }







}
