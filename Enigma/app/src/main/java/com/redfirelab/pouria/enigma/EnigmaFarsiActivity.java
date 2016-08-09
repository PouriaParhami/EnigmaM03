package com.redfirelab.pouria.enigma;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class EnigmaFarsiActivity extends AppCompatActivity {

    EditText etxtCFarsi;
    TextView txtVFarsi;
    EditText eAfarsi, eBfarsi, eCFarsi;


    int[] EditTextViewFarsiIds = new int[]{R.id.etAlef, R.id.etBe, R.id.etPe, R.id.etTe, R.id.etCee, R.id.etJim, R.id.etChe, R.id.etHe, R.id.etkhe,
            R.id.etDal, R.id.etZal, R.id.etRe, R.id.etZe, R.id.etZhe, R.id.etCin, R.id.etShin, R.id.etSad, R.id.etZat, R.id.etTa,
            R.id.etZa, R.id.etAyen, R.id.etGhayen, R.id.etFe, R.id.etGhaf, R.id.etKaf, R.id.etGaf, R.id.etLam, R.id.etMim, R.id.etNun,
            R.id.etVav, R.id.etHee, R.id.etYe};

    HashMap<String, String> plugBordFarsi = new HashMap<>();
    String[] charactersFarsi = {"ا", "ب", "پ", "ت", "ث", "ج", "چ", "ح", "خ", "د", "ذ", "ر", "ز", "ژ", "س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ک", "گ", "ل", "م", "ن", "و", "ه", "ی"};


    String[] roterFarsiR0 = {"پ", "ق", "ی", "ث", "ژ", "ل", "ص", "چ", "ا", "ش", "و", "ت", "م", "ف", "ه", "ض", "خ", "ز", "غ", "ب", "ح", "س", "گ", "د", "ن", "ظ", "ک", "ج", "ر", "ط", "ع", "ذ"};
    String[] roterFarsiR1 = {"ک", "ا", "غ", "ط", "پ", "ه", "س", "ق", "ت", "د", "ژ", "ج", "و", "ع", "گ", "م", "ب", "ن", "ی", "ش", "ظ", "خ", "ز", "ث", "ل", "ر", "ف", "ض", "ذ", "ص", "ح", "چ"};

    String[] roterFarsiM0 = {"ض", "ش", "ج", "ف", "ن", "ژ", "ه", "ظ", "چ", "ذ", "ق", "و", "ل", "گ", "ب", "ح", "ث", "ر", "ی", "ا", "ک", "غ", "ز", "م", "ع", "د", "ص", "خ", "پ", "س", "ت", "ط"};
    String[] roterFarsiM1 = {"ب", "خ", "س", "غ", "پ", "ق", "ض", "ن", "گ", "ژ", "ر", "ا", "ذ", "ط", "و", "چ", "ک", "ش", "ظ", "ل", "ه", "ص", "ث", "ز", "ت", "ی", "م", "ح", "ع", "ج", "ف", "د"};

    String[] roterFarsiL0 = {"ا", "ب", "پ", "ت", "ث", "ج", "چ", "ح", "خ", "د", "ذ", "ر", "ز", "ژ", "س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ک", "گ", "ل", "م", "ن", "و", "ه", "ی"};
    String[] roterFarsiL1 = {"د", "ج", "م", "ه", "ف", "ن", "ط", "ا", "ک", "گ", "ل", "چ", "غ", "ش", "ی", "ق", "ظ", "ث", "ت", "و", "پ", "ب", "ذ", "ح", "ژ", "خ", "ص", "ع", "ر", "س", "ز", "ض"};

    String[] reflectBordFarsi0 = {"گ", "ف", "ا", "غ", "ث", "ی", "پ", "ل", "ت", "ن", "چ", "ط", "ب", "ذ", "و", "ه", "ق", "ک", "ش", "خ", "م", "ح", "ژ", "س", "ظ", "ز", "ج", "د", "ص", "ر", "ض", "ع"};
    String[] reflectBordFarsi1 = {"ث", "ج", "پ", "ذ", "گ", "ن", "ا", "ش", "ظ", "ی", "م", "ه", "ح", "غ", "خ", "ط", "د", "ز", "ل", "و", "چ", "ب", "ع", "ض", "ت", "ک", "ف", "ق", "ر", "ص", "س", "ژ"};

    NumberPicker npLFarsi, npMFarsi, npRFarsi;

    int lWfarsi, mWfarsi, rWfarsi, roterCheckIndexFarsi, positionHolderFarsi, RightwheelFarsi, MidelwheelFarsi;
    String charKeeperFarsi;
    int iiFarsi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enigma_farsi);

        npLFarsi = (NumberPicker) findViewById(R.id.numPickFarsiL);
        npLFarsi.setMaxValue(31);
        npLFarsi.setMinValue(0);

        npRFarsi = (NumberPicker) findViewById(R.id.numPickFarsiR);
        npRFarsi.setMaxValue(31);
        npRFarsi.setMinValue(0);

        npMFarsi = (NumberPicker) findViewById(R.id.numPickFarrsiM);
        npMFarsi.setMaxValue(31);
        npMFarsi.setMinValue(0);


        etxtCFarsi = (EditText) findViewById(R.id.enterTextFarsi);
        txtVFarsi = (TextView) findViewById(R.id.showTextFarsi);

        etxtCFarsi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                int bb = plugBordFarsi.size();

                try {
                    if (bb == 32) {
                        if (s != null && s.toString().length() > 0) {
                            if (String.valueOf(s.charAt(iiFarsi)).equals(" ")) {
                                txtVFarsi.setText(txtVFarsi.getText() + " ");
                                iiFarsi++;
                            } else {

                                enigmaCodingFarsi(String.valueOf(s.charAt(iiFarsi)));
                                iiFarsi++;
                            }
                        }
                    } else {
                        Toast.makeText(EnigmaFarsiActivity.this, "پلاگ برد شما خالیست", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(EnigmaFarsiActivity.this, "شما مجاز به استفاده نمی باشید", Toast.LENGTH_SHORT).show();
                }

            }


        });

        for (int i = 0; i < EditTextViewFarsiIds.length; i++) {
            //noinspection ConstantConditions
            ((EditText) findViewById(EditTextViewFarsiIds[i])).addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    for (int j = 0; j < EditTextViewFarsiIds.length; j++) {
                        eAfarsi = (EditText) findViewById(EditTextViewFarsiIds[j]);

                        for (int m = 0; m < charactersFarsi.length; m++) {
                            eBfarsi = (EditText) findViewById(EditTextViewFarsiIds[m]);

                            if ((eAfarsi.getText().toString().equals(charactersFarsi[m])) && eBfarsi.getText().toString().isEmpty()) {

                                eBfarsi.setText(charactersFarsi[j]);

                                plugBordFarsi.put(charactersFarsi[j], charactersFarsi[m]);
                                Log.i("the input is : ", plugBordFarsi.get(charactersFarsi[j]));
                                plugBordFarsi.put(charactersFarsi[m], charactersFarsi[j]);
                                Log.i("the another input is : ", plugBordFarsi.get(charactersFarsi[m]));

                            } else if (((eBfarsi.getText().toString().equals(charactersFarsi[j])) && (eAfarsi.getText().toString().equals("")))) {
                                eBfarsi.setText("");

                            }
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }


    public void enigmaCodingFarsi(String characterFarsi) {


        characterFarsi = plugBordFarsi.get(characterFarsi);

        for (int mj = 0; mj <= 31; mj++) {
            if ((roterFarsiR0[mj].equals(characterFarsi))) {
                positionHolderFarsi = mj;
            }

        }

        characterFarsi = roterFarsiR1[positionHolderFarsi];
        for (int mj = 0; mj <= 31; mj++) {
            if ((roterFarsiM0[mj].equals(characterFarsi))) {
                positionHolderFarsi = mj;
            }
        }


        characterFarsi = roterFarsiM1[positionHolderFarsi];
        for (int jj = 0; jj <= 31; jj++) {
            if ((roterFarsiL0[jj] == characterFarsi)) {
                positionHolderFarsi = jj;
            }
        }

        characterFarsi = roterFarsiL1[positionHolderFarsi];
        for (int jj = 0; jj <= 31; jj++) {
            if ((reflectBordFarsi0[jj] == characterFarsi)) {
                positionHolderFarsi = jj;
            }

        }

        characterFarsi = reflectBordFarsi1[positionHolderFarsi];
        for (int i = 0; (i <= 31); i++) {
            if ((roterFarsiL1[i].equals(characterFarsi))) {
                positionHolderFarsi = i;
            }

        }

        characterFarsi = roterFarsiL0[positionHolderFarsi];
        for (int j = 0; (j <= 31); j++) {
            if ((roterFarsiM1[j].equals(characterFarsi))) {
                positionHolderFarsi = j;
            }

        }

        characterFarsi = roterFarsiM0[positionHolderFarsi];
        for (int i = 0; (i <= 31); i++) {
            if ((roterFarsiR1[i].equals(characterFarsi))) {
                positionHolderFarsi = i;
            }

        }

        characterFarsi = roterFarsiR0[positionHolderFarsi];

        txtVFarsi.setText(txtVFarsi.getText().toString() + plugBordFarsi.get(characterFarsi));

//////////////////// Scroll Number Pickers and roters for each click

        npRFarsi.setValue(npRFarsi.getValue() + 1);
        RightwheelFarsi = (RightwheelFarsi + 1);
        if ((npRFarsi.getValue() == 31)) {
            npRFarsi.setValue(0);
        }
        roterCheckIndexFarsi = 0;
        charKeeperFarsi = roterFarsiR0[31];

        for (int rI = 31; rI >= 0; rI--) {
            if ((rI == 0)) {
                roterFarsiR0[rI] = charKeeperFarsi;
            } else {
                roterFarsiR0[rI] = roterFarsiR0[rI - 1];
            }
        }

        if ((RightwheelFarsi == 4)) {
            RightwheelFarsi = 0;
            MidelwheelFarsi = (MidelwheelFarsi + 1);
            charKeeperFarsi = roterFarsiM0[31];

            for (int rI = 31; rI >= 0; rI--) {
                if ((rI == 0)) {
                    roterFarsiM0[rI] = charKeeperFarsi;
                } else {
                    roterFarsiM0[rI] = roterFarsiM0[rI - 1];
                }
            }

            npMFarsi.setValue(npMFarsi.getValue() + 1);
        }

        if ((npMFarsi.getValue() == 31)) {
            npMFarsi.setValue(0);
        }

        if ((MidelwheelFarsi == 4)) {
            MidelwheelFarsi = 0;
            charKeeperFarsi = roterFarsiL0[31];
            for (int rI = 31; rI >= 0; rI--) {
                if ((rI == 0)) {
                    roterFarsiL0[rI] = charKeeperFarsi;
                } else {
                    roterFarsiL0[rI] = roterFarsiL0[rI - 1];
                }
            }

            npLFarsi.setValue(npLFarsi.getValue() + 1);
        }

        if ((npLFarsi.getValue() == 31)) {
            npLFarsi.setValue(0);
        }


    }


    public void startEnigmaFarsi(View view) {

        lWfarsi = npLFarsi.getValue();
        mWfarsi = npMFarsi.getValue();
        rWfarsi = npRFarsi.getValue();

//////////////////Right////////////////////////
        roterCheckIndexFarsi = 0;
        if ((roterCheckIndexFarsi < rWfarsi)) {
            for (roterCheckIndexFarsi = 1; roterCheckIndexFarsi <= rWfarsi; roterCheckIndexFarsi++) {
                charKeeperFarsi = roterFarsiR0[31];
                for (int rI = 31; rI >= 0; rI--) {
                    if ((rI == 0)) {
                        roterFarsiR0[rI] = charKeeperFarsi;
                    } else {
                        roterFarsiR0[rI] = roterFarsiR0[rI - 1];
                    }
                }
            }
        }
////////////////////Midell////////////////////////
        roterCheckIndexFarsi = 0;
        if ((roterCheckIndexFarsi < mWfarsi)) {
            for (roterCheckIndexFarsi = 1; roterCheckIndexFarsi <= mWfarsi; roterCheckIndexFarsi++) {
                charKeeperFarsi = roterFarsiM0[31];
                for (int rI = 31; rI >= 0; rI--) {
                    if ((rI == 0)) {
                        roterFarsiM0[rI] = charKeeperFarsi;
                    } else {
                        roterFarsiM0[rI] = roterFarsiM0[rI - 1];
                    }
                }
            }
        }
/////////////////// Left ///////////////
        roterCheckIndexFarsi = 0;
        if ((roterCheckIndexFarsi < lWfarsi)) {
            for (roterCheckIndexFarsi = 1; roterCheckIndexFarsi <= lWfarsi; roterCheckIndexFarsi++) {
                charKeeperFarsi = roterFarsiL0[31];
                for (int rI = 31; rI >= 0; rI--) {
                    if ((rI == 0)) {
                        roterFarsiL0[rI] = charKeeperFarsi;
                    } else {
                        roterFarsiL0[rI] = roterFarsiL0[rI - 1];
                    }
                }
            }
        }

    }

    public void restartEnigmaFarsi(View view) {


        roterFarsiR0 = new String[]{"پ", "ق", "ی", "ث", "ژ", "ل", "ص", "چ", "ا", "ش", "و", "ت", "م", "ف", "ه", "ض", "خ", "ز", "غ", "ب", "ح", "س", "گ", "د", "ن", "ظ", "ک", "ج", "ر", "ط", "ع", "ذ"};


        roterFarsiM0 = new String[]{"ض", "ش", "ج", "ف", "ن", "ژ", "ه", "ظ", "چ", "ذ", "ق", "و", "ل", "گ", "ب", "ح", "ث", "ر", "ی", "ا", "ک", "غ", "ز", "م", "ع", "د", "ص", "خ", "پ", "س", "ت", "ط"};


        roterFarsiL0 = new String[]{"ا", "ب", "پ", "ت", "ث", "ج", "چ", "ح", "خ", "د", "ذ", "ر", "ز", "ژ", "س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ک", "گ", "ل", "م", "ن", "و", "ه", "ی"};


        npRFarsi.setValue(0);
        npMFarsi.setValue(0);
        npLFarsi.setValue(0);
        RightwheelFarsi = 0;
        MidelwheelFarsi = 0;

        iiFarsi = 0;
        txtVFarsi.setText("");
        etxtCFarsi.setText("");

    }

    public void restartPlugbordFarsi(View view) {

        plugBordFarsi.clear();
        iiFarsi = 0;
        txtVFarsi.setText("");
        etxtCFarsi.setText("");

        for (int gc = 0; gc < EditTextViewFarsiIds.length; gc++) {

            eCFarsi = ((EditText) findViewById(EditTextViewFarsiIds[gc]));
            eCFarsi.setText("");
        }
    }


    public void choseSender(View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EnigmaFarsiActivity.this);
        alertDialogBuilder.setMessage("ارسال متن از طریق")
                .setCancelable(false)
                .setPositiveButton("تلگرام",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent sendIntent = new Intent();
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.putExtra(Intent.EXTRA_TEXT,txtVFarsi.getText().toString());
                                sendIntent.setType("text/plain");
                                sendIntent.setPackage("org.telegram.messenger");
                                try{
                                    startActivity(sendIntent);
                                }
                                catch (android.content.ActivityNotFoundException ex){
                                    Toast.makeText(getApplicationContext(),"تلگرام را نصب کنید",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
        alertDialogBuilder.setNegativeButton("پیامک",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                        smsIntent.setData(Uri.parse("smsto:"));
                        smsIntent.setType("vnd.android-dir/mms-sms");
                        smsIntent.putExtra("sms_body", txtVFarsi.getText().toString());
                        startActivity(smsIntent);
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();


    }
}
