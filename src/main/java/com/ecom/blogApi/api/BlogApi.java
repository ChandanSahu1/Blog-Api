/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.ecom.blogApi.api;

import com.ecom.blogApi.api.model.Blog;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-30T23:01:36.740921300+05:30[Asia/Calcutta]")

@Validated
@Api(value = "blog", description = "the blog API")
public interface BlogApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /blog/{blogId} : Delete a Blog
     *
     * @param blogId  (required)
     * @return Successful (status code 201)
     *         or Bad request input (status code 400)
     *         or Data not found (status code 404)
     *         or Invalid input (status code 500)
     *         or Bad Gatway (status code 502)
     */
    @ApiOperation(value = "Delete a Blog", nickname = "blogBlogIdDelete", notes = "", tags={ "Blog", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful"),
        @ApiResponse(code = 400, message = "Bad request input"),
        @ApiResponse(code = 404, message = "Data not found"),
        @ApiResponse(code = 500, message = "Invalid input"),
        @ApiResponse(code = 502, message = "Bad Gatway") })
    @RequestMapping(value = "/blog/{blogId}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> _blogBlogIdDelete(@ApiParam(value = "",required=true) @PathVariable("blogId") Integer blogId) {
        return blogBlogIdDelete(blogId);
    }

    // Override this method
    default  ResponseEntity<Void> blogBlogIdDelete(Integer blogId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /blog/{blogId}
     *
     * @param blogId  (required)
     * @return A list of all blog details (status code 200)
     *         or Successful (status code 201)
     *         or Bad request input (status code 400)
     *         or Data not found (status code 404)
     *         or Invalid input (status code 500)
     *         or Bad Gatway (status code 502)
     */
    @ApiOperation(value = "", nickname = "blogBlogIdGet", notes = "", response = Blog.class, tags={ "Blog", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of all blog details", response = Blog.class),
        @ApiResponse(code = 201, message = "Successful"),
        @ApiResponse(code = 400, message = "Bad request input"),
        @ApiResponse(code = 404, message = "Data not found"),
        @ApiResponse(code = 500, message = "Invalid input"),
        @ApiResponse(code = 502, message = "Bad Gatway") })
    @RequestMapping(value = "/blog/{blogId}",
        produces = { "applicantion/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Blog> _blogBlogIdGet(@ApiParam(value = "",required=true) @PathVariable("blogId") Integer blogId) {
        return blogBlogIdGet(blogId);
    }

    // Override this method
    default  ResponseEntity<Blog> blogBlogIdGet(Integer blogId) {
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
     * PUT /blog/{blogId} : Updating an existing Blog
     *
     * @param blogId  (required)
     * @param body Blog details that needs to be update (required)
     * @return Successful (status code 201)
     *         or Bad request input (status code 400)
     *         or Data not found (status code 404)
     *         or Invalid input (status code 500)
     *         or Bad Gatway (status code 502)
     */
    @ApiOperation(value = "Updating an existing Blog", nickname = "blogBlogIdPut", notes = "", tags={ "Blog", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful"),
        @ApiResponse(code = 400, message = "Bad request input"),
        @ApiResponse(code = 404, message = "Data not found"),
        @ApiResponse(code = 500, message = "Invalid input"),
        @ApiResponse(code = 502, message = "Bad Gatway") })
    @RequestMapping(value = "/blog/{blogId}",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<Void> _blogBlogIdPut(@ApiParam(value = "",required=true) @PathVariable("blogId") Integer blogId,@ApiParam(value = "Blog details that needs to be update" ,required=true )  @Valid @RequestBody Blog body) {
        return blogBlogIdPut(blogId, body);
    }

    // Override this method
    default  ResponseEntity<Void> blogBlogIdPut(Integer blogId, Blog body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /blog : Getting blog details
     *
     * @return A list of all blog details (status code 200)
     *         or Successful (status code 201)
     *         or Bad request input (status code 400)
     *         or Data not found (status code 404)
     *         or Invalid input (status code 500)
     *         or Bad Gatway (status code 502)
     */
    @ApiOperation(value = "Getting blog details", nickname = "blogGet", notes = "", response = Blog.class, tags={ "Blog", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of all blog details", response = Blog.class),
        @ApiResponse(code = 201, message = "Successful"),
        @ApiResponse(code = 400, message = "Bad request input"),
        @ApiResponse(code = 404, message = "Data not found"),
        @ApiResponse(code = 500, message = "Invalid input"),
        @ApiResponse(code = 502, message = "Bad Gatway") })
    @RequestMapping(value = "/blog",
        produces = { "applicantion/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Blog> _blogGet() {
        return blogGet();
    }

    // Override this method
    default  ResponseEntity<Blog> blogGet() {
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
     * POST /blog : Adding a new Blog
     *
     * @param body Blog details that needs to be added (required)
     * @return Successful (status code 201)
     *         or Bad request input (status code 400)
     *         or Data not found (status code 404)
     *         or Invalid input (status code 500)
     *         or Bad Gatway (status code 502)
     */
    @ApiOperation(value = "Adding a new Blog", nickname = "blogPost", notes = "", tags={ "Blog", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful"),
        @ApiResponse(code = 400, message = "Bad request input"),
        @ApiResponse(code = 404, message = "Data not found"),
        @ApiResponse(code = 500, message = "Invalid input"),
        @ApiResponse(code = 502, message = "Bad Gatway") })
    @RequestMapping(value = "/blog",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> _blogPost(@ApiParam(value = "Blog details that needs to be added" ,required=true )  @Valid @RequestBody Blog body) {
        return blogPost(body);
    }

    // Override this method
    default  ResponseEntity<Void> blogPost(Blog body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
