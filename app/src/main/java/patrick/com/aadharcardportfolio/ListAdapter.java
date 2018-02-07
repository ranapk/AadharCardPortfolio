package patrick.com.aadharcardportfolio;

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

/**
 * Created by rana on 6/2/18.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<User> users;

    public ListAdapter(List<User> users) {
        this.users=users;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        byte[] bm=users.get(position).getImg2();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bm, 0, bm.length);
        holder.img.setImageBitmap(bitmap);
        holder.Name.setText(users.get(position).getName());
        holder.Time_stamp.setText((CharSequence) users.get(position).getCreateDate());
    }


    @Override
    public int getItemCount() {
        if (users != null) {
            return users.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView Name,Time_stamp;
        public ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img3);
            Name=itemView.findViewById(R.id.name1);
            Time_stamp=itemView.findViewById(R.id.stamp);

        }
    }
}
