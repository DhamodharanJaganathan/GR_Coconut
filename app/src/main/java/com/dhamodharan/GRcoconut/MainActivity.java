package com.dhamodharan.GRcoconut;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dhamodharan.GRcoconut.dbconnection.DatabaseClient;
import com.dhamodharan.GRcoconut.dbconnection.Task;
import com.dhamodharan.GRcoconut.notes.NotesActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    @BindView(R.id.Notes)
    TextView Notes;
    @BindView(R.id.textNotes)
    EditText textNotes;
    @BindView(R.id.button2)
    Button button2;

    private String waste, totalamnount, percoconut, weight, today, notes,coconutweight,coconutprice,totalcoconut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        ButterKnife.bind(this);

        editText1.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

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
                } else {

                    editText11.setText("");
                }
            }
        });


        editText10.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    if (editText1.length() != 0) {

                        try {
                            double value = Double.parseDouble(String.valueOf(Integer.valueOf(editText1.getText().toString()) -
                                    Integer.valueOf(editText10.getText().toString())));

                            if (value < 0) {
                                System.out.println(value + " is negative");
                                editText11.setText("");
                                Toast.makeText(MainActivity.this, getString(R.string.wrong_vehicle_weight), Toast.LENGTH_SHORT).show();

                            } else {
                                System.out.println(value + " is possitive");
                                editText11.setText(String.valueOf(Integer.valueOf(editText1.getText().toString()) -
                                        Integer.valueOf(editText10.getText().toString())));
                            }
                        } catch (NumberFormatException e) {
                        }

                    }
                } else {
                    editText11.setText("");
                }
            }
        });

    }

    private void calculation() {


        double a = 0.03 * Integer.valueOf(editText11.getText().toString());

        double c = Integer.valueOf(editText11.getText().toString()) - a;

        double total_price = c * Double.valueOf(editText2.getText().toString());

        double piece_price = total_price / Integer.valueOf(editText3.getText().toString());

        double avg_weight = c / Integer.valueOf(editText3.getText().toString());


        if (avg_weight < 1) {

            String Str = new String(String.valueOf(avg_weight));

            try {
                textView7.setText(getString(R.string.avg_weight) + " : " + Str.substring(2, 5) + " " + getString(R.string.grams));
            } catch (Exception e) {

                textView7.setText(getString(R.string.avg_weight) + " : " + Str.substring(2, 4) + "0" + " " + getString(R.string.grams));
            }

        } else {

            double value = Double.parseDouble(new DecimalFormat("##.###").format(avg_weight));
            textView7.setText(getString(R.string.avg_weight) + " : " + String.valueOf(value) + " " + getString(R.string.Kilo));


        }


        int a_1 = (int) Math.round(a);
        int a_2 = (int) Math.round(total_price);
        //int a_3 = (int) Math.round(avg_weight);

        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

        String moneyString = formatter.format(a_2);
        String one_coconut_price = formatter.format(piece_price);

        textView4.setText(getString(R.string.Coconut_waste) + " : " + String.valueOf(a_1) + " " + getString(R.string.Kilo));
        textView5.setText(getString(R.string.total_amount) + " : " + String.valueOf(moneyString));
        textView6.setText(getString(R.string.One_coconut_price) + " : " + String.valueOf(one_coconut_price));


        System.out.println(getString(R.string.Coconut_waste) + " : " + String.valueOf(a_1) + " " + getString(R.string.Kilo));
        System.out.println(getString(R.string.total_amount) + " : " + String.valueOf(moneyString));
        System.out.println(getString(R.string.One_coconut_price) + " : " + String.valueOf(one_coconut_price));


        waste = textView4.getText().toString();
        totalamnount = textView5.getText().toString();
        percoconut = textView6.getText().toString();
        weight = textView7.getText().toString();
        coconutweight =getString(R.string.coco_weight) + " : " +editText11.getText().toString()+ " " + getString(R.string.Kilo);
        coconutprice = getString(R.string.price) + " : " +editText2.getText().toString()+ " " + getString(R.string.thousand);
        totalcoconut = getString(R.string.total_coco) + " : " +editText3.getText().toString();


    }

    @OnClick({R.id.button, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (editText1.length() == 0) {
                    editText1.requestFocus();
                    editText1.setError(getString(R.string.wrong));
                } else if (editText10.length() == 0) {
                    editText10.requestFocus();
                    editText10.setError(getString(R.string.wrong));
                } else if (editText11.length() == 0) {
                    //editText11.requestFocus();
                    //editText11.setError("தவறு");
                    Toast.makeText(this, getString(R.string.wrong), Toast.LENGTH_SHORT).show();
                } else if (editText2.length() == 0) {
                    editText2.requestFocus();
                    editText2.setError(getString(R.string.wrong));
                } else if (editText3.length() == 0) {
                    editText3.requestFocus();
                    editText3.setError(getString(R.string.wrong));
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
                button2.setVisibility(View.VISIBLE);
                Notes.setVisibility(View.VISIBLE);
                textNotes.setVisibility(View.VISIBLE);

                break;
            case R.id.button2:
                Calendar c1 = Calendar.getInstance();
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
                String datetime = dateformat.format(c1.getTime());
                System.out.println(datetime);
                notes = textNotes.getText().toString();
                today = datetime;
                SaveTask st = new SaveTask();
                st.execute();
                break;
        }
    }

    class SaveTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //creating a task
            Task task = new Task();
            task.setWaste(waste);
            task.setTotalamount(totalamnount);
            task.setPercoconut(percoconut);
            task.setDate(today);
            task.setNotes(notes);
            task.setCoconutweight(coconutweight);
            task.setCoconutprice(coconutprice);
            task.setTotalcoconut(totalcoconut);

            //adding to database
            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .taskDao()
                    .insert(task);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, R.string.saved, Toast.LENGTH_SHORT).show();
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
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return true;
            case R.id.menu_db:
                Intent intent1 = new Intent(this, NotesActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
