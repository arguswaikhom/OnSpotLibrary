package com.crown.library.onspotlibrary.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.crown.library.onspotlibrary.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class CreateProfileImage extends AsyncTask<String, Void, Bitmap> {
    private final String TAG = CreateProfileImage.class.getName();
    private final WeakReference<Context> context;
    private String userId;

    public CreateProfileImage(Context context) {
        this.context = new WeakReference<>(context);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        userId = strings[0];
        URL url = stringToURL(strings[1]);
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            return BitmapFactory.decodeStream(bufferedInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.disconnect();
        }
        return null;
    }

    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            File file = saveImageToInternalStorage(result);
            updateUserProfile(Uri.fromFile(file));
        } else {
            Log.d(TAG, "Failed on onPostExecute");
        }
    }

    private void updateUserProfile(Uri uri) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("user-profile");
        final StorageReference imgStrRef = storageReference.child(userId);
        UploadTask uploadTask = imgStrRef.putFile(uri);
        uploadTask.continueWithTask((Task<UploadTask.TaskSnapshot> task) -> {
            if (!task.isSuccessful()) {
                throw task.getException();
            }
            return imgStrRef.getDownloadUrl();
        }).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Uri downloadUri = task.getResult();
                if (downloadUri == null) return;
                Log.d(TAG, "Uploaded: " + downloadUri);

                updateUser(downloadUri.toString());
            } else {
                Log.v(TAG, task.getException().getMessage());
                Log.d(TAG, "Upload failed: " + task.getException().getMessage());
            }
        });
    }

    private void updateUser(String url) {
        FirebaseFirestore.getInstance().collection(context.get().getString(R.string.ref_user))
                .document(userId).update("profileImageUrl", url);
    }

    private File saveImageToInternalStorage(Bitmap bitmap) {
        ContextWrapper wrapper = new ContextWrapper(context.get());
        File file = wrapper.getDir("Images", MODE_PRIVATE);
        file = new File(file, new Date().getTime() + ".jpg");

        try {
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private URL stringToURL(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
