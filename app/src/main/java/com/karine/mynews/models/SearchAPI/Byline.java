
package com.karine.mynews.models.SearchAPI;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Byline {

    @SerializedName("organization")
    private Object mOrganization;
    @SerializedName("original")
    private Object mOriginal;
    @SerializedName("person")
    private List<Object> mPerson;

    public Object getOrganization() {
        return mOrganization;
    }

    public void setOrganization(Object organization) {
        mOrganization = organization;
    }

    public Object getOriginal() {
        return mOriginal;
    }

    public void setOriginal(Object original) {
        mOriginal = original;
    }

    public List<Object> getPerson() {
        return mPerson;
    }

    public void setPerson(List<Object> person) {
        mPerson = person;
    }

}
