package activitytest.com.example.chatclient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Timer;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> mMsgList;

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
        ImageView leftImage;
        ImageView rightImage;
        TextView leftName;
        TextView rightName;
        TextView leftMin;
        TextView rightMin;

        public ViewHolder( View itemView) {
            super(itemView);
            leftLayout = itemView.findViewById(R.id.left_layout);
            rightLayout = itemView.findViewById(R.id.right_layout);
            leftMsg = itemView.findViewById(R.id.left_msg);
            rightMsg = itemView.findViewById(R.id.right_msg);
            leftImage=itemView.findViewById(R.id.left_image);
            rightImage=itemView.findViewById(R.id.right_image);
            leftName=itemView.findViewById(R.id.left_name);
            rightName=itemView.findViewById(R.id.right_name);
            leftMin=itemView.findViewById(R.id.left_min);
            rightMin=itemView.findViewById(R.id.right_min);
        }
    }

    public MsgAdapter(List<Msg> mMsgList) {
        this.mMsgList = mMsgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.msg_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {           //若收到消息，则显示左侧布局，若是发送的，显示右侧布局
        Msg msg = mMsgList.get(i);
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.rightMin.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msg.getContent());
            viewHolder.leftImage.setImageResource(msg.getImageId());
            viewHolder.leftName.setText(msg.getName());
            viewHolder.leftMin.setText(msg.getMin());
        } else {
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftMin.setVisibility(View.GONE);
            viewHolder.rightMsg.setText(msg.getContent());
            viewHolder.rightImage.setImageResource(msg.getImageId());
            viewHolder.rightName.setText(msg.getName());
            viewHolder.rightMin.setText(msg.getMin());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}
