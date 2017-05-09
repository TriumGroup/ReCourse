package by.triumgroup.recourse.document.model.provider;

import org.springframework.data.util.Pair;

import java.util.Collection;
import java.util.List;

public interface ContentProvider<TMainEntity, TTableEntity> {
    String createFilename(TMainEntity mainEntity);
    String createTitle(TMainEntity mainEntity);
    List<Pair<String, String>> createSubtitles(TMainEntity mainEntity);
    List<String> getHeaders();
    String createTableTitle(TMainEntity mainEntity, Collection<TTableEntity> tableEntities);
    List<List<String>> createRows(Collection<TTableEntity> entities);
}
