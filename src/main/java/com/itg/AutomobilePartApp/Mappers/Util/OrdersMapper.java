package com.itg.AutomobilePartApp.Mappers.Util;

import com.itg.AutomobilePartApp.Entities.Util.Orders;
import com.itg.AutomobilePartApp.Responses.Util.OrdersResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrdersMapper {
    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);

    OrdersResponse entityToResponse(Orders order);

    List<OrdersResponse> entityListToResponseList(List<Orders> orders);
}
