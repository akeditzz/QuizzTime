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


    private void typeRadioButton() {
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


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = Main2Activity.list.get(position).getAnswer();
                boolean result = true;
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

                if (result) {
                    AlertDialog(true, answer);
                } else {
                    AlertDialog(false, answer);
                }
            }
        });

    }

    private void typeCheckBoxButton() {
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

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = Main2Activity.list.get(position).getAnswer();
                boolean result = true;

                if (cb_option1.isChecked()) {
                    if (!CheckBoxAnswer(answer, cb_option1.getText().toString())) {
                        result = false;
                    }
                }
                if (cb_option2.isChecked()) {
                    if (!CheckBoxAnswer(answer, cb_option2.getText().toString())) {
                        result = false;
                    }
                }
                if (cb_option3.isChecked()) {
                    if (!CheckBoxAnswer(answer, cb_option3.getText().toString())) {
                        result = false;
                    }
                }
                if (cb_option4.isChecked()) {
                    if (!CheckBoxAnswer(answer, cb_option4.getText().toString())) {
                        result = false;
                    }
                }

                if (result) {
                    AlertDialog(true, answer);
                } else {
                    AlertDialog(false, answer);
                }


            }
        });
    }


    private void typeEditextButton() {
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

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredString = et_answer.getText().toString().trim();
                String answer = Main2Activity.list.get(position).getAnswer();

                if (!TextUtils.isEmpty(enteredString)) {
                    if (enteredString.equalsIgnoreCase(answer)) {
                        AlertDialog(true, answer);
                    } else {
                        AlertDialog(false, answer);
                    }
                } else {
                    MakeToast(getContext().getString(R.string.mesg_enter_your_ans));

                }


            }
        });

    }

    private boolean CheckBoxAnswer(String correctAnswer, String selectedAnswer) {

        if (!correctAnswer.contains(selectedAnswer)) {
            return false;
        } else {
            return true;
        }

    }

    private boolean CheckRadioAnswer(String correctAnswer, String selectedAnswer) {

        if (!selectedAnswer.equalsIgnoreCase(correctAnswer)) {
            return false;
        } else {
            return true;
        }

    }

    private void MakeToast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    private void AlertDialog(final boolean answer, final String correctAnswer) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());


        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.customdialog, null);
        dialogBuilder.setView(dialogView);

        ImageView iv_image = dialogView.findViewById(R.id.iv_image);
        TextView tv_mesg = dialogView.findViewById(R.id.tv_mesg);
        final TextView tv_answer = dialogView.findViewById(R.id.tv_viewAnswer);
        Button buttonSubmit = dialogView.findViewById(R.id.btn_submit);

        if (answer) {
            iv_image.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_action_right));
            tv_mesg.setText(String.format(getString(R.string.mesg_weldone), preferenceManager.getUserName()));
            tv_answer.setVisibility(View.GONE);
            buttonSubmit.setText(getContext().getString(R.string.label_continue));
            buttonSubmit.setBackground(getContext().getResources().getDrawable(R.drawable.btn_bg));
            if (!Main2Activity.list.get(position).isAnswered()) {
                ((Main2Activity)getContext()).UpdateScore();
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
