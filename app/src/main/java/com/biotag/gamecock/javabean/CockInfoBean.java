package com.biotag.gamecock.javabean;

/**
 * Created by Lxh on 2017/8/17.
 */

public class CockInfoBean {

    /**
     * IsSuccess : true
     * Values : {"ID":21,"chipCode":"943000100000001","cockNo":"0000000001","breedID":1,"memo":"brmark","birth":"2017-05-04T00:00:00","orgID":0,"cStatus":1,"farmID":1,"creatorid":1,"createAt":"2017-08-16T13:13:09.74","lastOwnerID":10001,"regDate1":"0001-01-01T00:00:00","regDate2":"0001-01-01T00:00:00","regDate3":"0001-01-01T00:00:00","isImport":true,"importerID":1,"mFarmName":"zhuhai","OrgName":"","ownerName":"Edmonds"}
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
         * ID : 21
         * chipCode : 943000100000001
         * cockNo : 0000000001
         * breedID : 1
         * memo : brmark
         * birth : 2017-05-04T00:00:00
         * orgID : 0
         * cStatus : 1
         * farmID : 1
         * creatorid : 1
         * createAt : 2017-08-16T13:13:09.74
         * lastOwnerID : 10001
         * regDate1 : 0001-01-01T00:00:00
         * regDate2 : 0001-01-01T00:00:00
         * regDate3 : 0001-01-01T00:00:00
         * isImport : true
         * importerID : 1
         * mFarmName : zhuhai
         * OrgName :
         * ownerName : Edmonds
         */

        private int ID;
        private String chipCode;
        private String cockNo;
        private int breedID;
        private String memo;
        private String birth;
        private int orgID;
        private int cStatus;
        private int farmID;
        private int creatorid;
        private String createAt;
        private int lastOwnerID;
        private String regDate1;
        private String regDate2;
        private String regDate3;
        private boolean isImport;
        private int importerID;
        private String mFarmName;
        private String OrgName;
        private String ownerName;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getChipCode() {
            return chipCode;
        }

        public void setChipCode(String chipCode) {
            this.chipCode = chipCode;
        }

        public String getCockNo() {
            return cockNo;
        }

        public void setCockNo(String cockNo) {
            this.cockNo = cockNo;
        }

        public int getBreedID() {
            return breedID;
        }

        public void setBreedID(int breedID) {
            this.breedID = breedID;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public int getOrgID() {
            return orgID;
        }

        public void setOrgID(int orgID) {
            this.orgID = orgID;
        }

        public int getCStatus() {
            return cStatus;
        }

        public void setCStatus(int cStatus) {
            this.cStatus = cStatus;
        }

        public int getFarmID() {
            return farmID;
        }

        public void setFarmID(int farmID) {
            this.farmID = farmID;
        }

        public int getCreatorid() {
            return creatorid;
        }

        public void setCreatorid(int creatorid) {
            this.creatorid = creatorid;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public int getLastOwnerID() {
            return lastOwnerID;
        }

        public void setLastOwnerID(int lastOwnerID) {
            this.lastOwnerID = lastOwnerID;
        }

        public String getRegDate1() {
            return regDate1;
        }

        public void setRegDate1(String regDate1) {
            this.regDate1 = regDate1;
        }

        public String getRegDate2() {
            return regDate2;
        }

        public void setRegDate2(String regDate2) {
            this.regDate2 = regDate2;
        }

        public String getRegDate3() {
            return regDate3;
        }

        public void setRegDate3(String regDate3) {
            this.regDate3 = regDate3;
        }

        public boolean isIsImport() {
            return isImport;
        }

        public void setIsImport(boolean isImport) {
            this.isImport = isImport;
        }

        public int getImporterID() {
            return importerID;
        }

        public void setImporterID(int importerID) {
            this.importerID = importerID;
        }

        public String getMFarmName() {
            return mFarmName;
        }

        public void setMFarmName(String mFarmName) {
            this.mFarmName = mFarmName;
        }

        public String getOrgName() {
            return OrgName;
        }

        public void setOrgName(String OrgName) {
            this.OrgName = OrgName;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }
    }
}
