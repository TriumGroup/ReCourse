package by.triumgroup.recourse.entity.support;

import by.triumgroup.recourse.document.DocumentType;

import java.beans.PropertyEditorSupport;

public class DocumentTypeEnumConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(DocumentType.valueOf(text.toUpperCase()));
    }
}
