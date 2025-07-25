package com.devgeda.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.devgeda.api.dtos.PecaDTO;
import com.devgeda.domain.models.Peca;

@Mapper(componentModel = "spring")
public interface PecaMapper {

	PecaDTO toDto(Peca peca);

	Peca toEntity(PecaDTO dto);

	List<PecaDTO> toDtoList(List<Peca> entities);

	List<Peca> toEntityList(List<PecaDTO> dtos);

}