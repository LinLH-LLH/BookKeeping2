package com.example.bookkeeping;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class CreateItemDialogFragment extends DialogFragment {
    private static final String TAG="DialogFragment";
    private TextInputEditText create_title,create_subtitle;
    private Spinner spinner;
    private Button btn_reset,btn_submit,btn_cancel;
    private String income_expense[]=new String[]{"請選擇紀錄類型","收入","支出"};
    private MyBookKeepDBManage manageDB;
    private RecyclerView recyclerView;
    Calendar calendar=Calendar.getInstance();
    public CreateItemDialogFragment(MyBookKeepDBManage manage,RecyclerView recyclerView){
        manageDB=manage;
        this.recyclerView=recyclerView;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view=inflater.inflate(R.layout.createlayout, null);
        findViews(view);
        builder.setView(view);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getBaseContext(), "資料已建立", Toast.LENGTH_SHORT).show();
                String title=create_title.getText().toString();
                Log.d(TAG,create_subtitle.getText().toString());
                int value=Integer.parseInt(create_subtitle.getText().toString());
                String type=spinner.getSelectedItem().toString();
                manageDB.insertData("BOOKKEEP",title,value,type
                        ,Integer.parseInt(getCalendar().split("/")[0])
                        ,Integer.parseInt(getCalendar().split("/")[1])
                        ,Integer.parseInt(getCalendar().split("/")[2]));
                DBTool.updateRecyclerView(getContext(),manageDB,recyclerView,"BOOKKEEP",getFragmentManager());
                CreateItemDialogFragment.this.getDialog().cancel();
            }

            private String getCalendar() {
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                String text=year+"/"+month+"/"+day;
                return  text;
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getBaseContext(), "資料已取消", Toast.LENGTH_SHORT).show();
                CreateItemDialogFragment.this.getDialog().cancel();
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_title.setText("");
                create_subtitle.setText("");
                spinner.setSelection(0);
            }
        });
        return builder.create();

    }

    private void findViews(View view) {
        create_title=(TextInputEditText)view.findViewById(R.id.create_title);
        create_subtitle=(TextInputEditText)view.findViewById(R.id.create_subtitle);
        spinner=(Spinner) view.findViewById(R.id.spinner);
        btn_cancel=(Button)view.findViewById(R.id.createlayout_btn_cancel);
        btn_submit=(Button)view.findViewById(R.id.createlayout_btn_submit);
        btn_reset=(Button)view.findViewById(R.id.createlayout_btn_reset);
        spinner.setAdapter(new ArrayAdapter<String>
                (getActivity().getBaseContext(),android.R.layout.simple_expandable_list_item_1,income_expense));
    }
}
