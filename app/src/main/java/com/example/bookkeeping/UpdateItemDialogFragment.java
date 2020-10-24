package com.example.bookkeeping;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

public class UpdateItemDialogFragment extends DialogFragment {
    private static final String TAG = "DialogFragment";
    private TextInputEditText create_title, create_subtitle;
    private Spinner spinner;
    private Button btn_reset, btn_update, btn_cancel;
    private String income_expense[] = new String[]{"請選擇紀錄類型", "收入", "支出"};
    private MyBookKeepDBManage manageDB;
    private int _id;
    private String title, subtitle, type;
    private RecyclerView recyclerView;

    public UpdateItemDialogFragment(MyBookKeepDBManage manage, RecyclerView recyclerView) {
        manageDB = manage;
        this.recyclerView = recyclerView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        _id = getArguments().getInt("_id");
        title = getArguments().getString("title");
        subtitle = getArguments().getString("subtitle");
        type = getArguments().getString("type");
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.updatelayout, null);
        findViews(view);
        setOriginalData();
        builder.setView(view);
        setOnClickListener();
        return builder.create();

    }

    private void setOnClickListener() {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getBaseContext(), "資料已更新", Toast.LENGTH_SHORT).show();
                String title = create_title.getText().toString();
                Log.d(TAG, create_subtitle.getText().toString());
                int value = Integer.parseInt(create_subtitle.getText().toString());
                String type = spinner.getSelectedItem().toString();
                manageDB.updateData("BOOKKEEP", _id, title, value, type);
                UpdateItemDialogFragment.this.getDialog().cancel();
                /*更新畫面-資料庫2.0-資料內容(ID 標題 金額 型態 圖片 日期)*/
                DBTool.updateRecyclerView2(getContext(), manageDB, recyclerView, "BOOKKEEP", getFragmentManager());
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getBaseContext(), "資料已取消", Toast.LENGTH_SHORT).show();
                UpdateItemDialogFragment.this.getDialog().cancel();
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
    }

    private void setOriginalData() {
        create_title.setText(title);
        create_subtitle.setText(subtitle);
        spinner.setSelection(type.equals("收入")?1:2);

    }

    private void findViews(View view) {
        create_title = (TextInputEditText) view.findViewById(R.id.create_title);
        create_subtitle = (TextInputEditText) view.findViewById(R.id.create_subtitle);
        spinner = (Spinner) view.findViewById(R.id.updatelayout_spinner);
        btn_cancel = (Button) view.findViewById(R.id.updatelayout_btn_cancel);
        btn_update = (Button) view.findViewById(R.id.updatelayout_btn_update);
        btn_reset = (Button) view.findViewById(R.id.updatelayout_btn_reset);
        spinner.setAdapter(new ArrayAdapter<String>
                (getActivity().getBaseContext(), android.R.layout.simple_expandable_list_item_1, income_expense));
    }
}
