package patrick.com.aadharcardportfolio;

/**
 * Created by rana on 7/2/18.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import patrick.com.aadharcardportfolio.db.User;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private List<User> users;


    public GalleryAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_row,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        byte[] bm=users.get(position).getImg1();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bm, 0, bm.length);
        holder.img.setImageBitmap(bitmap);
        holder.tv.setText(users.get(position).getName());
    }


    @Override
    public int getItemCount() {
        if (users != null) {
            return users.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        private ImageView img;
        public ViewHolder(View view) {
            super(view);
            tv = view.findViewById(R.id.tv);
            img = view.findViewById(R.id.img);
        }
    }

}