package com.ecom.blogApi.api.model;

import java.util.Objects;
import com.ecom.blogApi.api.model.BlogCategoryImage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BlogCategory
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-30T23:01:36.740921300+05:30[Asia/Calcutta]")

public class BlogCategory   {
  @JsonProperty("blogcategoryId")
  private Integer blogcategoryId;

  @JsonProperty("blogCategoryName")
  private String blogCategoryName;

  @JsonProperty("seoTitle")
  private String seoTitle;

  @JsonProperty("seoMetaDescription")
  private String seoMetaDescription;

  @JsonProperty("status")
  private String status;

  @JsonProperty("categoryImage")
  private BlogCategoryImage categoryImage;

  public BlogCategory blogcategoryId(Integer blogcategoryId) {
    this.blogcategoryId = blogcategoryId;
    return this;
  }

  /**
   * Get blogcategoryId
   * @return blogcategoryId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getBlogcategoryId() {
    return blogcategoryId;
  }

  public void setBlogcategoryId(Integer blogcategoryId) {
    this.blogcategoryId = blogcategoryId;
  }

  public BlogCategory blogCategoryName(String blogCategoryName) {
    this.blogCategoryName = blogCategoryName;
    return this;
  }

  /**
   * Get blogCategoryName
   * @return blogCategoryName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(max=120) 
  public String getBlogCategoryName() {
    return blogCategoryName;
  }

  public void setBlogCategoryName(String blogCategoryName) {
    this.blogCategoryName = blogCategoryName;
  }

  public BlogCategory seoTitle(String seoTitle) {
    this.seoTitle = seoTitle;
    return this;
  }

  /**
   * Get seoTitle
   * @return seoTitle
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(max=200) 
  public String getSeoTitle() {
    return seoTitle;
  }

  public void setSeoTitle(String seoTitle) {
    this.seoTitle = seoTitle;
  }

  public BlogCategory seoMetaDescription(String seoMetaDescription) {
    this.seoMetaDescription = seoMetaDescription;
    return this;
  }

  /**
   * Get seoMetaDescription
   * @return seoMetaDescription
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(max=200) 
  public String getSeoMetaDescription() {
    return seoMetaDescription;
  }

  public void setSeoMetaDescription(String seoMetaDescription) {
    this.seoMetaDescription = seoMetaDescription;
  }

  public BlogCategory status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(value = "")

@Size(max=8) 
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public BlogCategory categoryImage(BlogCategoryImage categoryImage) {
    this.categoryImage = categoryImage;
    return this;
  }

  /**
   * Get categoryImage
   * @return categoryImage
  */
  @ApiModelProperty(value = "")

  @Valid

  public BlogCategoryImage getCategoryImage() {
    return categoryImage;
  }

  public void setCategoryImage(BlogCategoryImage categoryImage) {
    this.categoryImage = categoryImage;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BlogCategory blogCategory = (BlogCategory) o;
    return Objects.equals(this.blogcategoryId, blogCategory.blogcategoryId) &&
        Objects.equals(this.blogCategoryName, blogCategory.blogCategoryName) &&
        Objects.equals(this.seoTitle, blogCategory.seoTitle) &&
        Objects.equals(this.seoMetaDescription, blogCategory.seoMetaDescription) &&
        Objects.equals(this.status, blogCategory.status) &&
        Objects.equals(this.categoryImage, blogCategory.categoryImage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blogcategoryId, blogCategoryName, seoTitle, seoMetaDescription, status, categoryImage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BlogCategory {\n");
    
    sb.append("    blogcategoryId: ").append(toIndentedString(blogcategoryId)).append("\n");
    sb.append("    blogCategoryName: ").append(toIndentedString(blogCategoryName)).append("\n");
    sb.append("    seoTitle: ").append(toIndentedString(seoTitle)).append("\n");
    sb.append("    seoMetaDescription: ").append(toIndentedString(seoMetaDescription)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    categoryImage: ").append(toIndentedString(categoryImage)).append("\n");
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

