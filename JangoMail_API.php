<?php

// JangoMail_API.php
// This example shows how to use PHP to access the JangoMail or JangoSMTP API.
// In this script, we retrieve a list of email lists for a user.
// see www.jangosmtp.com/developers.asp for more examples

// set up a custom error handler, this is just for convenient debugging
set_error_handler("customError"); 

// create a SoapClient object for the jangomail aPI
$client = new SoapClient("http://api.jangomail.com/api.asmx?WSDL");

// set the parameter array for the
// Groups_GetList_String method
$param=array(
	'Username'=>'Your JangoMail Username',
	'Password'=>'Your JangoMail Password',
	'RowDelimiter'=>'<br/>',
	'ColDelimiter'=>' - ',
	'TextQualifier'=>''
); 

// using the JangoMail API
// call the Groups_GetList_String method
$result = $client->Groups_GetList_String($param);

// print the groups that were returned
print($result->Groups_GetList_StringResult);

// this is usefule if you're using a new method
//var_dump($result);

function customError($errno, $errstr)
{
	echo "<b>Error:</b> [$errno] $errstr<br />";
	//die();
} 

?>