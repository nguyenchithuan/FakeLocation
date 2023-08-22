package edu.wkd.fakelocation.util;

import android.content.Context;

public interface CrudInterface {
    void insert(Context context, Object object);
    void update(Context context, Object object);
    void delete(Context context, Object object);
}
