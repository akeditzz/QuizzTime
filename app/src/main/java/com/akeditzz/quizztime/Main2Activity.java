package com.akeditzz.quizztime;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.akeditzz.quizztime.Utils.ImageHelper;
import com.akeditzz.quizztime.adapter.SectionsPagerAdapter;
import com.akeditzz.quizztime.model.QuizzModel;

import java.util.ArrayList;

import jp.wasabeef.blurry.Blurry;

public class Main2Activity extends AppCompatActivity {

    Bitmap bitmapFinal;
    ImageView iv_blurr_bg;

    public static ArrayList<QuizzModel> list;

    int radioButton = 0;
    int checkbox = 1;
    int editext = 2;
    int score = 0;

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        /*
      The {@link android.support.v4.view.PagerAdapter} that will provide
      fragments for each of the sections. We use a
      {@link FragmentPagerAdapter} derivative, which will keep every
      loaded fragment in memory. If this becomes too memory intensive, it
      may be best to switch to a
      {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        /*
      The {@link ViewPager} that will host the section contents.
     */
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                new SetimageOperation().execute(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initView();


    }

    private void initView() {

        list = new ArrayList<>();
        setData();

        iv_blurr_bg = findViewById(R.id.iv_blurr_bg);
        new SetimageOperation().execute(0);


    }

    public void setCurrentItem(int i){
        mViewPager.setCurrentItem(i,true);
    }

    public void openResultActivity(){
        startActivity(new Intent(this, ResultActivity.class).putExtra(getString(R.string.label_score),score));
        finish();
    }

    private void setData() {
        list.add(new QuizzModel(getString(R.string.question_1), R.drawable.tajmahal, getString(R.string.option_agra), getString(R.string.option_mumbai), getString(R.string.option_bengalore), getString(R.string.option_hyderabad), getString(R.string.option_agra), radioButton,false));
        list.add(new QuizzModel(getString(R.string.question_2), R.drawable.ganga, getString(R.string.option_uttarakhand), getString(R.string.option_maharashtra), getString(R.string.option_uttar_pradesh), getString(R.string.option_bihar), getString(R.string.question2_answer), checkbox,false));
        list.add(new QuizzModel(getString(R.string.question_3), R.drawable.mysorepalace, getString(R.string.option_mysore), getString(R.string.option_delhi), getString(R.string.option_bengalore), getString(R.string.option_pune), getString(R.string.option_mysore), editext,false));
        list.add(new QuizzModel(getString(R.string.question_4), R.drawable.lakepalace, getString(R.string.option_chennai), getString(R.string.option_udaipur), getString(R.string.option_kolkata), getString(R.string.option_hyderabad), getString(R.string.option_udaipur), radioButton,false));
        list.add(new QuizzModel(getString(R.string.question_5), R.drawable.goldentemple, getString(R.string.option_jaipur), getString(R.string.option_pune), getString(R.string.option_amritsar), getString(R.string.option_hyderabad), getString(R.string.option_amritsar), radioButton,false));
        list.add(new QuizzModel(getString(R.string.question_6), R.drawable.jog, getString(R.string.option_kerala), getString(R.string.option_uttar_pradesh), getString(R.string.option_maharashtra), getString(R.string.option_karnataka), getString(R.string.option_karnataka), radioButton,false));
        list.add(new QuizzModel(getString(R.string.question_7), R.drawable.ajantacaves, getString(R.string.option_ajanta_cave), getString(R.string.option_elephanta_island), getString(R.string.option_badami_caves), getString(R.string.option_undavalli_caves), getString(R.string.option_ajanta_cave), editext,false));
        list.add(new QuizzModel(getString(R.string.question_8), R.drawable.coffee, getString(R.string.option_karnataka), getString(R.string.option_maharashtra), getString(R.string.option_kerala), getString(R.string.option_bihar), getString(R.string.question8_answer), checkbox,false));
        list.add(new QuizzModel(getString(R.string.question_9), R.drawable.wankhede, getString(R.string.option_mumbai), getString(R.string.option_pune), getString(R.string.option_bengalore), getString(R.string.option_hyderabad), getString(R.string.option_mumbai), radioButton,false));
        list.add(new QuizzModel(getString(R.string.question_10), R.drawable.kanyakumari, getString(R.string.option_kanyakumari), getString(R.string.option_mumbai), getString(R.string.option_goa), getString(R.string.option_vizag), getString(R.string.option_kanyakumari), radioButton,false));

    }

    private class SetimageOperation extends AsyncTask<Integer, Void, Void> {


        @Override
        protected Void doInBackground(Integer... integers) {

            setBg(integers[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    Blurry.with(Main2Activity.this).from(bitmapFinal).into(iv_blurr_bg);
                }
            });

        }
    }

    private void setBg(int position) {


        bitmapFinal = ImageHelper.getInstance().getCompressedImage(this, list.get(position).getImage());

    }

    public void UpdateScore(){
        score+=1;
    }


}
