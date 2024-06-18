package org.rahul.ecommercebackend.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse {

    private String message;
    private boolean status;

    public ApiResponse(String message, boolean status) {
        super();
        this.message = message;
        this.status = status;
    }



}
