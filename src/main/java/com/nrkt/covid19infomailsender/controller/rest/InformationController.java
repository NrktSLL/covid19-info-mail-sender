package com.nrkt.covid19infomailsender.controller.rest;

import com.nrkt.covid19infomailsender.models.PersonDto;
import com.nrkt.covid19infomailsender.enums.CountryEnum;
import com.nrkt.covid19infomailsender.service.contract.ContactService;
import com.nrkt.covid19infomailsender.service.information.InformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping(value = "/v1/information")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(tags = "Contact")
@Transactional
public class InformationController {

    InformationService informationService;
    ContactService contactService;

    @SneakyThrows
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Add Information")
    public String contactInformation(
            @ApiParam(name = "Person", value = "Contact Information", required = true)
            @NotNull @RequestBody @Valid PersonDto person,
            @NotNull @ApiParam(value = "Country", required = true)
            @RequestParam CountryEnum country) {

        return informationService.sender(person, country).equals(true) ? "subscribed successfully" : "subscribed failed";
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

