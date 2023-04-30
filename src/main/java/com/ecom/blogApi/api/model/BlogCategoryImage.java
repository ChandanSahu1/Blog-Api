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
 * BlogCategoryImage
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-30T23:01:36.740921300+05:30[Asia/Calcutta]")

public class BlogCategoryImage   {
  @JsonProperty("blogCategoryImageId")
  private Integer blogCategoryImageId;

  @JsonProperty("categoryImageName")
  private String categoryImageName;

  @JsonProperty("categoryImageDownloadUrl")
  private String categoryImageDownloadUrl;

  public BlogCategoryImage blogCategoryImageId(Integer blogCategoryImageId) {
    this.blogCategoryImageId = blogCategoryImageId;
    return this;
  }

  /**
   * Get blogCategoryImageId
   * @return blogCategoryImageId
  */
  @ApiModelProperty(value = "")


  public Integer getBlogCategoryImageId() {
    return blogCategoryImageId;
  }

  public void setBlogCategoryImageId(Integer blogCategoryImageId) {
    this.blogCategoryImageId = blogCategoryImageId;
  }

  public BlogCategoryImage categoryImageName(String categoryImageName) {
    this.categoryImageName = categoryImageName;
    return this;
  }

  /**
   * Get categoryImageName
   * @return categoryImageName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(max=250) 
  public String getCategoryImageName() {
    return categoryImageName;
  }

  public void setCategoryImageName(String categoryImageName) {
    this.categoryImageName = categoryImageName;
  }

  public BlogCategoryImage categoryImageDownloadUrl(String categoryImageDownloadUrl) {
    this.categoryImageDownloadUrl = categoryImageDownloadUrl;
    return this;
  }

  /**
   * Get categoryImageDownloadUrl
   * @return categoryImageDownloadUrl
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(max=250) 
  public String getCategoryImageDownloadUrl() {
    return categoryImageDownloadUrl;
  }

  public void setCategoryImageDownloadUrl(String categoryImageDownloadUrl) {
    this.categoryImageDownloadUrl = categoryImageDownloadUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BlogCategoryImage blogCategoryImage = (BlogCategoryImage) o;
    return Objects.equals(this.blogCategoryImageId, blogCategoryImage.blogCategoryImageId) &&
        Objects.equals(this.categoryImageName, blogCategoryImage.categoryImageName) &&
        Objects.equals(this.categoryImageDownloadUrl, blogCategoryImage.categoryImageDownloadUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blogCategoryImageId, categoryImageName, categoryImageDownloadUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BlogCategoryImage {\n");
    
    sb.append("    blogCategoryImageId: ").append(toIndentedString(blogCategoryImageId)).append("\n");
    sb.append("    categoryImageName: ").append(toIndentedString(categoryImageName)).append("\n");
    sb.append("    categoryImageDownloadUrl: ").append(toIndentedString(categoryImageDownloadUrl)).append("\n");
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

