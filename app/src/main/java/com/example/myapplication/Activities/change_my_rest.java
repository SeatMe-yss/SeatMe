package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.DB.DB_model;
import com.example.myapplication.R;
import com.example.myapplication.data.LoginRepository;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import androidx.appcompat.widget.Toolbar;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;





public class change_my_rest extends AppCompatActivity {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int GALLERY_REQUEST_CODE = 105;

    EditText rest_name;
    EditText num_people;
    Button save;
    Button change_menu_gal;
    FirebaseAuth fAuth;
    int max_people;
    ImageView menu_Image;
    Button take_photo;
    String currentPhotoPath;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_my_rest);
        //view by id
        rest_name = findViewById(R.id.rest_name2);
        num_people = findViewById(R.id.num_people);
        save = findViewById(R.id.save);
        change_menu_gal = findViewById(R.id.change_menu_gal);
        menu_Image = findViewById(R.id.imagemenu);
        take_photo = findViewById(R.id.take_photo);
        Toolbar toolbar= findViewById(R.id.menu_bar);
        setSupportActionBar(toolbar);


        //firebase
        fAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r = rest_name.getText().toString();
                String u = fAuth.getUid();
                //update the rest name
                Map<String, Object> map = new HashMap<>();
                map.put("name_rest", r);
                DB_model.get_DB().getRef().child("Business").child(u).updateChildren(map);

                //if the num we get is vaild
                String n = num_people.getText().toString();
                try {
                    max_people = Integer.parseInt(n);
                } catch (Exception e) {
                    num_people.setError("הכנס מספר תקין");
                }
                //update num people
                Map<String, Integer> map1 = new HashMap<>();
                map.put("max_people", max_people);
                DB_model.get_DB().getRef().child("Business").child(u).updateChildren(map);
                //pass to the screen before
                startActivity(new Intent(getApplicationContext(), activity_rest.class));
                //upload to firestore

            }
        });

        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("askCamera");
                askCameraPermissions();

            }
        });

        change_menu_gal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, GALLERY_REQUEST_CODE);
            }
        });

    }

    // create the 3 dots at login_register activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // click on some item un the menu bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, login_register.class);
                startActivity(intent);
                finish();
                break;

            case R.id.action_myOrders:
                Intent intent1 = new Intent(this, rest_diary.class);
                startActivity(intent1);
                break;


            case R.id.action_backToMain:
                Intent intent2 = new Intent(this, activity_rest.class);
                startActivity(intent2);
                break;

            default:
        }

        return super.onOptionsItemSelected(item);
    }

    private void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        }else {
//            openCamera();
//            System.out.println("dispatchTakePictureIntent");
            dispatchTakePictureIntent();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAMERA_PERM_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                dispatchTakePictureIntent();
            }else {
                Toast.makeText(this, "Camera Permission is Required to Use camera.", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    private void openCamera(){
//       Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//       startActivityForResult(camera, CAMERA_REQUEST_CODE);
//    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                File f = new File(currentPhotoPath);
                menu_Image.setImageURI(Uri.fromFile(f));

                Log.d("tag", "ABsolute Url of Image is " + Uri.fromFile(f));

//                System.out.println("id :"+fAuth.getUid());
                //save the string in real db menu
                System.out.println("Menu_url:"+Uri.fromFile(f).toString());
                DB_model.get_DB().getRef().child("Business").child(fAuth.getUid()).child("Menu_url").setValue(Uri.fromFile(f).toString());




                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

                uploadImageToFirebase(f.getName(), contentUri);


            }

        }

        if (requestCode == GALLERY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contentUri = data.getData();
                String imageFileName = fAuth.getUid();
                Log.d("tag", "onActivityResult: Gallery Image Uri:  " + imageFileName);
                menu_Image.setImageURI(contentUri);
                System.out.println("imageFileName:"+imageFileName);
                uploadImageToFirebase(imageFileName, contentUri);


            }

        }


    }

    private void uploadImageToFirebase(String name, Uri contentUri) {
        System.out.println("name:"+name);
        final StorageReference image = storageReference.child("pictures/" + name);
        image.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        DB_model.get_DB().getRef().child("Business").child(fAuth.getUid()).child("Menu_url").setValue(uri.toString());

                        Log.d("tag", "onSuccess: Uploaded Image URl is " + uri.toString());
                    }
                });

                Toast.makeText(change_my_rest.this, "Image Is Uploaded.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(change_my_rest.this, "Upload Failled.", Toast.LENGTH_SHORT).show();
            }
        });

    }


//
//    private String getFileExt(Uri contentUri) {
//        ContentResolver c = getContentResolver();
//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        return mime.getExtensionFromMimeType(c.getType(contentUri));
//    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String imageFileName = fAuth.getUid();
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent

//        if (takePictureIntent.resolveActivity(getPackageManager()) == null) {
        // Create the File where the photo should go
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex) {

        }
        // Continue only if the File was successfully created
        if (photoFile != null) {
            Uri photoURI = FileProvider.getUriForFile(this,
                    "com.example.android.fileprovider",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
        }
        else{

        }
    }
}
//}