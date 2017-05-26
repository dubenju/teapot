package org.teapot.db.orm.beetlsql.pojo;
import java.io.Serializable;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

/*
*
* gen by beetlsql 2017-05-21
*/
public class TblAuth   implements Serializable{
    private String appliStDt ;
    private String userId ;
    private String appliEdDt ;
    private String fstCrtDt ;
    private String fstCrtTm ;
    private String rntUpdDt ;
    private String rntUpdTm ;
    private String updUserId ;
    private String updVwId ;
    private String userPwd ;

    public TblAuth() {
    }

    public String getAppliStDt(){
        return  appliStDt;
    }
    public void setAppliStDt(String appliStDt ){
        this.appliStDt = appliStDt;
    }

    public String getUserId(){
        return  userId;
    }
    public void setUserId(String userId ){
        this.userId = userId;
    }

    public String getAppliEdDt(){
        return  appliEdDt;
    }
    public void setAppliEdDt(String appliEdDt ){
        this.appliEdDt = appliEdDt;
    }

    public String getFstCrtDt(){
        return  fstCrtDt;
    }
    public void setFstCrtDt(String fstCrtDt ){
        this.fstCrtDt = fstCrtDt;
    }

    public String getFstCrtTm(){
        return  fstCrtTm;
    }
    public void setFstCrtTm(String fstCrtTm ){
        this.fstCrtTm = fstCrtTm;
    }

    public String getRntUpdDt(){
        return  rntUpdDt;
    }
    public void setRntUpdDt(String rntUpdDt ){
        this.rntUpdDt = rntUpdDt;
    }

    public String getRntUpdTm(){
        return  rntUpdTm;
    }
    public void setRntUpdTm(String rntUpdTm ){
        this.rntUpdTm = rntUpdTm;
    }

    public String getUpdUserId(){
        return  updUserId;
    }
    public void setUpdUserId(String updUserId ){
        this.updUserId = updUserId;
    }

    public String getUpdVwId(){
        return  updVwId;
    }
    public void setUpdVwId(String updVwId ){
        this.updVwId = updVwId;
    }

    public String getUserPwd(){
        return  userPwd;
    }
    public void setUserPwd(String userPwd ){
        this.userPwd = userPwd;
    }
}
