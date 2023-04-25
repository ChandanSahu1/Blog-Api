/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.ecom.blogApi.api;

import com.ecom.blogApi.api.model.UserRegistration;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-25T14:58:27.199245300+05:30[Asia/Calcutta]")

@Validated
@Api(value = "user", description = "the user API")
public interface UserApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /user : Adding a new user details
     *
     * @param body User details that needs to be added (required)
     * @return Successful (status code 201)
     *         or Bad request input (status code 400)
     *         or Data not found (status code 404)
     *         or Invalid input (status code 500)
     *         or Bad Gatway (status code 502)
     */
    @ApiOperation(value = "Adding a new user details", nickname = "userPost", notes = "", tags={ "User Registration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful"),
        @ApiResponse(code = 400, message = "Bad request input"),
        @ApiResponse(code = 404, message = "Data not found"),
        @ApiResponse(code = 500, message = "Invalid input"),
        @ApiResponse(code = 502, message = "Bad Gatway") })
    @RequestMapping(value = "/user",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> _userPost(@ApiParam(value = "User details that needs to be added" ,required=true )  @Valid @RequestBody UserRegistration body) {
        return userPost(body);
    }

    // Override this method
    default  ResponseEntity<Void> userPost(UserRegistration body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
