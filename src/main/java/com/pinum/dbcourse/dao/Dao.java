package com.pinum.dbcourse.dao;

import java.util.List;

public interface Dao<T, ID> {

    <S extends T> ID save(S entity);

    <S extends T> int[] batchSave(List<S> entities);

    <S extends T> int update(S entity);

    T find(ID id);

    List<T> findAll();

    void delete(ID id);
}
