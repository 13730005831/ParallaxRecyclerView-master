package com.otitan.coordinatorlayouttest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.otitan.coordinatorlayouttest.R;

import java.util.ArrayList;

public class Activity1 extends AppCompatActivity {

    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("第" + i + "个条目");
        }

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter(data);
        recyclerview.setAdapter(adapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        ArrayList<String> data;

        public MyAdapter(ArrayList<String> data) {
            this.data = data;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            private final TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv);
            }
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = View.inflate(parent.getContext(),R.layout.item_1,null);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
