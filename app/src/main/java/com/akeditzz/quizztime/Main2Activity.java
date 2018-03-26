package com.akeditzz.quizztime;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.akeditzz.quizztime.Utils.ImageHelper;

import java.util.ArrayList;

import jp.wasabeef.blurry.Blurry;

public class Main2Activity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    Bitmap bitmapMaster,bitmapFinal;
    ImageView iv_blurr_bg;

    static ArrayList<QuizzModel> list;

    int radioButton = 0;
    int checkbox = 1;
    int editext = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
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

    private void setData() {
        list.add(new QuizzModel(getString(R.string.question_1),R.drawable.tajmahal,getString(R.string.option_agra),getString(R.string.option_mumbai),getString(R.string.option_bengalore),getString(R.string.option_hyderabad),getString(R.string.option_agra),radioButton));
        list.add(new QuizzModel(getString(R.string.question_2),R.drawable.ganga,getString(R.string.option_uttarakhand),getString(R.string.option_maharashtra),getString(R.string.option_uttar_pradesh),getString(R.string.option_bihar),getString(R.string.question2_answer),checkbox,3));
        list.add(new QuizzModel(getString(R.string.question_3),R.drawable.mysorepalace,getString(R.string.option_mysore),getString(R.string.option_delhi),getString(R.string.option_bengalore),getString(R.string.option_pune),getString(R.string.option_mysore),editext));
        list.add(new QuizzModel(getString(R.string.question_4),R.drawable.lakepalace,getString(R.string.option_chennai),getString(R.string.option_udaipur),getString(R.string.option_kolkata),getString(R.string.option_hyderabad),getString(R.string.option_udaipur),radioButton));
        list.add(new QuizzModel(getString(R.string.question_5),R.drawable.goldentemple,getString(R.string.option_jaipur),getString(R.string.option_pune),getString(R.string.option_amritsar),getString(R.string.option_hyderabad),getString(R.string.option_amritsar),radioButton));
        list.add(new QuizzModel(getString(R.string.question_6),R.drawable.jog,getString(R.string.option_kerala),getString(R.string.option_uttar_pradesh),getString(R.string.option_maharashtra),getString(R.string.option_karnataka),getString(R.string.option_karnataka),radioButton));
        list.add(new QuizzModel(getString(R.string.question_7),R.drawable.ajantacaves,getString(R.string.option_ajanta_cave),getString(R.string.option_elephanta_island),getString(R.string.option_badami_caves),getString(R.string.option_undavalli_caves),getString(R.string.option_ajanta_cave),editext));
        list.add(new QuizzModel(getString(R.string.question_8),R.drawable.coffee,getString(R.string.option_karnataka),getString(R.string.option_maharashtra),getString(R.string.option_kerala),getString(R.string.option_bihar),getString(R.string.question8_answer),checkbox,2));
        list.add(new QuizzModel(getString(R.string.question_9),R.drawable.wankhede,getString(R.string.option_mumbai),getString(R.string.option_pune),getString(R.string.option_bengalore),getString(R.string.option_hyderabad),getString(R.string.option_mumbai),radioButton));
        list.add(new QuizzModel(getString(R.string.question_10),R.drawable.kanyakumari,getString(R.string.option_kanyakumari),getString(R.string.option_mumbai),getString(R.string.option_goa),getString(R.string.option_vizag),getString(R.string.option_kanyakumari),radioButton));

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

    private void setBg(int position){


        bitmapFinal = ImageHelper.getInstance().getCompressedImage(this,list.get(position).getImage());

    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number",Object = "QuizModel";



        public PlaceholderFragment() {


        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */

        public static PlaceholderFragment newInstance(int sectionNumber) {

            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        View rootView;

        TextView tv_question,tv_option1,tv_option2,tv_option3,tv_option4;
        Button btn_submit;
        RadioButton rb_option1,rb_option2,rb_option3,rb_option4;
        CheckBox cb_option1,cb_option2,cb_option3,cb_option4;
        EditText et_answer;
        ImageView iv_hero;
        int position;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            position = getArguments().getInt(ARG_SECTION_NUMBER);
            int itemType = Main2Activity.list.get(position).getType();


            switch (itemType){
                case 0:
                    rootView = inflater.inflate(R.layout.layout_radiobuttons, container, false);
                    typeRadioButton();
                    break;
                case 1:
                    rootView = inflater.inflate(R.layout.layout_checkbuttons, container, false);
                    typeCheckBoxButton();
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.layout_editext, container, false);
                    typeEditextButton();
                    break;
            }



            return rootView;
        }



        private void typeRadioButton(){
            tv_question = rootView.findViewById(R.id.tv_question);
            tv_question.setText(Main2Activity.list.get(position).getQuestion());
            btn_submit = rootView.findViewById(R.id.btn_submit);

            iv_hero = rootView.findViewById(R.id.iv_hero);
            rb_option1 = rootView.findViewById(R.id.rb_option1);
            rb_option2 = rootView.findViewById(R.id.rb_option2);
            rb_option3 = rootView.findViewById(R.id.rb_option3);
            rb_option4 = rootView.findViewById(R.id.rb_option4);

            iv_hero.setImageBitmap(ImageHelper.getInstance().getCompressedImage(getContext(),Main2Activity.list.get(position).getImage()));
            rb_option1.setText(Main2Activity.list.get(position).getOption1());
            rb_option2.setText(Main2Activity.list.get(position).getOption2());
            rb_option3.setText(Main2Activity.list.get(position).getOption3());
            rb_option4.setText(Main2Activity.list.get(position).getOption4());



            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String answer = Main2Activity.list.get(position).getAnswer();
                    if (rb_option1.isSelected()){

                    }else if (rb_option2.isSelected()){

                    }
                    else if (rb_option3.isSelected()){

                    }else if (rb_option4.isSelected()){

                    }else {
                        Toast.makeText(getContext(), R.string.mesg_select_option, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        private void typeCheckBoxButton(){
            tv_question = rootView.findViewById(R.id.tv_question);
            tv_question.setText(Main2Activity.list.get(position).getQuestion());
            btn_submit = rootView.findViewById(R.id.btn_submit);

            iv_hero = rootView.findViewById(R.id.iv_hero);
            cb_option1 = rootView.findViewById(R.id.cb_option1);
            cb_option2 = rootView.findViewById(R.id.cb_option2);
            cb_option3 = rootView.findViewById(R.id.cb_option3);
            cb_option4 = rootView.findViewById(R.id.cb_option4);
            iv_hero.setImageBitmap(ImageHelper.getInstance().getCompressedImage(getContext(),Main2Activity.list.get(position).getImage()));


            cb_option1.setText(Main2Activity.list.get(position).getOption1());
            cb_option2.setText(Main2Activity.list.get(position).getOption2());
            cb_option3.setText(Main2Activity.list.get(position).getOption3());
            cb_option4.setText(Main2Activity.list.get(position).getOption4());

            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String answer = Main2Activity.list.get(position).getAnswer();
                    boolean result = true;

                    if (cb_option1.isChecked()){

                    }
                    if (cb_option2.isChecked()){

                    }
                    if (cb_option3.isChecked()){

                    }
                    if (cb_option4.isChecked()){

                    }else {

                        Toast.makeText(getContext(), R.string.mesg_select_option, Toast.LENGTH_SHORT).show();

                    }

                    if (result){

                    }


                }
            });
        }
        private void typeEditextButton(){
            tv_question = rootView.findViewById(R.id.tv_question);
            tv_question.setText(Main2Activity.list.get(position).getQuestion());
            btn_submit = rootView.findViewById(R.id.btn_submit);
            iv_hero = rootView.findViewById(R.id.iv_hero);
            tv_option1 = rootView.findViewById(R.id.tv_option1);
            tv_option2 = rootView.findViewById(R.id.tv_option2);
            tv_option3 = rootView.findViewById(R.id.tv_option3);
            tv_option4 = rootView.findViewById(R.id.tv_option4);

            tv_option1.setText(Main2Activity.list.get(position).getOption1());
            tv_option2.setText(Main2Activity.list.get(position).getOption2());
            tv_option3.setText(Main2Activity.list.get(position).getOption3());
            tv_option4.setText(Main2Activity.list.get(position).getOption4());

            et_answer = rootView.findViewById(R.id.et_answer);
            iv_hero.setImageBitmap(ImageHelper.getInstance().getCompressedImage(getContext(),Main2Activity.list.get(position).getImage()));

            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String enteredString = et_answer.getText().toString().trim();
                    String answer = Main2Activity.list.get(position).getAnswer();

                    if (!TextUtils.isEmpty(enteredString)){
                        if (enteredString.equalsIgnoreCase(answer)){

                        }else {

                        }
                    }else {
                        Toast.makeText(getContext(), R.string.mesg_enter_your_ans, Toast.LENGTH_SHORT).show();
                    }



                }
            });

        }


    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).


            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 10;
        }
    }
}
