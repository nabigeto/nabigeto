package com.nabigeto.gavin.nabigetonabi.Reminder_Menu;

import android.graphics.Canvas;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.View;


public class reminderitemstouchhelper extends ItemTouchHelper.SimpleCallback{


        private RecyclerItemTouchHelperListener listener;

        public reminderitemstouchhelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener){
            super(dragDirs, swipeDirs);
            this.listener = listener;

        }

        public interface RecyclerItemTouchHelperListener {
            void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){

            return true;
        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState){

            if (viewHolder != null){
                final View foregroundView = ((recyclerviewholder) viewHolder).itemView;
                getDefaultUIUtil().onSelected(foregroundView);
            }

        }

        @Override
        public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
            final View foregroundView = ((recyclerviewholder) viewHolder).itemView;
            getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder){
            final View foregroundView = ((recyclerviewholder) viewHolder).itemView;
            getDefaultUIUtil().clearView(foregroundView);
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
            final View foregroundView = ((recyclerviewholder) viewHolder).itemView;
            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction){
            listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
        }

        @Override
        public int convertToAbsoluteDirection(int flags, int layoutDirection){
            return super.convertToAbsoluteDirection(flags, layoutDirection);

        }

}
