package com.example.chetanrajjain.filemanager;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by chetanrajjain on 2/17/18.
 */
    //filemanagerapp

public class UIjava extends ListFragment {
    private Presenter presenter;

    private void setPresenter(Presenter p){

        presenter = p;

        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.activity_main,container,false);
    }


    public void onActivityCreated(Bundle saveInstanceState){

        super.onActivityCreated(saveInstanceState);
        setPresenter(new Presenter(this));
    }

    public void onListItemClick(ListView listview,android.view.View view, int position, long id){

        super.onListItemClick(listview,view,position,id);
        presenter.listItemClick(listview,view,position,id);
    }

    public void onCreateOptionMenu(Menu menu, MenuInflater mInflater){

        super.onCreateOptionsMenu(menu, mInflater);
        mInflater.inflate(R.menu.menu_main,menu);
    }


}


