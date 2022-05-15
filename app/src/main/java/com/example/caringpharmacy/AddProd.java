package com.example.caringpharmacy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;

public class AddProd extends AppCompatActivity {

    ImageView img;
    Uri filepath;
    Bitmap bitmap;

    EditText pname, pprice, pimgurl;
    Button btn_add, choose, upload;
    DatabaseReference dbRef;
    Product prd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prod);

        img = findViewById(R.id.imageview_up);
        upload = findViewById(R.id.btn_upload);
        choose = findViewById(R.id.btn_choose);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(AddProd.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Please select Image"), 1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadtofirebase();
            }
        });

        pname = findViewById(R.id.upd_pname);
        pprice = findViewById(R.id.upd_pprice);
        pimgurl = findViewById(R.id.edit_url);

        btn_add = findViewById(R.id.btn_updsub);

        prd = new Product();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==RESULT_OK){
            filepath=data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            }catch (Exception ex){

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadtofirebase() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Image Uploader");
        dialog.show();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference().child("image1");
        uploader.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Image Uploaded", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        float percent = (100*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                        dialog.setMessage("Uploaded :" + (int)percent + " %");
                    }
                });
    }

    public void clearControls() {
        pname.setText("");
        pprice.setText("");
        pimgurl.setText("");
    }

    public void AddData(View view) {
        dbRef = FirebaseDatabase.getInstance().getReference().child("Product");

        if (TextUtils.isEmpty(pname.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter the product name", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pprice.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter the product price", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pimgurl.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter the Image url", Toast.LENGTH_SHORT).show();
        }
        else {
            prd.setPrname(pname.getText().toString().trim());
            prd.setPrprice(Integer.parseInt(pprice.getText().toString().trim()));
            prd.setPrimgurl(pimgurl.getText().toString().trim());

            dbRef.push().setValue(prd);

            Toast.makeText(getApplicationContext(), "Product added successfully", Toast.LENGTH_SHORT).show();
            clearControls();
        }

    }
}

