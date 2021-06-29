package activitytest.com.example.retrofit;

import com.google.gson.Gson;

public class Login {

    /**
     * status : 0
     * msg : 登录成功
     * data : {"id":23,"name":"111","phone":"111","password":"","sex":null,"msg":null,"img":null,"updateTime":"2021-03-29T16:03:05.000+00:00","createTime":"2021-03-29T16:03:05.000+00:00"}
     * success : true
     */

    private int status;
    private String msg;
    private DataBean data;
    private boolean success;

    public static Login objectFromData(String str) {

        return new Gson().fromJson(str, Login.class);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * id : 23
         * name : 111
         * phone : 111
         * password :
         * sex : null
         * msg : null
         * img : null
         * updateTime : 2021-03-29T16:03:05.000+00:00
         * createTime : 2021-03-29T16:03:05.000+00:00
         */

        private int id;
        private String name;
        private String phone;
        private String password;
        private Object sex;
        private Object msg;
        private Object img;
        private String updateTime;
        private String createTime;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getMsg() {
            return msg;
        }

        public void setMsg(Object msg) {
            this.msg = msg;
        }

        public Object getImg() {
            return img;
        }

        public void setImg(Object img) {
            this.img = img;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
