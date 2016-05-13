package com.cycleviewpager.rainbow.cycleviewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cycleviewpager.rainbow.cycleviewpager_library.CycleViewPager;

public class ExampleActivity extends AppCompatActivity {
    private final static String TAG = "ExampleActivity";
    private int state = STATE_STOP;
    private final static int STATE_START = 0;
    private final static int STATE_STOP = 1;
    private CycleViewPager mCycleViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        mCycleViewPager = (CycleViewPager) findViewById(R.id.cycle_viewpager);
        mCycleViewPager.setAdapter(new ImageAdapter());
        final Button startAuto = (Button) findViewById(R.id.start_auto);
        startAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == STATE_STOP) {
                    state = STATE_START;
                    startAutoMove();
                    startAuto.setText(R.string.stop_auto);

                } else {
                    state = STATE_STOP;
                    stopAutoMove();
                    startAuto.setText(R.string.start_auto);
                }
            }
        });
        //mCycleViewPager.setRecycleMode(false);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mCycleViewPager.setNextItem();
            sendEmptyMessageDelayed(0, 3000);
        }
    };

    private void startAutoMove() {
        mHandler.sendEmptyMessage(0);
    }

    private void stopAutoMove() {
        mHandler.removeMessages(0);
    }



    class ImageAdapter extends PagerAdapter {


        public int[] mBackgroundColor = new int[]{Color.GREEN, Color.BLUE, Color.GRAY};

        @Override
        public int getCount() {
            return mBackgroundColor.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(getBaseContext());
            textView.setText(String.format("No.%d", position + 1));
            textView.setTextSize(50);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(mBackgroundColor[position]);
            container.addView(textView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            Log.d("ExampleActivity", "instantiateItem position:" + position);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
