package ca.ccbmb.gardenland.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddressDto {
    private String addressNumber;
    private String street1;
    private String street2;
    private String postalCode;
    private String city;
    private String province;
    private String country;
}
