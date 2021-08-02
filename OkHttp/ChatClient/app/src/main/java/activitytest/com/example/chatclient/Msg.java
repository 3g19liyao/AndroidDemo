package activitytest.com.example.chatclient;

import org.litepal.crud.DataSupport;

public class Msg extends DataSupport {
    public static final int TYPE_SENT = 1;
    public static final int TYPE_RECEIVED = 0;
    private String min;
    public String content;
    private String name;
    public int imageId;
    private int type;

    public Msg(String content,String imageId,String name ,String min,int type) {
        this.content = content;
        this.type = type;
        this.name=name;
        this.min=min;
        switch (imageId){
            case "1":this.imageId=R.drawable.a1;break;
            case "2":this.imageId=R.drawable.a2;break;
            case "3":this.imageId=R.drawable.a3;break;
            case "4":this.imageId=R.drawable.a4;break;
            case "5":this.imageId=R.drawable.a5;break;
            case "6":this.imageId=R.drawable.a6;break;
            case "7":this.imageId=R.drawable.a7;break;
            case "8":this.imageId=R.drawable.a8;break;
            case "9":this.imageId=R.drawable.a9;break;
            default:break;
        }
    }

    public String getContent() {
        return content;
    }
    public String getMin(){
        return min;
    }
    public int getType() {
        return type;
    }

    public int getImageId(){
        return imageId;
    }
    public String getName(){
        return name;
    }
    public void setType(int type){
        this.type=type;
    }
}
