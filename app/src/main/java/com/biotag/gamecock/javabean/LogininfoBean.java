package com.biotag.gamecock.javabean;

/**
 * Created by Lxh on 2017/8/17.
 */

public class LogininfoBean {

    /**
     * IsSuccess : true
     * Values : {"id":10003,"ownerName":"lili","oTitle":2,"otitle":null,"oAddress":"fuyang anhui","oTel":"13985462587","oIDNo":"3421586854254789","ownDate":"2017-08-11T00:00:00","areaName":null,"dictName":null,"oDictionID":1,"isPersonal":false,"username":"lili","password":"123123"}
     * Status : 1
     * TotalCount : 0
     */

    private boolean IsSuccess;
    private ValuesBean Values;
    private int Status;
    private int TotalCount;

    public boolean isIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(boolean IsSuccess) {
        this.IsSuccess = IsSuccess;
    }

    public ValuesBean getValues() {
        return Values;
    }

    public void setValues(ValuesBean Values) {
        this.Values = Values;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int TotalCount) {
        this.TotalCount = TotalCount;
    }

    public static class ValuesBean {
        /**
         * id : 10003
         * ownerName : lili
         * oTitle : 2
         * otitle : null
         * oAddress : fuyang anhui
         * oTel : 13985462587
         * oIDNo : 3421586854254789
         * ownDate : 2017-08-11T00:00:00
         * areaName : null
         * dictName : null
         * oDictionID : 1
         * isPersonal : false
         * username : lili
         * password : 123123
         */

        private int id;
        private String ownerName;
        private int oTitle;
        private Object otitle;
        private String oAddress;
        private String oTel;
        private String oIDNo;
        private String ownDate;
        private Object areaName;
        private Object dictName;
        private int oDictionID;
        private boolean isPersonal;
        private String username;
        private String password;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public int getOTitle() {
            return oTitle;
        }

        public void setOTitle(int oTitle) {
            this.oTitle = oTitle;
        }

        public Object getOtitle() {
            return otitle;
        }

        public void setOtitle(Object otitle) {
            this.otitle = otitle;
        }

        public String getOAddress() {
            return oAddress;
        }

        public void setOAddress(String oAddress) {
            this.oAddress = oAddress;
        }

        public String getOTel() {
            return oTel;
        }

        public void setOTel(String oTel) {
            this.oTel = oTel;
        }

        public String getOIDNo() {
            return oIDNo;
        }

        public void setOIDNo(String oIDNo) {
            this.oIDNo = oIDNo;
        }

        public String getOwnDate() {
            return ownDate;
        }

        public void setOwnDate(String ownDate) {
            this.ownDate = ownDate;
        }

        public Object getAreaName() {
            return areaName;
        }

        public void setAreaName(Object areaName) {
            this.areaName = areaName;
        }

        public Object getDictName() {
            return dictName;
        }

        public void setDictName(Object dictName) {
            this.dictName = dictName;
        }

        public int getODictionID() {
            return oDictionID;
        }

        public void setODictionID(int oDictionID) {
            this.oDictionID = oDictionID;
        }

        public boolean isIsPersonal() {
            return isPersonal;
        }

        public void setIsPersonal(boolean isPersonal) {
            this.isPersonal = isPersonal;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
