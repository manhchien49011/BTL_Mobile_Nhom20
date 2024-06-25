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
    // variables for features
   // private FirebaseDatabase database;
   // private DatabaseReference reference;
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
        getListResignationLettersFromFirebase();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void getListResignationLettersFromFirebase() {
//        database = FirebaseDatabase.getInstance();
//        reference = database.getReference();

        listResignationLettersFromDatabase.addAll(reponsitoryLetter.getByWorkspacesId(workspaceId));
        resignationLettersAdapter.notifyDataSetChanged();

//        reference.child("Letters").child(String.valueOf(workspaceId)).addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                listResignationLettersFromDatabase.clear();
//                for (DataSnapshot data : snapshot.getChildren()){
//                    Letter letter = data.getValue(Letter.class);
//                    listResignationLettersFromDatabase.add(letter);
//                }
//
//                resignationLettersAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) { // if error
//                Toast.makeText(getApplicationContext(),"Get list resignation letters from firebase is fail",Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}