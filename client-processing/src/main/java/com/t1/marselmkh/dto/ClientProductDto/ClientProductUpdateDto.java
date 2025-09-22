package com.t1.marselmkh.dto.ClientProductDto;

import com.t1.marselmkh.entity.Status;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientProductUpdateDto {
     Status status;
     LocalDate closeDate;
}
