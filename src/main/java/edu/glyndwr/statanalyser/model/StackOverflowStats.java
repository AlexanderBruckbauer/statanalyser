
package edu.glyndwr.statanalyser.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
@Data
public class StackOverflowStats {

    @SerializedName("Year")
    @Expose
    public List<String> year = null;
    @SerializedName("Month")
    @Expose
    public List<String> month = null;
    @SerializedName("TagPercents")
    @Expose
    public TagPercents tagPercents;

}
