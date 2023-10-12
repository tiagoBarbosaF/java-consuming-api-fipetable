package br.com.fipetable.service.interfaces;

import java.util.List;

public interface IConverData {
    <T> T getData(String json, Class<T> tClass);

    <T> List<T> getList(String json, Class<T> tClass);
}
