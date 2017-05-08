package by.triumgroup.recourse.document.model.provider;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ContentProvider<TMainEntity, TTableEntity> {
    String createFilename(TMainEntity mainEntity);
    String createTitle(TMainEntity mainEntity);
    Map<String, String> createSubtitles(TMainEntity mainEntity);
    List<String> getHeaders();
    String createTableTitle(TMainEntity mainEntity, Collection<TTableEntity> tableEntities);
    List<List<String>> createRows(Collection<TTableEntity> entities);
}
