package com.nrkt.covid19infomailsender.controller.rest;

import com.nrkt.covid19infomailsender.error.ApiError;
import com.nrkt.covid19infomailsender.dto.PersonDto;
import com.nrkt.covid19infomailsender.enums.CountryEnum;
import com.nrkt.covid19infomailsender.service.contract.ContactService;
import io.swagger.annotations.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/v1/information", produces = "application/json", consumes = "application/json")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(tags = "Contact")
@Transactional
public class InformationController {

    ContactService contactService;

    @SneakyThrows
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Add Information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok!"),
            @ApiResponse(code = 500, message = "Internal Server Error!", response = ApiError.class)
    })
    public PersonDto contactInformation(
            @ApiParam(name = "Person", value = "Contact Information", required = true)
            @NotNull @RequestBody @Valid PersonDto person,
            @NotNull @ApiParam(value = "Country", required = true)
            @RequestParam CountryEnum country) {

        person.setCountry(country);
        return contactService.subscribe(person);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Remove Subscription")
    public void contactInformation(
            @NotNull @ApiParam(value = "Email", required = true)
            @RequestParam String email) {

        contactService.unsubscribe(email);
    }
}

