package com.emrhmrc.isttabletcrm.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.helper.AddNotes;
import com.emrhmrc.isttabletcrm.models.ServApp.Notes;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.emrhmrc.isttabletcrm.helper.Methodes.checkAndRequestPermissions;

public class BeforeAfterPicFragment extends DialogFragment implements View.OnClickListener {

    private static final int REQUEST_VIDEO_CAPTURE = 3;
    private static final int REQUEST_VIDEO_CAPTURE_SECOND = 4;
    private static final String TAG = "BeforeAfterPicFragment";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_CAPTURE_SECOND = 2;
    private ImageView img_close, img_first, img_second, img_add_pic_first, img_add_pic_second,
            img_add_video, img_add_video_second;
    private VideoView video_first, video_second;
    private Notes notePhoto1, notePhoto2, noteVideo1, noteVideo2;
    private Button btn_add_1, btn_add_2, btn_send;
    private EditText edt_1, edt_2;
    private AddNotes addNotes;
    private boolean isImage1 = false, isImage2 = false;
    private List<Notes> haveNotes;

    public static BeforeAfterPicFragment newInstance(List<Notes> haveNote) {
        Bundle args = new Bundle();
        args.putSerializable("notes", (Serializable) haveNote);
        BeforeAfterPicFragment fragment = new BeforeAfterPicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_before_after_pic, container);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img_close = view.findViewById(R.id.img_close);
        img_first = view.findViewById(R.id.img_first);
        img_second = view.findViewById(R.id.img_second);
        img_add_pic_first = view.findViewById(R.id.img_add_pic_first);
        img_add_pic_second = view.findViewById(R.id.img_add_pic_second);
        video_first = view.findViewById(R.id.video_first);
        video_second = view.findViewById(R.id.video_second);
        img_add_video = view.findViewById(R.id.img_add_video);
        img_add_video_second = view.findViewById(R.id.img_add_video_second);
        btn_send = view.findViewById(R.id.btn_send);
        btn_add_1 = view.findViewById(R.id.btn_add_1);
        btn_add_2 = view.findViewById(R.id.btn_add_2);
        edt_1 = view.findViewById(R.id.edt_1);
        edt_2 = view.findViewById(R.id.edt_2);

