package patrick.com.aadharcardportfolio;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import patrick.com.aadharcardportfolio.db.AppDatabase;
import patrick.com.aadharcardportfolio.db.User;

/**
 * Created by rana on 6/2/18.
 */

public class ListAll extends Fragment{

    private RecyclerView mRecyclerView;
    ListAdapter mAdapter;
    FloatingActionButton add;

    @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_all, container, false);

        mRecyclerView = rootView.findViewById(R.id.RecyclerView);

        AppDatabase db= Room.databaseBuilder(this.getContext(),AppDatabase.class,"aadhar")
                .allowMainThreadQueries()
                .build();

        final List<User> users= db.UserModel().loadAllUsers();

        mAdapter = new ListAdapter(users);

        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),LinearLayoutManager.VERTICAL);
         mRecyclerView.addItemDecoration(mDividerItemDecoration);
         mRecyclerView.setItemAnimator(new DefaultItemAnimator());
         mRecyclerView.setAdapter(mAdapter);

        final ViewPager viewPager = getActivity().findViewById(R.id.container);

        add=rootView.findViewById(R.id.add);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    viewPager.setCurrentItem(1);
                }
            });
            return rootView;
    }
}
