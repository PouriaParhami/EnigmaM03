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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

//{^}
public class MainActivity extends AppCompatActivity {

    Intent pages;

    private EditText etxtC;
    private TextView txtV;
    private EditText eA, eB, eC;


    private int[] EditTextViewIds = new int[]{R.id.etA, R.id.etB, R.id.etC, R.id.etD, R.id.etE, R.id.etF, R.id.etG, R.id.etH, R.id.etI,
            R.id.etJ, R.id.etK, R.id.etL, R.id.etM, R.id.etN, R.id.etO, R.id.etP, R.id.etQ, R.id.etR, R.id.etS,
            R.id.etT, R.id.etU, R.id.etV, R.id.etW, R.id.etX, R.id.etY, R.id.etZ,};

    private HashMap<String, String> plugBord = new HashMap<>();
    private String[] charactersEnglish = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    private String[] roterR0 = {"B", "R", "Q", "O", "F", "U", "C", "M", "H", "A", "I", "K", "X", "E", "V", "D", "L", "N", "P", "W", "S", "Z", "J", "T", "Y", "G"};
    private String[] roterR1 = {"X", "M", "B", "F", "Z", "W", "S", "O", "D", "R", "J", "U", "Y", "C", "G", "I", "P", "Q", "A", "V", "T", "N", "K", "L", "E", "H"};

    private String[] roterM0 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String[] roterM1 = {"H", "Z", "W", "R", "J", "D", "K", "A", "Q", "X", "N", "T", "U", "P", "Y", "V", "G", "E", "L", "C", "O", "I", "B", "S", "M", "F"};

    private String[] roterL0 = {"J", "F", "N", "S", "O", "I", "Z", "Y", "K", "C", "M", "B", "A", "P", "T", "X", "U", "R", "V", "H", "W", "D", "Q", "G", "E", "L"};
    private String[] roterL1 = {"W", "X", "M", "E", "H", "U", "A", "B", "L", "D", "Q", "F", "J", "N", "G", "P", "O", "S", "T", "R", "C", "I", "Z", "Y", "K", "V"};

    private String[] reflectBord0 = {"P", "S", "M", "Y", "G", "C", "I", "Z", "N", "T", "F", "U", "R", "V", "K", "O", "Q", "D", "J", "B", "H", "L", "A", "E", "X", "W"};
    private String[] reflectBord1 = {"B", "C", "T", "F", "R", "S", "O", "L", "K", "M", "Y", "D", "G", "J", "N", "I", "W", "U", "V", "P", "E", "Z", "X", "H", "A", "Q"};

    private NumberPicker npL, npM, npR;

    private int lW, mW, rW, roterCheckIndex, positionHolder, Rightwheel, Midelwheel;
    private String charKeeper;
    private int ii = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        npL = (NumberPicker) findViewById(R.id.numPickL);
        npL.setMaxValue(25);
        npL.setMinValue(0);

        npR = (NumberPicker) findViewById(R.id.numPickR);
        npR.setMaxValue(25);
        npR.setMinValue(0);

        npM = (NumberPicker) findViewById(R.id.numPickM);
        npM.setMaxValue(25);
        npM.setMinValue(0);


        etxtC = (EditText) findViewById(R.id.enterText);
        txtV = (TextView) findViewById(R.id.showText);

        etxtC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                int bb = plugBord.size();

