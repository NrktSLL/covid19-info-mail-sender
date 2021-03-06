package com.nrkt.covid19infomailsender.dto;

import com.nrkt.covid19infomailsender.enums.CountryEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PersonDto implements Serializable {
    @NotBlank
    @ApiModelProperty(notes = "Name", example = "Nrkt", required = true)
    String name;
    @Email
    @ApiModelProperty(notes = "Email", example = "aa@bb.com", required = true)
    String email;
    @ApiModelProperty(hidden = true)
    CountryEnum country;
}
