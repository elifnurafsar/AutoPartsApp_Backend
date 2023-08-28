package com.itg.AutomobilePartApp.Mappers.AutoPart;

import com.itg.AutomobilePartApp.Entities.AutoParts.AutoPart;
import com.itg.AutomobilePartApp.Responses.AutoPart.AutoPartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AutoPartMapper {
    AutoPartMapper INSTANCE = Mappers.getMapper(AutoPartMapper.class);

    AutoPartResponse entityToResponse(AutoPart product);

    List<AutoPartResponse> entityListToResponseList(List<AutoPart> products);

}
