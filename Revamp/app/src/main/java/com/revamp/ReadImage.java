package com.revamp;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadImage extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;
    Button read;
    TextView output;
    ArrayList<String> demo = new ArrayList<>();
    StringBuilder text;
    ReadImageAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        output = findViewById(R.id.output);
        read = findViewById(R.id.read);
        readFile();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        // ReadImageAdapter customAdapter = new ReadImageAdapter(ReadImage.this, text);

        customAdapter = new ReadImageAdapter(ReadImage.this, demo);
        //demo.clear();
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(ReadImage.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(ReadImage.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(ReadImage.this, "Write External Storage permission allows us to read files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(ReadImage.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

    public void readFile() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkPermission()) {
                    String folder_main = "Revamp";
                    File dir = new File(Environment.getExternalStorageDirectory(), folder_main);

                    if (dir.exists()) {
                        File file = new File(dir, "sample.txt");

                        FileOutputStream os = null;
                        // StringBuilder text = new StringBuilder();

                        try {
                            BufferedReader br = new BufferedReader(new FileReader(file));
                            String line;

                            line = br.readLine();

                            String[] split = line.split(",");

                            for (int index = 0; index < split.length; index++) {
                                demo.add(split[index]);
                                Log.d("line1111", "line1111 " + split[index]);
                                Log.d("lineSize", "lineSize " + demo.size());
                            }
                            br.close();
                        } catch (IOException e) {
                            //You'll need to add proper error handling here
                        }
                    }
                } else {
                    requestPermission(); // Code for permission
                }
            } else {

                String folder_main = "Revamp";
                File dir = new File(Environment.getExternalStorageDirectory(), folder_main);
                if (dir.exists()) {
                    File file = new File(dir, "sample.txt");

                    FileOutputStream os = null;

                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;
                        line = br.readLine();
                        for (int index = 0; index < line.length(); index++) {
                            demo.add(line);
                        }
                        br.close();
                    } catch (IOException e) {
                        //You'll need to add proper error handling here
                    }
                }
            }
        }
    }
}