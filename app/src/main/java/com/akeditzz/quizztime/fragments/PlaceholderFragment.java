package com.akeditzz.quizztime.fragments;

/**
 * Created by Akshay on 26-03-2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

import com.akeditzz.quizztime.Main2Activity;
import com.akeditzz.quizztime.R;
import com.akeditzz.quizztime.Utils.ImageHelper;
import com.akeditzz.quizztime.Utils.PreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";


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


    // Declarations
    View rootView;
    TextView tv_question, tv_option1, tv_option2, tv_option3, tv_option4;
    Button btn_submit;
    RadioButton rb_option1, rb_option2, rb_option3, rb_option4;
    CheckBox cb_option1, cb_option2, cb_option3, cb_option4;
    EditText et_answer;
    ImageView iv_hero;
    int position;
    PreferenceManager preferenceManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_SECTION_NUMBER);
        int itemType = Main2Activity.list.get(position).getType();
        preferenceManager = new PreferenceManager(getContext());

        //setting rootview according to type
        switch (itemType) {
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


    /**
     * This method will be executed if the rootview is Type RadioButton
     */
    private void typeRadioButton() {

        //Initialization
        tv_question = rootView.findViewById(R.id.tv_question);
        tv_question.setText(Main2Activity.list.get(position).getQuestion());
        btn_submit = rootView.findViewById(R.id.btn_submit);

        iv_hero = rootView.findViewById(R.id.iv_hero);
        rb_option1 = rootView.findViewById(R.id.rb_option1);
        rb_option2 = rootView.findViewById(R.id.rb_option2);
        rb_option3 = rootView.findViewById(R.id.rb_option3);
        rb_option4 = rootView.findViewById(R.id.rb_option4);

        iv_hero.setImageBitmap(ImageHelper.getInstance().getCompressedImage(getContext(), Main2Activity.list.get(position).getImage()));
        rb_option1.setText(Main2Activity.list.get(position).getOption1());
        rb_option2.setText(Main2Activity.list.get(position).getOption2());
        rb_option3.setText(Main2Activity.list.get(position).getOption3());
        rb_option4.setText(Main2Activity.list.get(position).getOption4());

        // setting click listener for submit button
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // to check if question is already answered correctly
                if (!Main2Activity.list.get(position).isAnswered()) {

                    String answer = Main2Activity.list.get(position).getAnswer();
                    boolean result = true; //boolean to set result

                    //condition to check which Button is selected
                    if (rb_option1.isChecked()) {
                        result = CheckRadioAnswer(answer, rb_option1.getText().toString());
                    } else if (rb_option2.isChecked()) {
                        result = CheckRadioAnswer(answer, rb_option2.getText().toString());
                    } else if (rb_option3.isChecked()) {
                        result = CheckRadioAnswer(answer, rb_option3.getText().toString());
                    } else if (rb_option4.isChecked()) {
                        result = CheckRadioAnswer(answer, rb_option4.getText().toString());
                    } else {
                        MakeToast(getContext().getString(R.string.mesg_select_option));
                    }

                    //condition to show alertdialog with result for current question
                    if (result) {
                        AlertDialog(true, answer);
                    } else {
                        AlertDialog(false, answer);
                    }
                } else {
                    MakeToast(getContext().getString(R.string.mesg_already_answered));
                }
            }
        });

    }

    /**
     * This method will be executed if the rootview is Type CheckBoxButton
     */
    private void typeCheckBoxButton() {
        //Initialization
        tv_question = rootView.findViewById(R.id.tv_question);
        tv_question.setText(Main2Activity.list.get(position).getQuestion());
        btn_submit = rootView.findViewById(R.id.btn_submit);

        iv_hero = rootView.findViewById(R.id.iv_hero);
        cb_option1 = rootView.findViewById(R.id.cb_option1);
        cb_option2 = rootView.findViewById(R.id.cb_option2);
        cb_option3 = rootView.findViewById(R.id.cb_option3);
        cb_option4 = rootView.findViewById(R.id.cb_option4);

        iv_hero.setImageBitmap(ImageHelper.getInstance().getCompressedImage(getContext(), Main2Activity.list.get(position).getImage()));
        cb_option1.setText(Main2Activity.list.get(position).getOption1());
        cb_option2.setText(Main2Activity.list.get(position).getOption2());
        cb_option3.setText(Main2Activity.list.get(position).getOption3());
        cb_option4.setText(Main2Activity.list.get(position).getOption4());

        // setting click listener for submit button
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // to check if question is already answered correctly
                if (!Main2Activity.list.get(position).isAnswered()) {

                    String answer = Main2Activity.list.get(position).getAnswer();
                    List<String> answerList = Arrays.asList(answer.split(","));// converting answer string into list.
                    List<String> selectedAnswer = new ArrayList<>();// new list for selected answers
                    boolean result;//boolean to set result

                    //conditions to check which Buttons are checked
                    if (cb_option1.isChecked()) {
                        selectedAnswer.add(cb_option1.getText().toString());
                    }
                    if (cb_option2.isChecked()) {
                        selectedAnswer.add(cb_option2.getText().toString());
                    }
                    if (cb_option3.isChecked()) {
                        selectedAnswer.add(cb_option3.getText().toString());
                    }
                    if (cb_option4.isChecked()) {
                        selectedAnswer.add(cb_option4.getText().toString());
                    }

                    result = CheckBoxAnswer(answerList, selectedAnswer);// getting the boolean value for result

                    //condition to show alertdialog with result for current question
                    if (result) {
                        AlertDialog(true, answer);
                    } else {
                        AlertDialog(false, answer);
                    }
                } else {
                    MakeToast(getContext().getString(R.string.mesg_already_answered));
                }

            }
        });
    }

    /**
     * This method will be executed if the rootview is Type EditextButton
     */
    private void typeEditextButton() {
        //Initialization
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
        iv_hero.setImageBitmap(ImageHelper.getInstance().getCompressedImage(getContext(), Main2Activity.list.get(position).getImage()));

        // setting click listener for submit button
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // to check if question is already answered correctly
                if (!Main2Activity.list.get(position).isAnswered()) {

                    String enteredString = et_answer.getText().toString().trim();
                    String answer = Main2Activity.list.get(position).getAnswer();

                    //conditions to check typed answer
                    if (!TextUtils.isEmpty(enteredString)) {
                        if (enteredString.equalsIgnoreCase(answer)) {
                            AlertDialog(true, answer);
                        } else {
                            AlertDialog(false, answer);
                        }
                    } else {
                        MakeToast(getContext().getString(R.string.mesg_enter_your_ans));

                    }

                } else {
                    MakeToast(getContext().getString(R.string.mesg_already_answered));
                }
            }
        });

    }

    /**
     * Method to check answers for Type CheckBox
     * @param correctAnswer
     * @param selectedAnswer
     * @return boolean(result)
     */
    private boolean CheckBoxAnswer(List<String> correctAnswer, List<String> selectedAnswer) {

        // condition to check if user have selected more than what is required
        if (correctAnswer.size() == selectedAnswer.size()) {
            //correct answers are removed from selected answers
            selectedAnswer.removeAll(correctAnswer);
            return selectedAnswer.size() == 0;// hence if selected answer size is zero then result is true
        } else {
            return false;
        }


    }

    /**
     * Method to check answers for Type RadioButton
     * @param correctAnswer
     * @param selectedAnswer
     * @return boolean(result)
     */
    private boolean CheckRadioAnswer(String correctAnswer, String selectedAnswer) {

        return selectedAnswer.equalsIgnoreCase(correctAnswer);// simple string match if matched returns true else false

    }

    /**
     * Common Method to Display toast messagesa
     * @param s
     */
    private void MakeToast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    /**
     * Common Method to show Alert dialog for results
     * @param answer
     * @param correctAnswer
     */
    private void AlertDialog(final boolean answer, final String correctAnswer) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        //inflating custom layout
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.customdialog, null);
        dialogBuilder.setView(dialogView);

        ImageView iv_image = dialogView.findViewById(R.id.iv_image);
        TextView tv_mesg = dialogView.findViewById(R.id.tv_mesg);
        final TextView tv_answer = dialogView.findViewById(R.id.tv_viewAnswer);
        Button buttonSubmit = dialogView.findViewById(R.id.btn_submit);

        // condition to display dialog according to correct or wrong answer
        if (answer) {
            iv_image.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_action_right));
            tv_mesg.setText(String.format(getString(R.string.mesg_weldone), preferenceManager.getUserName()));
            tv_answer.setVisibility(View.GONE);
            buttonSubmit.setText(getContext().getString(R.string.label_continue));
            buttonSubmit.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg));
            if (!Main2Activity.list.get(position).isAnswered()) {
                ((Main2Activity) getContext()).UpdateScore();
            }
            Main2Activity.list.get(position).setAnswered(true);

        } else {
            iv_image.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_action_wrong));
            tv_mesg.setText(R.string.mesg_tryagain);
            tv_answer.setVisibility(View.VISIBLE);
            buttonSubmit.setText(getContext().getString(R.string.label_tryagain));
            buttonSubmit.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg_red));
            Main2Activity.list.get(position).setAnswered(false);
            tv_answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv_answer.setText(String.format(getString(R.string.label_answer), correctAnswer));
                }
            });
        }

        final AlertDialog alertDialog = dialogBuilder.create();
        /**
         * Onclick of submit button if result is true then checking to see if the current layout is lastone
         * if lastone then open resultActivity else navigate to next question
         */
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer) {
                    alertDialog.dismiss();
                    if (position < 9) {
                        ((Main2Activity) getContext()).setCurrentItem(position + 1);
                    } else {
                        ((Main2Activity) getContext()).openResultActivity();
                    }

                } else {
                    alertDialog.dismiss();
                }
            }
        });


        alertDialog.show();
    }


}
