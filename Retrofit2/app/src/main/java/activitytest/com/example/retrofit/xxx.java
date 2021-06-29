package activitytest.com.example.retrofit;

import com.google.gson.Gson;

public class xxx {

    private String statusCode;
    private String desc;
    private ResultBean result;

    public static xxx objectFromData(String str) {

        return new Gson().fromJson(str, xxx.class);
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String province;
        private String city;
        private String areacode;
        private String zip;
        private String company;
        private String card;

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAreacode() {
            return areacode;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }
    }
}
