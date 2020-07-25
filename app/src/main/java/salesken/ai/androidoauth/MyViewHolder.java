package salesken.ai.androidoauth;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyViewHolder extends RecyclerView.ViewHolder  {
    private Context context;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.name)
    TextView name;


    public MyViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this, itemView);
    }
    public void render(DataPojo dataPojo) {
        age.setText(dataPojo.getAge());
        name.setText(dataPojo.getName());
    }
}
