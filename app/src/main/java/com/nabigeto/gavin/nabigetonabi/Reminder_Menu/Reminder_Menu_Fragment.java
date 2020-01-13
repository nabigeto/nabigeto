package com.nabigeto.gavin.nabigetonabi.Reminder_Menu;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nabigeto.gavin.nabigetonabi.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Reminder_Menu_Fragment extends Fragment implements reminderitemstouchhelper.RecyclerItemTouchHelperListener {

    public String user_id;
    public DatabaseReference mReference;
    public RecyclerView mrecyclerview;

    ArrayList<String> arrayList = new ArrayList<String>();

    public String position_key_ref;

    private int pos;

    private boolean t = false;
    private String key;

    OnTouchlistner callback;

    public Reminder_Menu_Fragment() {
        // Required empty public constructor
    }

    public void setOnTouchListner(OnTouchlistner callback){
        this.callback = callback;
    }


    public interface OnTouchlistner{
        public void edit_reminder_m(boolean edit_reminder, String reminderkey);

    }

    public FirebaseRecyclerOptions<adapter_model> options;
    public FirebaseRecyclerAdapter<adapter_model, recyclerviewholder> adapter;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



    }

    private final View.OnClickListener monclicklistner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            t = true;


      pos = mrecyclerview.getChildAdapterPosition(v);

      adapter.getItem(pos);

      adapter_model Adapter_model = new adapter_model();

      Adapter_model.reminder_title = adapter.getItem(pos).reminder_title;
      Adapter_model.reminder_content = adapter.getItem(pos).reminder_content;
      Adapter_model.user_id = adapter.getItem(pos).user_id;
      Adapter_model.reminder_position = adapter.getItem(pos).reminder_position;
      Adapter_model.reminder_keyposition = adapter.getItem(pos).reminder_keyposition;


        callback.edit_reminder_m(true, Adapter_model.reminder_keyposition);


      Log.v("Gavin", "Reminder_Menu_Fragment - title and key " + Adapter_model.reminder_title + " " + Adapter_model.reminder_keyposition);





        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_reminder__menu_, container, false);

        Log.v("Gavin", "Reminder Menu Fragment - Creating");

        Log.v("Gavin", "Reminder Menu Fragment - Inflating");

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        user_id = currentUser.getUid();

        Log.v("Gavin", "Reminder Menu Fragment - Getting user" + user_id);

        mReference  = FirebaseDatabase.getInstance().getReference().child("reminder").child(user_id);


        if (mReference == null){
            Log.v("Gavin", "Reminder MEnu Fragment " + "Error reference is null");

        }


        mReference.keepSynced(true);








        options = new FirebaseRecyclerOptions.Builder<adapter_model>().setQuery(mReference, adapter_model.class).setLifecycleOwner(getActivity()).build();

        adapter = new FirebaseRecyclerAdapter<adapter_model, recyclerviewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull recyclerviewholder holder, int position, @NonNull adapter_model model) {

                String position_number = Integer.toString(position);

                Log.v("Gavin", "Reminder Menu Fragment " + position_number);
                Log.v("Gavin", "Reminder Menu Fragment " + model.reminder_title);
                Log.v("Gavin", "Reminder Menu Fragment " + model.mtime);
                Log.v("Gavin", "Reminder Menu Fragment " + model.reminder_content);

                position_key_ref = options.getSnapshots().getSnapshot(position).getKey();
                Log.v("Gavin", "contacts_menu_fragment " + "key_ref " + position_key_ref);
                arrayList.add(position_key_ref);

                model.reminder_keyposition = position_key_ref;

                holder.mReminder_title.setText(model.reminder_title);
                holder.mdate.setText(model.mtime);
                holder.mReminder_content.setText(model.reminder_content);

            }

            @NonNull
            @Override
            public recyclerviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_menu_item, parent, false);

                view.setOnClickListener(monclicklistner);

                return new recyclerviewholder(view);
            }
        };

        Log.v("Gavin", "Reminder Menu Fragment - Build Query");

        mrecyclerview = rootView.findViewById(R.id.mrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mrecyclerview.setLayoutManager(linearLayoutManager);
        adapter.startListening();
        mrecyclerview.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new reminderitemstouchhelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(mrecyclerview);

        return rootView;
    }


    @Override
    public void onStart(){
        super.onStart();
        if(adapter != null) {
            adapter.startListening();
        }
        else{
            Log.v("Gavin", "Reminder Menu Fragment " + "Adapter Error");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(adapter != null){
            adapter.startListening();
        }
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position){


        String keyref = arrayList.get(position);
        Log.v("Gavin", "contacts_menu_fragment " + " keyref1 " + keyref);
        FirebaseDatabase.getInstance().getReference().child("reminder").child(user_id).child(keyref).removeValue();
        Log.v("Gavin", "contacts_menu_fragment"+ " onswiped 4");
        adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

        Log.v("Gavin", "contacts_menu_fragment"+ " onswiped 5");
        adapter.notifyDataSetChanged();






    }

}
