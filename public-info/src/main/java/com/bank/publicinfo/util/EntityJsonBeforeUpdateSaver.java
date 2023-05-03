package com.bank.publicinfo.util;

import com.bank.publicinfo.model.BaseClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Класс EntityJsonBeforeUpdateSaver предназначен для сохранения информации о сущности до редактирования,
 * для дальнейшей вставки этой информации в экземпляры сущности Audit
 */
public class EntityJsonBeforeUpdateSaver {
    /**objectMapper производит конвертацию объектов в JSON-формат*/
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**статическая переменная entityJson хранит в себе представление объекта ДО редактирования*/
    private static String entityJson;

    public static String getEntityJson() {
        return entityJson;
    }

    /**статический метод saveEntityJsonBeforeUpdate сохраняет в поле entityJson представление объекта в формате JSON.
     * @param unupdatedEntity неотредактированный объект, результат сериализации которого необходимо сохранить
     */
    public static void saveEntityJsonBeforeUpdate(BaseClass unupdatedEntity) throws JsonProcessingException {
        entityJson = objectMapper.writeValueAsString(unupdatedEntity);
    }
}
