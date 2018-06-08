package listservice;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DataList {
    private static List<Data> dataList = new ArrayList<>();

    static {
        dataList.add(new Data("1", "Имя1", "Большая Морская 67", "7106510", "2018"));
        dataList.add(new Data("2", "Имя2", "М.Садовая 19", "89118972874", "17800"));
    }

    public void addData(Data data){
        dataList.add(data);
    }

    public Data addData(String id, String name, String address, String phone, String cost) {

        Data newData = new Data(id, name, address, phone, cost);

        dataList.add(newData);

        return newData;
    }

    public boolean delData(String id)
    {
        Iterator<Data> iterator = dataList.iterator();
        while (iterator.hasNext()) {
            Data curData = iterator.next();
            if (curData.getId().toLowerCase().equals(id.toLowerCase())) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Data> searchData(String id) {
        List<Data> result = new LinkedList<>();
        if (id == null) return result;
        for (Data data:dataList) {
            if (data.getId().toLowerCase().equals(id.toLowerCase())) result.add(data);
        }
        return result;
    }

    public List<Data> getDataList() {
        return dataList;
    }
}
