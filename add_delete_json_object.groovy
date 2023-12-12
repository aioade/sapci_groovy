import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.*

def Message processData(Message message) {
    def json = new JsonSlurper().parseText(body)

    //get content
    def body = message.getBody(String.class);
    
    def newvalue = "some value for json"

    //remove a field from json
    def fieldToRemove = "key"
    json.remove(fieldToRemove)

    //add a JSON key/value to an object
    json.d[0] << [key: 'test/testfield=' + newvalue]
        
    message.setBody(JsonOutput.toJson(json));        
    

    return message;
}