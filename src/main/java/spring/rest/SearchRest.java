package spring.rest;

import listservice.Data;
import listservice.DataList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/search")
public class SearchRest {

    private DataList dataList = new DataList();

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> searchData(@PathVariable("id") String id) {
        if (id.equals("undefined"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\" : \"Invalid name\"}");
        List<Data> searchResult = dataList.searchData(id);
        if (searchResult.isEmpty())
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\" : \"Data not found\"}");
        else return ResponseEntity.ok(searchResult);
    }

}
