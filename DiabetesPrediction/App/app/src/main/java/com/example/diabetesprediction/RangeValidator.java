package com.example.diabetesprediction;

import android.text.InputFilter;
import android.text.Spanned;

    public class RangeValidator implements InputFilter {

        private int min, max;

        public RangeValidator(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public RangeValidator(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                CharSequence part1 = dest.subSequence(0, dstart);
                CharSequence part2 = dest.subSequence(dend, dest.length());
                int input = Integer.parseInt(dest.toString() + source.toString());

                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }