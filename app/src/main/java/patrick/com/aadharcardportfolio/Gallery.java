package patrick.com.aadharcardportfolio;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import patrick.com.aadharcardportfolio.db.AppDatabase;
import patrick.com.aadharcardportfolio.db.User;

/**
 * Created by rana on 7/2/18.
 */

public class Gallery extends Fragment {

    private RecyclerView mRecyclerView;
    GalleryAdapter mGalleryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gallery, container, false);

        mRecyclerView = rootView.findViewById(R.id.card_recycler_view);

        AppDatabase db= Room.databaseBuilder(this.getContext(),AppDatabase.class,"aadhar")
                .allowMainThreadQueries()
                .build();

        final List<User> users= db.UserModel().loadAllUsers();

        mGalleryAdapter = new GalleryAdapter(users);

        RecyclerView.LayoutManager mLayoutManager=new GridLayoutManager(getContext(),2);

        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mGalleryAdapter);

        return rootView;
    }
}
