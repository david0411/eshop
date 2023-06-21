package fsse2305.eshop.data.transactionEnum;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<TransStatus, String> {

    @Override
    public String convertToDatabaseColumn(TransStatus transStatus) {
        if (transStatus == null) {
            return null;
        }
        return transStatus.toString();
    }

    @Override
    public TransStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(TransStatus.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}