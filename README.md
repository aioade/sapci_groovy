# sapci_groovy
A collection of Groovy Scripts for SAP Cloud Integration

## Logging message monitor attachment
This script allows you to catch the current message body in the node and save it as file attachment to the log.
This way you can access the payload of a specific flow step without the need to activate Trace.

It can also be extended for all other objects like header or properties, but in our daily work the payload is the most common object.

You can adjust the title you want to see, it's useful if you are logging multiple steps in a flow (like start and end payload).
Also the message format can be set so you will get a pretty print of your content.

`messageLog.addAttachmentAsString("SAP Response", body, "application/xml");`

First parameter here is the **title**, last one the file format (similar to Content-Type header values): **application/xml**.


## Add / Delete JSON Object
Sometimes you have the case that you have a big JSON message in the payload but only want to change one field.
In this case creating a message mapping with input / output structure can be a litte over-engineered :) Also changing it by string replace will not always do the job if you have content with same keys or values.

So here is a simple solution to parse the String body as JSON object and you have one example how to delete an object in the JSON payload or add a new one with an easy one-liner.


## Refactor OData Arrays
This is working general on all arrays inside a JSON payload, but I needed this solution especially regarding OData in SF.
There are cases like Picklist, where you have for example multiple labels depending on the languagues available. Making filtering in the mapping for one value can cause problems, that the other values of the array get shifted to the next object and the payload content gets invalid.
So this function is harmonizing the payload by refactoring and releasing arrays into a simple JSON object with the current first object and his values.
This way the mapping condition is always unique and small and especially big payloads are working correct.

As input besides the payload, you can give a list of comma-separated json array objects, where you want to use the refactoring.

## Calendar details
A small helper for message mappings, if you need to get more details for a date object. It offers the calculation of the actual calendar week or calendar quarter of a specific date. As input parameter you need to give the date for calculation (format YYYY-MM-dd) and the type of response, so 'week' or 'quarter'.