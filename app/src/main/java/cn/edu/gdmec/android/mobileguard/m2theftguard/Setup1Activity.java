package cn.edu.gdmec.android.mobileguard.m2theftguard;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import cn.edu.gdmec.android.mobileguard.R;


/**
 * Created by pc on 2017/10/11.
 */

public class Setup1Activity extends BaseSetUpActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_1);
        initView();
    }

    private void initView() {
        ((RadioButton)findViewById(R.id.rb_first)).setChecked(true);
    }
    @Override
    public void showNext(){
        startActivityAndFinishShelf(Setup2Activity.class);
    }

    @Override
    public void showPre(){
        Toast.makeText(this,"当前页面已经是第一页", Toast.LENGTH_SHORT).show();
    }
}