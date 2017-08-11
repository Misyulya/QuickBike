package com.example.dmitry.quickbike.ui.widget;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dmitry.quickbike.R;
import com.shawnlin.numberpicker.NumberPicker;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BicycleSearchWidget extends RelativeLayout {

    @BindView(R.id.date_text_view)
    TextView mDateTextView;
    @BindView(R.id.expand_button)
    Button mExpandButton;
    @BindView(R.id.checkbox_container)
    ViewGroup mCheckboxContainer;
    @BindView(R.id.quantity_text_view)
    TextView mQuantityTextView;
    @BindView(R.id.from_time_text_view)
    TextView mFromTimeTextView;
    @BindView(R.id.till_time_text_view)
    TextView mTillTimeTextView;
    @BindString(R.string.expand_icon)
    String mExpandIcon;
    @BindString(R.string.collapse_icon)
    String mCollapseIcon;
    @BindString(R.string.toast_bad_date)
    String mBadDateToastMessage;
    @BindString(R.string.toast_bad_time)
    String mBadTimeToastMessage;
    @BindString(R.string.toast_bad_till_time)
    String mBadTillTimeToastMessage;

    private Unbinder mUnbinder;
    private boolean mIsCheckboxVisible;
    private SimpleDateFormat mDateFormatter = new SimpleDateFormat("dd.MM.yy", Locale.ROOT);
    private SimpleDateFormat mTimeFormatter = new SimpleDateFormat("HH:mm", Locale.ROOT);

    private Context mContext;
    private static final int DEFAULT_QUANTITY_NUMBER = 1;
    private static final int DEFAULT_RENT_TIME = 2;
    private Date mFromDate;
    private Date mTillDate;

    public BicycleSearchWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View view = inflate(getContext(), R.layout.bicycle_search_widget, null);
        addView(view);
    }

    @OnClick({R.id.expand_button, R.id.date_choice, R.id.quantity_choice, R.id.time_from_choice, R.id.time_till_choice,
            R.id.male_checkbox, R.id.female_checkbox, R.id.kids_checkbox})
    void onButtonsClick(View view) {
        Date currentDate = roundingTime(new Date());
        if (mFromDate.before(currentDate)) mFromDate = currentDate;
        if (mTillDate.before(currentDate)) mTillDate = currentDate;
        switch (view.getId()) {
            case R.id.expand_button:
                mCheckboxContainer.setVisibility(mIsCheckboxVisible ? GONE : VISIBLE);
                mExpandButton.setText(mIsCheckboxVisible ? mExpandIcon : mCollapseIcon);
                mIsCheckboxVisible = !mIsCheckboxVisible;
                break;
            case R.id.date_choice:
                showDatePickerDialog(mFromDate);
                break;
            case R.id.quantity_choice:
                showQuantityPickerDialog();
                break;
            case R.id.time_from_choice:
                showTimePickerDialog(mFromDate, TimeChoiceMode.FROM);
                break;
            case R.id.time_till_choice:
                showTimePickerDialog(mTillDate, TimeChoiceMode.TILL);
                break;
            default:
        }
    }

    private void showDatePickerDialog(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        if (c.getTime().after(new Date())) {
                            mFromDate = c.getTime();
                            mDateTextView.setText(mDateFormatter.format(mFromDate));
                        } else {
                            showToast(mBadDateToastMessage);
                        }
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTimePickerDialog(Date date, TimeChoiceMode mode) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);

                        //compare rounded date with current date
                        if (roundingTime(c.getTime()).after(new Date())) {

                            switch (mode) {
                                case FROM:
                                    mFromDate = roundingTime(c.getTime());
                                    mFromTimeTextView.setText(mTimeFormatter.format(mFromDate));
                                    if (mFromDate.after(mTillDate)) {
                                        c.add(Calendar.HOUR_OF_DAY, DEFAULT_RENT_TIME);
                                        mTillDate = roundingTime(c.getTime());
                                        mTillTimeTextView.setText(mTimeFormatter.format(mTillDate));
                                    }
                                    break;
                                case TILL:
                                    mTillDate = roundingTime(c.getTime());
                                    if (mTillDate.before(mFromDate)) {
                                        c.setTime(mFromDate);
                                        c.add(Calendar.HOUR_OF_DAY, DEFAULT_RENT_TIME);
                                        mTillDate = roundingTime(c.getTime());
                                        showToast(mBadTillTimeToastMessage);
                                    }
                                    mTillTimeTextView.setText(mTimeFormatter.format(mTillDate));
                                    break;
                            }
                        } else {
                            showToast(mBadTimeToastMessage);
                        }
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }

    private void showQuantityPickerDialog() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(mContext, R.style.MyDialogTheme);
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        View pickerView = inflater.inflate(R.layout.quantity_picker, this, false);
        NumberPicker numberPicker = (NumberPicker) pickerView.findViewById(R.id.quantity_picker);
        numberPicker.setValue(Integer.valueOf(mQuantityTextView.getText().toString()));
        builder.setView(pickerView)
                .setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mQuantityTextView.setText(String.valueOf(numberPicker.getValue()));
                    }
                })
                .setNegativeButton(R.string.cancel_button, (dialog, which) -> {
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mUnbinder = ButterKnife.bind(this);
        setDefaultValues();
    }

    private void setDefaultValues() {

        mFromDate = roundingTime(new Date());
        Calendar c = Calendar.getInstance();
        c.setTime(mFromDate);

        mDateTextView.setText(mDateFormatter.format(mFromDate));
        mFromTimeTextView.setText(mTimeFormatter.format(mFromDate));

        c.add(Calendar.HOUR_OF_DAY, DEFAULT_RENT_TIME);
        mTillDate = c.getTime();

        mTillTimeTextView.setText(mTimeFormatter.format(mTillDate));
        mQuantityTextView.setText(String.valueOf(DEFAULT_QUANTITY_NUMBER));
    }

    private Date roundingTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        int mod = unroundedMinutes % 30;
        if (mod != 0) calendar.add(Calendar.MINUTE, (30 - mod));
        return calendar.getTime();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mUnbinder.unbind();
    }

    private enum TimeChoiceMode {
        FROM,
        TILL
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}

