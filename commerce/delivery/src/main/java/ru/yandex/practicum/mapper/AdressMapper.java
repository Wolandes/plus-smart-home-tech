package ru.yandex.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.model.Address;
import ru.yandex.practicum.model.AddressDto;

/**
 * Маппер адреса
 */
@Component
public class AdressMapper {

    public Address toAddress(AddressDto addressDto) {
        return Address.builder()
                .country(addressDto.getCountry())
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .flat(addressDto.getFlat())
                .build();
    }

    public AddressDto toAddressDto(Address address){
        return AddressDto.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .flat(address.getFlat())
                .build();
    }
}
