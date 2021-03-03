package com.capstone2.funiturear;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class ViewAR extends AppCompatActivity {
    private static final String TAG = "ARactivity";
    private static final double MIN_OPENGL_VERSION = 3.0;
    private static String GLTF_ASSET;
    internetConnectivity it;
    private ModelRenderable modelRenderable;
    private ArFragment arFragment;
    DatabaseReference mDataItem;
    private String name;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_a_r);




        name=getIntent().getStringExtra("name");

        FirebaseApp.initializeApp(this);
        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference modelRef=storage.getReference().child("model").child(name+".glb");

       // mDataItem = FirebaseDatabase.getInstance().getReference("Item");




        ArFragment arFragment=(ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        findViewById(R.id.btnDownload).setOnClickListener(v -> {
            try{
                File file = File.createTempFile("tempt","glb");
                modelRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                        buildModel(file);

                    }
                });
            }catch(IOException e) {
                e.printStackTrace();
            }


        });
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            AnchorNode anchorNode= new AnchorNode(hitResult.createAnchor());
            anchorNode.setRenderable(renderable);
            arFragment.getArSceneView().getScene().addChild(anchorNode);
        });


    }
    private ModelRenderable renderable;

    private void buildModel(File file) {

        RenderableSource renderableSource=RenderableSource
                .builder()
                .setSource(this, Uri.parse(file.getPath()),RenderableSource.SourceType.GLB)
                .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                .build();

        ModelRenderable
                .builder()
                .setSource(this,renderableSource)
                .setRegistryId(file.getPath())
                .build()
                .thenAccept(modelRenderable -> {
                    Toast.makeText(this,"Đã tải xong mẫu, có thể view !",Toast.LENGTH_SHORT).show();
                    renderable=modelRenderable;
                });

    }


}