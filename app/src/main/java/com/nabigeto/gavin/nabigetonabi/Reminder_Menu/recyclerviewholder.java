package com.nabigeto.gavin.nabigetonabi.Reminder_Menu;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.nabigeto.gavin.nabigetonabi.R;

/**
 * Created by Gavin on 7/24/2018.
 */

public class recyclerviewholder extends RecyclerView.ViewHolder {

    public TextView mdate;
    public TextView mReminder_title;
    public TextView mReminder_content;
    public TextView mKey;

    public recyclerviewholder(View itemView){
        super(itemView);

        mReminder_title = (TextView) itemView.findViewById(R.id.reminder_title_m);
        mdate = (TextView) itemView.findViewById(R.id.reminder_date_m);
        mReminder_content = (TextView) itemView.findViewById(R.id.reminder_content_m);

    }




/**
    public void bind(adapter_model model){

        setmUser_id(model.getUser_id());
        setmReminder_title(model.getReminder_title());
        setmReminder_content(model.getReminder_content());
    }

    private void setmUser_id (String name){
        mUser_id.setText(name);
    }

    private void setmReminder_title(String date){
        mReminder_title.setText(date);
    }

    private void setmReminder_content(String type){
        mReminder_content.setText(type);
    }
**/
}
