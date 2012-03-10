#############################################
# JangoMail_API.rb
# 
# This example shows how to connect to the 
# JangoMail/JangoSMTP web service using SOAP
# in Ruby.
#############################################


# make sure to include savon for building soap requests
# see http://savonrb.com/ to download
require 'savon'

# first, build the soap client from the wsdl
client = Savon::Client.new "http://api.jangomail.com/api.asmx?wsdl"

# then just specify the xml for the request, inserting your own credentials
response = client.request "http://api.jangomail.com/Groups_GetList_String" do |soap|
  soap.xml = """<?xml version=\"1.0\" encoding=\"utf-8\"?>
    <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">
    <soap12:Body>
    <Groups_GetList_String xmlns=\"http://api.jangomail.com/\">
    <Username>Your JangoMail/JangoSMTP Username</Username>
    <Password>Your JangoMail/JangoSMTP Password</Password>
    <RowDelimiter> </RowDelimiter>
    <ColDelimiter> - </ColDelimiter>
    <TextQualifier></TextQualifier>
    </Groups_GetList_String></soap12:Body></soap12:Envelope>"""
end
	 
puts "Response:\n"
puts response