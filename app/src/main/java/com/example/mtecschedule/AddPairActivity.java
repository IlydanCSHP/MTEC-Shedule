package com.example.mtecschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mtecschedule.dao.PairDao;
import com.example.mtecschedule.database.MtecRoomDatabase;
import com.example.mtecschedule.model.Pair;

public class AddPairActivity extends AppCompatActivity {

    EditText pairTitle;
    EditText pairTeacher;
    EditText pairRoom;
    EditText pairNumber;
    AppCompatButton addPairButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pair);
        getInputs();
        addPairButton = findViewById(R.id.add_pair_button);
        addPairButton.setOnClickListener(view -> addPair());
    }

    private void getInputs() {
        pairTitle = findViewById(R.id.pair_title);
        pairTeacher = findViewById(R.id.pair_teacher);
        pairRoom = findViewById(R.id.pair_room);
        pairNumber = findViewById(R.id.pair_number);
    }

    private void addPair() {
        new Thread(() -> {
            PairDao pairDao = MtecRoomDatabase.getInstance(this).pairDao();
            Pair pair = new Pair(pairTitle.getText().toString(),
                    pairTeacher.getText().toString(),
                    Integer.parseInt(pairRoom.getText().toString()),
                    Integer.parseInt(pairNumber.getText().toString()));
            pairDao.insert(pair);

            runOnUiThread(() -> {
                Toast.makeText(this, "Добавлено!", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }
}