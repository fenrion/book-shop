package kopylov.book_shop.domain.mapper;

import kopylov.book_shop.domain.dto.BookDto;
import kopylov.book_shop.domain.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    @Mapping(target = "vendorCode", source = "vendorCode")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "year", source = "year")
    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "stock", source = "stock")
    @Mapping(target = "price", source = "price")
    Book BookDtoToBook(BookDto bookDto);
}
