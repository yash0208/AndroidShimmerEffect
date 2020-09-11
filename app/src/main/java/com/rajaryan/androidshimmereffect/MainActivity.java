package com.rajaryan.androidshimmereffect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {
    RecyclerView rec;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rec=findViewById(R.id.rec);
        rec.setLayoutManager(new LinearLayoutManager(this));
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        Query query= FirebaseDatabase.getInstance().getReference().child("Workshop");
        FirebaseRecyclerOptions<WorkshopClass> options =
                new FirebaseRecyclerOptions.Builder<WorkshopClass>()
                        .setQuery(query,WorkshopClass.class)
                        .build();
        adapter=new Adapter(options);
        rec.setAdapter(adapter);
        adapter.startListening();

    }
    public class Adapter extends FirebaseRecyclerAdapter<WorkshopClass,Adapter.viewholder> {
        /**
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        public Adapter(@NonNull FirebaseRecyclerOptions<WorkshopClass> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull viewholder viewholder, int i, @NonNull final WorkshopClass workshopClass) {
            viewholder.Name.setText(workshopClass.getName());
            viewholder.Teacher.setText(workshopClass.getTeacher());
            Glide.with(getApplicationContext()).load(workshopClass.getImage()).centerCrop().into(viewholder.image);

        }

        @NonNull
        @Override
        public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.itemview, parent, false);

            return new Adapter.viewholder(view);
        }

        public class viewholder extends RecyclerView.ViewHolder {
            ImageView image;
            TextView Name, Teacher;
            Button join;

            public viewholder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.image);
                Name = itemView.findViewById(R.id.name);
                Teacher = itemView.findViewById(R.id.teacher);
                join = itemView.findViewById(R.id.done);
            }
        }
    }}