                try {
                    if (bb == 26) {
                        if (s != null && s.toString().length() > 0) {
                            if (String.valueOf(s.charAt(ii)).equals(" ")) {
                                txtV.setText(txtV.getText() + " ");
                                ii++;
                            } else {

                                enigmaCoding(String.valueOf(s.charAt(ii)));
                                ii++;
                            }
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "پلاگ برد شما خالیست", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "شما مجاز به استفاده نمی باشید", Toast.LENGTH_SHORT).show();
                }

            }


        });

        for (int i = 0; i < EditTextViewIds.length; i++) {
            //noinspection ConstantConditions
            ((EditText) findViewById(EditTextViewIds[i])).addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    for (int j = 0; j < EditTextViewIds.length; j++) {
                        eA = (EditText) findViewById(EditTextViewIds[j]);

                        for (int m = 0; m < charactersEnglish.length; m++) {
                            eB = (EditText) findViewById(EditTextViewIds[m]);

                            if ((eA.getText().toString().toUpperCase().equals(charactersEnglish[m])) && eB.getText().toString().isEmpty()) {

                                eB.setText(charactersEnglish[j]);
                                plugBord.put(charactersEnglish[j], charactersEnglish[m]);
                                plugBord.put(charactersEnglish[m], charactersEnglish[j]);

                            } else if (((eB.getText().toString().equals(charactersEnglish[j])) && (eA.getText().toString().equals("")))) {
                                eB.setText("");

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


    public void enigmaCoding(String character) {


        character = plugBord.get(character.toUpperCase());


        for (int mj = 0; mj <= 25; mj++) {
            if ((roterR0[mj].equals(character))) {
                positionHolder = mj;
            }

        }

        character = roterR1[positionHolder];

        for (int mj = 0; mj <= 25; mj++) {
            if ((roterM0[mj].equals(character))) {
                positionHolder = mj;
            }
        }


        character = roterM1[positionHolder];

        for (int jj = 0; jj <= 25; jj++) {
            if ((roterL0[jj] == character)) {
                positionHolder = jj;
            }
        }


        character = roterL1[positionHolder];

        for (int jj = 0; jj <= 25; jj++) {
            if ((reflectBord0[jj] == character)) {
                positionHolder = jj;
            }

        }

        character = reflectBord1[positionHolder];

        for (int i = 0; (i <= 25); i++) {
            if ((roterL1[i].equals(character))) {
                positionHolder = i;
            }

        }

        character = roterL0[positionHolder];

        for (int j = 0; (j <= 25); j++) {
            if ((roterM1[j].equals(character))) {
                positionHolder = j;
            }

        }

        character = roterM0[positionHolder];

        for (int i = 0; (i <= 25); i++) {
            if ((roterR1[i].equals(character))) {
                positionHolder = i;
            }

        }

        character = roterR0[positionHolder];

        txtV.setText(txtV.getText().toString() + plugBord.get(character));

//////////////////// Scroll Number Pickers and roters for each click

        npR.setValue(npR.getValue() + 1);
        Rightwheel = (Rightwheel + 1);

        if ((npR.getValue() == 25)) {
            npR.setValue(0);
        }
        roterCheckIndex = 0;
        charKeeper = roterR0[25];

        for (int rI = 25; rI >= 0; rI--) {
            if ((rI == 0)) {
                roterR0[rI] = charKeeper;
            } else {
                roterR0[rI] = roterR0[rI - 1];
            }
        }

        if ((Rightwheel == 4)) {
            Rightwheel = 0;
            Midelwheel = (Midelwheel + 1);
            charKeeper = roterM0[25];
            for (int rI = 25; rI >= 0; rI--) {
                if ((rI == 0)) {
                    roterM0[rI] = charKeeper;
                } else {
                    roterM0[rI] = roterM0[rI - 1];
                }
            }

            npM.setValue(npM.getValue() + 1);
        }

        if ((npM.getValue() == 25)) {
            npM.setValue(0);
        }

        if ((Midelwheel == 4)) {
            Midelwheel = 0;
            charKeeper = roterL0[25];
            for (int rI = 25; rI >= 0; rI--) {
                if ((rI == 0)) {
                    roterL0[rI] = charKeeper;
                } else {
                    roterL0[rI] = roterL0[rI - 1];
                }
            }

            npL.setValue(npL.getValue() + 1);
        }

        if ((npL.getValue() == 25)) {
            npL.setValue(0);
        }


    }


    public void startEnigma(View view) {

        lW = npL.getValue();
        mW = npM.getValue();
        rW = npR.getValue();

//////////////////Right////////////////////////
        roterCheckIndex = 0;
        if ((roterCheckIndex < rW)) {
            for (roterCheckIndex = 1; roterCheckIndex <= rW; roterCheckIndex++) {
                charKeeper = roterR0[25];
                for (int rI = 25; rI >= 0; rI--) {
                    if ((rI == 0)) {
                        roterR0[rI] = charKeeper;
                    } else {
                        roterR0[rI] = roterR0[rI - 1];
                    }
                }
            }
        }
////////////////////Midell////////////////////////
        roterCheckIndex = 0;
        if ((roterCheckIndex < mW)) {
            for (roterCheckIndex = 1; roterCheckIndex <= mW; roterCheckIndex++) {
                charKeeper = roterM0[25];
                for (int rI = 25; rI >= 0; rI--) {
                    if ((rI == 0)) {
                        roterM0[rI] = charKeeper;
                    } else {
                        roterM0[rI] = roterM0[rI - 1];
                    }
                }
            }
        }
/////////////////// Left ///////////////
        roterCheckIndex = 0;
        if ((roterCheckIndex < lW)) {
            for (roterCheckIndex = 1; roterCheckIndex <= lW; roterCheckIndex++) {
                charKeeper = roterL0[25];
                for (int rI = 25; rI >= 0; rI--) {
                    if ((rI == 0)) {
                        roterL0[rI] = charKeeper;
                    } else {
                        roterL0[rI] = roterL0[rI - 1];
                    }
                }
            }
        }

    }

    public void restartEnigma(View view) {


        roterR0 = new String[]{"B", "R", "Q", "O", "F", "U", "C", "M", "H", "A", "I", "K", "X", "E", "V", "D", "L", "N", "P", "W", "S", "Z", "J", "T", "Y", "G"};


        roterM0 = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


        roterL0 = new String[]{"J", "F", "N", "S", "O", "I", "Z", "Y", "K", "C", "M", "B", "A", "P", "T", "X", "U", "R", "V", "H", "W", "D", "Q", "G", "E", "L"};


        npR.setValue(0);
        npM.setValue(0);
        npL.setValue(0);
        Rightwheel = 0;
        Midelwheel = 0;

        ii = 0;
        txtV.setText("");
        etxtC.setText("");

    }

    public void restartPlugbord(View view) {

        plugBord.clear();
        ii = 0;
        txtV.setText("");
        etxtC.setText("");

        for(int gc = 0 ; gc < EditTextViewIds.length; gc++){

           eC= ((EditText) findViewById(EditTextViewIds[gc]));
            eC.setText("");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.aboutEnigma) {
            pages = new Intent(MainActivity.this, AboutEnigma.class);
            startActivity(pages);
            return true;
        } else if (id == R.id.tutorial) {
            pages = new Intent(MainActivity.this, TutorialActivity.class);
            startActivity(pages);
            return true;

        } else if (id == R.id.farsiEnigma) {
            pages = new Intent(MainActivity.this, EnigmaFarsiActivity.class);
            startActivity(pages);
            return true;
        } else if (id == R.id.aboutUs) {
            pages = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(pages);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void choseEnglishSender(View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setMessage("ارسال متن از طریق")
                .setCancelable(false)
                .setPositiveButton("تلگرام",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent sendIntent = new Intent();
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.putExtra(Intent.EXTRA_TEXT,txtV.getText().toString());
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
                        smsIntent.putExtra("sms_body", txtV.getText().toString());
                        startActivity(smsIntent);
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }
}