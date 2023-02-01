package com.example.validation.dto;

import com.example.validation.annotation.Country;
import com.example.validation.validator.group.AdvanceInfo;
import com.example.validation.validator.group.BasicInfo;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@GroupSequence({UserDto.class, BasicInfo.class, AdvanceInfo.class})
public class UserDto {

    @NotBlank(groups = BasicInfo.class)
    private String name;

    @NotNull(groups = BasicInfo.class)
    @Email(groups = AdvanceInfo.class)
    private String email;

    @Min(value = 18, groups = BasicInfo.class)
    @Max(value = 60, groups = BasicInfo.class)
    private Integer age;

    @NotNull(groups = BasicInfo.class)
    @Country
    private String country;

    @NotNull(groups = AdvanceInfo.class, message = "IP Address must not be null")
    @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$", message = "IP Address must be valid")
    private String ipAddress;
}