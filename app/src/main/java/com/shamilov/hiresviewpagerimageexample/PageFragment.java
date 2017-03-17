package com.shamilov.hiresviewpagerimageexample;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.File;

public class PageFragment extends Fragment {

    public static final String KEY_FILE = "key-filename";

    private SubsamplingScaleImageView mImage;

    public static PageFragment newInstance(File file) {

        Bundle args = new Bundle();

        PageFragment fragment = new PageFragment();
        args.putSerializable(KEY_FILE, file);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        mImage = (SubsamplingScaleImageView) view.findViewById(R.id.subsampling_image);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        File file = (File) getArguments().getSerializable(KEY_FILE);
        mImage.setImage(ImageSource.uri(Uri.fromFile(file)));
    }

}
