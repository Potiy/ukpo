package spring.rest;

import listservice.DataList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/rest/houses")
public class MyFirsRest {

    private DataList houseList = new DataList();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public  ResponseEntity<Object> loadPage(){
        return ResponseEntity.ok(houseList.getDataList());
    }

    @RequestMapping(value= "add/{id}/{name}/{address}/{phone}/{cost}", method = RequestMethod.POST)
    public ResponseEntity<Object> addData(@PathVariable("id") String id,
                                          @PathVariable("name") String name,
                                          @PathVariable("address") String address,
                                          @PathVariable("phone") String phone,
                                          @PathVariable("cost") String cost,
                                          @AuthenticationPrincipal Principal principal)
    {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\" : \"Unauthorized\"}");
        }


            if (id.equals("undefined") || name.equals("undefined") || address.equals("undefined") || phone.equals("undefined") || cost.equals("undefined"))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\" : \"Invalid data\"}");

        return ResponseEntity.status(HttpStatus.CREATED).body(houseList.addData(id, name, address, phone, cost));
    }

        @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
        public ResponseEntity<Object> delData(@PathVariable("id") String id, @AuthenticationPrincipal Principal principal)
        {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body ("{\"message\" : \"Invalid data\"}");
        }

        System.out.println(id);
        if (id.equals("undefined"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\" : \"Invalid id\"}");
        if (houseList.delData(id)) return ResponseEntity.ok("{\"message\" : \"Deleted\"}");
        else return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }
}

