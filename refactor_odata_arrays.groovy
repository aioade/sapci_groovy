import com.sap.gateway.ip.core.customdev.util.Message;
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def Message processData(Message message) {
    def body = message.getBody(java.lang.String);
    /*
    def properties = message.getProperties();
    value = properties.get("objectList");
    */
    def objectList = "JobRequisitionPosting,PicklistLabel"
    
    message.setBody(processPayload(body, objectList))
    
    return message;
}

def processPayload(String jsonPayload, String arrayNamesStr) {
    def arrayNames = arrayNamesStr.split(",").collect { it.trim() }
    def jsonSlurper = new JsonSlurper()
    def payload = jsonSlurper.parseText(jsonPayload)
    
    def result = processObject(payload, arrayNames)
    
    // Convert back to JSON string
    return JsonOutput.toJson(result)
}

def processObject(object, arrayNames) {
    if (object instanceof Map) {
        // Process each entry in the map
        object.each { key, value ->
            if (value instanceof Map) {
                object[key] = processObject(value, arrayNames)
            } else if (value instanceof List) {
                // Check if this is one of the arrays we need to transform
                def match = arrayNames.find { arrName -> 
                    key == arrName
                }
                
                if (match && value.size() > 0) {
                    object[key] = value[0]
                } else {
                    object[key] = processArray(value, arrayNames)
                }
            }
        }
    }
    return object
}

def processArray(array, arrayNames) {
    def result = []
    array.each { item ->
        if (item instanceof Map) {
            result.add(processObject(item, arrayNames))
        } else if (item instanceof List) {
            result.add(processArray(item, arrayNames))
        } else {
            result.add(item)
        }
    }
    return result
}