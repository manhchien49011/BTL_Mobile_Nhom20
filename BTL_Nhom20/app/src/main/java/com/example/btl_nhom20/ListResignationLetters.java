package com.example.btl_nhom20;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom20.Reponsity.ReponsitoryLetter;
import com.example.btl_nhom20.adapter.ResignationLettersAdapter;
import com.example.btl_nhom20.model.letter.Letter;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListResignationLetters extends AppCompatActivity {
    ReponsitoryLetter reponsitoryLetter;
    private ArrayList<Letter> listResignationLettersFromDatabase;
    private int workspaceId;

    // variables for view
    private String txtViewTypeOfLetters;
    private String txtViewNameStaff;
    private ResignationLettersAdapter resignationLettersAdapter;
    private RecyclerView recyclerViewListResignationLetters;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_resignation_letters);

        reponsitoryLetter = new ReponsitoryLetter(this);

        listResignationLettersFromDatabase = new ArrayList<Letter>();

        // set by id
        recyclerViewListResignationLetters = findViewById(R.id.recyclerViewListResignationLetters);
        btnBack = findViewById(R.id.btnBackPressed);

        // get data in bundle
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        workspaceId = bundle.getInt("wspID");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewListResignationLetters.setLayoutManager(linearLayoutManager);

        // set view adapter
        resignationLettersAdapter = new ResignationLettersAdapter(listResignationLettersFromDatabase, getApplicationContext());
        recyclerViewListResignationLetters.setAdapter(resignationLettersAdapter);

        // processing
        getListResignationLettersFromDatabase();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void getListResignationLettersFromDatabase() {

        listResignationLettersFromDatabase.addAll(reponsitoryLetter.getByWorkspacesId(workspaceId));
        resignationLettersAdapter.notifyDataSetChanged();



    }
}