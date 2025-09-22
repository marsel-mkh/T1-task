package com.t1.marselmkh.dto.ClientProductDto;

import com.t1.marselmkh.entity.Status;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientProductEventDto {
     Long clientId;
     Long productId;
     Status status;

}
