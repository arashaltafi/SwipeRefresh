package com.arash.altafi.swiperefresh.java;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.arash.altafi.swiperefresh.R;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.UserHolder> {

    private final ArrayList<Users> userList;

    public AdapterUser(ArrayList<Users> userList) {
        this.userList = userList;
    }

    @NonNull
    @NotNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user , parent , false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserHolder holder, int position) {
        Users item = userList.get(position);
        holder.txtName.setText(item.name);
        holder.txtFamily.setText(item.family);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtFamily;

        public UserHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtFamily = itemView.findViewById(R.id.txt_family);
        }
    }
}

