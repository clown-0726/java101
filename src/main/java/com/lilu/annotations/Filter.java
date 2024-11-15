package com.lilu.annotations;

import com.lilu.annotations.annotation.Column;
import com.lilu.annotations.annotation.Table;
import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Filter {
    public static String query(People people) {
        StringBuilder sb = new StringBuilder();
        Class c = people.getClass();

        // 处理 Table 的 Annotation
        boolean isPresent = c.isAnnotationPresent(Table.class);
        if (!isPresent) {
            return null;
        }
        Table table = (Table) c.getAnnotation(Table.class);
        String tableName = table.value();
        sb.append("SELECT * FROM ").append(tableName).append(" WHERE 1=1");

        // 处理 Column 的 Annotation
        Field[] declaredFields = c.getDeclaredFields();
        for (Field field : declaredFields) {
            boolean isColPresent = field.isAnnotationPresent(Column.class);
            if (!isColPresent) continue;
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            String fieldName = field.getName();
            String getFieldName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Object fieldValue = null;
            try {
                Method method = c.getMethod(getFieldName);
                fieldValue = method.invoke(people);

            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if (fieldValue == null || fieldValue instanceof Integer && (Integer) fieldValue == 0) continue;
            sb.append(" AND ").append(columnName);

            if (fieldValue instanceof Integer) {
                sb.append(" = ").append(fieldValue);
            }

            if (fieldValue instanceof String) {
                sb.append(" = ").append("'").append(fieldValue).append("'");
            }
        }
        sb.append(";");

        return sb.toString();
    }
}
