package cn.edu.gdmec.android.mobileguard.m6cleancache;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Chino-Lee on 2017/11/20.
 */

public class CleanCacheActivity extends AppCompatActivity  implements View.OnClickListener {
    protected static final int CLEANNING = 100;
    protected static final int CLEAN_FINISH = 10;
    private AnimationDrawable animation;
    private long cacheMemory;
    private TextView mMemoryTV;
    private TextView mMemoryUnitTV;
    private PackageManager pm;
    private FrameLayout mCleanCacheFL;
    private FrameLayout mFinishCleanFL;
    private TextView mSizeTV;

    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case CLEANNING:
                    long memory = (Long) msg.obj;
                    formatMemory(memory);
                    if(memory==cacheMemory){
                        animation.stop();
                        mCleanCacheFL.setVisibility(View.GONE);
                        mFinishCleanFL.setVisibility(View.VISIBLE);
                        mSizeTV.setText("成功清理："+
                                Formatter.formatFileSize(CleanCacheActivity.this,cacheMemory)
                        );
                    }
                    break;

            }
        };
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_cache);
        initView();
        pm = getPackageManager();
        Intent intent = getIntent();
        cacheMemory = intent.getLongExtra("cacheMemory",0);
        initData();

    }

    private void initData() {
        cleanAll()；
        new Thread() {
            public void run() {
                long memory = 0;
                while(memory < cacheMemory){
                    try{
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Random rand = new Random();
                    int i = rand.nextInt();
                    i = rand.nextInt(1024);
                    memory +=1024*i;
                    if(memory > cacheMemory) {
                        memory = cacheMemory;
                    }
                    Message message = Message.obtain();
                    message.what = CLEANNING;
                    message.obj = memory;
                    mHandler.sendMessageDelayed(message,200);

                }
            };
        }.start();
    }


    public void onClick(DialogInterface dialogInterface, int i) {

    }

    @Override
    public void onClick(View view) {

    }
}
