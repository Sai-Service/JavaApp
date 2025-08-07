package com.sai.erp.master.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"versionName",
"versionCode",
"downloadUrl",
"releaseDate",
"createdBy",
"creationDate",
"updatedBy",
"updationDate",
"attribute1",
"attribute2",
"attribute3",
"attribute4"
})
@Generated("jsonschema2pojo")
public class AppVersionDto {

@JsonProperty("versionName")
private String versionName;
@JsonProperty("versionCode")
private String versionCode;
@JsonProperty("downloadUrl")
private String downloadUrl;
@JsonProperty("releaseDate")
private Date releaseDate;
@JsonProperty("createdBy")
private String createdBy;
@JsonProperty("creationDate")
private Date creationDate;
@JsonProperty("updatedBy")
private String updatedBy;
@JsonProperty("updationDate")
private Date updationDate;
@JsonProperty("attribute1")
private String attribute1;
@JsonProperty("attribute2")
private String attribute2;
@JsonProperty("attribute3")
private String attribute3;
@JsonProperty("attribute4")
private String attribute4;

@JsonProperty("versionName")
public String getVersionName() {
return versionName;
}

@JsonProperty("versionName")
public void setVersionName(String versionName) {
this.versionName = versionName;
}

@JsonProperty("versionCode")
public String getVersionCode() {
return versionCode;
}

@JsonProperty("versionCode")
public void setVersionCode(String versionCode) {
this.versionCode = versionCode;
}

@JsonProperty("downloadUrl")
public String getDownloadUrl() {
return downloadUrl;
}

@JsonProperty("downloadUrl")
public void setDownloadUrl(String downloadUrl) {
this.downloadUrl = downloadUrl;
}

@JsonProperty("releaseDate")
public Date getReleaseDate() {
return releaseDate;
}

@JsonProperty("releaseDate")
public void setReleaseDate(Date releaseDate) {
this.releaseDate = releaseDate;
}

@JsonProperty("createdBy")
public String getCreatedBy() {
return createdBy;
}

@JsonProperty("createdBy")
public void setCreatedBy(String createdBy) {
this.createdBy = createdBy;
}

@JsonProperty("creationDate")
public Date getCreationDate() {
return creationDate;
}

@JsonProperty("creationDate")
public void setCreationDate(Date creationDate) {
this.creationDate = creationDate;
}

@JsonProperty("updatedBy")
public String getUpdatedBy() {
return updatedBy;
}

@JsonProperty("updatedBy")
public void setUpdatedBy(String updatedBy) {
this.updatedBy = updatedBy;
}

@JsonProperty("updationDate")
public Date getUpdationDate() {
return updationDate;
}

@JsonProperty("updationDate")
public void setUpdationDate(Date updationDate) {
this.updationDate = updationDate;
}

@JsonProperty("attribute1")
public String getAttribute1() {
return attribute1;
}

@JsonProperty("attribute1")
public void setAttribute1(String attribute1) {
this.attribute1 = attribute1;
}

@JsonProperty("attribute2")
public String getAttribute2() {
return attribute2;
}

@JsonProperty("attribute2")
public void setAttribute2(String attribute2) {
this.attribute2 = attribute2;
}

@JsonProperty("attribute3")
public String getAttribute3() {
return attribute3;
}

@JsonProperty("attribute3")
public void setAttribute3(String attribute3) {
this.attribute3 = attribute3;
}

@JsonProperty("attribute4")
public String getAttribute4() {
return attribute4;
}

@JsonProperty("attribute4")
public void setAttribute4(String attribute4) {
this.attribute4 = attribute4;
}

}