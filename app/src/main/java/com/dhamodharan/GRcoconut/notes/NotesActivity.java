package com.dhamodharan.GRcoconut.notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dhamodharan.GRcoconut.MainActivity;
import com.dhamodharan.GRcoconut.R;
import com.dhamodharan.GRcoconut.dbconnection.DatabaseClient;
import com.dhamodharan.GRcoconut.dbconnection.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NotesActivity extends AppCompatActivity implements NotesAdapter.AdapterCallback {


    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getTasks();

    }


    public void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Task>> {

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                adapter = new NotesAdapter(tasks,  getApplicationContext(), NotesActivity.this);
                recyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    @Override
    public void onItemClicked(Task task) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getString(R.string.delete_alret));
        alertDialogBuilder.setPositiveButton(getString(R.string.yes),
                (arg0, arg1) -> {
                    deleteTask(task);
                });

        alertDialogBuilder.setNegativeButton(getString(R.string.no), (dialog, which) ->{});

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void deleteTask(final Task task) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(NotesActivity.this, R.string.deleted, Toast.LENGTH_SHORT).show();
                getTasks();
            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();

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
                alret_dialog(getString(R.string.delete_all_alret));
                return true;
            case R.id.menu_db:
                finish();
                Intent intent=new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void deleteDB() {
        class DeleteBD extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .nukeTable();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(NotesActivity.this, R.string.delete_all, Toast.LENGTH_LONG).show();
                getTasks();
            }
        }

        DeleteBD dt = new DeleteBD();
        dt.execute();

    }


    public void alret_dialog(String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(message);
                alertDialogBuilder.setPositiveButton(getString(R.string.yes),
                        (arg0, arg1) -> {
                            deleteDB();
                        });

        alertDialogBuilder.setNegativeButton(getString(R.string.no), (dialog, which) ->{});

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
