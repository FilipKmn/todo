package todo.backend.model.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


public final class PersistenceConverters {

    private PersistenceConverters() {
    }

    @Converter(autoApply = true)
    public static class LocalTimeConverter implements AttributeConverter<LocalTime, Date> {

        @Override
        public Date convertToDatabaseColumn(LocalTime time) {
            return time == null ? null : Date.from(time.atDate(LocalDate.now()).atZone(ZoneId.of("UTC")).toInstant());
        }

        @Override
        public LocalTime convertToEntityAttribute(Date date) {
            return date == null ? null : ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")).toLocalTime();
        }
    }

    @Converter(autoApply = true)
    public static class LocalDateConverter implements AttributeConverter<LocalDate, java.sql.Date> {

        @Override
        public java.sql.Date convertToDatabaseColumn(LocalDate date) {

            return date == null ? null : java.sql.Date.valueOf(date);
        }

        @Override
        public LocalDate convertToEntityAttribute(java.sql.Date date) {
            return date == null ? null : date.toLocalDate();
        }
    }

    @Converter(autoApply = true)
    public static class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Date> {

        @Override
        public Date convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
            return zonedDateTime == null ? null : Date.from(zonedDateTime.toInstant());
        }

        @Override
        public ZonedDateTime convertToEntityAttribute(Date date) {
            return date == null ? null : ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC"));
        }
    }

}
