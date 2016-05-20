package com.legendmohe.rappid.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.legendmohe.rappid.R;
import com.legendmohe.rappid.vendor.datepicker.NumberPicker;
import com.legendmohe.rappid.vendor.datepicker.Sound;

import java.util.Calendar;
import java.util.Date;

public class TimerDatePicker extends LinearLayout implements NumberPicker.OnValueChangeListener {

    private NumberPicker mHourPicker;
    private NumberPicker mMinutePicker;

    private Calendar mCalendar;

    private OnDateChangedListener mOnDateChangedListener;

    private LayoutInflater mLayoutInflater;

    public TimerDatePicker(Context context) {
        this(context, null);
    }

    public TimerDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        init();
    }

    private void init() {
        mLayoutInflater.inflate(R.layout.timer_date_picker_layout, this, true);

        mHourPicker = (NumberPicker) findViewById(R.id.hour_picker);
        mMinutePicker = (NumberPicker) findViewById(R.id.minute_picker);

        mHourPicker.setOnValueChangeListener(this);
        mMinutePicker.setOnValueChangeListener(this);

        mCalendar = Calendar.getInstance();
        setDate(mCalendar.getTime());
    }

    public TimerDatePicker setDate(Date date) {
        mCalendar.setTime(date);

        mHourPicker.setCurrentNumber(mCalendar.get(Calendar.HOUR) + 1);
        mMinutePicker.setCurrentNumber(mCalendar.get(Calendar.MINUTE));

        return this;
    }

    @Override
    public void onValueChange(final NumberPicker picker, final int oldVal, final int newVal) {

        if (picker == mHourPicker) {
            mCalendar.set(Calendar.HOUR, newVal);
        } else if (picker == mMinutePicker) {
            mCalendar.set(Calendar.MINUTE, newVal);
        }
        notifyDateChanged();
    }

    public interface OnDateChangedListener {

        void onDateChanged(TimerDatePicker view, int hour, int min);
    }

    public TimerDatePicker setOnDateChangedListener(OnDateChangedListener l) {
        mOnDateChangedListener = l;
        return this;
    }

    private void notifyDateChanged() {
        if (mOnDateChangedListener != null) {
            mOnDateChangedListener.onDateChanged(this, getHour(), getMinute());
        }
    }

    public int getYear() {
        return mCalendar.get(Calendar.YEAR);
    }

    public int getMonth() {
        return mCalendar.get(Calendar.MONTH) + 1;
    }

    public int getDayOfMonth() {
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getHour() {
        return mCalendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute() {
        return mCalendar.get(Calendar.MINUTE);
    }

    public int getSecond() {
        return mCalendar.get(Calendar.SECOND);
    }

    public long getTimeInMillis() {
        return mCalendar.getTimeInMillis();
    }

    public TimerDatePicker setSoundEffect(Sound sound) {
        mHourPicker.setSoundEffect(sound);
        mMinutePicker.setSoundEffect(sound);
        return this;
    }

    @Override
    public void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
        super.setSoundEffectsEnabled(soundEffectsEnabled);
        mHourPicker.setSoundEffectsEnabled(soundEffectsEnabled);
        mMinutePicker.setSoundEffectsEnabled(soundEffectsEnabled);
    }

    public TimerDatePicker setRowNumber(int rowNumber) {
        mHourPicker.setRowNumber(rowNumber);
        mMinutePicker.setRowNumber(rowNumber);
        return this;
    }

    public TimerDatePicker setTextSize(float textSize) {
        mHourPicker.setTextSize(textSize);
        mMinutePicker.setTextSize(textSize);
        return this;
    }

    public TimerDatePicker setFlagTextSize(float textSize) {
        mHourPicker.setFlagTextSize(textSize);
        mMinutePicker.setFlagTextSize(textSize);
        return this;
    }

    public TimerDatePicker setTextColor(int color) {
        mHourPicker.setTextColor(color);
        mMinutePicker.setTextColor(color);
        return this;
    }

    public TimerDatePicker setFlagTextColor(int color) {
        mHourPicker.setFlagTextColor(color);
        mMinutePicker.setFlagTextColor(color);
        return this;
    }

    public TimerDatePicker setBackground(int color) {
        super.setBackgroundColor(color);
        mHourPicker.setBackground(color);
        mMinutePicker.setBackground(color);
        return this;
    }

    public void setHour(int hour) {
        mHourPicker.setCurrentNumber(hour);
    }

    public void setMinute(int minute) {
        mMinutePicker.setCurrentNumber(minute);
    }
}
