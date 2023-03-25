package ca.ccbmb.gardenland.core.plot;

import ca.ccbmb.gardenland.dto.AddressDto;
import ca.ccbmb.gardenland.dto.PlotDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class PlotValidator {

    private static final String PRICE_REQUIRED = "PRICE_REQUIRED";
    private static final String PRICE_NOT_NEGATIVE = "PRICE_NOT_NEGATIVE";
    private static final String SIZE_REQUIRED = "SIZE_REQUIRED";
    private static final String SIZE_NOT_NEGATIVE = "SIZE_NOT_NEGATIVE";
    private static final String SIZE_UNIT_TYPE_REQUIRED = "SIZE_UNIT_TYPE_REQUIRED";
    private static final String PLOT_TYPE_REQUIRED = "PLOT_TYPE_REQUIRED";
    private static final String ADDRESS_REQUIRED = "ADDRESS_REQUIRED";
    private static final String CITY_REQUIRED = "CITY_REQUIRED";
    private static final String STREET_1_REQUIRED = "STREET_1_REQUIRED";
    private static final String PROVINCE_REQUIRED = "PROVINCE_REQUIRED";
    private static final String COUNTRY_REQUIRED = "COUNTRY_REQUIRED";
    private static final String POSTAL_CODE_REQUIRED = "POSTAL_CODE_REQUIRED";

    public Map<String, String> validateForSave(PlotDto dto) {
        Map<String, String> validationErrors = new HashMap<>();

        if (dto.getPrice() == null) {
            validationErrors.put("price", PRICE_REQUIRED);
        } else if (dto.getPrice().compareTo(BigDecimal.ZERO) >= 0) {
            validationErrors.put("price", PRICE_NOT_NEGATIVE);
        }

        if (dto.getSize() == null) {
            validationErrors.put("size", SIZE_REQUIRED);
        } else if (dto.getSize().compareTo(BigDecimal.ZERO) >= 0) {
            validationErrors.put("size", SIZE_NOT_NEGATIVE);
        }

        if (dto.getSizeUnitType() == null) {
            validationErrors.put("sizeUnitType", SIZE_UNIT_TYPE_REQUIRED);
        }

        if (dto.getPlotTypeNumber() == null) {
            validationErrors.put("plotTypeNumber", PLOT_TYPE_REQUIRED);
        }

        if (dto.getAddress() == null) {
            validationErrors.put("address", ADDRESS_REQUIRED);
        } else {
            validationErrors.putAll(validateAddressForSave(dto.getAddress()));
        }

        return validationErrors;
    }

    public Map<String, String> validateAddressForSave(AddressDto dto) {
        Map<String, String> validationErrors = new HashMap<>();

        if (dto.getCity() == null) {
            validationErrors.put("address.city", CITY_REQUIRED);
        }

        if (dto.getCountry() == null) {
            validationErrors.put("address.country", COUNTRY_REQUIRED);
        }

        if (dto.getProvince() == null) {
            validationErrors.put("address.province", PROVINCE_REQUIRED);
        }

        if (dto.getPostalCode() == null) {
            validationErrors.put("address.postalCode", POSTAL_CODE_REQUIRED);
        }

        if (dto.getStreet1() == null) {
            validationErrors.put("address.street1", STREET_1_REQUIRED);
        }

        return validationErrors;
    }
}
