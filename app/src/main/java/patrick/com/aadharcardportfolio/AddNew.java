package patrick.com.aadharcardportfolio;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import patrick.com.aadharcardportfolio.db.AppDatabase;
import patrick.com.aadharcardportfolio.db.User;

public class AddNew extends Fragment {

    private ListAdapter mAdapter;
    private String userChoosenTask;
    private List<User> users;
    Button upload_profile, upload_aadhar;
    FloatingActionButton submit;
    EditText name, dob, postal, aadhar_no;
    ImageView img1, img2;
    private int camera = 0, SELECT_FILE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_new, container, false);
        name = rootView.findViewById(R.id.name);
        dob = rootView.findViewById(R.id.dob);
        submit = rootView.findViewById(R.id.submit);
        postal = rootView.findViewById(R.id.address);
        img1 = rootView.findViewById(R.id.img1);
        img2 = rootView.findViewById(R.id.img2);
        aadhar_no = rootView.findViewById(R.id.adhar_no);
        upload_profile = rootView.findViewById(R.id.profile);
        upload_aadhar = rootView.findViewById(R.id.aadhar);
        final ViewPager viewPager = getActivity().findViewById(R.id.container);
        upload_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        upload_aadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SELECT_FILE=3;
                camera=2;
                selectImage();
            }
        });
        final AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "aadhar").allowMainThreadQueries().build();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name!=null&&dob!=null&&postal!=null&&aadhar_no!=null&&img1.getDrawable()!=null&&img2.getDrawable()!=null) {
                    String time = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss").format(new Date());
                    User user = new User(aadhar_no.getText().toString(), name.getText().toString(), dob.getText().toString(), postal.getText().toString(), time, imageViewToByte(img1), imageViewToByte(img2));
                    db.UserModel().insertUser(user);
                    name.setText(null);
                    dob.setText(null);
                    postal.setText(null);
                    aadhar_no.setText(null);
                    img1.setImageResource(0);
                    img2.setImageResource(0);
                    viewPager.setCurrentItem(0);
                }
                else
                {
                    Toast toast=Toast.makeText(getContext(),"All Fields are Mandatory",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        return rootView;
    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Gallery",
                "Cancel" };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this.getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(getContext());

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Gallery")) {
                    userChoosenTask ="Choose from Gallery";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select Image"),SELECT_FILE);
    }

    public void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, camera);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1)
                img1.setImageBitmap(onSelectFromGalleryResult(data));
            else if (requestCode == 0)
                img1.setImageBitmap(onCaptureImageResult(data));
            else if (requestCode == 2)
                img2.setImageBitmap(onCaptureImageResult(data));
            else if(requestCode == 3)
                img2.setImageBitmap(onSelectFromGalleryResult(data));
        }
    }

    private Bitmap onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        return thumbnail;
    }

    private Bitmap onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bm;
    }
}