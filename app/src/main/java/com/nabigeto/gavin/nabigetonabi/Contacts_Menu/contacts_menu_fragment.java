package com.nabigeto.gavin.nabigetonabi.Contacts_Menu;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModel;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

public class contacts_menu_fragment extends Fragment implements contactsitemtouchhelper.RecyclerItemTouchHelperListener {


    public String user_id;
    public String name;
    public String email;

    public DatabaseReference mReference;
    public RecyclerView mrecyclerview;

    public Boolean alertdialog;

    public String position_key_ref;

  /**  con
    contacts_viewmodel mViewModel;
**/

    ArrayList<String> arrayList = new ArrayList<String>();

    public contacts_menu_fragment() {
        // Required empty public constructor
    }


    public FirebaseRecyclerOptions<contacts_menu_model> options;
    public FirebaseRecyclerAdapter<contacts_menu_model, contacts_recyclerviewholder_m> adapter;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


/**
        mViewModel = ViewModelProviders.of(this).get(contacts_viewmodel.class);
**/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_contacts_menu, container, false);

        Log.v("Gavin", "Contacts Menu Fragment - Creating");

        Log.v("Gavin", "Contacts Menu Fragment - Inflating");

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        user_id = currentUser.getUid();

        Log.v("Gavin", "Contacts Menu Fragment - Getting user" + user_id);

        mReference  = FirebaseDatabase.getInstance().getReference().child("contacts").child(user_id);


        if (mReference == null){
            Log.v("Gavin", "Contacts Menu Fragment " + "Error reference is null");

        }

/**
 mReference.keepSynced(true);

 **/






        options = new FirebaseRecyclerOptions.Builder<contacts_menu_model>().setQuery(mReference, contacts_menu_model.class).setLifecycleOwner(getActivity()).build();



        adapter = new FirebaseRecyclerAdapter<contacts_menu_model, contacts_recyclerviewholder_m>(options) {
            @Override
            protected void onBindViewHolder(@NonNull contacts_recyclerviewholder_m holder, int position, @NonNull contacts_menu_model model) {

                String position_number = Integer.toString(position);


                Log.v("Gavin", "Reminder Menu Fragment " + model.name);
                Log.v("Gavin", "Reminder Menu Fragment " + model.email);


                position_key_ref = options.getSnapshots().getSnapshot(position).getKey();
                Log.v("Gavin", "contacts_menu_fragment " + "key_ref " + position_key_ref);
                arrayList.add(position_key_ref);


                holder.mname.setText(model.name);
                holder.memail.setText(model.email);
            }

            @NonNull
            @Override
            public contacts_recyclerviewholder_m onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_menu_item, parent, false);
                return new contacts_recyclerviewholder_m(view);
            }
        };

        Log.v("Gavin", "Reminder Menu Fragment - Build Query");

        mrecyclerview = rootView.findViewById(R.id.crecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mrecyclerview.setLayoutManager(linearLayoutManager);
        adapter.startListening();
        mrecyclerview.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new contactsitemtouchhelper(0, ItemTouchHelper.LEFT, this);
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
            Log.v("Gavin", "Contacts Menu Fragment " + "Adapter Error");
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
/**
        boolean alertdialog = mViewModel.isDelete_contact();

        String check_1 = String.valueOf(alertdialog);
        Log.v("Gavin", "contacts_menu_fragment"+ " onswiped 3a" + check_1);
        mViewModel.setDelete_contact(true);

        alertdialog = mViewModel.isDelete_contact();

        check_1 = String.valueOf(alertdialog);



        Log.v("Gavin", "contacts_menu_fragment"+ " onswiped 3" + check_1);
        Log.v("Gavin", "contacts_menu_fragment"+ " onswiped");



        DialogFragment dialogFragment = new contacts_alertdialog();
        dialogFragment.show(getFragmentManager(), "launch");

        Boolean t = dialogFragment.isVisible();
   **/

                String keyref = arrayList.get(position);
                Log.v("Gavin", "contacts_menu_fragment " + " keyref1 " + keyref);
                FirebaseDatabase.getInstance().getReference().child("contacts").child(user_id).child(keyref).removeValue();
                Log.v("Gavin", "contacts_menu_fragment"+ " onswiped 4");
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            Log.v("Gavin", "contacts_menu_fragment"+ " onswiped 5");
            adapter.notifyDataSetChanged();






    }


}
