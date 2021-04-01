package com.example.diabetesprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.SingleSpinnerListener;
import com.androidbuts.multispinnerfilter.SingleSpinnerSearch;

import java.util.ArrayList;
import java.util.List;

public class Working extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);

        List functions = new ArrayList<>();
        functions.add("OPtion1");
        functions.add("OPtion2");

        SingleSpinnerSearch singleSpinnerSearch = findViewById(R.id.singleItemSelectionSpinner);

        // Pass true, If you want color separation. Otherwise false. default = false.
        singleSpinnerSearch.setColorseparation(true);

        // Pass true If you want searchView above the list. Otherwise false. default = true.
        singleSpinnerSearch.setSearchEnabled(false);

        // A text that will display in search hint.
        singleSpinnerSearch.setSearchHint("Select Activation function");

        // Removed second parameter, position. Its not required now..
        // If you want to pass preselected items, you can do it while making listArray,
        // pass true in setSelected of any item that you want to preselect
        // LOGICALLY, PASS Only One Item As SELECTED...
        singleSpinnerSearch.setItems(functions, new SingleSpinnerListener() {
            @Override
            public void onItemsSelected(KeyPairBoolData selectedItem) {
                Log.i("TAG", "Selected Item : " + selectedItem.getName());
            }

            @Override
            public void onClear() {
                Toast.makeText(Working.this, "Cleared Selected Item", Toast.LENGTH_SHORT).show();
            }
        });


    }
}