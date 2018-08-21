package com.dhamodharan.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.text.NumberFormat;
import java.util.Locale;

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


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);


  }

  private void calculation() {

    double a = 0.03 * Integer.valueOf(editText1.getText().toString());

    double c = Integer.valueOf(editText1.getText().toString()) - a;

    double total_price = c * Integer.valueOf(editText2.getText().toString());

    double piece_price = total_price / Integer.valueOf(editText3.getText().toString());

    int a_1 = (int) Math.round(a);
    int a_2 = (int) Math.round(total_price);

    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

    String moneyString = formatter.format(a_2);
    String one_coconut_price = formatter.format(piece_price);

    textView4.setText("கழிவுகள்" + " : " + String.valueOf(a_1) + " " + "கிலோ");
    textView5.setText("மொத்த தொகை" + " : " + String.valueOf(moneyString));
    textView6.setText("ஒரு தேங்காய் விலை" + " : " + String.valueOf(one_coconut_price));

  }

  @OnClick(R.id.button)
  public void onViewClicked() {

    if (editText1.length() == 0) {
      editText1.requestFocus();
      editText1.setError("தவறு");
    } else if (editText2.length() == 0) {
      editText2.requestFocus();
      editText2.setError("தவறு");
    } else if (editText3.length() == 0) {
      editText3.requestFocus();
      editText3.setError("தவறு");
    } else {
      try  {
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
      } catch (Exception e) {

      }
      calculation();
    }

  }
}
