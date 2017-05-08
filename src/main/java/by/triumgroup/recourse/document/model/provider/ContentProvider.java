package by.triumgroup.recourse.document.model.provider;

import java.util.List;
import java.util.Map;

public interface ContentProvider<TMainEntity, TTableEntity> {
    String createTitle(TMainEntity mainModel);
    Map<String, String> createSubtitles(TMainEntity mainModel);
    List<String> getHeaders();
    String createTableTitle(TMainEntity mainEntity, List<TTableEntity> tableEntities);
    List<List<String>> createRows(List<TTableEntity> entities);
}
