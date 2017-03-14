package no.rbrastad.json.spreadsheets.exception;

import no.rbrastad.json.spreadsheets.model.ApiResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class ReportsExceptionMapper implements ExceptionMapper<ApiException> {
        public Response toResponse(ApiException exception) {
            if (exception instanceof NotFoundException) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ApiResponse(ApiResponse.ERROR, exception
                                .getMessage())).build();
            } else if (exception instanceof BadRequestException) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse(ApiResponse.ERROR, exception
                                .getMessage())).build();
            } else if (exception instanceof ApiException) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse(ApiResponse.ERROR, exception
                                .getMessage())).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(new ApiResponse(ApiResponse.ERROR,
                                "a system error occured")).build();
            }
        }
    }
