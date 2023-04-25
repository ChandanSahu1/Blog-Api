package com.ecom.blogApi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BlogImages
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-25T14:58:27.199245300+05:30[Asia/Calcutta]")

public class BlogImages   {
  @JsonProperty("blogImageId")
  private Integer blogImageId;

  @JsonProperty("blogImageName")
  private String blogImageName;

  @JsonProperty("imageDownloadUrl")
  private String imageDownloadUrl;

  @JsonProperty("imageType")
  private String imageType;

  public BlogImages blogImageId(Integer blogImageId) {
    this.blogImageId = blogImageId;
    return this;
  }

  /**
   * Get blogImageId
   * @return blogImageId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getBlogImageId() {
    return blogImageId;
  }

  public void setBlogImageId(Integer blogImageId) {
    this.blogImageId = blogImageId;
  }

  public BlogImages blogImageName(String blogImageName) {
    this.blogImageName = blogImageName;
    return this;
  }

  /**
   * Get blogImageName
   * @return blogImageName
  */
  @ApiModelProperty(value = "")


  public String getBlogImageName() {
    return blogImageName;
  }

  public void setBlogImageName(String blogImageName) {
    this.blogImageName = blogImageName;
  }

  public BlogImages imageDownloadUrl(String imageDownloadUrl) {
    this.imageDownloadUrl = imageDownloadUrl;
    return this;
  }

  /**
   * Get imageDownloadUrl
   * @return imageDownloadUrl
  */
  @ApiModelProperty(value = "")


  public String getImageDownloadUrl() {
    return imageDownloadUrl;
  }

  public void setImageDownloadUrl(String imageDownloadUrl) {
    this.imageDownloadUrl = imageDownloadUrl;
  }

  public BlogImages imageType(String imageType) {
    this.imageType = imageType;
    return this;
  }

  /**
   * Get imageType
   * @return imageType
  */
  @ApiModelProperty(value = "")


  public String getImageType() {
    return imageType;
  }

  public void setImageType(String imageType) {
    this.imageType = imageType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BlogImages blogImages = (BlogImages) o;
    return Objects.equals(this.blogImageId, blogImages.blogImageId) &&
        Objects.equals(this.blogImageName, blogImages.blogImageName) &&
        Objects.equals(this.imageDownloadUrl, blogImages.imageDownloadUrl) &&
        Objects.equals(this.imageType, blogImages.imageType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blogImageId, blogImageName, imageDownloadUrl, imageType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BlogImages {\n");
    
    sb.append("    blogImageId: ").append(toIndentedString(blogImageId)).append("\n");
    sb.append("    blogImageName: ").append(toIndentedString(blogImageName)).append("\n");
    sb.append("    imageDownloadUrl: ").append(toIndentedString(imageDownloadUrl)).append("\n");
    sb.append("    imageType: ").append(toIndentedString(imageType)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

