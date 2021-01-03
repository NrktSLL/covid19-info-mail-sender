package com.nrkt.covid19infomailsender.error;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NonNull
@Builder
public class ApiError {
  String message;
  Date timestamp;
  Integer status;
  String error;
  String path;
}
