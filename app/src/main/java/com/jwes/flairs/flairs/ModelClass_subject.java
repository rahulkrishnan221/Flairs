package com.jwes.flairs.flairs;

/**
 * Created by rahul on 17/1/18.
 */

public class ModelClass_subject {

    String title1,code;

    public ModelClass_subject(String title1,String code)
    {
        this.title1=title1;
        this.code=code;
    }
    public ModelClass_subject()
    {

    }
    public String getTitle1()
    {
        return title1;
    }
    public void setTitle(String title1)
    {
        this.title1=title1;
    }
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code=code;
    }
}
