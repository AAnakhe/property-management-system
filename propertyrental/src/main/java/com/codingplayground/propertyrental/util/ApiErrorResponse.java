package com.codingplayground.propertyrental.util;

import com.codingplayground.propertyrental.enums.ErrorType;
import com.fasterxml.jackson.annotation.JsonProperty;


public record ApiErrorResponse(Boolean success, Object data, String message, @JsonProperty("error_type") ErrorType errorType) {}

