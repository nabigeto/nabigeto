package com.nabigeto.gavin.nabigetonabi.Contacts_Menu;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nabigeto.gavin.nabigetonabi.R;

public class contacts_recyclerviewholder_m extends RecyclerView.ViewHolder {

    public TextView mname;
    public TextView memail;

    public contacts_recyclerviewholder_m(View itemView) {
        super(itemView);

        mname = (TextView) itemView.findViewById(R.id.contact_name_m);
        memail = (TextView) itemView.findViewById(R.id.contact_email_m);


    }
}
