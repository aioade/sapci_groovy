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