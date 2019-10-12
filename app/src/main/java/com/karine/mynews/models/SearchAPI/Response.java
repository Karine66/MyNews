
package com.karine.mynews.models.SearchAPI;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Response {

    @SerializedName("docs")
    private List<Doc> mDocs;
    @SerializedName("meta")
    private Meta mMeta;

    public List<Doc> getDocs() {
        return mDocs;
    }

    public void setDocs(List<Doc> docs) {
        mDocs = docs;
    }

    public Meta getMeta() {
        return mMeta;
    }

    public void setMeta(Meta meta) {
        mMeta = meta;
    }

}
