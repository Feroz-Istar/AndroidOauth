package salesken.ai.androidoauth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<DataPojo> dataPojos;

    public MyRecyclerAdapter(Context context, List<DataPojo> dataPojos) {
        this.context = context;
        this.dataPojos = dataPojos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new MyViewHolder(context,itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.render(dataPojos.get(position));
    }

    @Override
    public int getItemCount() {
        return dataPojos.size();
    }
}
