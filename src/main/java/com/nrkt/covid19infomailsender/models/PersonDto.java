package com.nrkt.covid19infomailsender.models;

import com.nrkt.covid19infomailsender.enums.CountryEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonDto {
    @NotBlank
    @ApiModelProperty(notes = "Name", example = "Nrkt", required = true)
    String name;
    @Email
    @ApiModelProperty(notes = "Email", example = "aa@bb.com", required = true)
    String email;
    @ApiModelProperty(hidden = true)
    CountryEnum country;
}
