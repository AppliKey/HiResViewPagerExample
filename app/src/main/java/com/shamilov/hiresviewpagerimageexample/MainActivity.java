package com.shamilov.hiresviewpagerimageexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private HiResPagerAdapter mPagerAdapter;
    private String imageFolderPath = Environment.getExternalStorageDirectory().getPath() + "/DCIM/folder/cats";
    public static final int REQUEST_PERMISSION_EXTERNAL_STORAGE = 228;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerAdapter = new HiResPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        getFiles();
    }

    private void getFiles() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestReadStoragePermission();
        } else {
            readFiles(imageFolderPath);
        }
    }

    private void readFiles(String imageFolderPath) {
        File directory = new File(imageFolderPath);
        List<File> images = new ArrayList<>();
        if (directory.exists() && directory.isDirectory()) {
            images.addAll(Arrays.asList(directory.listFiles()));
        }
        mPagerAdapter.setImages(images);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_EXTERNAL_STORAGE) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if ((permission.equalsIgnoreCase(Manifest.permission.READ_EXTERNAL_STORAGE)
                        && grantResult == PackageManager.PERMISSION_GRANTED)) {
                    readFiles(imageFolderPath);
                } else {
                    requestReadStoragePermission();
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestReadStoragePermission() {
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_EXTERNAL_STORAGE);
    }
}
