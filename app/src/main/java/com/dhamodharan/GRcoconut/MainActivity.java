package com.dhamodharan.GRcoconut;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.editText10)
    EditText editText10;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.editText11)
    EditText editText11;


    //SharedPreferences app_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
 /*   app_preferences =
        PreferenceManager.getDefaultSharedPreferences(this);
    String counter = app_preferences.getString("counter", "");
    if (counter != null) {
      editText2.setText(counter);
    } else {
      editText2.setText("");
    }*/

        editText1.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    if (editText10.length() != 0) {

                        try {
                            double value = Double.parseDouble(String.valueOf(Integer.valueOf(editText1.getText().toString()) -
                                    Integer.valueOf(editText10.getText().toString())));

                            if (value < 0) {
                                System.out.println(value + " is negative");
                                Toast.makeText(MainActivity.this, R.string.wrong, Toast.LENGTH_SHORT).show();
                                editText11.setText("");
                            } else {
                                System.out.println(value + " is possitive");
                                editText11.setText(String.valueOf(Integer.valueOf(editText1.getText().toString()) -
                                        Integer.valueOf(editText10.getText().toString())));
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }else{

                    editText11.setText("");
                }
            }
        });


        editText10.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0){
                    if(editText1.length()!=0){

                        try {
                            double value = Double.parseDouble(String.valueOf(Integer.valueOf(editText1.getText().toString())-
                                    Integer.valueOf(editText10.getText().toString())));

                            if(value<0){
                                System.out.println(value + " is negative");
                                editText11.setText("");
                                Toast.makeText(MainActivity.this, getString(R.string.wrong_vehicle_weight), Toast.LENGTH_SHORT).show();

                            }
                            else{
                                System.out.println(value + " is possitive");
                                editText11.setText(String.valueOf(Integer.valueOf(editText1.getText().toString())-
                                        Integer.valueOf(editText10.getText().toString())));
                            }
                        } catch (NumberFormatException e) {
                        }

                    }
            }else{
                    editText11.setText("");
                }
            }
        });

    }

    private void calculation() {

/*    SharedPreferences.Editor editor = app_preferences.edit();
    editor.putString("counter", String.valueOf(editText2.getText().toString()));
    editor.commit(); */

        double a = 0.03 * Integer.valueOf(editText11.getText().toString());

        double c = Integer.valueOf(editText11.getText().toString()) - a;

        double total_price = c * Double.valueOf(editText2.getText().toString());

        double piece_price = total_price / Integer.valueOf(editText3.getText().toString());

        double avg_weight = c / Integer.valueOf(editText3.getText().toString());

        double value = Double.parseDouble(new DecimalFormat("##.####").format(avg_weight));

        int a_1 = (int) Math.round(a);
        int a_2 = (int) Math.round(total_price);
        //int a_3 = (int) Math.round(avg_weight);

        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

        String moneyString = formatter.format(a_2);
        String one_coconut_price = formatter.format(piece_price);

        textView4.setText("தேங்காய் கழிவுகள்" + " : " + String.valueOf(a_1) + " " + "கிலோ");
        textView5.setText("மொத்த தொகை" + " : " + String.valueOf(moneyString));
        textView6.setText("ஒரு தேங்காய் விலை" + " : " + String.valueOf(one_coconut_price));
        textView7.setText("சராசரி எடை" + " : " + String.valueOf(c) + " " + "கிராம்");


        System.out.println("தேங்காய் கழிவுகள்" + " : " + String.valueOf(a_1) + " " + "கிலோ");
        System.out.println("மொத்த தொகை" + " : " + String.valueOf(moneyString));
        System.out.println("ஒரு தேங்காய் விலை" + " : " + String.valueOf(one_coconut_price));
        System.out.println("சராசரி எடை" + " : " + String.valueOf(c) + " " + "கிராம்");
    }

    @OnClick(R.id.button)
    public void onViewClicked() {

    if (editText1.length() == 0) {
      editText1.requestFocus();
      editText1.setError("தவறு");
    }
    else if (editText10.length() == 0) {
        editText10.requestFocus();
        editText10.setError("தவறு");
    }
    else if (editText11.length() == 0) {
        //editText11.requestFocus();
        //editText11.setError("தவறு");
        Toast.makeText(this, getString(R.string.wrong), Toast.LENGTH_SHORT).show();
    } else if (editText2.length() == 0) {
      editText2.requestFocus();
      editText2.setError("தவறு");
    } else if (editText3.length() == 0) {
      editText3.requestFocus();
      editText3.setError("தவறு");
    } else {
      try {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
          imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
        }
      } catch (Exception ignored) {

            }
            calculation();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_clear:
                recreate();
                editText1.getText().clear();
                editText10.getText().clear();
                editText11.getText().clear();
                editText2.getText().clear();
                editText3.getText().clear();
                editText1.requestFocus();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
