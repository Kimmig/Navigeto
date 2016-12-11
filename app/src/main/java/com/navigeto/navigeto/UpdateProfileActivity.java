package com.navigeto.navigeto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.navigeto.navigeto.MainActivity.AllWayPoints;
import static com.navigeto.navigeto.MainActivity.CurrentTripID;
import static com.navigeto.navigeto.MainActivity.DataLoadOnStart;
import static com.navigeto.navigeto.MainActivity.MyUID;
import static com.navigeto.navigeto.MainActivity.Name;
import static com.navigeto.navigeto.MainActivity.TripID;
import static com.navigeto.navigeto.MainActivity.UsersOnTrip;
import static com.navigeto.navigeto.MainActivity.editSharedpreferences;
import static com.navigeto.navigeto.MainActivity.loggedIn;
import static com.navigeto.navigeto.MainActivity.sharedpreferences;
import static java.lang.Thread.sleep;

public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaring views

    private Button buttonUpload;
    private android.support.v7.widget.AppCompatEditText editTextName;
    String nameofpic;
    private AwesomeValidation awesomeValidation;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;

    ImageButton imageButton;

    //Persist URI image to crop URI if specific permissions are required
    private Uri mCropImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        //Requesting storage permission
        requestStoragePermission();

        //Initializing views
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        editTextName = (AppCompatEditText) findViewById(R.id.editTextName);
        buttonUpload.setOnClickListener(this);

        //Setting clicklistener
        imageButton = (ImageButton) findViewById(R.id.quick_start_cropped_image);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectImageClick(v);
            }
        });
    }

    /*
    * This is the method responsible for image upload
    * We need the full image path and the name for the image in this method
    * */
    public void uploadMultipart() {
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.editTextName, "^[a-z0-9_-]{3,10}$", R.string.nameerror);

        if (awesomeValidation.validate()) {
            String MyUserName = editTextName.getText().toString().trim();
            //getting the actual path of the image
            String path = FilePath.getPath(this, mCropImageUri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mCropImageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Uploading code
            String uploadImage = getStringImage(Bitmap.createScaledBitmap(bitmap, 100, 100, true));

            editSharedpreferences = sharedpreferences.edit();
            editSharedpreferences.putString(Name, MyUserName);
            editSharedpreferences.apply();

            try {
                //Creating a multi part request
                new MultipartUploadRequest(this, nameofpic, Config.UPLOAD_URL)
                        .addFileToUpload(path, "image") //Adding file
                        .addParameter("name", MyUserName) //Adding text parameter to the request
                        .addParameter("User_ID", String.valueOf(MyUID)) //Adding UID to the request
                        .addParameter("ImageBlob", uploadImage) //Adding compressed image to the request

                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
                initiateApp();
            } catch (Exception exc) {
                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    //handling the image activity result

    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // handle result of pick image chooser
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);

            // For API >= 23 we need to check specifically that we have permissions to read external storage.
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                mCropImageUri = imageUri;
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            } else {
                // no permissions required or already grunted, can start crop image activity
                startCropImageActivity(imageUri);
                mCropImageUri = imageUri;
            }
        }

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                ((ImageButton) findViewById(R.id.quick_start_cropped_image)).setImageURI(result.getUri());
                Toast.makeText(this, "Cropping successful", Toast.LENGTH_LONG).show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onSelectImageClick(View view) {
        CropImage.startPickImageActivity(this);
    }

    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }

    private void initiateApp() {
        if (loggedIn == true) {
            DataLoadOnStart();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            AllWayPoints();
            //UsersOnTrip();

            for (int i = 0; i < MainActivity.masterTable.getMasterTable().size(); i++) {

                String TripStage = MainActivity.masterTable.getMasterTable().get(i).getTripStage();
                if (TripStage.equalsIgnoreCase("Active")) {
                    if (CurrentTripID == 0) {
                        CurrentTripID = MainActivity.masterTable.getMasterTable().get(i).getTripID();
                        //Save Active Trip in Shared Preferences
                        editSharedpreferences = sharedpreferences.edit();
                        editSharedpreferences.putLong(TripID, CurrentTripID);
                        editSharedpreferences.apply();
                    }
                    Intent intent = new Intent(UpdateProfileActivity.this, MapsActivity.class);
                    startActivity(intent);
                    break;
                } else {
                    if (CurrentTripID != 0) {
                        String nameofTrip = MainActivity.masterTable.getMasterTable().get(i).getTripName();
                        Toast.makeText(this, "Trip " + nameofTrip + " is over", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(UpdateProfileActivity.this, TripInitActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        } else {
            Intent intent = new Intent(UpdateProfileActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == buttonUpload && mCropImageUri != null) {
               uploadMultipart();
        } else {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
        }
    }
}