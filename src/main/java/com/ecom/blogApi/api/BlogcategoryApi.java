/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.ecom.blogApi.api;

import com.ecom.blogApi.api.model.BlogCategory;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-13T13:14:56.410513400+05:30[Asia/Calcutta]")

@Validated
@Api(value = "blogcategory", description = "the blogcategory API")
public interface BlogcategoryApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /blogcategory/{blogcategoryId} : Delete a Blog category
     *
     * @param blogcategoryId  (required)
     * @return Successful (status code 201)
     *         or Bad request input (status code 400)
     *         or Data not found (status code 404)
     *         or Invalid input (status code 500)
     *         or Bad Gatway (status code 502)
     */
    @ApiOperation(value = "Delete a Blog category", nickname = "blogcategoryBlogcategoryIdDelete", notes = "", tags={ "Blog category", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful"),
        @ApiResponse(code = 400, message = "Bad request input"),
        @ApiResponse(code = 404, message = "Data not found"),
        @ApiResponse(code = 500, message = "Invalid input"),
        @ApiResponse(code = 502, message = "Bad Gatway") })
    @RequestMapping(value = "/blogcategory/{blogcategoryId}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> _blogcategoryBlogcategoryIdDelete(@ApiParam(value = "",required=true) @PathVariable("blogcategoryId") Integer blogcategoryId) {
        return blogcategoryBlogcategoryIdDelete(blogcategoryId);
    }

    // Override this method
    default  ResponseEntity<Void> blogcategoryBlogcategoryIdDelete(Integer blogcategoryId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /blogcategory/{blogcategoryId}
     *
     * @param blogcategoryId  (required)
     * @return A list of all blog category details (status code 200)
     *         or Successful (status code 201)
     *         or Bad request input (status code 400)
     *         or Data not found (status code 404)
     *         or Invalid input (status code 500)
     *         or Bad Gatway (status code 502)
     */
    @ApiOperation(value = "", nickname = "blogcategoryBlogcategoryIdGet", notes = "", response = BlogCategory.class, tags={ "Blog category", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of all blog category details", response = BlogCategory.class),
        @ApiResponse(code = 201, message = "Successful"),
        @ApiResponse(code = 400, message = "Bad request input"),
        @ApiResponse(code = 404, message = "Data not found"),
        @ApiResponse(code = 500, message = "Invalid input"),
        @ApiResponse(code = 502, message = "Bad Gatway") })
    @RequestMapping(value = "/blogcategory/{blogcategoryId}",
        produces = { "applicantion/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<BlogCategory> _blogcategoryBlogcategoryIdGet(@ApiParam(value = "",required=true) @PathVariable("blogcategoryId") Integer blogcategoryId) {
        return blogcategoryBlogcategoryIdGet(blogcategoryId);
    }

    // Override this method
    default  ResponseEntity<BlogCategory> blogcategoryBlogcategoryIdGet(Integer blogcategoryId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(""))) {
                    String exampleString = "";
                    ApiUtil.setExampleResponse(request, "", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /blogcategory/{blogcategoryId} : Updating an existing Blog category
     *
     * @param blogcategoryId  (required)
     * @param body Blog category details that needs to be added (required)
     * @return Successful (status code 201)
     *         or Bad request input (status code 400)
     *         or Data not found (status code 404)
     *         or Invalid input (status code 500)
     *         or Bad Gatway (status code 502)
     */
    @ApiOperation(value = "Updating an existing Blog category", nickname = "blogcategoryBlogcategoryIdPut", notes = "", tags={ "Blog category", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful"),
        @ApiResponse(code = 400, message = "Bad request input"),
        @ApiResponse(code = 404, message = "Data not found"),
        @ApiResponse(code = 500, message = "Invalid input"),
        @ApiResponse(code = 502, message = "Bad Gatway") })
    @RequestMapping(value = "/blogcategory/{blogcategoryId}",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<Void> _blogcategoryBlogcategoryIdPut(@ApiParam(value = "",required=true) @PathVariable("blogcategoryId") Integer blogcategoryId,@ApiParam(value = "Blog category details that needs to be added" ,required=true )  @Valid @RequestBody BlogCategory body) {
        return blogcategoryBlogcategoryIdPut(blogcategoryId, body);
    }

    // Override this method
    default  ResponseEntity<Void> blogcategoryBlogcategoryIdPut(Integer blogcategoryId, BlogCategory body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /blogcategory : Getting blog category details
     *
     * @return A list of all blog category details (status code 200)
     *         or Successful (status code 201)
     *         or Bad request input (status code 400)
     *         or Data not found (status code 404)
     *         or Invalid input (status code 500)
     *         or Bad Gatway (status code 502)
     */
    @ApiOperation(value = "Getting blog category details", nickname = "blogcategoryGet", notes = "", response = BlogCategory.class, tags={ "Blog category", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of all blog category details", response = BlogCategory.class),
        @ApiResponse(code = 201, message = "Successful"),
        @ApiResponse(code = 400, message = "Bad request input"),
        @ApiResponse(code = 404, message = "Data not found"),
        @ApiResponse(code = 500, message = "Invalid input"),
        @ApiResponse(code = 502, message = "Bad Gatway") })
    @RequestMapping(value = "/blogcategory",
        produces = { "applicantion/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<BlogCategory> _blogcategoryGet() {
        return blogcategoryGet();
    }

    // Override this method
    default  ResponseEntity<BlogCategory> blogcategoryGet() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(""))) {
                    String exampleString = "";
                    ApiUtil.setExampleResponse(request, "", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /blogcategory : Adding a new Blog category
     *
     * @param body Blog category details that needs to be added (required)
     * @return Successful (status code 201)
     *         or Bad request input (status code 400)
     *         or Data not found (status code 404)
     *         or Invalid input (status code 500)
     *         or Bad Gatway (status code 502)
     */
    @ApiOperation(value = "Adding a new Blog category", nickname = "blogcategoryPost", notes = "", tags={ "Blog category", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful"),
        @ApiResponse(code = 400, message = "Bad request input"),
        @ApiResponse(code = 404, message = "Data not found"),
        @ApiResponse(code = 500, message = "Invalid input"),
        @ApiResponse(code = 502, message = "Bad Gatway") })
    @RequestMapping(value = "/blogcategory",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> _blogcategoryPost(@ApiParam(value = "Blog category details that needs to be added" ,required=true )  @Valid @RequestBody BlogCategory body) {
        return blogcategoryPost(body);
    }

    // Override this method
    default  ResponseEntity<Void> blogcategoryPost(BlogCategory body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
