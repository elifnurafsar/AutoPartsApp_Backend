package com.itg.AutomobilePartApp.Mappers.Util;

import com.itg.AutomobilePartApp.Entities.Util.CreditCardInfo;
import com.itg.AutomobilePartApp.Responses.Util.CreditCardInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CreditCardInfoMapper {
    CreditCardInfoMapper INSTANCE = Mappers.getMapper(CreditCardInfoMapper.class);

    CreditCardInfoResponse entityToResponse(CreditCardInfo creditCardInfo);

    List<CreditCardInfoResponse> entityListToResponseList(List<CreditCardInfo> creditCardsInfo);
}