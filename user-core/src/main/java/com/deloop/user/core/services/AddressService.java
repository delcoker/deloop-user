package com.deloop.user.core.services;


import com.deloop.user.core.models.requests.address.AddAddressRequest;
import com.deloop.user.core.models.requests.address.UpdateAddressRequest;
import com.deloop.user.data.api.dtos.AddressDto;
import com.deloop.user.data.exceptions.EmailNotFoundException;

public interface AddressService {
    
    AddressDto add(AddAddressRequest addAddressRequest) throws EmailNotFoundException;

    AddressDto update(UpdateAddressRequest updateAddressRequest);

}
