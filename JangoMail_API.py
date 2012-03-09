###############################################################################################
# JangoMail_API.py
# An example from www.jangomail.com or www.jangosmtp.com
# This example shows how to connect to the JangoMail/JangoSMTP web service from a python script.
###############################################################################################

import sys
import httplib

# setup the sml template xml for a call to Groups_GetList_String
SM_TEMPLATE = """<?xml version="1.0" encoding="utf-8"?>
<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
<soap12:Body>
<Groups_GetList_String xmlns="http://api.jangomail.com/">
<Username>%s</Username>
<Password>%s</Password>
<RowDelimiter>%s</RowDelimiter>
<ColDelimiter>%s</ColDelimiter>
<TextQualifier>%s</TextQualifier>
</Groups_GetList_String>
</soap12:Body>
</soap12:Envelope>
"""

# fill in the parameters for our call (username, password, rowdelimiter, coldelimiter, textqualifier)
SoapMessage = SM_TEMPLATE%("Your JangoMail/JangoSMTP Username", "Your Password", "\n", " - ", "")

# examine the soap message that we have constructed
print SoapMessage

# insert the proper values into the header
webservice = httplib.HTTP("api.jangomail.com")
webservice.putrequest("POST", "/api.asmx")
webservice.putheader("Host", "api.jangomail.com")
webservice.putheader("User-Agent", "Python post")
webservice.putheader("Content-type", "application/soap+xml; charset=\"UTF-8\"")
webservice.putheader("Content-length", "%d" % len(SoapMessage))
webservice.putheader("SOAPAction", "http://api.jangomail.com/Groups_GetList_String")
webservice.endheaders()
webservice.send(SoapMessage)

# get the response and print it
statuscode, statusmessage, header = webservice.getreply()
print "Response: ", statuscode, statusmessage
print "headers: ", header
res = webservice.getfile().read()
print res