        btn_send.setOnClickListener(this);
        btn_add_1.setOnClickListener(this);
        btn_add_2.setOnClickListener(this);
        img_close.setOnClickListener(this);
        img_add_pic_second.setOnClickListener(this);
        img_add_pic_first.setOnClickListener(this);
        img_add_video.setOnClickListener(this);
        img_add_video_second.setOnClickListener(this);
        video_first.setOnClickListener(this);
        video_second.setOnClickListener(this);
        addNotes = (AddNotes) getActivity();
        haveNotes = (List<Notes>) getArguments().getSerializable("notes");
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);

        initFields();

    }

    private void initFields() {
        if (haveNotes.size() == 0) {

            haveNotes.add(0, new Notes());
            haveNotes.add(1, new Notes());

        }

        if (haveNotes.get(0) != null && haveNotes.get(0).getFrom() == 1) {
            if (haveNotes.get(0).isImage1() && haveNotes.get(0).getSelectedImageUri() != null) {
                img_first.setImageURI(haveNotes.get(0).getSelectedImageUri());
                video_first.setVisibility(View.GONE);
                img_first.setVisibility(View.VISIBLE);
            } else if(haveNotes.get(0).getSelectedVideoUri() != null){
                video_first.setVideoURI(haveNotes.get(0).getSelectedVideoUri());
                video_first.setVisibility(View.VISIBLE);
                img_first.setVisibility(View.GONE);

            }
            edt_1.setText(haveNotes.get(0).getNoteText());

        }
        if (haveNotes.get(1) != null && haveNotes.get(1).getFrom() == 2) {
            if (haveNotes.get(1).isImage1()&&haveNotes.get(1).getSelectedImageUri() != null) {
                img_second.setImageURI(haveNotes.get(1).getSelectedImageUri());
                video_second.setVisibility(View.GONE);
                img_second.setVisibility(View.VISIBLE);

            } else if(haveNotes.get(1).getSelectedVideoUri() != null) {
                video_second.setVideoURI(haveNotes.get(1).getSelectedVideoUri());
                img_second.setVisibility(View.GONE);
                video_second.setVisibility(View.VISIBLE);

            }
            edt_2.setText(haveNotes.get(1).getNoteText());

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = 9 * width / 10;
        params.height = 9 * height / 10;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.img_close:
                this.dismiss();
                break;
            case R.id.img_add_pic_first:
                dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE);
                break;
            case R.id.img_add_pic_second:
                dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_SECOND);
                break;
            case R.id.img_add_video:
                dispatchTakeVideoIntent(REQUEST_VIDEO_CAPTURE);
                break;
            case R.id.img_add_video_second:
                dispatchTakeVideoIntent(REQUEST_VIDEO_CAPTURE_SECOND);
                break;
            case R.id.video_first:
                video_first.start();
                break;
            case R.id.video_second:
                video_second.start();
                break;
            case R.id.btn_add_1:
                edtfill(1);
                break;
            case R.id.btn_add_2:
                edtfill(2);
                break;

            case R.id.btn_send:
                sendToRequest();
                break;

        }

    }

    private void sendToRequest() {

        haveNotes.get(0).setNoteText(edt_1.getText().toString());
        haveNotes.get(0).setFrom(1);
        haveNotes.get(1).setNoteText(edt_2.getText().toString());
        haveNotes.get(1).setFrom(2);
        addNotes.addNote(haveNotes);
        dismiss();

    }

    private void edtfill(int i) {
        if (i == 1) {
            if (notePhoto1 != null) notePhoto1.setSubject(edt_1.getText().toString());
            else if (noteVideo1 != null) noteVideo1.setSubject(edt_1.getText().toString());
        } else if (i == 2) {
            if (notePhoto2 != null) notePhoto2.setSubject(edt_2.getText().toString());
            else if (noteVideo2 != null) noteVideo2.setSubject(edt_2.getText().toString());
        }
    }

    private void dispatchTakePictureIntent(int i) {
        if (checkAndRequestPermissions(getActivity())) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       /* if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, i);
        }*/
            this.startActivityForResult(takePictureIntent, i);
        }
    }

    private void dispatchTakeVideoIntent(int i) {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
            startActivityForResult(takeVideoIntent, i);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (notePhoto1 == null) {
                notePhoto1 = new Notes();
                noteVideo1 = null;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            img_first.setImageBitmap(photo);
            video_first.setVisibility(View.GONE);
            video_first.setVideoURI(null);
            img_first.setVisibility(View.VISIBLE);


            Uri selectedImage = getImageUri(getActivity(), photo);
            String realPath = getRealPathFromURI(selectedImage);
            selectedImage = Uri.parse(realPath);
            /*ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();*/


            Bitmap bitmap = BitmapFactory.decodeFile(realPath, options);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            notePhoto1.setSelectedImageUri(selectedImage);
            notePhoto1.setDocumentBody(Base64.encodeToString(byteArray, Base64.DEFAULT));
            notePhoto1.setDocument(true);
            notePhoto1.setFileName(realPath);
            notePhoto1.setMimeType("image/jpg");
            notePhoto1.setSubject("Önceki Görünüm");
            isImage1 = true;
            notePhoto1.setFrom(1);
            notePhoto1.setImage1(true);
            haveNotes.set(0, notePhoto1);


        } else if (requestCode == REQUEST_IMAGE_CAPTURE_SECOND && resultCode == RESULT_OK) {
            if (notePhoto2 == null) {
                notePhoto2 = new Notes();
                noteVideo2 = null;
            }
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            img_second.setImageBitmap(photo);
            video_second.setVisibility(View.GONE);
            video_second.setVideoURI(null);
            img_second.setVisibility(View.VISIBLE);

            Uri selectedImage = getImageUri(getActivity(), photo);
            String realPath = getRealPathFromURI(selectedImage);
            selectedImage = Uri.parse(realPath);

          /*  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();*/
            Bitmap bitmap = BitmapFactory.decodeFile(realPath, options);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            notePhoto2.setSelectedImageUri(selectedImage);
            notePhoto2.setDocumentBody(Base64.encodeToString(byteArray, Base64.DEFAULT));
            notePhoto2.setDocument(true);
            notePhoto2.setFileName(realPath);
            notePhoto2.setMimeType("image/jpg");
            notePhoto2.setSubject("Sonraki Görünüm");
            isImage2 = true;
            notePhoto2.setFrom(2);
            notePhoto2.setImage1(true);
            haveNotes.set(1, notePhoto2);
        } else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri selectedVideoUri = data.getData();
            video_first.setVideoURI(selectedVideoUri);
            img_first.setVisibility(View.GONE);
            img_first.setImageBitmap(null);
            video_first.setVisibility(View.VISIBLE);

            String[] projection = {MediaStore.Video.Media.DATA, MediaStore.Video.Media.SIZE, MediaStore.Video.Media.DURATION};
            Cursor cursor = getActivity().managedQuery(selectedVideoUri, projection, null, null,
                    null);

            cursor.moveToFirst();
            String filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
            Log.d("File Name:", filePath);

            Bitmap thumb = ThumbnailUtils.createVideoThumbnail(filePath, MediaStore.Video.Thumbnails.MINI_KIND);
            // Setting the thumbnail of the video in to the image view
            // msImage.setImageBitmap(thumb);
            InputStream inputStream = null;
// Converting the video in to the bytes
            try {
                inputStream = getActivity().getContentResolver().openInputStream(selectedVideoUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            ByteArrayOutputStream byteBuffer;
            byteBuffer = new ByteArrayOutputStream();
            int len = 0;
            try {
                while ((len = inputStream.read(buffer)) != -1) {
                    byteBuffer.write(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("converted!");

            String videoData = "";
            //Converting bytes into base64
            videoData = Base64.encodeToString(byteBuffer.toByteArray(), Base64.DEFAULT);
            // Log.d("VideoData**>  ", videoData);
            String sinSaltoFinal2 = videoData.trim();
            String sinsinSalto2 = sinSaltoFinal2.replaceAll("\n", "");
            //Log.d("VideoData**>  ", sinsinSalto2);
            String realPath = getRealPathFromURI(selectedVideoUri);
            if (noteVideo1 == null) {
                noteVideo1 = new Notes();
                notePhoto1 = null;
            }
            noteVideo1.setDocumentBody(Base64.encodeToString(byteBuffer.toByteArray(), Base64.DEFAULT));
            noteVideo1.setDocument(true);
            noteVideo1.setFileName(realPath);
            noteVideo1.setMimeType("video/mp4");
            noteVideo1.setSubject("Önceki Görünüm Video");
            noteVideo1.setSelectedVideoUri(selectedVideoUri);
            isImage1 = false;
            noteVideo1.setFrom(1);
            noteVideo1.setImage1(false);
            haveNotes.set(0, noteVideo1);

        } else if (requestCode == REQUEST_VIDEO_CAPTURE_SECOND && resultCode == RESULT_OK) {
            Uri selectedVideoUri = data.getData();
            video_second.setVideoURI(selectedVideoUri);
            img_second.setVisibility(View.GONE);
            img_second.setImageBitmap(null);
            video_second.setVisibility(View.VISIBLE);
            String[] projection = {MediaStore.Video.Media.DATA, MediaStore.Video.Media.SIZE, MediaStore.Video.Media.DURATION};
            Cursor cursor = getActivity().managedQuery(selectedVideoUri, projection, null, null,
                    null);

            cursor.moveToFirst();
            String filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
            Log.d("File Name:", filePath);

            Bitmap thumb = ThumbnailUtils.createVideoThumbnail(filePath, MediaStore.Video.Thumbnails.MINI_KIND);
            // Setting the thumbnail of the video in to the image view
            // msImage.setImageBitmap(thumb);
            InputStream inputStream = null;
// Converting the video in to the bytes
            try {
                inputStream = getActivity().getContentResolver().openInputStream(selectedVideoUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            ByteArrayOutputStream byteBuffer;
            byteBuffer = new ByteArrayOutputStream();
            int len = 0;
            try {
                while ((len = inputStream.read(buffer)) != -1) {
                    byteBuffer.write(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("converted!");

            String videoData = "";
            //Converting bytes into base64
            videoData = Base64.encodeToString(byteBuffer.toByteArray(), Base64.DEFAULT);
            //  Log.d("VideoData**>  ", videoData);
            String sinSaltoFinal2 = videoData.trim();
            String sinsinSalto2 = sinSaltoFinal2.replaceAll("\n", "");
            //   Log.d("VideoData**>  ", sinsinSalto2);
            String realPath = getRealPathFromURI(selectedVideoUri);
            if (noteVideo2 == null) {
                noteVideo2 = new Notes();
                notePhoto2 = null;
            }
            noteVideo2.setDocumentBody(Base64.encodeToString(byteBuffer.toByteArray(), Base64.DEFAULT));
            noteVideo2.setDocument(true);
            noteVideo2.setFileName(realPath);
            noteVideo2.setMimeType("video/mp4");
            noteVideo2.setSubject("Sonraki Görünüm Video");
            noteVideo2.setSelectedVideoUri(selectedVideoUri);
            isImage2 = false;
            noteVideo2.setFrom(2);
            noteVideo2.setImage1(false);
            haveNotes.set(1, noteVideo2);
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
