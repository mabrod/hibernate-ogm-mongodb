package com.brodma.util;

import com.brodma.util.converter.LocalDateAttributeConverter;
import org.junit.Before;
import org.junit.Test;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import static org.assertj.core.api.Assertions.assertThat;

public class LocalDateAttributeConverterTest {

    private LocalDateAttributeConverter sut;

    @Before
    public void setUp() {
        sut = new LocalDateAttributeConverter();
    }

    @Test
    public void shouldConvertFromDateIntoLocalDate() {
        Date date = Date.valueOf("2000-06-15");
        LocalDate result = sut.convertToEntityAttribute(date);
        assertThat(result).isInstanceOf(LocalDate.class);
        assertThat(result.getYear()).isEqualTo(2000);
        assertThat(result.getMonth()).isEqualTo(Month.JUNE);
        assertThat(result.getDayOfMonth()).isEqualTo(15);
    }

    @Test
    public void shouldConvertFromIntoLocalDateIntoDateIfExists() {
        LocalDate localDate = LocalDate.of(2000, 6, 15);
        Date result = sut.convertToDatabaseColumn(localDate);
        assertThat(result).isInstanceOf(Date.class);
        assertThat(result).hasYear(2000);
        assertThat(result).hasMonth(6);
        assertThat(result).hasDayOfMonth(15);
    }

    @Test
    public void shouldReturnNullIfLocalDateParamIsMissing() {
        Date result = sut.convertToDatabaseColumn(null);
        assertThat(result).isNull();
    }

    @Test
    public void shouldReturnNullIfDateParamIsMissing() {
        LocalDate result = sut.convertToEntityAttribute(null);
        assertThat(result).isNull();
    }
}